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
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * <p>
 * The
 * <i>IfcBuildingElementProxy</i> is a proxy definition that
 * provides the same functionality as an
 * <i>IfcBuildingElement</i>, but without having a defined
 * meaning of the special type of building element, it represents.
 * </p>
 * <blockquote>
 * <p>
 * <small>NOTE1 The <i>IfcBuildingElementProxy</i> should be
 * used to exchange special types of building elements for which the current IFC
 * Release does not yet provide a semantic definition.</small>
 * </p>
 * <p>
 * <small>NOTE2 The <i>IfcBuildingElementProxy</i> can also
 * be used to represent building elements for which the participating
 * applications can not provide additional semantic classification.</small>
 * </p>
 * </blockquote>
 * <p>
 * <b><u>Type Use Definition</u></b>
 * </p>
 * <p>
 * The <i>IfcBuildingElementProxy</i> defines the occurrence of any building
 * element, common information about the types (or styles) is handled by
 * <i>IfcBuildingElementProxyType</i>.
 * </p>
 * <p>
 * <i>IfcBuildingElementProxyType</i> (if present) may
 * blish the common type name, usage (or predefined) type, on material, common
 * set of properties and common shape esentations (using
 * <i>IfcRepresentationMap</i>). The fcBuildingElementProxyType</i> is attached
 * using the fcRelDefinedByType.RelatingType</i> objectified tionship and is
 * accessible by the inverse sDefinedBy</i> attribute.
 * </p>
 * ckquote>
 * <p>
 * ll>NOTE The <i>IfcBuildingElementProxyType</i> can be to share common
 * information among many occurrences he same proxy without establishing a
 * particular ntic meaning of the type.</small>
 * </p>
 * ockquote>
 * <p>
 * o <i>IfcBuildingElementProxyType</i> is attached (i.e. nly occurrence
 * information is given) the predefined may be given by using the
 * <i>ObjectType</i> attribute.
 * </p>
 * <p>
 * b>Property Set Use Definition</b></u>:
 * </p>
 * <p>
 * property sets relating to the <i>IfcBuildingElementProxy</i> are ned by the
 * <i>IfcPropertySet</i> and attached by the fcRelDefinesByProperties</i>
 * relationship. It is ssible by the inverse <i>IsDefinedBy</i> relationship.
 * following property set definitions specific to the fcBuildingElementProxy/i>
 * are part of this IFC release:
 * </p>
 * <ul>
 *   <li>
 *     <a href=
 *     "../../psd/IfcProductExtension/Pset_BuildingElementProxyCommon.xml"
 *     target="SOURCE">Pset_BuildingElementProxyCommon</a>: common
 *     property set
 *     for all occurrences of building element proxies.
 *   </li>
 * </ul>
 * <p>
 *   <u><b>Geometry Use Definitions</b></u>
 * </p>
 * <p>
 *   The geometric representation of any
 *   <i>IfcBuildingElementProxy</i> is given by the
 *   <i>IfcProductDefinitionShape</i> and
 *   <i>IfcLocalPlacement</i> allowing multiple geometric
 *   representations. The representation types defined at the
 *   supertype <i>IfcBuildingElement</i> also apply.
 * </p>
 * <p>
 *   <b>Local Placement</b>
 * </p>
 * <p>
 *   The local placement for any <i>IfcBuildingElementProxy</i>
 *   is defined in its supertype <i>IfcProduct</i>. It is
 *   defined by the <i>IfcLocalPlacement</i>, which defines the
 *   local coordinate system that is referenced by all geometric
 *   representations. The local placement can be given
 *   relatively.
 * </p>
 * <ul>
 *   <li>The <i>PlacementRelTo</i> relationship of
 *   <i>IfcLocalPlacement</i> shall point (if given) to the
 *   local placement of the same
 *   <i>IfcSpatialStructureElement</i>, which is used in the <i>
 *     ContainedInStructure</i> inverse attribute, or to a
 *     spatial structure element at a higher level, referenced
 *     by that.
 *   </li>
 *   <li>If the relative placement is not used, the absolute
 *   placement is defined within the world coordinate system.
 *   </li>
 * </ul>
 * <p>
 *   <u><b>Geometric Representation</b></u>
 * </p>
 * <p>
 *   Currently, the use of the representation types
 *   'BoundingBox', 'GeometricSet', 'GeometricCurveSet',
 *   'SweptSolid', 'SurfaceModel', 'Brep', and
 *   'MappedRepresentation' are allowed. The geometry use
 *   definitions for 'BoundingBox', 'SurfaceModel', 'Brep', and
 *   'MappedRepresentation' are explained at the supertype
 *   <i>IfcBuildingElement</i>.
 * </p>
 * <p>
 *   <b>GeometricCurveSet Representation</b>
 * </p>
 * <p>
 *   Any building element proxy may be represented by a
 *   geometric curve set, given by a collection of 2D points and
 *   curves. The following attribute values for the
 *   <i>IfcShapeRepresentation</i> holding this geometric
 *   representation shall be used:
 * </p>
 * <ul>
 *   <li>
 *     <i>RepresentationIdentifier</i>: 'FootPrint'
 *   </li>
 *   <li>
 *     <i>RepresentationType</i>: 'GeometricCurveSet'
 *   </li>
 * </ul>
 * <p>
 *   <b>GeometricSet Representation</b>
 * </p>
 * <p>
 *   Any building element proxy may be represented by a
 *   geometric set, given by a collection of 2D and 3D points,
 *   curves, and surfaces. It represents the body of the proxy
 *   object, when no topological structure is available. The
 *   following attribute values for the
 *   <i>IfcShapeRepresentation</i> holding this geometric
 *   representation shall be used:
 * </p>
 * <ul>
 *   <li>
 *     <i>RepresentationIdentifier</i>: 'Body'
 *   </li>
 *   <li>
 *     <i>RepresentationType</i>: 'GeometricSet'
 *   </li>
 * </ul>
 * <p>
 *   <b>Swept Solid Representation</b>
 * </p>
 * <p>
 *   Any building element proxy may be represented by swept
 *   solid geometry (either by extrusion or by revolution). The
 *   following attribute values for the
 *   <i>IfcShapeRepresentation</i> holding this geometric
 *   representation shall be used:
 * </p>
 * <ul>
 *   <li>
 *     <i>RepresentationIdentifier</i>: 'Body'
 *   </li>
 *   <li>
 *     <i>RepresentationType</i>: 'SweptSolid'
 *   </li>
 * </ul>
 * <p>
 *   No further restrictions (e.g., for the profile or extrusion
 *   direction) are defined at this level. A single or multiple
 *   swept area solid(s) can be the <i>Items</i> of the
 *   <i>IfcShapeRepresentation</i>.
 * </p>
 */
@ToString(callSuper = true)
public class IfcBuildingElementProxy extends IfcBuildingElement {
    @Attribute(8)
    private final IfcElementCompositionEnum compositionType;

    /**
     * Creates a new IfcBuildingElementProxy, using the provided globalId.
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
     * @param compositionType Indication, whether the proxy is intended to form
     *                        an aggregation (COMPLEX), an integral element
     *                        (ELEMENT), or a part in an aggregation (PARTIAL).
     * @throws NullPointerException     If globalId, ownerHistory or name are
     *                                  null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    @Builder
    public IfcBuildingElementProxy(@NonNull IfcGloballyUniqueId globalId,
                                   @NonNull IfcOwnerHistory ownerHistory,
                                   @NonNull IfcLabel name,
                                   IfcText description,
                                   IfcLabel objectType,
                                   IfcObjectPlacement objectPlacement,
                                   IfcProductRepresentation representation,
                                   IfcIdentifier tag,
                                   IfcElementCompositionEnum compositionType) {
        super(globalId,
              ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation,
              tag);
        this.compositionType = compositionType;
    }
}
