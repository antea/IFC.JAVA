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

import buildingsmart.ifc.IfcProduct;
import buildingsmart.ifc.IfcProject;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class Serializer {

    private final Set<Future<?>> tasksToComplete;
    private final Map<IfcEntity, Long> serializedEntitiesToIds;
    private ExecutorService exec;
    private Writer fileWriter;
    private long idCounter;

    public Serializer() {
        tasksToComplete = ConcurrentHashMap.newKeySet();
        serializedEntitiesToIds = new ConcurrentHashMap<>();
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
     * @throws NullPointerException     If {@code entity} or {@code type} is
     *                                  null.
     * @throws SecurityException        If a security manager is present and
     *                                  access to private Fields of {@code
     *                                  entity} by calling { @link
     *                                  Field#setAccessible(boolean)}
     *                                  is not permitted based on the security
     *                                  policy currently in effect.
     * @throws SecurityException        If a security manager, <i>s</i>, is
     *                                  present and any of the following
     *                                  conditions is met:
     *                                  <ul>
     *                                    <li>
     *                                      the class loader of {@code
     *                                      Serializer} is not the same as
     *                                      the class loader of {@code entity
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPermission(Permission)}
     *                                      method with {@code
     *                                      RuntimePermission
     *                                      ("accessDeclaredMembers")} denies
     *                                      access to the declared fields
     *                                      within{@code entity.getClass()}
     *                                    </li>
     *                                    <li>
     *                                      the class loader of
     *                                      {@link Serializer} is not the
     *                                      same as or an ancestor of the
     *                                      class loader for {@code entity
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPackageAccess(String) denies
     *                                      access to the package of
     *                                      {@code entity.getClass()}
     *                                    </li>
     *                                  </ul>
     */
    private static <T extends Annotation> Object[] getAttributes(
            @NotNull IfcEntity entity, Class<T> type) {
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
     * @throws SecurityException If a security manager, <i>s</i>, is present and
     *                           any of the following conditions is met:
     *                           <ul>
     *                           <li> the class loader of {@code Serializer}
     *                           is not the
     *                           same as the
     *                           class loader of {@code type} and invocation of
     *                           {@link SecurityManager#checkPermission
     *                           s.checkPermission} method with
     *                           {@code RuntimePermission
     *                           ("accessDeclaredMembers")}
     *                           denies access to the declared fields within
     *                           {@code type}
     *                           <li> the class loader of {@link Serializer}
     *                           is not the
     *                           same as or an
     *                           ancestor of the class loader for {@code type
     *                           } and
     *                           invocation of
     *                           {@link SecurityManager#checkPackageAccess
     *                           s.checkPackageAccess()} denies access to the
     *                           package
     *                           of {@code type}
     *                           </ul>
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
     * Creates a File in the given filePath. If some of the directories in the
     * filePath do not exist, this method creates them.
     *
     * @param filePath The path to the file to create, or to an already existing
     *                 file.
     * @throws IllegalArgumentException If {@code filePath} is null or empty.
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
    private static File createFile(@NotNull String filePath) {
        String directoryPath = null;
        if (filePath != null && filePath.length() > 0) {
            int endIndex = filePath.lastIndexOf(File.separatorChar);
            if (endIndex != -1) {
                directoryPath = filePath.substring(0, endIndex);
            }
        } else {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        if (directoryPath != null) {
            //noinspection ResultOfMethodCallIgnored
            new File(directoryPath).mkdirs();
        }

        return new File(filePath);
    }

    /**
     * Serializes the given Collection. If coll contains only objects of type
     * IfcProduct (for example, if the given coll is
     * IfcRelContainedInSpatialStructure.relatedElements),
     * each of them will be serialized in its own thread.
     *
     * @param coll The Collection to serialize.
     * @param <T>  The type of the elements contained in the Collection.
     * @return The serialization of the given Collection in an IFC file.
     * @throws IOException          If an I/O error occurs.
     * @throws ExecutionException   If an exception was thrown in one of the
     *                              threads serializing IfcEntities contained in
     *                              the project.
     * @throws InterruptedException If one of the threads serializing
     *                              IfcEntities was interrupted while waiting.
     */
    private <T> String serializeCollection(Collection<T> coll)
            throws ExecutionException, InterruptedException, IOException {
        boolean collectionOfIfcProducts = true;
        for (T element : coll) {
            if (!(element instanceof IfcProduct)) {
                collectionOfIfcProducts = false;
                break;
            }
        }
        List<String> serializations = new ArrayList<>(coll.size());
        if (collectionOfIfcProducts) {
            List<Future<String>> productSerializers =
                    new ArrayList<>(coll.size());
            for (T ifcProduct : coll) {
                Callable<String> worker = () -> serialize(ifcProduct);
                productSerializers.add(exec.submit(worker));
            }
            for (Future<String> result : productSerializers) {
                serializations.add(result.get());
            }
        } else {
            for (T element : coll) {
                serializations.add(serialize(element));
            }
        }
        StringBuilder serializedColl = new StringBuilder("(");
        for (String serialization : serializations) {
            serializedColl.append(serialization).append(",");
        }
        serializedColl.deleteCharAt(serializedColl.length() - 1);
        // removing the last comma
        serializedColl.append(")");
        return serializedColl.toString();
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
     * @throws IllegalArgumentException If the tree having the IfcProject as its
     *                                  root, where parent nodes are IfcEntity
     *                                  types and children are the {@link
     *                                  Attribute}s and
     *                                  {@link InverseAttribute}s
     *                                  of the parent node, contains nodes whose
     *                                  Fields are annotated with {@link
     *                                  Attribute} but not with {@link Order}.
     * @throws IllegalArgumentException If {@code header} is null; if {@code
     *                                  filePath} is null or empty.
     * @throws IOException              If the file exists but is a directory
     *                                  rather than a regular file, does not
     *                                  exist but cannot be created, or cannot
     *                                  be opened for any other reason; if an
     *                                  I/O error occurs.
     * @throws ExecutionException       If an exception was thrown in one of the
     *                                  threads serializing IfcEntities
     *                                  contained in the project.
     * @throws InterruptedException     If one of the threads serializing
     *                                  IfcEntities was interrupted while
     *                                  waiting.
     * @throws SecurityException        Let {@code obj} be any node of the tree
     *                                  having the IfcProject as its root, where
     *                                  parent nodes are IfcEntity types and
     *                                  children are the {@link Attribute}s and
     *                                  {@link InverseAttribute}s of the parent
     *                                  node. This exception is thrown if a
     *                                  security manager is present and access
     *                                  to private Fields of {@code obj} by
     *                                  calling { @link Field#setAccessible
     *                                  (boolean)} is not permitted based on the
     *                                  security policy currently in effect.
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
     *                                  having the IfcProject as its root, where
     *                                  parent nodes are IfcEntity types and
     *                                  children are the {@link Attribute}s and
     *                                  {@link InverseAttribute}s of the parent
     *                                  node. This exception is thrown if a
     *                                  security manager,
     *                                  <i>s</i>, is present and any of the
     *                                  following conditions is met:
     *                                  <ul>
     *                                    <li>
     *                                      the class loader of {@code
     *                                      Serializer} is not the same as
     *                                      the class loader of {@code obj
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPermission(Permission)}
     *                                      method with {@code
     *                                      RuntimePermission
     *                                      ("accessDeclaredMembers")} denies
     *                                      access to the declared fields
     *                                      within{@code obj.getClass()}
     *                                    </li>
     *                                    <li>
     *                                      the class loader of
     *                                      {@link Serializer} is not the
     *                                      same as or an ancestor of the
     *                                      class loader for {@code obj
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPackageAccess(String) denies
     *                                      access to the package of
     *                                      {@code obj.getClass()}
     *                                    </li>
     *                                  </ul>
     */
    public void serialize(@NotNull Header header, IfcProject project,
                          @NotNull String filePath)
            throws IOException, ExecutionException, InterruptedException {
        if (header == null) {
            throw new IllegalArgumentException("header cannot be null");
        }
        exec = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        File output = createFile(filePath);
        header.setFileName(output.getName());
        fileWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(output),
                        StandardCharsets.UTF_8));
        fileWriter.write("ISO-10303-21;\n" + header.serialize() + "DATA;\n");

        serialize(project);
        boolean done = false;
        while (!done) {
            int initialSize = tasksToComplete.size();
            for (Future<?> taskResult : tasksToComplete) {
                taskResult.get();
                //will block the current thread until all tasks are completed
            }
            int finalSize = tasksToComplete.size();
            if (initialSize == finalSize) {
                done = true;
                // if the size of tasksToComplete before iterating on the
                // Set is the same as after having iterated on the Set,
                // that means no elements were added to the Set while
                // we were iterating on the older Iterator; because we're out
                // of the above for cycle, then all tasksToComplete must have
                // completed.
            }
        }
        fileWriter.write("ENDSEC;\n" + "END-ISO-10303-21;\n");
        fileWriter.close();
        serializedEntitiesToIds.clear();
        idCounter = 0;
        tasksToComplete.clear();
        exec.shutdown();
        try {
            if (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                exec.shutdownNow();
            }
        } catch (InterruptedException e) {
            exec.shutdownNow();
        }
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
     *     will be returned;</li>
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
     * @throws IllegalArgumentException If the given {@code obj} is an IfcEntity
     *                                  and contains Fields that are annotated
     *                                  with {@link Attribute} but not with
     *                                  {@link Order}.
     * @throws IOException              If an I/O error occurs.
     * @throws ExecutionException       If an exception was thrown in one of the
     *                                  threads serializing IfcEntities
     *                                  contained in the project.
     * @throws InterruptedException     If one of the threads serializing
     *                                  IfcEntities was interrupted while
     *                                  waiting.
     * @throws SecurityException        If obj is an instance of IfcEntity, a
     *                                  security manager is present and access
     *                                  to private Fields of {@code obj} by
     *                                  calling { @link Field#setAccessible
     *                                  (boolean)} is not permitted based on the
     *                                  security policy currently in effect.
     * @throws SecurityException        If obj is an instance of a class that
     *                                  extends IfcEntity, a security manager,
     *                                  <i>s</i>, is present and any of the
     *                                  following conditions is met:
     *                                  <ul>
     *                                    <li>
     *                                      the class loader of {@code
     *                                      Serializer} is not the same as
     *                                      the class loader of {@code obj
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPermission(Permission)}
     *                                      method with {@code
     *                                      RuntimePermission
     *                                      ("accessDeclaredMembers")} denies
     *                                      access to the declared fields
     *                                      within{@code obj.getClass()}
     *                                    </li>
     *                                    <li>
     *                                      the class loader of
     *                                      {@link Serializer} is not the
     *                                      same as or an ancestor of the
     *                                      class loader for {@code obj
     *                                      .getClass()} and invocation of
     *                                      {@link SecurityManager
     *                                      #checkPackageAccess(String) denies
     *                                      access to the package of
     *                                      {@code obj.getClass()}
     *                                    </li>
     *                                  </ul>
     */
    private String serialize(Object obj)
            throws IOException, ExecutionException, InterruptedException {
        if (obj == null) {
            return "$";
        }
        if (obj instanceof IfcDefinedType) {
            return ((IfcDefinedType) obj).serialize();
        }
        if (obj instanceof Collection) {
            return serializeCollection((Collection) obj);
        }
        IfcEntity entity = (IfcEntity) obj;
        // if obj is neither an IfcDefinedType nor a Collection (List or
        // Set), then it must be an IfcEntity
        Long entityId = serializedEntitiesToIds.get(entity);
        if (entityId != null) {
            return "#" + entityId;
        }
        // obj hasn't been serialized yet, so we'll do it now
        StringBuilder serializedEntity = new StringBuilder(
                entity.getClass().getSimpleName().toUpperCase() + "(");
        Object[] attributes = getAttributes(entity, Attribute.class);
        for (Object attr : attributes) {
            serializedEntity.append(serialize(attr)).append(",");
        }
        serializedEntity.deleteCharAt(serializedEntity.length() - 1);
        // removing the last comma
        serializedEntity.append(");\n");
        boolean wrote = writeToFile(entity, serializedEntity.toString());

        if (wrote) {
            Object[] invAttributes =
                    getAttributes(entity, InverseAttribute.class);
            for (Object attr : invAttributes) {
                Runnable worker = () -> {
                    try {
                        serialize(attr);
                    } catch (IOException | ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                tasksToComplete.add(exec.submit(worker));
                // the return value of serialize() is ignored, because the only
                // thing that matters is that the entities in invAttributes are
                // serialized in the output file. For example, if entity is
                // IfcProject we want the entities referenced in
                // isDecomposedBy to be serialized in the file.
            }
        }

        return "#" + serializedEntitiesToIds.get(entity);
        // because we call exec.submit(a new Runnable) before completing the
        // current Runnable, we know for sure that if each Future in
        // tasksToComplete has completed, then no other Runnables are running
        // or waiting to be run
    }

    /**
     * @param entity           The entity to serialize in the output file.
     * @param serializedEntity The serialization of the given {@code entity} in
     *                         the form "IFCENTITY(#1,#2,'text',#4);\n", that is
     *                         without the id of the entity in the beginning of
     *                         the String.
     * @return {@code true} If the serialization of the given entity was
     * successfully written to file, {@code false} if the serialization was not
     * written, because another Thread managed to serialize the same entity
     * before the current Thread.
     * @throws IOException If an I/O error occurs.
     */
    private synchronized boolean writeToFile(IfcEntity entity,
                                             String serializedEntity)
            throws IOException {
        Long entityId = serializedEntitiesToIds.get(entity);
        if (entityId != null) {
            return false;
        }
        String serializedEntityWithId =
                "#" + ++idCounter + "=" + serializedEntity;
        fileWriter.write(serializedEntityWithId);
        serializedEntitiesToIds.put(entity, idCounter);
        return true;
    }
}
