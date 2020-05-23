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

import com.sun.istack.internal.NotNull;

/**
 * A building represents a structure that provides shelter for its occupants or
 * contents and stands in one place. The building is also used to provide a
 * basic element within the spatial structure hierarchy for the components of a
 * building project (together with site, storey, and space).
 * </p>
 * <p>
 * A building is (if specified) associated to a site. A ding may span over
 * several connected or disconnected dings. Therefore building complex provides
 * for a ection of buildings included in a site. A building can be decomposed in
 * (vertical) parts, where each part nes a building section. This is defined by
 * the osition type attribute of the supertype fcSpatialStructureElements</i>
 * which is interpreted as ow:
 * </p>
 * <ul>
 *   <li>COMPLEX = building complex
 *   </li>
 *   <li>ELEMENT = building
 *   </li>
 *   <li>PARTIAL = building section
 *   </li>
 * </ul>
 * <p>
 *   <u><b>Quantity Use Definition</b></u>:
 * </p>
 * <p>
 *   The quantities relating to the <i>IfcBuilding</i> are
 *   defined by the <i>IfcElementQuantity</i> and attached by
 *   the <i>IfcRelDefinesByProperties</i>. The following
 *   quantities are foreseen, but will be subjected to the local
 *   standard of measurement:
 * </p>
 * <table border="1" cellpadding="2" cellspacing="2">
 *   <tbody>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1"><i><b>Name</b></i></font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1"><i><b>Description</b></i></font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i><b>Value Type</b></i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">NominalHeight</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated height of the
 *         building, measured from the level of terrain to the
 *         top part of the building. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityLength</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">NominalArea</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated coverage of the
 *         site area that is occupied by the building (also
 *         referred to as footprint). The exact definition
 *         and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityArea</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">GrossFloorArea</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated sum of all areas covered
 *         by the building</font> <font size="-1">(normally
 *         including the area of construction
 *         elements)</font><font size="-1">. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityArea</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">NetFloorArea</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated sum of all usable areas
 *         covered by the building (normally excluding the area
 *         of construction elements). The exact definition and
 *         calculation rules depend on the method of measurement
 *         used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityArea</i> </font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">GrossVolume</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated gross volume of all areas
 *         enclosed by the building</font> <font size=
 *         "-1">(normally including the area of construction
 *         elements)</font><font size="-1">. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityVolume</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">NetVolume</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated net volume of all areas
 *         enclosed by the building</font> <font size=
 *         "-1">(normally excluding the area of construction
 *         elements)</font><font size="-1">. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityVolume</i></font>
 *       </td>
 *     </tr>
 *   </tbody>
 * </table>
 * <p>
 *   <u><b>Spatial Structure Use Definition</b></u>
 * </p>
 * <p>
 *   The <i>IfcBuilding</i> is used to build the spatial
 *   structure of a building (that serves as the primary project
 *   breakdown and is required to be hierarchical). The spatial
 *   structure elements are linked together by using the
 *   objectified relationship <i>IfcRelAggregates</i>. The
 *   <i>IfcBuilding</i> references them by its inverse
 *   relationships:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcBuilding.Decomposes</i> -- referencing
 *     (<i>IfcSite</i> || <i>IfcBuilding</i>) by
 *     <i>IfcRelAggregates.RelatingObject</i>, If it refers to
 *     another instance of <i>IfcBuilding</i>, the referenced
 *     <i>IfcBuilding</i> needs to have a different and higher
 *     <i>CompositionType</i>, i.e. COMPLEX (if the other
 *     <i>IfcBuilding</i> has ELEMENT), or ELEMENT (if the other
 *     <i>IfcBuilding</i> has PARTIAL).
 *   </li>
 *   <li>
 *     <i>IfcBuilding.IsDecomposedBy</i> -- referencing
 *     (<i>IfcBuilding</i> || <i>IfcBuildingStorey</i>) by
 *     <i>IfcRelAggregates.RelatedObjects</i>. If it refers to
 *     another instance of <i>IfcBuilding</i>, the referenced
 *     <i>IfcBuilding</i> needs to have a different and lower
 *     CompositionType, i.e. ELEMENT (if the other
 *     <i>IfcBuilding</i> has COMPLEX), or PARTIAL (if the other
 *     <i>IfcBuilding</i> has ELEMENT).
 *   </li>
 * </ul>If there are building elements and/or other elements
 * directly related to the <i>IfcBuilding</i> (like a curtain
 * wall spanning several stories), they are associated with the
 * <i>IfcBuilding</i> by using the objectified relationship
 * <i>IfcRelContainedInSpatialStructure</i>. The
 * <i>IfcBuilding</i> references them by its inverse
 * relationship:<br>
 * <ul>
 *   <li>
 *     <i>IfcBuilding.ContainsElements</i> -- referencing any
 *     subtype of <i>IfcProduct</i> (with the exception of other
 *     spatial structure element) by
 *     <i>IfcRelContainedInSpatialStructure.RelatedElements</i>.
 *   </li>
 * </ul>
 * <p>
 *   <b><u>Attribute Use Definition</u></b>:
 * </p>
 *         <p>
 *           The heated space within a Building
 *           shall be handled by the <i>IfcZone</i>, including
 *           the property for overall height of the heated space
 *           in the Building. The following figure shall define
 *           the interpretation of building heights and
 *           elevations for <i>IfcBuilding</i>.
 *         </p>
 *         <p>
 *           The <i>ElevationOfRefHeight</i> is
 *           used to give the height above sea level of the
 *           internal height 0.00. The height 0.00 is often used
 *           as a building internal reference height and equal
 *           to the floor finish level of the ground
 *           floor.
 *         </p>
 * <p>
 *   <u><b>Geometry Use Definitions</b></u>:
 * </p>
 * <p>
 *   The geometric representation of <i>IfcBuilding</i> is given by the
 *   <i>IfcProductDefinitionShape</i> and
 *   <i>IfcLocalPlacement</i>, allowing multiple geometric
 *   representation.
 * </p>
 * <p>
 *   <b>Local Placement</b>
 * </p>
 * <p>
 *   The local placement for <i>IfcBuilding</i> is defined in
 *   its supertype <i>IfcProduct</i>. It is defined by the
 *   <i>IfcLocalPlacement</i>, which defines the local
 *   coordinate system that is referenced by all geometric
 *   representations.
 * </p>
 * <ul>
 *   <li>The <i>PlacementRelTo</i> relationship of
 *   <i>IfcLocalPlacement</i> shall point (if relative placement
 *   is used) to the <i>IfcSpatialStructureElement</i> of type
 *   <i>IfcSite</i>, or of type <i>IfcBuilding</i> (e.g. to position a
 *   building relative to a building complex, or a building
 *   section to a building).
 *   </li>
 *   <li>If the relative placement is not used, the absolute
 *   placement is defined within the world coordinate system.
 *   </li>
 * </ul>
 * <p>
 *   <b><i>Geometric Representations</i></b>
 * </p>
 * <p>
 *   Currently, the use of a 2D 'FootPrint' representation of
 *   type 'GeometricCurveSet' and a 3D 'Body' representation of
 *   type 'Brep' is supported.
 * </p>
 * <p>
 *   <b>Foot Print Representation</b>
 * </p>
 * <p>
 *   The foot print representation of <i>IfcBuilding</i> is
 *   given by either a single 2D curve (such as
 *   <i>IfcPolyline</i> or <i>IfcCompositeCurve</i>), or by a
 *   list of 2D curves (in case of inner boundaries), if the
 *   building has an independent geometric representation.
 * </p>
 * <p>
 *   The representation identifier and type of this geometric
 *   representation of <i>IfcBuilding</i> is:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationIdentifier</i> =
 *     'FootPrint'
 *   </li>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationType</i> =
 *     'GeometricCurveSet'
 *   </li>
 * </ul>
 * <p>
 *   <b>Body Representation</b>
 * </p>
 * <p>
 *   The body (or solid model) geometric representation (if the
 *   building has an independent geometric representation) of
 *   <i>IfcBuilding</i> is defined using faceted B-Rep
 *   capabilities (with or without voids), based on the
 *   <i>IfcFacetedBrep</i> or on the
 *   <i>IfcFacetedBrepWithVoids</i>.
 * </p>
 * <p>
 *   The representation identifier and type of this
 *   representation of <i>IfcBuilding</i> is:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationIdentifier</i> =
 *     'Body'
 *   </li>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationType</i> = 'Brep'
 *   </li>
 * </ul>
 * <p>
 *   Since the building shape is usually described by the
 *   exterior building elements, an independent shape
 *   representation shall only be given, if the building is
 *   exposed independently from its constituting elements.
 * </p>
 */
public class IfcBuilding extends IfcSpatialStructureElement {
    private final IfcLengthMeasure elevationOfRefHeight;
    private final IfcLengthMeasure elevationOfTerrain;
    private final IfcPostalAddress buildingAddress;

    /**
     * Creates a new IfcBuilding, using the provided globalId.
     *
     * @param globalId             Assignment of a globally unique identifier
     *                             within the entire software world.
     * @param ownerHistory         Assignment of the information about the
     *                             current ownership of that object, including
     *                             owning actor, application, local
     *                             identification and information captured about
     *                             the recent changes of the object, NOTE: only
     *                             the last modification in stored.
     * @param name                 Optional name for use by the participating
     *                             software systems or users. For some subtypes
     *                             of IfcRoot the insertion of the Name
     *                             attribute may be required . This would be
     *                             enforced by a where rule.
     * @param description          Optional description, provided for exchanging
     *                             informative comments.
     * @param objectType           The type denotes a particular type that
     *                             indicates the object further. The use has to
     *                             be established at the level of instantiable
     *                             subtypes. In particular it holds the user
     *                             defined type, if the enumeration of the
     *                             attribute PredefinedType is set to
     *                             USERDEFINED.
     * @param objectPlacement      Placement of the product in space, the
     *                             placement can either be absolute (relative to
     *                             the world coordinate system), relative
     *                             (relative to the object placement of another
     *                             product), or constraint (e.g. relative to
     *                             grid axes). It is determined by the various
     *                             subtypes of IfcObjectPlacement, which
     *                             includes the axis placement information to
     *                             determine the transformation for the object
     *                             coordinate system.
     * @param representation       Reference to the representations of the
     *                             product, being either a representation
     *                             (IfcProductRepresentation) or as a special
     *                             case a shape representations
     *                             (IfcProductDefinitionShape).
     *                             The product definition shape provides for
     *                             multiple geometric representations of the
     *                             shape property of the object within the same
     *                             object coordinate system, defined by the
     *                             object placement.
     * @param longName             Long name for a spatial structure element,
     *                             used for informal purposes. Maybe used in
     *                             conjunction with the inherited Name
     *                             attribute.
     * @param compositionType      Denotes, whether the predefined spatial
     *                             structure element represents itself, or an
     *                             aggregate (complex) or a part (part). The
     *                             interpretation is given separately for each
     *                             subtype of spatial structure element.
     * @param elevationOfRefHeight Elevation above sea level of the reference
     *                             height used for all storey elevation
     *                             measures, equals to height 0.0. It is usually
     *                             the ground floor level.
     * @param elevationOfTerrain   Elevation above the minimal terrain level
     *                             around the foot print of the building, given
     *                             in elevation above sea level.
     * @param buildingAddress      Address given to the building for postal
     *                             purposes.
     * @throws IllegalArgumentException If globalId or ownerHistory are null; if
     *                                  globalId was used in another instance of
     *                                  this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape; if
     *                                  compositionType is null.
     */
    public IfcBuilding(@NotNull IfcGloballyUniqueId globalId,
                       @NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, IfcLabel objectType,
                       IfcObjectPlacement objectPlacement,
                       IfcProductRepresentation representation, IfcLabel longName,
                       @NotNull IfcElementCompositionEnum compositionType,
                       IfcLengthMeasure elevationOfRefHeight, IfcLengthMeasure elevationOfTerrain,
                       IfcPostalAddress buildingAddress) {
        super(globalId, ownerHistory, name, description, objectType,
                objectPlacement, representation, longName, compositionType);
        this.elevationOfRefHeight = elevationOfRefHeight;
        this.elevationOfTerrain = elevationOfTerrain;
        this.buildingAddress = buildingAddress;
    }

    /**
     * Creates a new IfcBuilding and generates a pseudo random globalId.
     *
     * @param ownerHistory         Assignment of the information about the
     *                             current ownership of that object, including
     *                             owning actor, application, local
     *                             identification and information captured about
     *                             the recent changes of the object, NOTE: only
     *                             the last modification in stored.
     * @param name                 Optional name for use by the participating
     *                             software systems or users. For some subtypes
     *                             of IfcRoot the insertion of the Name
     *                             attribute may be required . This would be
     *                             enforced by a where rule.
     * @param description          Optional description, provided for exchanging
     *                             informative comments.
     * @param objectType           The type denotes a particular type that
     *                             indicates the object further. The use has to
     *                             be established at the level of instantiable
     *                             subtypes. In particular it holds the user
     *                             defined type, if the enumeration of the
     *                             attribute PredefinedType is set to
     *                             USERDEFINED.
     * @param objectPlacement      Placement of the product in space, the
     *                             placement can either be absolute (relative to
     *                             the world coordinate system), relative
     *                             (relative to the object placement of another
     *                             product), or constraint (e.g. relative to
     *                             grid axes). It is determined by the various
     *                             subtypes of IfcObjectPlacement, which
     *                             includes the axis placement information to
     *                             determine the transformation for the object
     *                             coordinate system.
     * @param representation       Reference to the representations of the
     *                             product, being either a representation
     *                             (IfcProductRepresentation) or as a special
     *                             case a shape representations
     *                             (IfcProductDefinitionShape).
     *                             The product definition shape provides for
     *                             multiple geometric representations of the
     *                             shape property of the object within the same
     *                             object coordinate system, defined by the
     *                             object placement.
     * @param longName             Long name for a spatial structure element,
     *                             used for informal purposes. Maybe used in
     *                             conjunction with the inherited Name
     *                             attribute.
     * @param compositionType      Denotes, whether the predefined spatial
     *                             structure element represents itself, or an
     *                             aggregate (complex) or a part (part). The
     *                             interpretation is given separately for each
     *                             subtype of spatial structure element.
     * @param elevationOfRefHeight Elevation above sea level of the reference
     *                             height used for all storey elevation
     *                             measures, equals to height 0.0. It is usually
     *                             the ground floor level.
     * @param elevationOfTerrain   Elevation above the minimal terrain level
     *                             around the foot print of the building, given
     *                             in elevation above sea level.
     * @param buildingAddress      Address given to the building for postal
     *                             purposes.
     * @throws IllegalArgumentException If ownerHistory is null; if
     *                                  representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape;
     *                                  if compositionType is null.
     */
    public IfcBuilding(@NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, IfcLabel objectType,
                       IfcObjectPlacement objectPlacement,
                       IfcProductRepresentation representation, IfcLabel longName,
                       @NotNull IfcElementCompositionEnum compositionType,
                       IfcLengthMeasure elevationOfRefHeight, IfcLengthMeasure elevationOfTerrain,
                       IfcPostalAddress buildingAddress) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType, objectPlacement, representation, longName,
                compositionType, elevationOfRefHeight, elevationOfTerrain,
                buildingAddress);
    }
}
