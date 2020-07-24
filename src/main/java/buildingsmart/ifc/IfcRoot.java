/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.ifc;

import buildingsmart.io.Attribute;
import buildingsmart.io.IfcEntity;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The IfcRoot is the most abstract and root class for all IFC entity
 * definitions that roots in the kernel or in subsequent layers of the IFC
 * object model. It is therefore the common supertype all all IFC entities,
 * beside those defined in an IFC resource schema. All entities that are
 * subtypes of IfcRoot can be used independently, whereas resource schema
 * entities, that are not subtypes of IfcRoot, are not supposed to be
 * independent entities.
 * <p>
 * The IfcRoot assigns the globally unique ID, and the ownership and history
 * information to the entity. In addition it may provide for a name and a
 * description about the concepts.
 */
public abstract class IfcRoot extends IfcEntity {
    private static final Set<IfcGloballyUniqueId> uniqueGlobalIds =
            new HashSet<>();
    @Attribute(0)
    private final IfcGloballyUniqueId globalId;
    @Attribute(1)
    private final IfcOwnerHistory ownerHistory;
    @Attribute(2)
    private final IfcLabel name;
    @Attribute(3)
    private final IfcText description;

    /**
     * Creates a new IfcRoot, using the provided globalId.
     *
     * @param globalId     Assignment of a globally unique identifier within the
     *                     entire software world.
     * @param ownerHistory Assignment of the information about the current
     *                     ownership of that object, including owning actor,
     *                     application, local identification and information
     *                     captured about the recent changes of the object,
     *                     NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software
     *                     systems or users. For some subtypes of IfcRoot the
     *                     insertion of the Name attribute may be required. This
     *                     would be enforced by a where rule.
     * @param description  Optional description, provided for exchanging
     *                     informative comments.
     * @throws IllegalArgumentException If globalId or ownerHistory are null, or
     *                                  if globalId was used in another instance
     *                                  of this class.
     */
    public IfcRoot(@NonNull IfcGloballyUniqueId globalId,
                   @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
                   IfcText description) {
        if (globalId == null) {
            throw new IllegalArgumentException("globalId cannot be null");
        }
        if (ownerHistory == null) {
            throw new IllegalArgumentException("ownerHistory cannot be null");
        }
        if (uniqueGlobalIds.contains(globalId)) {
            throw new IllegalArgumentException(
                    "globalId must be unique, and this one was used in " +
                            "another instance of this class");
        }
        uniqueGlobalIds.add(globalId);
        this.globalId = globalId;
        this.ownerHistory = ownerHistory;
        this.name = name;
        this.description = description;
    }

    /**
     * Creates a new IfcRoot and generates a pseudo random globalId.
     *
     * @param ownerHistory Assignment of the information about the current
     *                     ownership of that object, including owning actor,
     *                     application, local identification and information
     *                     captured about the recent changes of the object,
     *                     NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software
     *                     systems or users. For some subtypes of IfcRoot the
     *                     insertion of the Name attribute may be required. This
     *                     would be enforced by a where rule.
     * @param description  Optional description, provided for exchanging
     *                     informative comments.
     * @throws IllegalArgumentException If ownerHistory is null.
     */
    public IfcRoot(@NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
                   IfcText description) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description);
    }

    /**
     * Clears the Sets used to keep track of attributes that must be unique in
     * different instances of this class and its subclasses, thus allowing the
     * creation of new instances of this class having attributes marked as
     * UNIQUE with the same values as ones belonging to instances of this class
     * created before calling this method. Use at your own risk.
     */
    public static void clearUniqueConstraint() {
        uniqueGlobalIds.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcRoot ifcRoot = (IfcRoot) o;
        return globalId.equals(ifcRoot.globalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalId);
    }
}
