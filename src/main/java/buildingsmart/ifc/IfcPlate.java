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

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * <p>An <i>IfcPlate</i>
 * is a planar and often flat part with constant thickness. A plate can be a
 * structural part carrying loads between or beyond points of support, however
 * it is not required to be load bearing.&nbsp;The location of the plate (being
 * horizontal, vertical or sloped) is not relevant to its definition (in
 * contrary to <i>IfcWall</i> and <i>IfcSlab</i> (as floor slab)).&nbsp;</p>
 * <blockquote>
 * <p><small>NOTE &nbsp;Plates are&nbsp;normally
 * made of steel, other metallic material, or by glass panels. However the
 * definition of <i>IfcPlate</i> is material independent and specific material
 * information shall be handled by using
 * <i>IfcAssociatesMaterial</i>
 * to assign a material specification to the <i>IfcPlate</i>.&nbsp;</small></p>
 * <p><small>NOTE &nbsp;Although not necessarily, plates
 * are often add-on parts. This is represented by the <i>IfcRelAggregates</i>
 * decomposition mechanism used to aggregate parts, such as <i>IfcPlate</i>,
 * into a container element, e.g. <i>IfcElementAssembly</i>, or
 * <i>IfcCurtainWall</i>.&nbsp;</small> </p>
 * </blockquote>
 * <p> An instance <i>IfcPlate</i> should preferably
 * get its geometric representation and material assignment through the type
 * definition by
 * <i>IfcPlateType</i> assigned using the <i>IfcRelDefinesByType</i>
 * relationship. This allows identical plates in a construction to be
 * represented by the same instance of <i>IfcPlateType</i>. </p>
 * <p><u><b>Property Set Use Definition</b></u>:</p>
 * <p>The property sets relating to the <i>IfcPlate</i> are defined by the
 * <i>IfcPropertySet</i> and attached by the
 * <i>IfcRelDefinesByProperties</i>
 * relationship. It is accessible by the inverse <i>IsDefinedBy</i>
 * relationship. The following property set definitions specific to the
 * <i>IfcPlate</i> are part of this IFC release:
 * </p>
 *     <ul>
 *        <li><a href="../../psd/IfcSharedBldgElements/Pset_PlateCommon.xml"
 *          target="SOURCE">Pset_PlateCommon</a>: common property set for all
 *          plate occurrences
 * </li>
 * </ul>
 *  <p><u><b>Containment Use Definitions</b></u></p>
 * <p>The <i>IfcPlate</i>, as any subtype of <i>IfcBuildingElement</i>,
 * may participate in two different containment relationships. The first
 * (and in most implementation scenarios mandatory) relationship is the
 * hierachical spatial containment, the second relationship is
 * the aggregation within an&nbsp;element assembly.</p>
 * <ul>
 *   <li>The&nbsp;<i>IfcPlate</i> is placed within
 * the
 * project spatial hierarchy using the objectified relationship
 * <i>IfcRelContainedInSpatialStructure</i>,
 * referring to it by its inverse attribute <i>SELF\IfcElement
 * .ContainedInStructure</i>.
 * Subtypes of&nbsp;<i>IfcSpatialStructureElement</i> are
 * valid spatial containers, with <i>IfcBuildingStorey</i>
 * being the default container.</li>
 *   <li>The&nbsp;<i>IfcPlate</i> may be
 * aggregated into an
 * element assembly using the objectified relationship <i>IfcRelAggregates</i>,
 * referring to it by its inverse attribute <i>SELF\IfcObjectDefinition
 * .Decomposes</i>.
 * Any subtype of <i>IfcElement</i> can be an element
 * assembly, with <i>IfcElementAssembly</i> as a special
 * focus subtype. In this case, no additional relationship to the spatial
 * hierarchy shall be given (i.e.&nbsp;<i>SELF\IfcElement
 * .ContainedInStructure</i>
 * = NIL), the relationship to the spatial container is handled by the
 * element assembly<i>.</i> </li>
 * </ul>
 * <u><b><br>
 * Geometry use definition<br>
 * </b></u>
 * <p>The geometric representation of <i>Ifc</i><i>Plate</i>
 * is given by the <i>IfcProductDefinitionShape</i>, allowing
 * multiple geometric representations. Included are: </p>
 * <p><b>Local Placement</b></p>
 * <p>The local placement for <i>Ifc</i><i>Plate</i>
 * is
 * defined in its supertype <i>IfcProduct</i>. It is defined
 * by the <i>IfcLocalPlacement</i>, which defines the local
 * coordinate system that is referenced by all geometric representations. </p>
 * <ul>
 *   <li>The <i>PlacementRelTo</i> relationship of <i>IfcLocalPlacement</i>
 * shall point (if given) to the local placement of the same
 * <i>IfcSpatialStructureElement</i>,
 * which is used in the <i>ContainedInStructure</i> inverse
 * attribute, or to a spatial structure element at a higher level,
 * referenced by that.</li>
 *   <ul>
 *     <li>If the <i>IfcPlate</i> is part of an
 * assembly, the <i>PlacementRelTo</i> relationship of <i>IfcLocalPlacement</i>
 * shall point to the local placement of the container element, e.g.
 * <i>&nbsp;IfcElementAssembly</i>,
 *     </li>
 *   </ul>
 *   <li>If the relative placement is not used, the absolute
 * placement is defined within the world coordinate system.</li>
 * </ul>
 * <p><b><i>Geometric Representations</i></b></p>
 * <p>Currently, the use of 'SweptSolid', 'Clipping', 'Brep' and
 * 'MappedRepresentation' representations is supported. In addition the
 * general representation types 'SurfaceModel' and 'BoundingBox' are
 * allowed. The geometry use definition for 'BoundingBox', 'SurfaceModel'
 * and 'Brep' is explained at <i>IfcBuildingElement</i>.</p>
 * <p><b>SweptSolid Representation</b><br>
 * The standard geometric representation of <i>IfcPlate</i>
 * is defined using the 'SweptSolid' representation. The following
 * attribute values for the <i>IfcShapeRepresentation</i>
 * holding this geometric representation shall be used:</p>
 * <ul>
 *   <li><i>RepresentationIdentifier</i> : 'Body'</li>
 *   <li><i>RepresentationType</i> : 'SweptSolid'</li>
 * </ul>
 * <p><b>Clipping Representation</b><br>
 * The advanced geometric representation of <i>IfcMember </i>is
 * defined using the 'Clipping' geometry. The following attribute values
 * for the <i>IfcShapeRepresentation</i>
 * holding this geometric representation shall be used:</p>
 * <ul>
 *   <li><i>RepresentationIdentifier</i> : 'Body'</li>
 *   <li><i>RepresentationType</i> : 'Clipping'</li>
 * </ul>
 * <p><b>MappedRepresentation</b><br>
 * In addition to the standard geometric
 * representation of <i>Ifc</i><i>Plate</i> that
 * is defined using
 * the 'SweptSolid' or 'Clipping' geometry, also the
 * 'MappedRepresentation'
 * shall be supported as it allows for reusing the geometry definition of
 * the member type at all occurrences of the same type. The following
 * attribute values for the <i>IfcShapeRepresentation</i>
 * holding this geometric representation shall be used:</p>
 * <ul>
 *   <li><i>RepresentationIdentifier</i> : 'Body'</li>
 *   <li><i>RepresentationType</i> :
 * 'MappedRepresentation'</li>
 * </ul>
 * <p>The same constraints, as given for the standard 'SweptSolid'
 * and the advanced 'SweptSolid' and 'Clipping' geometric representation,
 * shall apply to the <i>MappedRepresentation</i> of the
 * <i>IfcRepresentationMap</i>.</p>
 * <p></p>
 * <p><u><b>Use definition for steel members</b></u></p>
 * <p> When using the <i>IfcPlate</i> for steel members
 * in steel construction
 * applications the following additional conventions apply: </p>
 * <p><b>Definition by non-geometric properties</b><br>
 * Additional
 * non-geometric properties can be specified through the class
 * <i>IfcPropertySet</i>, which is attached to the inverse
 * attribute
 * <i>IfcObject.IsDefinedBy</i> through the objectified
 * relationship
 * <i>IfcRelDefinesByProperties</i>. This allows for attaching
 * country-specific
 * information to structural members. </p>
 * <p><b>Decomposition</b><br>
 * An instance of <i>IfcPlate</i> can be part of
 * a decomposition through the <i>IfcRelAggregates</i>
 * relationship - both as
 * sub-ordinate or as a super-ordinate component. </p>
 * <ul>
 *   <li>If the <i>IfcPlate</i> instance is a
 * sub-ordinate component (i.e. the
 * plate is an add-on part), its local placement shall be relative to that
 * of the
 * super-ordinate instance. </li>
 *   <li>As a super-ordinate component, the sub-ordinates of <i>IfcPlate</i>
 * can be other plates or instances of <i>IfcProxy</i>, or
 *     <i>IfcDiscreteAccessory</i> (like <i>IfcFastener</i>).
 *   </li>
 * </ul>
 * <p><b>Position number</b><br>
 * The position number is assigned through the
 * attribute <i>IfcElement.Tag</i> </p>
 */
@ToString(callSuper = true)
public class IfcPlate extends IfcBuildingElement {
    /**
     * Creates a new IfcPlate, using the provided globalId.
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
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    @Builder
    public IfcPlate(@NonNull IfcGloballyUniqueId globalId,
                    @NonNull IfcOwnerHistory ownerHistory,
                    IfcLabel name,
                    IfcText description,
                    IfcLabel objectType,
                    IfcObjectPlacement objectPlacement,
                    IfcProductRepresentation representation,
                    IfcIdentifier tag) {
        super(globalId,
              ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation,
              tag);
    }
}
