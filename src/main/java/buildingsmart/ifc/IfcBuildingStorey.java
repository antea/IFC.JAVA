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
import lombok.NonNull;

/**
 * The building storey has an elevation and typically represents a (nearly)
 * horizontal aggregation of spaces that are vertically bound.
 * </p>
 * <p>
 * A storey is (if specified) associated to a building. A storey may span over
 * several connected storeys. Therefore ey complex provides for a collection of
 * storeys uded in a building. A storey can also be decomposed in izontal)
 * parts, where each part defines a partial ey. This is defined by the
 * composition type attribute he supertype <i>IfcSpatialStructureElements</i>
 * which nterpreted as follow:
 * </p>
 * <ul>
 *   <li>COMPLEX = building storey complex
 *   </li>
 *   <li>ELEMENT = building storey
 *   </li>
 *   <li>PARTIAL = partial building storey
 *   </li>
 * </ul>
 * <blockquote>
 *   <font size="-1">EXAMPLE: In split level houses, a storey is
 *   split into two or more partial storeys, each with a
 *   different elevation. It can be handled by defining a
 *   storey, which includes two or more partial storeys with the
 *   individual elevations.</font>
 * </blockquote>
 * <p>
 *   <u><b>Quantity Use Definition</b></u>
 * </p>
 * <p>
 *   The quantities relating to the building storey are defined
 *   by the <i>IfcElementQuantity</i> and attached by the
 *   <i>IfcRelDefinesByProperties</i>. The following quantities
 *   are foreseen, but will be subjected to the local standard
 *   of measurement:
 * </p>
 * <table border="1" cellpadding="2" cellspacing="2" summary=
 * "summary of base quantities available for IfcBuildingStorey">
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
 *         <font size="-1">Standard height of this storey, from
 *         the bottom surface of the floor, to the bottom
 *         surface of the floor or roof above. The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityLength</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">GrossFloorArea</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated sum of all areas covered
 *         by the building storey (as horizontal projections and
 *         normally including the area of construction elements.
 *         The exact definition and calculation rules depend on
 *         the method of measurement used.</font>
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
 *         covered by the building storey (normally excluding
 *         the area of construction elements). The exact
 *         definition and calculation rules depend on the method
 *         of measurement used.</font>
 *       </td>
 *       <td align="left" valign="top">
 *         <font size="-1"><i>IfcQuantityArea</i></font>
 *       </td>
 *     </tr>
 *     <tr valign="top">
 *       <td align="left" valign="top">
 *         <font size="-1">GrossVolume</font>
 *       </td>
 *       <td align="left" valign="top" width="60%">
 *         <font size="-1">Calculated gross volume of all areas
 *         enclosed by the building storey (</font><font size=
 *         "-1">normally including the area of construction
 *         elements</font><font size="-1">). The exact
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
 *         enclosed by the building storey</font> <font size=
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
 *   The <i>IfcBuildingStorey</i> is used to build the spatial
 *   structure of a building (that serves as the primary project
 *   breakdown and is required to be hierarchical). The spatial
 *   structure elements are linked together by using the
 *   objectified relationship <i>IfcRelAggregates</i>. The
 *   <i>IfcBuildingStorey</i>references them by its inverse
 *   relationships:
 * </p>
 * <ul>
 *   <li>
 *     <i>IfcBuildingStorey.Decomposes</i> -- referencing
 *     (<i>IfcBuilding</i> || <i>IfcBuildingStorey</i>) by
 *     <i>IfcRelAggregates.RelatingObject</i>, If it refers to
 *     another instance of <i>IfcBuildingStorey</i>, the
 *     referenced <i>IfcBuildingStorey</i> needs to have a
 *     different and higher <i>CompositionType</i>, i.e. COMPLEX
 *     (if the other <i>IfcBuildingStorey</i> has ELEMENT), or
 *     ELEMENT (if the other <i>IfcBuildingStorey</i> has
 *     PARTIAL).
 *   </li>
 *   <li>
 *     <i>IfcBuildingStorey.IsDecomposedBy</i> -- referencing
 *     (<i>IfcBuildingStorey</i> || <i>IfcSpace</i>) by
 *     <i>IfcRelAggregates.RelatedObjects</i>. If it refers to
 *     another instance of <i>IfcBuildingStorey</i>, the
 *     referenced <i>IfcBuildingStorey</i> needs to have a
 *     different and lower CompositionType, i.e. ELEMENT (if the
 *     other <i>IfcBuildingStorey</i> has COMPLEX), or PARTIAL
 *     (if the other <i>IfcBuildingStorey</i> has ELEMENT).
 *   </li>
 * </ul>If there are building elements and/or other elements
 * directly related to the <i>IfcBuildingStorey</i> (like most
 * building elements, such as walls, columns, etc.), they are
 * associated with the <i>IfcBuildingStorey</i> by using the
 * objectified relationship
 * <i>IfcRelContainedInSpatialStructure</i>. The
 * <i>IfcBuildingStorey</i> references them by its inverse
 * relationship:<br>
 * <ul>
 *   <li>
 *     <i>IfcBuildingStorey.ContainsElements</i> -- referencing
 *     any subtype of <i>IfcProduct</i> (with the exception of
 *     other spatial structure element) by
 *     <i>IfcRelContainedInSpatialStructure.RelatedElements</i>.
 *   </li>
 * </ul>
 * <p>
 *   <b><u>Geometry Use Definitions</u></b>
 * </p>
 * <p>
 *   The geometric representation of <i>IfcBuildingStorey</i> is
 *   given by the <i>IfcProductDefinitionShape</i> and
 *   <i>IfcLocalPlacement</i>, allowing multiple geometric
 *   representation.
 * </p>
 * <p>
 *   <b>Local Placement</b>
 * </p>
 * <p>
 *   The local placement for <i>IfcBuildingStorey</i> is defined
 *   in its supertype <i>IfcProduct</i>. It is defined by the
 *   <i>IfcLocalPlacement</i>, which defines the local
 *   coordinate system that is referenced by all geometric
 *   representations.
 * </p>
 * <ul>
 *   <li>The <i>PlacementRelTo</i> relationship of
 *   <i>IfcLocalPlacement</i> shall point (if relative placement
 *   is used) to the <i>IfcSpatialStructureElement</i> of type
 *   <i>IfcBuilding</i>, or of type <i>IfcBuildingStorey</i>
 *   (e.g. to position a building storey relative to a building
 *   storey complex, or a partial building storey to a building
 *   storey).
 *   </li>
 *   <li>If the relative placement is not used, the absolute
 *   placement is defined within the world coordinate system.
 *   </li>
 * </ul>
 * <p>
 *   <b><u>Geometric Representations</u></b>
 * </p>
 * <p>
 *   Currently, the use of a 2D 'FootPrint' representation of
 *   type 'GeometricCurveSet' and a 3D 'Body' representation of
 *   type 'Brep' is supported.
 * </p>
 * <blockquote>
 *   <p>
 *     <small>NOTE The independent geometric representation of
 *     <i>IfcBuildingStorey</i> may not be required or allowed
 *     in certain view definitions. In those cases only the
 *     contained elements and spaces have an independent
 *     geometric representation.</small>
 *   </p>
 * </blockquote>
 * <p>
 *   <b>Foot Print Representation</b>
 * </p>
 * <p>
 *   The foot print representation of <i>IfcBuildingStorey</i>
 *   is given by either a single 2D curve (such as
 *   <i>IfcPolyline</i> or <i>IfcCompositeCurve</i>), or by a
 *   list of 2D curves (in case of inner boundaries), if the
 *   building storey has an independent geometric
 *   representation.
 * </p>
 * <p>
 *   The representation identifier and type of this geometric
 *   representation of <i>IfcBuildingStorey</i> is:
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
 *   building storey has an independent geometric
 *   representation) of <i>IfcBuildingStorey</i> is defined
 *   using faceted B-Rep capabilities (with or without voids),
 *   based on the <i>IfcFacetedBrep</i> or on the
 *   <i>IfcFacetedBrepWithVoids</i>.
 * </p>
 * <p>
 *   The representation identifier and type of this
 *   representation of <i>IfcBuildingStorey</i> is:
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
 *   Since the building storey shape is usually described by the
 *   exterior building elements, an independent shape
 *   representation shall only be given, if the building storey
 *   is exposed independently from its constituting elements.
 * </p>
 */
public class IfcBuildingStorey extends IfcSpatialStructureElement {
    @Attribute(order = 9)
    private final IfcLengthMeasure elevation;

    /**
     * Creates a new IfcBuildingStorey, using the provided globalId.
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
     * @param elevation       Elevation of the base of this storey, relative to
     *                        the 0,00 internal reference height of the
     *                        building. The 0.00 level is given by the absolute
     *                        above sea level height by the ElevationOfRefHeight
     *                        attribute given at IfcBuilding.
     * @throws IllegalArgumentException If globalId or ownerHistory are null; if
     *                                  globalId was used in another instance of
     *                                  this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape; if
     *                                  compositionType is null.
     */
    public IfcBuildingStorey(@NonNull IfcGloballyUniqueId globalId,
                             @NonNull IfcOwnerHistory ownerHistory,
                             IfcLabel name, IfcText description,
                             IfcLabel objectType,
                             IfcObjectPlacement objectPlacement,
                             IfcProductRepresentation representation,
                             IfcLabel longName,
                             @NonNull IfcElementCompositionEnum compositionType,
                             IfcLengthMeasure elevation) {
        super(globalId, ownerHistory, name, description, objectType,
                objectPlacement, representation, longName, compositionType);
        this.elevation = elevation;
    }

    /**
     * Creates a new IfcBuildingStorey and generates a pseudo random globalId.
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
     * @param elevation       Elevation of the base of this storey, relative to
     *                        the 0,00 internal reference height of the
     *                        building. The 0.00 level is given by the absolute
     *                        above sea level height by the ElevationOfRefHeight
     *                        attribute given at IfcBuilding.
     * @throws IllegalArgumentException If ownerHistory is null; if
     *                                  representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape;
     *                                  if compositionType is null.
     */
    public IfcBuildingStorey(@NonNull IfcOwnerHistory ownerHistory,
                             IfcLabel name, IfcText description,
                             IfcLabel objectType,
                             IfcObjectPlacement objectPlacement,
                             IfcProductRepresentation representation,
                             IfcLabel longName,
                             @NonNull IfcElementCompositionEnum compositionType,
                             IfcLengthMeasure elevation) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType, objectPlacement, representation, longName,
                compositionType, elevation);
    }

    public static final class Builder {
        private IfcLengthMeasure elevation;
        private IfcLabel longName;
        private IfcElementCompositionEnum compositionType;
        private IfcObjectPlacement objectPlacement;
        private IfcProductRepresentation representation;
        private IfcLabel objectType;
        private IfcGloballyUniqueId globalId;
        private IfcOwnerHistory ownerHistory;
        private IfcLabel name;
        private IfcText description;

        private Builder() {
        }

        public static Builder anIfcBuildingStorey() {
            return new Builder();
        }

        public Builder elevation(IfcLengthMeasure elevation) {
            this.elevation = elevation;
            return this;
        }

        public Builder longName(IfcLabel longName) {
            this.longName = longName;
            return this;
        }

        public Builder compositionType(
                IfcElementCompositionEnum compositionType) {
            this.compositionType = compositionType;
            return this;
        }

        public Builder objectPlacement(IfcObjectPlacement objectPlacement) {
            this.objectPlacement = objectPlacement;
            return this;
        }

        public Builder representation(IfcProductRepresentation representation) {
            this.representation = representation;
            return this;
        }

        public Builder objectType(IfcLabel objectType) {
            this.objectType = objectType;
            return this;
        }

        public Builder globalId(IfcGloballyUniqueId globalId) {
            this.globalId = globalId;
            return this;
        }

        public Builder ownerHistory(IfcOwnerHistory ownerHistory) {
            this.ownerHistory = ownerHistory;
            return this;
        }

        public Builder name(IfcLabel name) {
            this.name = name;
            return this;
        }

        public Builder description(IfcText description) {
            this.description = description;
            return this;
        }

        public IfcBuildingStorey build() {
            return new IfcBuildingStorey(globalId, ownerHistory, name,
                    description, objectType, objectPlacement, representation,
                    longName, compositionType, elevation);
        }
    }
}
