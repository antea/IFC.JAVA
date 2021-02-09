/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of ifc-java.
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
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

import java.util.Set;

/**
 * The undertaking of some design, engineering, construction, or maintenance
 * activities leading towards a product. The project establishes the context for
 * information to be exchanged or shared, and it may represent a construction
 * project but does not have to. </p>
 * <p>The representation context, in the case of a geometric
 * representation context, which is referenced from the <i>IfcProject</i>,
 * includes:</p>
 * <ul>
 *   <li>the default units used</li>
 *   <li>the world coordinate system</li>
 *   <li>the coordinate space dimension</li>
 *   <li>the precision used within the geometric representations, and</li>
 *   <li>optionally the indication of the true north relative to the
 * world coordinate system</li>
 * </ul>
 */
@ToString(callSuper = true)
public class IfcProject extends IfcObject {
    @Attribute(5)
    private final IfcLabel longName;
    @Attribute(6)
    private final IfcLabel phase;
    @Attribute(7)
    private final Set<IfcRepresentationContext> representationContexts;
    @Attribute(8)
    private final IfcUnitAssignment unitsInContext;

    /**
     * Creates a new IfcProject, using the provided globalId.
     *
     * @param globalId               Assignment of a globally unique identifier
     *                               within the entire software world.
     * @param ownerHistory           Assignment of the information about the
     *                               current ownership of that object, including
     *                               owning actor, application, local
     *                               identification and information captured
     *                               about the recent changes of the object,
     *                               NOTE: only the last modification in
     *                               stored.
     * @param name                   Optional name for use by the participating
     *                               software systems or users. For some
     *                               subtypes of IfcRoot the insertion of the
     *                               Name attribute may be required. This would
     *                               be enforced by a where rule.
     * @param description            Optional description, provided for
     *                               exchanging informative comments.
     * @param objectType             The type denotes a particular type that
     *                               indicates the object further. The use has
     *                               to be established at the level of
     *                               instantiable subtypes. In particular it
     *                               holds the user defined type, if the
     *                               enumeration of the attribute PredefinedType
     *                               is set to USERDEFINED.
     * @param longName               Long name for the project as used for
     *                               reference purposes.
     * @param phase                  Current project phase, open to
     *                               interpretation for all project partner,
     *                               therefore given as IfcString.
     * @param representationContexts Context of the representations used within
     *                               the project. When the project includes
     *                               shape representations for its components,
     *                               one or several geometric representation
     *                               contexts need to be included that define
     *                               e.g. the world coordinate system, the
     *                               coordinate space dimensions, and/or the
     *                               precision factor.
     * @param unitsInContext         Units globally assigned to measure types
     *                               used within the context of this project.
     * @throws NullPointerException     If globalId, ownerHistory, name,
     *                                  representationContexts or unitsInContext
     *                                  are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class, if representationContexts
     *                                  contains one or more objects of type
     *                                  IfcGeometricRepresentationSubContexts.
     */
    @Builder
    public IfcProject(@NonNull IfcGloballyUniqueId globalId,
                      @NonNull IfcOwnerHistory ownerHistory,
                      @NonNull IfcLabel name,
                      IfcText description,
                      IfcLabel objectType,
                      IfcLabel longName,
                      IfcLabel phase,
                      @NonNull @Singular
                              Set<IfcRepresentationContext> representationContexts,
                      @NonNull IfcUnitAssignment unitsInContext) {
        super(globalId, ownerHistory, name, description, objectType);
        if (representationContexts.size() < 1) {
            throw new IllegalArgumentException(
                    "size of representationContexts must be at least 1");
        }
        for (IfcRepresentationContext reprCtx : representationContexts) {
            if (reprCtx instanceof IfcGeometricRepresentationSubContext) {
                throw new IllegalArgumentException(
                        "there can be no instances of " +
                                "IfcGeometricRepresentationSubContext in " +
                                "representationContexts");
            }
        }
        this.longName = longName;
        this.phase = phase;
        this.representationContexts = representationContexts;
        this.unitsInContext = unitsInContext;
    }

    /**
     * Creates a new IfcProject and generates a pseudo random globalId.
     *
     * @param ownerHistory           Assignment of the information about the
     *                               current ownership of that object, including
     *                               owning actor, application, local
     *                               identification and information captured
     *                               about the recent changes of the object,
     *                               NOTE: only the last modification in
     *                               stored.
     * @param name                   Optional name for use by the participating
     *                               software systems or users. For some
     *                               subtypes of IfcRoot the insertion of the
     *                               Name attribute may be required. This would
     *                               be enforced by a where rule.
     * @param description            Optional description, provided for
     *                               exchanging informative comments.
     * @param objectType             The type denotes a particular type that
     *                               indicates the object further. The use has
     *                               to be established at the level of
     *                               instantiable subtypes. In particular it
     *                               holds the user defined type, if the
     *                               enumeration of the attribute PredefinedType
     *                               is set to USERDEFINED.
     * @param longName               Long name for the project as used for
     *                               reference purposes.
     * @param phase                  Current project phase, open to
     *                               interpretation for all project partner,
     *                               therefore given as IfcString.
     * @param representationContexts Context of the representations used within
     *                               the project. When the project includes
     *                               shape representations for its components,
     *                               one or several geometric representation
     *                               contexts need to be included that define
     *                               e.g. the world coordinate system, the
     *                               coordinate space dimensions, and/or the
     *                               precision factor.
     * @param unitsInContext         Units globally assigned to measure types
     *                               used within the context of this project.
     * @throws NullPointerException     If ownerHistory, name,
     * representationContexts
     *                                  or unitsInContext are null.
     * @throws IllegalArgumentException If globalId, if representationContexts
     *                                  contains one or more objects of type
     *                                  IfcGeometricRepresentationSubContexts.
     */
    public IfcProject(@NonNull IfcOwnerHistory ownerHistory,
                      @NonNull IfcLabel name,
                      IfcText description,
                      IfcLabel objectType,
                      IfcLabel longName,
                      IfcLabel phase,
                      @NonNull Set<IfcRepresentationContext> representationContexts,
                      @NonNull IfcUnitAssignment unitsInContext) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             objectType,
             longName,
             phase,
             representationContexts,
             unitsInContext);
    }

    /**
     * @param relationship The relationship to add to the Set isDecomposedBy.
     * @throws IllegalArgumentException If this object is not the relatingObject
     *                                  in the relationship; if relationship is
     *                                  not of type IfcRelAggregates; if the
     *                                  relatedObjects in relationship are not
     *                                  of type IfcSite or IfcBuilding.
     * @throws NullPointerException     If relationship is null.
     */
    @Override
    protected void addToIsDecomposedBy(@NonNull IfcRelDecomposes relationship) {
        if (!(relationship instanceof IfcRelAggregates)) {
            throw new IllegalArgumentException(
                    "relationship must be of type IfcRelAggregates");
        }
        addToIsDecomposedBy((IfcRelAggregates) relationship);
    }

    /**
     * @param relationship The relationship to add to the Set isDecomposedBy.
     * @throws IllegalArgumentException If this object is not the relatingObject
     *                                  in the relationship; if the
     *                                  relatedObjects in relationship are not
     *                                  of type IfcSite or IfcBuilding.
     * @throws NullPointerException     If relationship is null.
     */
    protected void addToIsDecomposedBy(@NonNull IfcRelAggregates relationship) {
        for (IfcObjectDefinition obj : relationship.getRelatedObjects()) {
            if (!(obj instanceof IfcSite) && !(obj instanceof IfcBuilding)) {
                throw new IllegalArgumentException(
                        "relationship.relatedObjects can contain only objects" +
                                " of type IfcSite and IfcBuilding");
            }
        }
        super.addToIsDecomposedBy(relationship);
    }

    /**
     * @param decomposes References to the decomposition relationship, that
     *                   allows this object to be a part of the decomposition.
     *                   An object can only be part of a single decomposition
     *                   (to allow hierarchical strutures only).
     * @throws IllegalStateException If this method is called, because
     *                               IfcProject cannot decompose other
     *                               IfcObjectDefinition.
     */
    @Override
    protected void setDecomposes(IfcRelDecomposes decomposes) {
        throw new IllegalStateException(
                "IfcProject cannot decompose any other " +
                        "IfcObjectDefinition");
    }
}
