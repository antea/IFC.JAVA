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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode
public class Serializer {

    private final Map<IfcEntity, Long> serializedEntitiesToIds;
    private Writer fileWriter;
    private long idCounter;

    public Serializer() {
        serializedEntitiesToIds = new HashMap<>();
        idCounter = 0;
    }

    /**
     * @param entity The entity for which to return its inverse relationships.
     * @return A Stream containing the values of the fields of {@code entity}
     * representing an inverse relationship. If there are no inverse
     * relationships, the returned Stream will be empty.
     *
     * @throws NullPointerException If {@code entity} is null.
     * @throws SecurityException    If a security manager, <i>s</i>, is present
     *                              and any of the following conditions is met:
     *                              <ul>
     *                                <li>
     *                                  invocation of
     *                                  {@link SecurityManager
     *                                  #checkPermission(Permission)}
     *                                  method with {@code
     *                                  RuntimePermission
     *                                  ("accessDeclaredMembers")} denies
     *                                  access to the declared fields
     *                                  within{@code entity.getClass()}
     *                                </li>
     *                                <li>
     *                                  invocation of
     *                                  {@link SecurityManager
     *                                  #checkPackageAccess(String)} denies
     *                                  access to the package of
     *                                  {@code entity.getClass()}
     *                                </li>
     *                                <li>
     *                                  access to private Fields of
     *                                  {@code entity} by calling
     *                                  {@link Field#setAccessible(boolean)} is
     *                                  not permitted based on the security
     *                                  policy currently in effect.
     *                                </li>
     *                              </ul>
     */
    private static Stream<Object> getInvRels(@NonNull IfcEntity entity) {
        return getAllFields(entity.getClass()).stream().filter(field -> field
                .isAnnotationPresent(InverseRelationship.class)).map(field -> {
            field.setAccessible(true);
            Object invRel = null;
            try {
                invRel = field.get(entity);
            } catch (IllegalAccessException e) {
                // this should never happen, since field was set as accessible
                e.printStackTrace();
            }
            return invRel;
        });
    }

    /**
     * @param type The type for which to get all fields.
     * @return The unsorted fields of the given type and all its superclasses.
     * If there are none, the returned List will be empty.
     *
     * @throws NullPointerException If type is null.
     * @throws SecurityException    If a security manager, <i>s</i>, is present
     *                              and any of the following conditions is met:
     *                              <ul>
     *                              <li> invocation of
     *                              {@link SecurityManager#checkPermission
     *                              s.checkPermission} method with
     *                              {@code RuntimePermission
     *                              ("accessDeclaredMembers")}
     *                              denies access to the declared fields within
     *                              {@code type}
     *                              <li> invocation of
     *                              {@link SecurityManager#checkPackageAccess
     *                              s.checkPackageAccess()} denies access to the
     *                              package of {@code type}
     *                              </ul>
     */
    private static List<Field> getAllFields(@NonNull Class<?> type) {
        List<Field> fields = new LinkedList<>();
        do {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        } while (type != null);

        return fields;
    }

    /**
     * @param entity An IfcEntity object.
     * @return The serialization of the entity in an IFC STEP file. This method
     * will also call {@link Serializer#serialize(Object)} for each attribute of
     * {@code entity}.
     *
     * @throws NullPointerException If {@code entity} is null.
     * @throws IOException          If an I/O error occurs.
     * @throws SecurityException    If a security manager, <i>s</i>, is present
     *                              and any of the following conditions is met:
     *                              <ul>
     *                                <li>
     *                                  invocation of
     *                                  {@link SecurityManager
     *                                  #checkPermission(Permission)}
     *                                  method with {@code
     *                                  RuntimePermission
     *                                  ("accessDeclaredMembers")} denies
     *                                  access to the declared fields
     *                                  within{@code entity.getClass()}
     *                                </li>
     *                                <li>
     *                                  invocation of
     *                                  {@link SecurityManager
     *                                  #checkPackageAccess(String)} denies
     *                                  access to the package of
     *                                  {@code entity.getClass()}
     *                                </li>
     *                                <li>
     *                                  access to private Fields of
     *                                  {@code entity} by calling
     *                                  {@link Field#setAccessible(boolean)} is
     *                                  not permitted based on the security
     *                                  policy currently in effect.
     *                                </li>
     *                              </ul>
     */
    @SuppressWarnings("JavaDoc")
    private String serializeEntity(@NonNull IfcEntity entity) {
        DerivedAttributes derivedAttributes =
                entity.getClass().getAnnotation(DerivedAttributes.class);
        Set<String> derivedAttributesNames =
                derivedAttributes == null ? new HashSet<>(0) :
                        new HashSet<>(Arrays.asList(derivedAttributes.value()));
        return getAllFields(entity.getClass()).stream()
                .filter(field -> field.isAnnotationPresent(Attribute.class))
                .sorted((field1, field2) -> {
                    int order1 = field1.getAnnotation(Attribute.class).value();
                    int order2 = field2.getAnnotation(Attribute.class).value();
                    return order1 - order2;
                }).map(field -> {
                    if (derivedAttributesNames.contains(field.getName())) {
                        return "*";
                    }
                    field.setAccessible(true);
                    try {
                        Object attribute = field.get(entity);
                        if (field.getType().isInterface() &&
                                (attribute instanceof IfcDefinedType ||
                                        attribute instanceof Enum)) {
                            return attribute.getClass().getSimpleName()
                                    .toUpperCase() + "(" +
                                    serialize(attribute) + ")";
                        }
                        return serialize(attribute);
                    } catch (IllegalAccessException e) {
                        // this should never happen, since field was set as
                        // accessible
                        e.printStackTrace();
                    }
                    return "";
                }).collect(Collectors.joining(",",
                                              entity.getClass().getSimpleName()
                                                      .toUpperCase() + "(",
                                              ");\n"));
    }

    /**
     * Creates a File in the given filePath. If some of the directories in the
     * filePath do not exist, this method creates them.
     *
     * @param filePath The path to the file to create, or to an already existing
     *                 file.
     * @throws NullPointerException     If {@code filePath} is null.
     * @throws IllegalArgumentException If {@code filePath} is empty.
     * @throws SecurityException        If a security manager exists and its
     *                                  <code>{@link java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *                                  method does not permit verification of
     *                                  the existence of the named directory and
     *                                  all necessary parent directories; or if
     *                                  the
     *                                  <code>{@link java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *                                  method does not permit the named
     *                                  directory and all necessary parent
     *                                  directories to be created
     */
    private static File createFile(@NonNull String filePath) {
        String directoryPath = null;
        if (filePath.length() > 0) {
            int endIndex = filePath.lastIndexOf(File.separatorChar);
            if (endIndex != -1) {
                directoryPath = filePath.substring(0, endIndex);
            }
        } else {
            throw new IllegalArgumentException("filePath is empty");
        }
        if (directoryPath != null) {
            //noinspection ResultOfMethodCallIgnored
            new File(directoryPath).mkdirs();
        }

        return new File(filePath);
    }

    /**
     * Creates an IFC STEP file in the given filePath. If some of the
     * directories in the filePath do not exist, this method creates them. Note
     * that if this operation fails it may have succeeded in creating the file
     * and some of the necessary parent directories, and in writing some of the
     * content of the file.
     *
     * @param header   The {@link Header} of the IFC file to create. Even if it
     *                 has already been set, its fileName will be set to the
     *                 file name provided in filePath.
     * @param project  The {@link IfcProject} to serialize.
     * @param filePath The path to the file to create, or to an already existing
     *                 file.
     * @throws NullPointerException     If {@code header} is null; if {@code
     *                                  filePath} is null.
     * @throws IllegalArgumentException If {@code filePath} is empty.
     * @throws IOException              If the file exists but is a directory
     *                                  rather than a regular file, does not
     *                                  exist but cannot be created, or cannot
     *                                  be opened for any other reason; if an
     *                                  I/O error occurs during serialization of
     *                                  {@code project}.
     * @throws SecurityException        If a security manager exists and its
     *                                  <code>{@link java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *                                  method does not permit verification of
     *                                  the existence of the file and
     *                                  directories named in filePath, and all
     *                                  necessary parent directories; or if the
     *                                  <code>{@link java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *                                  method does not permit the named file,
     *                                  directories and all necessary parent
     *                                  directories to be created or written
     *                                  to.
     * @throws SecurityException        Let {@code obj} be any node of the tree
     *                                  having {@code project} as its root,
     *                                  where parent nodes are IfcEntity types
     *                                  and children are the {@link Attribute}s
     *                                  and {@link InverseRelationship}s of the
     *                                  parent node. This exception is thrown if
     *                                  a security manager,
     *                                  <i>s</i>, is present and any of the
     *                                  following conditions is met:
     *                                  <ul>
     *                                    <li>
     *                                      invocation of
     *                                      {@link SecurityManager
     *                                      #checkPermission(Permission)}
     *                                      method with {@code
     *                                      RuntimePermission
     *                                      ("accessDeclaredMembers")} denies
     *                                      access to the declared fields
     *                                      within{@code obj.getClass()}
     *                                    </li>
     *                                    <li>
     *                                      invocation of
     *                                      {@link SecurityManager
     *                                      #checkPackageAccess(String)} denies
     *                                      access to the package of
     *                                      {@code obj.getClass()}
     *                                    </li>
     *                                    <li>
     *                                       access to private Fields of
     *                                       {@code obj} by calling
     *                                      {@link Field#setAccessible(boolean)}
     *                                       is not permitted based on the
     *                                       security policy currently in
     *                                       effect.
     *                                    </li>
     *                                  </ul>
     */
    public void serialize(@NonNull Header header,
                          IfcProject project,
                          @NonNull String filePath) throws IOException {
        File output = createFile(filePath);
        header.setFileName(output.getName());

        fileWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        output), StandardCharsets.US_ASCII));

        fileWriter.write("ISO-10303-21;\n" + header.serialize() + "DATA;\n");
        serialize(project);
        fileWriter.write("ENDSEC;\n" + "END-ISO-10303-21;\n");
        fileWriter.close();
        serializedEntitiesToIds.clear();
        idCounter = 0;
    }

    /**
     * @param obj The object to serialize in an IFC file.
     * @return The serialization of the object:
     * <ul>
     *     <li> if it's {@code null}, the String {@code "$"} will be
     *     returned;</li>
     *     <li>if it is an instance of IfcDefinedType, the serialization of
     *     the Type according to the STEP file specification will be
     *     returned;</li>
     *     <li>if it is a List or a Set, each contained object will be
     *     serialized, its serialization put between parenthesis, and a
     *     String containing the parentheses and everything between them
     *     will
     *     be returned;</li>
     *     <li>if it is an instance of IfcEntity:</li>
     *          <li>if the entity was already serialized, a String
     *          containing
     *          a hash mark and the Id of the entity in the IFC file will be
     *          returnedi;</li>
     *          <li>if the entity wasn't already serialized, its attributes
     *          will be serialized, then the attributes which represent an
     *          inverse relationship will also be serialized (for example,
     *          these will be isDecomposedBy in the case of an
     *          IfcProject). A
     *          String containing the representation of the entity in the
     *          IFC
     *          file will be returned;</li>
     * </ul>
     *
     * @throws IOException       If an I/O error occurs.
     * @throws SecurityException If obj is an instance of a class that extends
     *                           IfcEntity, a security manager,
     *                           <i>s</i>, is present and any of the
     *                           following conditions is met:
     *                           <ul>
     *                             <li>
     *                               invocation of
     *                               {@link SecurityManager
     *                               #checkPermission(Permission)}
     *                               method with {@code
     *                               RuntimePermission
     *                               ("accessDeclaredMembers")} denies
     *                               access to the declared fields
     *                               within{@code obj.getClass()}
     *                             </li>
     *                             <li>
     *                               invocation of
     *                               {@link SecurityManager
     *                               #checkPackageAccess(String)} denies
     *                               access to the package of
     *                               {@code obj.getClass()}
     *                             </li>
     *                             <li>
     *                               access to private Fields of {@code obj} by
     *                               calling {@link Field#setAccessible
     *                               (boolean)} is not permitted based on the
     *                               security policy currently in effect.
     *                             </li>
     *                           </ul>
     */
    @SuppressWarnings("JavaDoc")
    @SneakyThrows
    private String serialize(Object obj) {
        if (obj == null) {
            return "$";
        }
        if (obj instanceof IfcDefinedType) {
            return ((IfcDefinedType) obj).serialize();
        }
        if (obj instanceof Collection) {
            @SuppressWarnings({"unchecked", "rawtypes"})
            Stream<String> stream =
                    ((Collection) obj).stream().map(this::serialize);
            return stream.collect(Collectors.joining(",", "(", ")"));
        }
        IfcEntity entity = (IfcEntity) obj;
        // if obj is neither an IfcDefinedType nor a Collection (List or
        // Set), then it must be an IfcEntity
        Long entityId = serializedEntitiesToIds.get(entity);
        if (entityId != null) {
            return "#" + entityId;
        }
        // entity hasn't been serialized yet, so we'll do it now
        String serializedEntity = serializeEntity(entity);
        entityId = serializedEntitiesToIds.get(entity);
        if (entityId != null) {
            return "#" + entityId;
            // the current entity has already been serialized while we were
            // serializing our attributes, because one of our attributes
            // contained a reference to obj in its inverse relationships
        }
        String serializedEntityString =
                "#" + ++idCounter + "=" + serializedEntity;
        fileWriter.write(serializedEntityString);
        serializedEntitiesToIds.put(entity, idCounter);

        getInvRels(entity).forEach(this::serialize);

        return "#" + serializedEntitiesToIds.get(entity);
    }
}
