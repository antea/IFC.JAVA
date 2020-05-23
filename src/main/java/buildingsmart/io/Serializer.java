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

import buildingsmart.ifc.IfcDefinedType;
import buildingsmart.ifc.IfcEntity;
import buildingsmart.ifc.IfcProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Serializer {

    private static final Map<IfcEntity, Long> serializedEntitiesIds =
            new HashMap<>();
    private static final StringBuilder dataSection = new StringBuilder();
    private static long idCounter = 0;

    /**
     * @param project the {@link IfcProject} to serialize.
     * @return A String containing the DATA section of an IFC file with the
     * representation of the given project. The tags indicating the beginning
     * and the end of the DATA section are not included.
     */
    public static String serialize(IfcProject project) {
        serialize((Object) project);
        String result = dataSection.toString();
        serializedEntitiesIds.clear();
        dataSection.setLength(0);
        idCounter = 0;
        return result;
    }

    /**
     * @param obj The object to serialize in an IFC file.
     * @return The serialization of the object:
     * <ul>
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
     */
    private static String serialize(Object obj) {
        if (obj instanceof IfcDefinedType) {
            return ((IfcDefinedType) obj).serialize();
        } else if (obj instanceof Collection) {
            Collection coll = (Collection) obj;
            StringBuilder serializedColl = new StringBuilder("(");
            for (Object element : coll) {
                serializedColl.append(serialize(element)).append(",");
            }
            serializedColl.deleteCharAt(serializedColl.length());
            // removing the last comma
            serializedColl.append(")");
            return serializedColl.toString();
        } else if (serializedEntitiesIds.containsKey(obj)) {
            // if obj is neither an IfcDefinedType nor a Collection (List or
            // Set), then it must be an IfcEntity
            return "#" + serializedEntitiesIds.get(obj);
        }
        // obj hasn't been serialized yet, so we'll do it now
        IfcEntity entity = (IfcEntity) obj;
        StringBuilder serializedEntity = new StringBuilder(
                entity.getClass().getName().toUpperCase() + "(");
        Object[] attributes = entity.getAttributes();
        for (Object attr : attributes) {
            serializedEntity.append(serialize(attr)).append(",");
        }
        serializedEntity.deleteCharAt(serializedEntity.length());
        // removing the last comma
        serializedEntity.append(");\n");
        String serializedEntityString =
                "#" + ++idCounter + "=" + serializedEntity.toString();
        dataSection.append(serializedEntityString);
        serializedEntitiesIds.put(entity, idCounter);

        Object[] invAttributes = entity.getInverseAttributes();
        for (Object attr : invAttributes) {
            serialize(attr);
            // the result of the serialization is ignored, because the only
            // thing that matters is that the entities in invAttributes are
            // serialized in dataSection. For example, if entity is IfcProject
            // we want the entities referenced in isDecomposedBy to be
            // serialized in dataSection.
        }

        return "#" + serializedEntitiesIds.get(obj);
    }

    /**
     * Placeholder method, it will remain here until I figure out what to put in
     * the headers of an IFC file.
     *
     * @param dataSection The DATA section of the IFC file to generate.
     * @return The content of the IFC file, meaning headers + the provided DATA
     * section.
     */
    public static String generateIfcFileContent(String dataSection) {
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
}
