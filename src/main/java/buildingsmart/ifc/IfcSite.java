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
 * A defined area of land, possibly covered with water, on which the project
 * construction is to be completed. A site may be used to erect building(s) or
 * other AEC products.
 * </p>
 * <p>
 * A site (<i>IfcSite</i>) may include a definition of the single geographic
 * reference point for this site (global position using <i>Longitude</i>,
 * <i>Latitude</i> and
 * <i>Elevation</i>) for the project. This definition may specify an
 * exact global position of the origin of the local placement of the IfcSite in
 * geospatial terms or it may specify an approximate position intended for
 * informational purposes only.
 * </p>
 * <p>
 * The geometrical placement of the site, defined by the
 * <i>IfcLocalPlacement</i>, shall be always relative to the
 * spatial structure element, in which this site is included, or absolute, i.e.
 * to the world coordinate system, as tablished by the geometric representation
 * context of the oject. The world coordinate system, established at the
 * >IfcProject.RepresentationContexts</i>, may include a finition of the true
 * north within the XY plane of the rld coordinate system, if provided, it can
 * be obtained at >IfcGeometricRepresentationContext.TrueNorth</i>. p> > project
 * may span over several connected or disconnected tes. Therefore site complex
 * provides for a collection of tes included in a project. A site can also be
 * decomposed parts, where each part defines a site section. This is fined by
 * the composition type attribute of the supertype
 * >IfcSpatialStructureElements</i>
 * which is interpreted as llow: p>
 * <ul>
 *   <li>COMPLEX = site complex
 *   </li>
 *   <li>ELEMENT = site
 *   </li>
 *   <li>PARTIAL = site section
 *   </li>
 * </ul>
 * <p>
 *   <u><b>Quantity Use Definition</b></u>
 * </p>
 * <p>
 *   The quantities relating to the <i>IfcSite</i> are defined
 *   by the <i>IfcElementQuantity</i> and attached by the
 *   <i>IfcRelAssignsProperties</i>. The following quantities
 *   are foreseen, but will be subjected to the local standard
 *   of measurement:
 * </p>
 * <table style="width: 100%;" border="1" cellpadding="2"
 * cellspacing="2" summary="quantity use definition">
 *   <tbody>
 *     <tr>
 *       <td>
 *         <font size="-1"><i><b>Name</b></i></font>
 *       </td>
 *       <td>
 *         <font size="-1"><i><b>Description</b></i></font>
 *       </td>
 *       <td>
 *         <font size="-1"><i><b>Value Type</b></i></font>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>
 *         <font size="-1">NominalPerimeter</font>
 *       </td>
 *       <td>
 *         <font size="-1">Perimeter of the Site boundary. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td>
 *         <font size="-1"><i>IfcQuantityLength</i></font>
 *       </td>
 *     </tr>
 *     <tr>
 *       <td>
 *         <font size="-1">NominalArea</font>
 *       </td>
 *       <td>
 *         <font size="-1">Area for this site (horizontal
 *         projections). The exact definition and calculation
 * 		rules depend on the method of
 * 		measurement used.</font>
 *       </td>
 *       <td>
 *         <font size="-1"><i>IfcQuantityArea</i></font>
 *       </td>
 *     </tr>
 *   </tbody>
 * </table>
 * <p>
 *   <u><b>Spatial Structure Use Definition</b></u>
 * </p>
 * <p>
 *   The <i>IfcSite</i> is used to build the spatial structure
 *   of a building (that serves as the primary project breakdown
 *   and is required to be hierarchical). The spatial structure
 *   elements are linked together by using the objectified
 *   relationship <i>IfcRelAggregates</i>. The <i>IfcSite</i>
 *   references them by its inverse relationships:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcSite.Decomposes</i> -- referencing
 *     (<i>IfcProject</i> || <i>IfcSite</i>) by
 *     <i>IfcRelAggregates.RelatingObject</i>, If it refers to
 *     another instance of <i>IfcSite</i>, the referenced
 *     <i>IfcSite</i> needs to have a different and higher
 *     <i>CompositionType</i>, i.e. COMPLEX (if the other
 *     <i>IfcSite</i> has ELEMENT), or ELEMENT (if the other
 *     <i>IfcSite</i> has PARTIAL).
 *   </li>
 *   <li>
 *     <i>IfcSite.IsDecomposedBy</i> -- referencing
 *     (<i>IfcSite</i> || <i>IfcBuilding</i> || <i>IfcSpace</i>)
 *     by <i>IfcRelAggregates.RelatedObjects</i>. If it refers
 *     to another instance of <i>IfcSite</i>, the referenced
 *     <i>IfcSite</i> needs to have a different and lower
 *     CompositionType, i.e. ELEMENT (if the other
 *     <i>IfcSite</i> has COMPLEX), or PARTIAL (if the other
 *     <i>IfcSite</i> has ELEMENT).
 *   </li>
 * </ul>If there are building elements and/or other elements
 * directly related to the <i>IfcSite</i> (like a fence, or a
 * shear wall), they are associated with the <i>IfcSite</i> by
 * using the objectified relationship
 * <i>IfcRelContainedInSpatialStructure</i>. The
 * <i>IfcIfcSite</i> references them by its inverse
 * relationship:<br>
 * <ul>
 *   <li>
 *     <i>IfcSite.ContainsElements</i> -- referencing any
 *     subtype of <i>IfcProduct</i> (with the exception of other
 *     spatial structure element) by
 *     <i>IfcRelContainedInSpatialStructure.RelatedElements</i>.
 *   </li>
 * </ul>
 * <p>
 *   <u><b>Geometry Use Definitions</b></u>
 * </p>
 * <p>
 *   The geometric representation of <i>IfcSite</i> is given by
 *   the <i>IfcProductDefinitionShape</i> and
 *   <i>IfcLocalPlacement</i> allowing multiple geometric
 *   representations.
 * </p>
 * <p>
 *   <b>Local placement</b>
 * </p>
 * <p>
 *   The local placement for <i>IfcSite</i> is defined in its
 *   supertype <i>IfcProduct</i>. It is defined by the
 *   <i>IfcLocalPlacement</i>, which defines the local
 *   coordinate system that is referenced by all geometric
 *   representations.
 * </p>
 * <ul>
 *   <li>The <i>PlacementRelTo</i> relationship of
 *   <i>IfcLocalPlacement</i> shall point to the
 *   <i>IfcSpatialStructureElement</i> of type "<i>IfcSite</i>",
 *   if relative placement is used (e.g. to position a site
 *   relative a a site complex, or a site section to a site).
 *   </li>
 *   <li>If the relative placement is not used, the absolute
 *   placement is defined within the world coordinate system. If
 *   there is only one site object, then this is the default
 *   situation.
 *   </li>
 * </ul>
 * <p>
 *   <b>Foot Print Representation</b>
 * </p>
 * <p>
 *   The foot print representation of <i>IfcSite</i> is given by
 *   either a single 2D curve (such as <i>IfcPolyline</i> or
 *   <i>IfcCompositeCurve</i>), or by a list of 2D curves (in
 *   case of inner boundaries).
 * </p>
 * <p>
 *   The representation identifier and type of this geometric
 *   representation of <i>IfcSite</i> is:
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
 *   <b>Survey Points Representation</b>
 * </p>
 * <p>
 *   The survey point representation of <i>IfcSite</i> is
 *   defined using a set of survey points and optionally
 *   breaklines. The breaklines are restricted to only connect
 *   points given in the set of survey points. Breaklines, if
 *   given, are used to constrain the triangulation.
 * </p>
 * <p>
 *   The representation identifier and type of this geometric
 *   representation of <i>IfcSite</i> is:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationIdentifier</i> =
 *     'SurveyPoints'
 *   </li>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationType</i> =
 *     'GeometricSet'
 *   </li>
 * </ul>
 * <p>
 *   <b>Facetation Representation</b>
 * </p>
 * <p>
 *   The facetation representation of <i>IfcSite</i> is defined
 *   using a surface model, based on the
 *   <i>IfcFaceBasedSurfaceModel</i> or on the
 *   <i>IfcShellBasedSurfaceModel</i>. Normally the surface
 *   model is the result after triangulation of the site survey
 *   points.
 * </p>
 * <p>
 *   The representation identifier and type of this
 *   representation of <i>IfcSite</i> is:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationIdentifier</i> =
 *     'Facetation'
 *   </li>
 *   <li>
 *     <i>IfcShapeRepresentation.RepresentationType</i> =
 *     'SurfaceModel'
 *   </li>
 * </ul>
 * <p>
 *   <b>Body Representation</b>
 * </p>
 * <p>
 *   The body (or solid model) representation of <i>IfcSite</i>
 *   is defined using a faceted boundary representation based on
 *   the <i>IfcFacetedBrep</i> or on the
 *   <i>IfcFacetedBrepWithVoids</i>.
 * </p>
 * <p>
 *   The representation identifier and type of this
 *   representation of <i>IfcSite</i> is:
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
 */
public class IfcSite extends IfcSpatialStructureElement {
    private final IfcCompoundPlaneAngleMeasure refLatitude;
    private final IfcCompoundPlaneAngleMeasure refLongitude;
    private final IfcLengthMeasure refElevation;
    private final IfcLabel landTitleNumber;
    private final IfcPostalAddress siteAddress;

    /**
     * Creates a new IfcProduct, using the provided globalId.
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
     * @param refLatitude     World Latitude at reference point (most likely
     *                        defined in legal description). Defined as integer
     *                        values for degrees, minutes, seconds, and,
     *                        optionally, millionths of seconds with respect to
     *                        the world geodetic system WGS84.
     *                        <BLOCKQUOTE> <FONT SIZE="-1">Latitudes are
     *                        measured relative to the geodetic equator, north
     *                        of the equator by positive values - from 0 till
     *                        +90,   south of the equator by negative values -
     *                        from 0 till  -90 .</FONT></BLOCKQUOTE>
     * @param refLongitude    World Longitude at reference point (most likely
     *                        defined in legal description). Defined as integer
     *                        values for degrees, minutes, seconds, and,
     *                        optionally, millionths of seconds with respect to
     *                        the world geodetic system WGS84.
     *                        <BLOCKQUOTE> <FONT SIZE="-1">Longitudes are
     *                        measured relative to the geodetic zero meridian,
     *                        nominally the same as the Greenwich prime
     *                        meridian: longitudes west of the zero meridian
     *                        have positive values - from 0 till +180,
     *                        longitudes east of the zero meridian have negative
     *                        values - from 0 till -180 .</FONT></BLOCKQUOTE>
     * @param refElevation    Datum elevation relative to sea level.
     * @param landTitleNumber The land title number (designation of the site
     *                        within a regional system).
     * @param siteAddress     Address given to the site for postal purposes.
     * @throws IllegalArgumentException If globalId or ownerHistory are null; if
     *                                  globalId was used in another instance of
     *                                  this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape; if
     *                                  compositionType is null.
     */
    public IfcSite(@NotNull IfcGloballyUniqueId globalId,
                   @NotNull IfcOwnerHistory ownerHistory, IfcLabel name, IfcText description, IfcLabel objectType,
                   IfcObjectPlacement objectPlacement, IfcProductRepresentation representation, IfcLabel longName,
                   @NotNull IfcElementCompositionEnum compositionType,
                   IfcCompoundPlaneAngleMeasure refLatitude,
                   IfcCompoundPlaneAngleMeasure refLongitude, IfcLengthMeasure refElevation, IfcLabel landTitleNumber,
                   IfcPostalAddress siteAddress) {
        super(globalId, ownerHistory, name, description, objectType,
                objectPlacement, representation, longName, compositionType);
        this.refLatitude = refLatitude;
        this.refLongitude = refLongitude;
        this.refElevation = refElevation;
        this.landTitleNumber = landTitleNumber;
        this.siteAddress = siteAddress;
    }

    /**
     * Creates a new IfcProduct and generates a pseudo random globalId.
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
     * @param refLatitude     World Latitude at reference point (most likely
     *                        defined in legal description). Defined as integer
     *                        values for degrees, minutes, seconds, and,
     *                        optionally, millionths of seconds with respect to
     *                        the world geodetic system WGS84.
     *                        <BLOCKQUOTE> <FONT SIZE="-1">Latitudes are
     *                        measured relative to the geodetic equator, north
     *                        of the equator by positive values - from 0 till
     *                        +90,   south of the equator by negative values -
     *                        from 0 till  -90 .</FONT></BLOCKQUOTE>
     * @param refLongitude    World Longitude at reference point (most likely
     *                        defined in legal description). Defined as integer
     *                        values for degrees, minutes, seconds, and,
     *                        optionally, millionths of seconds with respect to
     *                        the world geodetic system WGS84.
     *                        <BLOCKQUOTE> <FONT SIZE="-1">Longitudes are
     *                        measured relative to the geodetic zero meridian,
     *                        nominally the same as the Greenwich prime
     *                        meridian: longitudes west of the zero meridian
     *                        have positive values - from 0 till +180,
     *                        longitudes east of the zero meridian have negative
     *                        values - from 0 till -180 .</FONT></BLOCKQUOTE>
     * @param refElevation    Datum elevation relative to sea level.
     * @param landTitleNumber The land title number (designation of the site
     *                        within a regional system).
     * @param siteAddress     Address given to the site for postal purposes.
     * @throws IllegalArgumentException If ownerHistory is null; if
     *                                  representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape;
     *                                  if compositionType is null.
     */
    public IfcSite(@NotNull IfcOwnerHistory ownerHistory, IfcLabel name, IfcText description, IfcLabel objectType,
                   IfcObjectPlacement objectPlacement, IfcProductRepresentation representation, IfcLabel longName,
                   @NotNull IfcElementCompositionEnum compositionType,
                   IfcCompoundPlaneAngleMeasure refLatitude,
                   IfcCompoundPlaneAngleMeasure refLongitude, IfcLengthMeasure refElevation, IfcLabel landTitleNumber,
                   IfcPostalAddress siteAddress) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType, objectPlacement, representation, longName,
                compositionType, refLatitude, refLongitude, refElevation,
                landTitleNumber, siteAddress);
    }
}
