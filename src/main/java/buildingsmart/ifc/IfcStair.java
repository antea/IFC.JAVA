/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2020 Antea S.r.l.
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
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * <P><U>Definition from ISO 6707-1:1989</U>: Construction comprising a
 * succession of horizontal stages (steps or landings) that make it possible to pass on foot to other levels.</P>
 * <P><U>Definition from IAI</U>: A vertical passageway allowing occupants to
 * walk (step) from one floor level to another floor level at a different elevation. It may include a landing as an
 * intermediate floor slab. </P>
 * <P>The stair is a container entity that aggregates all components of the
 * stair, it represents. The aggregation is handled via the
 * <I>IfcRelAggregates</I> relationship, relating an <I>IfcStair</I> with the
 * related flights (<I>IfcStairFlight</I>) and landings (<I>IfcSlab</I> with type 'Landing').</P>
 * <P><U><B>Property Set Use Definition</B></U>:</P>
 * <P>The property sets relating to the <I>IfcStair</I> are defined by the
 * <I>IfcPropertySet</I> and attached by the <I>IfcRelDefinesByProperties</I>
 * relationship. It is accessible by the inverse <I>IsDefinedBy</I> relationship. The following property set definitions
 * specific to the <I>IfcStair</I> are part of this IFC release:</P>
 * <UL>
 * <LI><A
 * HREF="../../psd/IfcSharedBldgElements/Pset_StairCommon.xml" TARGET="SOURCE">Pset_StairCommon</A>: common property set
 * for all stair occurrences</LI>
 * </UL>
 * <P><U><B>Geometry Use Definitions</B></U>:</P>
 * <P>The geometric representation of <I>IfcStair</I> is given by the
 * <I>IfcProductDefinitionShape</I>, allowing multiple geometric representations.
 * Independent geometric representations should only be used when the IfcStair is not defined as an aggregate. If
 * defined as an aggregate, the geometric representation is the sum of the representation of the components within the
 * aggregate. </P>
 * <P><B>Local placement</B></P>
 * <P>The local placement for <I>IfcStair</I> is defined in its supertype
 * <I>IfcProduct</I>. It is defined by the <I>IfcLocalPlacement</I>, which defines
 * the local coordinate system that is referenced by all geometric representations. </P>
 * <UL>
 * <LI>The <I>PlacementRelTo</I> relationship of <I>IfcLocalPlacement</I>
 * shall point (if given) to the local placement of the same
 * <I>IfcSpatialStructureElement</I> that is used in the
 * <I>ContainedInStructure</I> inverse attribute or to a referenced spatial
 * structure element at a higher level. </LI>
 * <LI>If the relative placement is not used, the absolute placement is
 * defined within the world coordinate system. </LI>
 * </UL>
 * <P>If the <I>LocalPlacement</I> is given for the <I>IfcStair</I>, then all
 * components, which are aggregated to the stair should use this placement as their relative placement.</P>
 * <P><B><I>Geometric Representation</I></B></P>
 * <P>If the <I>IfcStair</I> has components (referenced by
 * <I>SELF\IfcObject.IsDecomposedBy</I>) then no independent geometric
 * representation shall defined for the <I>IfcStair</I>. The <I>IfcStair</I> is then geometrically represented by the
 * geometric representation of its components. The components are accessed via
 * <I>SELF\IfcObject.IsDecomposedBy[1].RelatedObjects</I>.</P>
 * <P>If the <I>IfcStair</I> has no components defined (empty set of
 * <I>SELF\IfcObject.IsDecomposedBy</I>) then the <I>IfcStair</I> may be
 * represented by an <I>IfcShapeRepresentation</I> with the RepresentationType = 'Brep'.</P>
 */
@ToString(callSuper = true)
public class IfcStair extends IfcBuildingElement {
    @Attribute(8)
    private final IfcStairTypeEnum shapeType;

    /**
     * Creates a new IfcBuildingElement, using the provided globalId.
     *
     * @param globalId        Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory    Assignment of the information about the current ownership of that object, including owning
     *                        actor, application, local identification and information captured about the recent changes
     *                        of the object, NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be required . This would be enforced by a
     *                        where rule.
     * @param description     Optional description, provided for exchanging informative comments.
     * @param objectType      The type denotes a particular type that indicates the object further. The use has to be
     *                        established at the level of instantiable subtypes. In particular it holds the user defined
     *                        type, if the enumeration of the attribute PredefinedType is set to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement can either be absolute (relative to the
     *                        world coordinate system), relative (relative to the object placement of another product),
     *                        or constraint (e.g. relative to grid axes). It is determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product, being either a representation
     *                        (IfcProductRepresentation) or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product definition shape provides for multiple geometric
     *                        representations of the shape property of the object within the same object coordinate
     *                        system, defined by the object placement.
     * @param tag             The tag (or label) identifier at the particular instance of a product, e.g. the serial
     *                        number, or the position number. It is the identifier at the occurrence level.
     * @param shapeType       Predefined shape types for a stair that are specified in an Enum.
     * @throws NullPointerException     If globalId, ownerHistory or shapeType are null.
     * @throws IllegalArgumentException If globalId was used in another instance of this class; if representation is not
     *                                  null and objectPlacement is, while representation is an instance of
     *                                  IfcProductDefinitionShape; if representation is not null and {@code
     *                                  this.isDecomposedBy} is not empty.
     */
    @Builder
    public IfcStair(@NonNull IfcGloballyUniqueId globalId,
                    @NonNull IfcOwnerHistory ownerHistory,
                    IfcLabel name,
                    IfcText description,
                    IfcLabel objectType,
                    IfcObjectPlacement objectPlacement,
                    IfcProductRepresentation representation,
                    IfcIdentifier tag,
                    @NonNull IfcStairTypeEnum shapeType) {
        super(globalId, ownerHistory, name, description, objectType, objectPlacement, representation, tag);
        if (!this.getIsDecomposedBy().isEmpty() && this.getRepresentation() != null) {
            throw new IllegalArgumentException(
                    "Either the stair is not decomposed into its flights and landings (the stair can have independent" +
                            " geometry), or the geometry shall not be given at IfcStair directly.");
        }
        this.shapeType = shapeType;
    }

    /**
     * Creates a new IfcBuildingElement and generates a pseudo random globalId.
     *
     * @param ownerHistory    Assignment of the information about the current ownership of that object, including owning
     *                        actor, application, local identification and information captured about the recent changes
     *                        of the object, NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be required . This would be enforced by a
     *                        where rule.
     * @param description     Optional description, provided for exchanging informative comments.
     * @param objectType      The type denotes a particular type that indicates the object further. The use has to be
     *                        established at the level of instantiable subtypes. In particular it holds the user defined
     *                        type, if the enumeration of the attribute PredefinedType is set to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement can either be absolute (relative to the
     *                        world coordinate system), relative (relative to the object placement of another product),
     *                        or constraint (e.g. relative to grid axes). It is determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product, being either a representation
     *                        (IfcProductRepresentation) or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product definition shape provides for multiple geometric
     *                        representations of the shape property of the object within the same object coordinate
     *                        system, defined by the object placement.
     * @param tag             The tag (or label) identifier at the particular instance of a product, e.g. the serial
     *                        number, or the position number. It is the identifier at the occurrence level.
     * @param shapeType       Predefined shape types for a stair that are specified in an Enum.
     * @throws NullPointerException     If ownerHistory or shapeType are null.
     * @throws IllegalArgumentException If representation is not null and objectPlacement is, while representation is an
     *                                  instance of IfcProductDefinitionShape; if representation is not null and {@code
     *                                  this.isDecomposedBy} is not empty.
     */
    public IfcStair(@NonNull IfcOwnerHistory ownerHistory,
                    IfcLabel name,
                    IfcText description,
                    IfcLabel objectType,
                    IfcObjectPlacement objectPlacement,
                    IfcProductRepresentation representation,
                    IfcIdentifier tag,
                    @NonNull IfcStairTypeEnum shapeType) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             objectType,
             objectPlacement,
             representation,
             tag,
             shapeType);
    }
}