/*
 * Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of IFC.JAVA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package buildingsmart.io;

import buildingsmart.ifc.IfcProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class Serializer {

    private final Map<IfcEntity, Long> serializedEntitiesToIds;
    private final StringBuilder dataSection;
    private long idCounter = 0;

    public Serializer() {
        serializedEntitiesToIds = new HashMap<>();
        dataSection = new StringBuilder();
        idCounter = 0;
    }

    /**
     * @param entity The entity for which to return the array of attributes.
     * @param type   The Annotation indicating what type of attributes to
     *               return, either regular attributes ({@link Attribute}) or
     *               inverse attributes ({@link InverseAttribute}).
     * @return If {@code type} is {@code Attribute.class}, returns the
     * attributes that should be serialized in the representation of {@code
     * entity} in an IFC file; if {@code type} is {@code
     * InverseAttribute.class}, returns the attributes of {@code entity}
     * representing an inverse relationship.</p> In the first case the returned
     * array is ordered according to the order defined by {@code entity}'s
     * fields' {@link Order} annotation. If there are no attributes, the
     * returned array will have length == 0.
     * @throws IllegalArgumentException If {@code type} is not {@code
     *                                  Attribute.class} nor {@code
     *                                  InverseAttribute.class}.
     * @throws IllegalArgumentException If the given {@code entity} contains
     *                                  Fields that are annotated with {@link
     *                                  Attribute} but not with {@link Order}.
     */
    private static <T extends Annotation> Object[] getAttributes(
            IfcEntity entity, Class<T> type) {
        if (!(type.equals(Attribute.class) ||
                type.equals(InverseAttribute.class))) {
            throw new IllegalArgumentException(
                    "type must be either Attribute.class or InverseAttribute" +
                            ".class");
        }
        List<Field> fields = getAllFields(entity.getClass());
        fields.removeIf(field -> field.getAnnotation(type) == null);
        if (type.equals(Attribute.class)) {
            sortFields(fields);
        }
        Object[] attributes = new Object[fields.size()];
        for (int i = 0; i < attributes.length; i++) {
            Field field = fields.get(i);
            DerivedInSubclass derivedAnnotation =
                    field.getAnnotation(DerivedInSubclass.class);
            if (derivedAnnotation != null &&
                    entity.getClass().equals(derivedAnnotation.value())) {
                // field is a derived attribute in entity, so it will be
                // serialized as an asterisk
                attributes[i] = (IfcDefinedType) () -> "*";
                continue;
            }
            field.setAccessible(true);
            try {
                attributes[i] = field.get(entity);
            } catch (IllegalAccessException e) {
                // this should never happen, since field was set as accessible
                e.printStackTrace();
            }
        }
        return attributes;
    }

    /**
     * @param type The type for which to get all fields.
     * @return The unsorted fields of the given type and all its superclasses.
     * If there are none, the returned List will be empty.
     */
    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        } while (type != null);

        return fields;
    }

    /**
     * Sorts a List of Fields based on the value of the field's Order annotation
     * (ascending).
     *
     * @param unsortedFields The List of Field to sort. All Fields should be
     *                       annotated with {@link Order}.
     * @throws IllegalArgumentException If any of the Fields in unsortedFields
     *                                  are not annotated with {@link Order}.
     */
    private static void sortFields(List<Field> unsortedFields) {
        unsortedFields.sort((field1, field2) -> {
            Order order1 = field1.getAnnotation(Order.class);
            Order order2 = field2.getAnnotation(Order.class);
            if (order1 != null && order2 != null) {
                return order1.value() - order2.value();
            }
            throw new IllegalArgumentException(
                    "unsortedFields must contain only Fields annotated " +
                            "with Order");
        });
    }

    /**
     * Placeholder method, it will remain here until I figure out what to put in
     * the headers of an IFC file.
     *
     * @param dataSection The DATA section of the IFC file to generate.
     * @return The content of the IFC file, meaning headers + the provided DATA
     * section.
     */
    public static String addHeader(String dataSection) {
        String top = "ISO-10303-21;\n" + "HEADER;\n" +
                "FILE_DESCRIPTION(('ViewDefinition [CoordinationView]'),'2;" +
                "1');\n" +
                "FILE_NAME('freecad-cylinder.ifc','2020-04-14T22:16:25',(''," +
                "''),(''),'IfcOpenShell 0.5.0-dev','IfcOpenShell 0.5.0-dev'," +
                "'');\n" + "FILE_SCHEMA(('IFC2X3'));\n" + "ENDSEC;\n" +
                "DATA;\n";
        String bottom = "ENDSEC;\n" + "END-ISO-10303-21;\n";
        return top + dataSection + bottom;
    }

    /**
     * Writes the given String to the file located in the given filePath. If
     * some of the directories in the filePath do not exist, this method creates
     * them.
     *
     * @param fileContent the String to write in the file.
     * @param filePath    the path to the file in which to write.
     * @throws IllegalArgumentException if {@code filePath} is null or empty.
     * @throws IOException              If the file exists but is a directory
     *                                  rather than a regular file, does not
     *                                  exist but cannot be created, or cannot
     *                                  be opened for any other reason; if an
     *                                  I/O error occurs.
     */
    public static void writeToFile(String fileContent, String filePath)
            throws IOException {
        //TODO: test method
        String directoryPath = null;
        if (filePath != null && filePath.length() > 0) {
            int endIndex = filePath.lastIndexOf(File.separatorChar);
            if (endIndex != -1) {
                directoryPath = filePath.substring(0, endIndex);
            }
        }
        if (directoryPath == null) {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        //noinspection ResultOfMethodCallIgnored
        new File(directoryPath).mkdirs();

        File outputFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    /**
     * @param project the {@link IfcProject} to serialize.
     * @return A String containing the DATA section of an IFC file with the
     * representation of the given project. The tags indicating the beginning
     * and the end of the DATA section are not included.
     * @throws IllegalArgumentException If the tree having IfcProject as its
     *                                  root, where parent nodes are IfcEntity
     *                                  types and children are the Fields of the
     *                                  parent node, contains nodes whose Fields
     *                                  are annotated with {@link Attribute} but
     *                                  not with {@link Order}.
     */
    public String serialize(IfcProject project) {
        serialize((Object) project);
        String result = dataSection.toString();
        serializedEntitiesToIds.clear();
        dataSection.setLength(0);
        idCounter = 0;
        return result;
    }

    /**
     * @param obj The object to serialize in an IFC file.
     * @return The serialization of the object:
     * <ul>
     *     <li> if it's {@code null}, the String {@code "$"} will be
     *     returned;</li>
     *     <li>if it is an instance of IfcDefinedType, returns the
     *     serialization of
     *     the Type according to the STEP file specification will be returned
     *     ;</li>
     *     <li>if it is a List or a Set, each contained object will be
     *     serialized, its serialization put between parenthesis, and a
     *     String containing the parentheses and everything between them will
     *     be returned;</li>
     *     <li>if it is an instance of IfcEntity:</li>
     *          <li>if the entity was already serialized, a String containing
     *          a hash mark and the Id of the entity in the IFC file will be
     *          returnedi;</li>
     *          <li>if the entity wasn't already serialized, its attributes
     *          will be serialized, then the attributes which represent an
     *          inverse relationship will also be serialized (for example,
     *          these will be isDecomposedBy in the case of an IfcProject). A
     *          String containing the representation of the entity in the IFC
     *          file will be returned;</li>
     * </ul>
     * @throws IllegalArgumentException If the given {@code obj} is an IfcEntity
     *                                  and contains Fields that are annotated
     *                                  with {@link Attribute} but not with
     *                                  {@link Order}.
     */
    private String serialize(Object obj) {
        if (obj == null) {
            return "$";
        }
        if (obj instanceof IfcDefinedType) {
            return ((IfcDefinedType) obj).serialize();
        }
        if (obj instanceof Collection) {
            Collection coll = (Collection) obj;
            StringBuilder serializedColl = new StringBuilder("(");
            for (Object element : coll) {
                serializedColl.append(serialize(element)).append(",");
            }
            serializedColl.deleteCharAt(serializedColl.length() - 1);
            // removing the last comma
            serializedColl.append(")");
            return serializedColl.toString();
        }
        // if obj is neither an IfcDefinedType nor a Collection (List or
        // Set), then it must be an IfcEntity
        Long entityId = serializedEntitiesToIds.get(obj);
        if (entityId != null) {
            return "#" + entityId;
        }
        // obj hasn't been serialized yet, so we'll do it now
        IfcEntity entity = (IfcEntity) obj;
        StringBuilder serializedEntity = new StringBuilder(
                entity.getClass().getSimpleName().toUpperCase() + "(");
        Object[] attributes = getAttributes(entity, Attribute.class);
        for (Object attr : attributes) {
            serializedEntity.append(serialize(attr)).append(",");
        }
        serializedEntity.deleteCharAt(serializedEntity.length() - 1);
        // removing the last comma
        serializedEntity.append(");\n");

        entityId = serializedEntitiesToIds.get(obj);
        if (entityId != null) {
            return "#" + entityId;
            // the current obj has already been serialized while we were
            // serializing our attributes, because one of our attributes
            // contained a reference to obj in its inverse relationships
        }
        String serializedEntityString =
                "#" + ++idCounter + "=" + serializedEntity.toString();
        dataSection.append(serializedEntityString);
        serializedEntitiesToIds.put(entity, idCounter);

        Object[] invAttributes = getAttributes(entity, InverseAttribute.class);
        for (Object attr : invAttributes) {
            serialize(attr);
            // the return value of serialize() is ignored, because the only
            // thing that matters is that the entities in invAttributes are
            // serialized in dataSection. For example, if entity is IfcProject
            // we want the entities referenced in isDecomposedBy to be
            // serialized in dataSection.
        }

        return "#" + serializedEntitiesToIds.get(obj);
    }
}
