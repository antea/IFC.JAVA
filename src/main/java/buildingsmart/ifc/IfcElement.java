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
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

/**
 * Generalization of all components that make up an AEC product. Those elements
 * can be logically contained by a spatial structure element that constitutes a
 * certain level within a project structure hierarchy (e.g., site, building,
 * storey or space). This is done by using the
 * <i>IfcRelContainedInSpatialStructure</i>
 * relationship.
 * </p>
 * <p>
 * Elements are physically existent objects, although they might be void
 * elements, such as holes. Elements either remain permanently in the AEC
 * product, or only temporarily, as formwork does. Elements can be either
 * assembled on site or pre-manufactured and built in on site.
 * </p>
 * <blockquote>
 * <font size="-1">EXAMPLEs of elements in a building
 * construction context are walls, floors, windows and recesses.</font>
 * </blockquote>
 * <p>
 * An element can have material and quantity information assigned through the
 * <i>IfcRelAssociatesMaterial</i> and
 * <i>IfcRelDefinesByProperties</i> relationship.
 * </p>
 * <p>
 * In addition an element can be declared to be a specific occurrence of an
 * element type (and thereby be defined by the element type properties) using
 * the
 * <i>IfcRelDefinesByType</i> relationship.
 * </p>
 * <p>
 * An element can also be defined as an element assembly that is a group of
 * semantically and topologically related elements that form a higher level part
 * of the AEC product. Those element assemblies are defined by virtue of the
 * <i>IfcRelAggregates</i> relationship.
 * </p>
 * <blockquote>
 * <font size="-1">EXAMPLEs for element assembly are complete
 * Roof Structures, made by several Roof Areas, or a Stair, composed by Flights
 * and Landings.</font>
 * </blockquote>
 * <p>
 * Elements that performs the same function may be grouped by an "Element Group
 * By Function". It is realized by an instance of <i>IfcGroup</i> with the
 * <i>ObjectType</i> = 'ElementGroupByFunction".
 * </p>
 * <p>
 * <u><b>Quantity Use Definition</b></u>:
 * </p>
 * <p>
 * The quantities relating to the <i>IfcElement</i> are defined by the
 * <i>IfcElementQuantity</i> and attached by the
 * <i>IfcRelDefinesByProperties</i>.
 * A detailed specification for individual quantities is introduced at the level
 * of subtypes of <i>IfcElement</i>.
 * </p>
 * <p>
 * <u><b>Geometry Use Definitions</b></u>
 * </p>
 * <p>
 * The geometric representation of any <i>IfcElement</i> is given by the
 * <i>IfcProductDefinitionShape</i> and
 * <i>IfcLocalPlacement</i> allowing multiple geometric
 * representations. A detailed specification for the shape representation is
 * introduced at the level of subtypes of
 * <i>IfcElement</i>.
 * </p>
 */
public abstract class IfcElement extends IfcProduct {
    @Attribute
    @Order(7)
    private final IfcIdentifier tag;
    /*
    private IfcRelConnectsStructuralElement[] HasStructuralMember;
    private IfcRelFillsElement[] FillsVoids;
    private IfcRelConnectsElements[] ConnectedTo;
    private IfcRelCoversBldgElements[] HasCoverings;
    private IfcRelProjectsElement[] HasProjections;
    private IfcRelReferencedInSpatialStructure[] ReferencedInStructures;
    private IfcRelConnectsPortToElement[] HasPorts;
    private IfcRelVoidsElement[] HasOpenings;
    private IfcRelConnectsWithRealizingElements[] IsConnectionRealization;
    private IfcRelSpaceBoundary[] ProvidesBoundaries;
    private IfcRelConnectsElements[] ConnectedFrom;
    private IfcRelContainedInSpatialStructure[] ContainedInStructure;
     */

    /**
     * Creates a new IfcElement, using the provided globalId.
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
     * @param tag             The tag (or label) identifier at the particular
     *                        instance of a product, e.g. the serial number, or
     *                        the position number. It is the identifier at the
     *                        occurrence level.
     * @throws IllegalArgumentException If globalId or ownerHistory are null; if
     *                                  globalId was used in another instance of
     *                                  this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcElement(@NotNull IfcGloballyUniqueId globalId,
                      @NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                      IfcText description, IfcLabel objectType,
                      IfcObjectPlacement objectPlacement,
                      IfcProductRepresentation representation,
                      IfcIdentifier tag) {
        super(globalId, ownerHistory, name, description, objectType,
                objectPlacement, representation);
        this.tag = tag;
    }

    /**
     * Creates a new IfcElement and generates a pseudo random globalId.
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
     * @param tag             The tag (or label) identifier at the particular
     *                        instance of a product, e.g. the serial number, or
     *                        the position number. It is the identifier at the
     *                        occurrence level.
     * @throws IllegalArgumentException If ownerHistory is null; if
     *                                  representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcElement(@NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                      IfcText description, IfcLabel objectType,
                      IfcObjectPlacement objectPlacement,
                      IfcProductRepresentation representation,
                      IfcIdentifier tag) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType, objectPlacement, representation, tag);
    }
}
