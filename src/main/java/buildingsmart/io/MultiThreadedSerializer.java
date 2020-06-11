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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

class MultiThreadedSerializer extends Serializer {

    private Set<Future<?>> tasksToComplete;
    private ExecutorService exec;

    public MultiThreadedSerializer() {
        idCounter = 0;
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
     * @throws IllegalArgumentException If {@code header} is null, if {@code
     *                                  project} is null, if {@code filePath} is
     *                                  null or empty.
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
     *                                  <code>{@link SecurityManager#checkRead(String)}</code>
     *                                  method does not permit verification of
     *                                  the existence of the file and
     *                                  directories named in filePath, and all
     *                                  necessary parent directories; or if the
     *                                  <code>{@link SecurityManager#checkWrite(String)}</code>
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
     *                                      {@link MultiThreadedSerializer}
     *                                      is not the
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
    @Override
    public void serialize(@NotNull Header header, @NotNull IfcProject project,
                          @NotNull String filePath)
            throws IOException, ExecutionException, InterruptedException {
        if (header == null) {
            throw new IllegalArgumentException("header cannot be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
        }
        File output = createFile(filePath);
        serializedEntitiesToIds =
                serializedEntitiesToIds == null ? new ConcurrentHashMap<>() :
                        serializedEntitiesToIds;
        tasksToComplete =
                tasksToComplete == null ? ConcurrentHashMap.newKeySet() :
                        tasksToComplete;
        header.setFileName(output.getName());
        fileWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(output),
                        StandardCharsets.UTF_8));
        fileWriter.write("ISO-10303-21;\n" + header.serialize() + "DATA;\n");
        exec = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        serialize(project);
        boolean done = false;
        while (!done) {
            int initialSize = tasksToComplete.size();
            for (Future<?> taskResult : tasksToComplete) {
                taskResult.get();
            }
            int finalSize = tasksToComplete.size();
            if (initialSize == finalSize) {
                done = true;
                /*
                 If the size of tasksToComplete before iterating on the
                 Set is the same as after having iterated on the Set,
                 that means no elements were added to the Set while
                 we were iterating on the older Iterator; because we're out
                 of the above for cycle, then all tasksToComplete must have
                 completed. Since the only point at which we call exec.submit
                 (Runnable) is before exiting serialize(Object), we know for
                 sure that if each Future in tasksToComplete has completed, then
                 no other Runnables will be given to exec, and therefore no
                 Futures will be added to taskToComplete.
                 */
            }
        }
        fileWriter.write("ENDSEC;\n" + "END-ISO-10303-21;\n");
        // cleanup
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
     *                                      {@link MultiThreadedSerializer}
     *                                      is not the
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
    }

    /**
     * @param entity           The entity to serialize in the output file.
     * @param serializedEntity The serialization of the given {@code entity} in
     *                         the form "IFCENTITY(#1,#3,'text',#4, ...);\n",
     *                         that is without the id of the entity in the
     *                         beginning of the String.
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
