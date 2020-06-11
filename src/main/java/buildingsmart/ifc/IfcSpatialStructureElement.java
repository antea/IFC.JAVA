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
import buildingsmart.io.InverseAttribute;
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * A spatial structure element (<i>IfcSpatialStructureElement</i>) is the
 * generalization of all spatial elements that might be used to define a spatial
 * structure. That spatial structure is often used to provide a project
 * structure to organize a building project.
 * </p>
 * <p>
 * atial project structure might define as many levels of mposition as necessary
 * for the building project. ents within the spatial project structure are:
 * </p>
 * <ul>
 *   <li>site as <i>IfcSite</i>
 *   </li>
 *   <li>building as <i>IfcBuilding</i>
 *   </li>
 *   <li>storey as <i>IfcBuildingStorey</i>
 *   </li>
 *   <li>space as <i>IfcSpace</i>
 *   </li>
 * </ul>
 * <p>
 *   or aggregations or parts thereof. The composition type
 *   declares an element to be either an element itself, or an
 *   aggregation (complex) or a decomposition (part). The
 *   interpretation of these types is given at each subtype of
 *   <i>IfcSpatialStructureElement</i>.
 * </p>
 * <p>
 *   The <i>IfcRelAggregates</i> is defined as an 1-to-many
 *   relationship and used to establish the relationship between
 *   exactly two levels within the spatial project structure.
 *   Finally the highest level of the spatial structure is
 *   assigned to <i>IfcProject</i> using the
 *   <i>IfcRelAggregates</i>.
 * </p>
 * <p>
 *   <u>Informal proposition</u>:
 * </p>
 * <ol>
 *   <li>The spatial project structure, established by the
 *   <i>IfcRelAggregates</i>, shall be acyclic.
 *   </li>
 *   <li>A site should not be (directly or indirectly)
 *   associated to a building, storey or space.
 *   </li>
 *   <li>A building should not be (directly or indirectly)
 *   associated to a storey or space.
 *   </li>
 *   <li>A storey should not be (directly or indirectly)
 *   associated to a space.
 *   </li>
 * </ol>
 */
public abstract class IfcSpatialStructureElement extends IfcProduct {
    @Attribute
    @Order(7)
    private final IfcLabel longName;
    @Attribute
    @Order(8)
    private final IfcElementCompositionEnum compositionType;
    //private IfcRelReferencedInSpatialStructure[] ReferencesElements;

    //private IfcRelServicesBuildings[] ServicedBySystems;
    @InverseAttribute
    private Set<IfcRelContainedInSpatialStructure> containsElements;

    /**
     * Creates a new IfcSpatialStructureElement, using the provided globalId.
     *
     * @param globalId        Assignment of a globally unique identifier within
     *                        the entire software world.
     * @param ownerHistory    Assignment of the information about the current
     *                        ownership of that object, including owning actor,
     *                        application, local identification and information
     *                        captured about the recent changes of the object,
     *                        NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating
     *                        software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be
     *                        required . This would be enforced by a where
     *                        rule.
     * @param description     Optional description, provided for exchanging
     *                        informative comments.
     * @param objectType      The type denotes a particular type that indicates
     *                        the object further. The use has to be established
     *                        at the level of instantiable subtypes. In
     *                        particular it holds the user defined type, if the
     *                        enumeration of the attribute PredefinedType is set
     *                        to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement
     *                        can either be absolute (relative to the world
     *                        coordinate system), relative (relative to the
     *                        object placement of another product), or
     *                        constraint (e.g. relative to grid axes). It is
     *                        determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis
     *                        placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product,
     *                        being either a representation
     *                        (IfcProductRepresentation)
     *                        or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product
     *                        definition shape provides for multiple geometric
     *                        representations of the shape property of the
     *                        object within the same object coordinate system,
     *                        defined by the object placement.
     * @param longName        Long name for a spatial structure element, used
     *                        for informal purposes. Maybe used in conjunction
     *                        with the inherited Name attribute.
     * @param compositionType Denotes, whether the predefined spatial structure
     *                        element represents itself, or an aggregate
     *                        (complex) or a part (part). The interpretation is
     *                        given separately for each subtype of spatial
     *                        structure element.
     * @throws IllegalArgumentException If globalId or ownerHistory are null; if
     *                                  globalId was used in another instance of
     *                                  this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape; if
     *                                  compositionType is null.
     */
    public IfcSpatialStructureElement(@NotNull IfcGloballyUniqueId globalId,
                                      @NotNull IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcObjectPlacement objectPlacement,
                                      IfcProductRepresentation representation,
                                      IfcLabel longName, @NotNull
                                              IfcElementCompositionEnum compositionType) {
        super(globalId, ownerHistory, name, description, objectType,
                objectPlacement, representation);
        if (compositionType == null) {
            throw new IllegalArgumentException(
                    "compositionType cannot be null");
        }
        this.longName = longName;
        this.compositionType = compositionType;
    }

    /**
     * Creates a new IfcSpatialStructureElement and generates a pseudo random
     * globalId.
     *
     * @param ownerHistory    Assignment of the information about the current
     *                        ownership of that object, including owning actor,
     *                        application, local identification and information
     *                        captured about the recent changes of the object,
     *                        NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating
     *                        software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be
     *                        required . This would be enforced by a where
     *                        rule.
     * @param description     Optional description, provided for exchanging
     *                        informative comments.
     * @param objectType      The type denotes a particular type that indicates
     *                        the object further. The use has to be established
     *                        at the level of instantiable subtypes. In
     *                        particular it holds the user defined type, if the
     *                        enumeration of the attribute PredefinedType is set
     *                        to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement
     *                        can either be absolute (relative to the world
     *                        coordinate system), relative (relative to the
     *                        object placement of another product), or
     *                        constraint (e.g. relative to grid axes). It is
     *                        determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis
     *                        placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product,
     *                        being either a representation
     *                        (IfcProductRepresentation)
     *                        or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product
     *                        definition shape provides for multiple geometric
     *                        representations of the shape property of the
     *                        object within the same object coordinate system,
     *                        defined by the object placement.
     * @param longName        Long name for a spatial structure element, used
     *                        for informal purposes. Maybe used in conjunction
     *                        with the inherited Name attribute.
     * @param compositionType Denotes, whether the predefined spatial structure
     *                        element represents itself, or an aggregate
     *                        (complex) or a part (part). The interpretation is
     *                        given separately for each subtype of spatial
     *                        structure element.
     * @throws IllegalArgumentException If ownerHistory is null; if
     *                                  representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape;
     *                                  if compositionType is null.
     */
    public IfcSpatialStructureElement(@NotNull IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcObjectPlacement objectPlacement,
                                      IfcProductRepresentation representation,
                                      IfcLabel longName, @NotNull
                                              IfcElementCompositionEnum compositionType) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType, objectPlacement, representation, longName,
                compositionType);
    }

    @Override
    protected IfcRelAggregates getDecomposes() {
        return (IfcRelAggregates) super.getDecomposes();
    }

    /**
     * @param decomposes References to the decomposition relationship, that
     *                   allows this object to be a part of the decomposition.
     *                   An object can only be part of a single decomposition
     *                   (to allow hierarchical strutures only).
     * @throws IllegalArgumentException If decomposes.relatedObjects does not
     *                                  contain this object; if decomposed is
     *                                  not of type IfcRelAggregates; if
     *                                  decomposes.relatingObject is not of type
     *                                  IfcProject nor
     *                                  IfcSpatialStructureElement.
     * @throws NullPointerException     If decomposes is null.
     */
    @Override
    protected void setDecomposes(@NotNull IfcRelDecomposes decomposes) {
        if (!(decomposes instanceof IfcRelAggregates)) {
            throw new IllegalArgumentException(
                    "decomposes must be of type IfcRelAggregates");
        }
        setDecomposes((IfcRelAggregates) decomposes);
    }

    /**
     * @param decomposes References to the decomposition relationship, that
     *                   allows this object to be a part of the decomposition.
     *                   An object can only be part of a single decomposition
     *                   (to allow hierarchical strutures only).
     * @throws IllegalArgumentException If decomposes.relatedObjects does not
     *                                  contain this object; if decomposes
     *                                  .relatingObject is not of type
     *                                  IfcProject nor
     *                                  IfcSpatialStructureElement.
     * @throws NullPointerException     If decomposes is null.
     */
    protected void setDecomposes(@NotNull IfcRelAggregates decomposes) {
        if (!(decomposes.getRelatingObject() instanceof IfcProject) &&
                !(decomposes
                        .getRelatingObject() instanceof IfcSpatialStructureElement)) {
            throw new IllegalArgumentException(
                    "decomposes.relatingObject must be of type IfcProject or " +
                            "IfcSpatialStructureElement");
        }
        super.setDecomposes(decomposes);
    }

    /**
     * @return A copy of containsElements. Operations performed on this Set
     * don't have any effect on containsElements. This is done to prevent adding
     * illegal IfcRelContainedInSpatialStructure to the Set.
     * @see #addToContainsElements(IfcRelContainedInSpatialStructure)
     */
    public Set<IfcRelContainedInSpatialStructure> getContainsElements() {
        return containsElements == null ? new HashSet<>(0) :
                new HashSet<>(containsElements);
    }

    /**
     * @param relationship The relationship to add to the Set containsElements.
     * @throws IllegalArgumentException If this object is not the
     * relatingStructure
     *                                  in the relationship.
     * @throws NullPointerException     If relationship is null.
     */
    protected void addToContainsElements(
            @NotNull IfcRelContainedInSpatialStructure relationship) {
        if (!relationship.getRelatingStructure().equals(this)) {
            throw new IllegalArgumentException(
                    "any IfcRelContainedInSpatialStructure part of " +
                            "containsElements must have this instance of " +
                            "IfcSpatialStructureElement as its " +
                            "relatingStructure");
        }
        if (this.containsElements != null) {
            this.containsElements.add(relationship);
        } else {
            Set<IfcRelContainedInSpatialStructure> containsElements =
                    new HashSet<>();
            containsElements.add(relationship);
            this.containsElements = containsElements;
        }
    }
}
