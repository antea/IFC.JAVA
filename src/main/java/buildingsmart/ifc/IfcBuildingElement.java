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

import lombok.NonNull;
import lombok.ToString;

/**
 * The building element comprises all elements that are primarily part of the
 * construction of a building, i.e., its structural and space separating
 * system.
 * </p>
 * <blockquote>
 * <small>EXAMPLEs of building elements are walls, beams, or
 * doors, they are all physically existent and tangible things.</small>
 * </blockquote>
 * <p>
 * The <i>IfcBuildingElement</i> utilizes the following capabilities mainly
 * through inverse attributes referencing objectified relationships:
 * </p>
 *   <ol>
 *     <li>Grouping - being part of a logical group of objects
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelAssignsToGroup</i>
 *         </li>
 *         <li>inverse attribute: <i>HasAssignment</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Work processes - reference to work tasks, in which this
 *     building element is used
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelAssignsToProcess</i>
 *         </li>
 *         <li>inverse attribute: <i>HasAssignments</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Aggregation - aggregated together with other elements
 *     to form an aggregate
 *       <ul>
 *         <li>objectified relationship: <i>IfcRelAggregates</i>
 *         </li>
 *         <li>inverse attribute (for container):
 *         <i>IsDecomposedBy</i>
 *         </li>
 *         <li>inverse attribute (for contained parts):
 *         <i>Decomposes</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Material - assignment of material used by this building
 *     element
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelAssociatesMaterial</i>
 *         </li>
 *         <li>inverse attribute: <i>HasAssociations</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Classification - assigned reference to an external
 *     classification
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelAssociatesClassification</i>
 *         </li>
 *         <li>inverse attribute: <i>HasAssociations</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Documentation - assigned reference to an external
 *     documentation
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelAssociatesDocumentation</i>
 *         </li>
 *         <li>inverse attribute: <i>HasAssociations</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Type - reference to the common product type information
 *     for the element occurrence
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelDefinesByType</i>
 *         </li>
 *         <li>inverse attribute: <i>IsDefinedBy</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Properties - reference to all attached properties,
 *     including quantities
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelDefinesByProperties</i>
 *         </li>
 *         <li>inverse attribute: <i>IsDefinedBy</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Connection - connectivity to other elements, including
 *     the definition of the joint
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelConnectsElements</i>
 *         </li>
 *         <li>inverse attribute: <i>ConnectedTo</i>
 *         </li>
 *         <li>inverse attribute: <i>ConnectedFrom</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Realization - information, whether the building element
 *     is used to realize a connection (e.g. as a weld in a
 *     connection between two members)
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelConnectsWithRealizingElements</i>
 *         </li>
 *         <li>inverse attribute: <i>IsConnectionRealization</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Ports - information, whether the building element has
 *     ports for system connections (note: normally not used, this
 *     relationship is inherited from <i>IfcElement</i> and mainly
 *     applicable to <i>IfcDistributionElement</i>'s)
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelConnectsPortToElement</i>
 *         </li>
 *         <li>inverse attribute: <i>HasPorts</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Assignment to spatial structure - hierarchical
 *     assignment to the right level within the spatial structure
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelContainedInSpatialStructure</i>
 *         </li>
 *         <li>inverse attribute: <i>ContainedInStructure</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Reference to spatial structure(s) - non hierarchical
 *     reference to one or more elements within the spatial
 *     structure (e.g. a curtain wall, being contained in the
 *     building, references several stories)
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelContainedInSpatialStructure</i>
 *         </li>
 *         <li>inverse attribute: <i>ContainedInStructure</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Boundary - provision of space boundaries by this
 *     building element
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelSpaceBoundary</i>
 *         </li>
 *         <li>inverse attribute: <i>ProvidesBoundaries</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Coverings - assignment of covering elements to this
 *     building element (note: normally covering elements are
 *     assigned to the space, only used for special cases)
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelCoversBldgElements</i>
 *         </li>
 *         <li>inverse attribute: <i>HasCoverings</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Voids - information, whether the building element
 *     includes openings, recesses or other voids
 *       <ul>
 *         <li>objectified relationship: <i>IfcRelVoidsElement</i>
 *         </li>
 *         <li>inverse attribute: <i>HasOpenings</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Projection - information, whether the building element
 *     has projections (such as a fascia)
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelProjectsElement</i>
 *         </li>
 *         <li>inverse attribute: <i>HasProjections</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Filling - information whether the building element is
 *     used to fill openings
 *       <ul>
 *         <li>objectified relationship: <i>IfcRelFillsElement</i>
 *         </li>
 *         <li>inverse attribute: <i>FillsVoids</i>
 *         </li>
 *       </ul>
 *     </li>
 *     <li>Structural member reference - information whether the
 *     building element is represented in a structural analysis
 *     model by a structural member
 *       <ul>
 *         <li>objectified relationship:
 *         <i>IfcRelConnectsStructuralElement</i>
 *         </li>
 *         <li>inverse attribute: <i>HasStructuralMember</i>
 *         </li>
 *       </ul>
 *     </li>
 *   </ol>
 *   <p>
 *     <u><b>Quantity Use Definition</b></u>:
 *   </p>
 *   <p>
 *     The quantities relating to the <i>IfcBuildingElement</i>
 *     are defined by the <i>IfcElementQuantity</i> and attached
 *     by the <i>IfcRelDefinesByProperties</i>. A detailed
 *     specification for individual quantities is introduced at
 *     the level of subtypes of <i>IfcBuildingElement</i>.
 *   </p>
 *   <p>
 *     <u><b>Geometry Use Definitions</b></u>
 *   </p>
 *   <p>
 *     The geometric representation of any
 *     <i>IfcBuildingElement</i> is given by the
 *     <i>IfcProductDefinitionShape</i> and
 *     <i>IfcLocalPlacement</i> allowing multiple geometric
 *     representations.
 *   </p>
 *   <p>
 *     <b>Local Placement</b>
 *   </p>
 *   <p>
 *     The local placement for any <i>IfcBuildingElement</i> is
 *     defined in its supertype <i>IfcProduct</i>. It is defined
 *     by the <i>IfcLocalPlacement</i>, which defines the local
 *     coordinate system that is referenced by all geometric
 *     representations. Further constraints are defined at the
 *     level of its subtypes.
 *   </p>
 *   <p>
 *     <b><i>Geometric Representations</i></b>
 *   </p>
 * <p>Any <i>IfcBuildingElement</i> can be represented by one
 *   or several geometric representations. A detailed
 *   specification is introduced at the level of subtypes. Only
 *   the general representation types 'BoundingBox',
 *   'SurfaceModel', 'Brep', and 'MappedRepresentation' are
 *   defined here.</p>
 *   <p>
 *     <b>Bounding Box Representation</b>
 *   </p>
 *   <p>
 *     Any <i>IfcBuildingElement</i> may be represented as a
 *     bounding box, which shows the maximum extend of the body
 *     within the coordinated system established by the
 *     <i>IfcLocalPlacement</i>. The bounding box representation
 *     is the simplest geometric representation available. The
 *     following attribute values for the
 *     <i>IfcShapeRepresentation</i> holding this geometric
 *     representation shall be used:
 *   </p>
 *   <ul>
 *     <li>
 *       <i>RepresentationIdentifier</i> : 'Box'
 *       <strike>Body</strike>
 *     </li>
 *     <li>
 *       <i>RepresentationType</i> : 'BoundingBox'
 *     </li>
 *   </ul>
 *   <table>
 *     <tbody>
 *       <tr valign="top">
 *         <td align="left" valign="top">
 *           <p>
 *             <font size="-1">The bounding box representation is
 *             given by an <i>IfcShapeRepresentation</i>, which
 *             includes a single item, an
 *             <i>IfcBoundingBox</i>.</font>
 *           </p>
 *         </td>
 *       </tr>
 *     </tbody>
 *   </table>
 *   <p>
 *     <b>SurfaceModel Representation</b>
 *   </p>
 *   <p>
 *     Any <i>IfcBuildingElement</i> (so far no further
 *     constraints are defined at the level of its subtypes) may
 *     be represented as a single or multiple surface models,
 *     based on either shell or face based models. The following
 *     attribute values for the <i>IfcShapeRepresentation</i>
 *     holding this geometric representation shall be used:
 *   </p>
 *   <ul>
 *     <li>
 *       <i>RepresentationIdentifier</i> : 'Body'
 *     </li>
 *     <li>
 *       <i>RepresentationType</i> : 'SurfaceModel'
 *     </li>
 *   </ul>
 *   <p>
 *     In some cases it may be useful to also expose a simple
 *     representation as a bounding box representation of the same
 *     complex shape.
 *   </p>
 *   <table>
 *     <tbody>
 *       <tr valign="top">
 *         <td align="left" valign="top">
 *           <p>
 *             <font size="-1">The surface model representation is
 *             given by an IfcShapeRepresentation, which includes
 *             a single item, which is either:</font>
 *           </p>
 *           <ul>
 *             <li>
 *               <font size="-1"><i>IfcShellBasedSurfaceModel</i>,
 *               or</font>
 *             </li>
 *             <li>
 *               <font size=
 *               "-1"><i>IfcFaceBasedSurfaceModel</i>.</font>
 *             </li>
 *           </ul>
 *         </td>
 *       </tr>
 *     </tbody>
 *   </table>
 *   <p>
 *     <b>Brep Representation</b>
 *   </p>
 *   <p>
 *     Any <i>IfcBuildingElement</i> (so far no further
 *     constraints are defined at the level of its subtypes) may
 *     be represented as a single or multiple Boundary
 *     Representation elements (which are restricted to faceted
 *     Brep with or without voids). The Brep representation allows
 *     for the representation of complex element shape. The
 *     following attribute values for the
 *     <i>IfcShapeRepresentation</i> holding this geometric
 *     representation shall be used:
 *   </p>
 *   <ul>
 *     <li>
 *       <i>RepresentationIdentifier</i> : 'Body'
 *     </li>
 *     <li>
 *       <i>RepresentationType</i> : 'Brep'
 *     </li>
 *   </ul>
 *   <p>
 *     In some cases it may be useful to also expose a simple
 *     representation as a bounding box representation of the same
 *     complex shape.
 *   </p>
 *   <table cellpadding="2" cellspacing="2">
 *     <tbody>
 *       <tr valign="top">
 *         <td align="left" valign="top">
 *           TheBrep representation is given by an
 *           IfcShapeRepresentation, which includes one or more
 *           items, all of type IfcManifoldSolidBrep.
 *         </td>
 *       </tr>
 *     </tbody>
 *   </table>
 *   <p>
 *     <b>MappedRepresentation</b>
 *   </p>
 *   <p>
 *     Any <i>IfcBuildingElement</i> (so far no further
 *     constraints are defined at the level of its subtypes) may
 *     be represented using the MappedRepresentation. This shall
 *     be supported as it allows for reusing the geometry
 *     definition of a type at all occurrences of the same type.
 *     The following attribute values for the
 *     <i>IfcShapeRepresentation</i> holding this geometric
 *     representation shall be used:
 *   </p>
 *   <ul>
 *     <li>
 *       <i>RepresentationIdentifier</i> : 'Body'
 *     </li>
 *     <li>
 *       <i>RepresentationType</i> : 'MappedRepresentation'
 *     </li>
 *   </ul>
 *   <p>
 *     The same constraints, as given for the 'SurfaceModel' and
 *     the 'Brep' geometric representation, shall apply to the
 *     <i>MappedRepresentation</i> of the
 *     <i>IfcRepresentationMap</i>.
 *   </p>
 */
@ToString(callSuper = true)
public abstract class IfcBuildingElement extends IfcElement {
    /**
     * Creates a new IfcBuildingElement, using the provided globalId.
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
    public IfcBuildingElement(@NonNull IfcGloballyUniqueId globalId,
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

    /**
     * Creates a new IfcBuildingElement and generates a pseudo random globalId.
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
     * @throws NullPointerException     If ownerHistory is null.
     * @throws IllegalArgumentException If representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcBuildingElement(@NonNull IfcOwnerHistory ownerHistory,
                              IfcLabel name,
                              IfcText description,
                              IfcLabel objectType,
                              IfcObjectPlacement objectPlacement,
                              IfcProductRepresentation representation,
                              IfcIdentifier tag) {
        super(ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation,
              tag);
    }
}
