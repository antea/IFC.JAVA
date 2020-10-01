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
 * <p>
 * The distribution element <i>IfcDistributionFlowElement</i> defines occurrence
 * elements of a distribution system that facilitate the distribution of energy
 * or matter, such as air, water or power.</p>
 * <blockquote>
 * <font size="-1">EXAMPLEs of distribution flow elements are
 * ducts, pipes, wires, fittings, equipment, etc.
 * </font>
 * </blockquote>
 * <p>The <i>IfcDistributionFlowElement</i> utilizes the following
 * capabilities mainly through inverse references to objectified
 * relationships:</p>
 * 	 <ul>
 * 		<li>Grouping - being part of a logical group of objects</li>
 * 		<li>Classification - assigned reference to an external
 * 		  classification</li>
 * 		<li>Documentation - assigned reference to an external
 * 		documentation</li>
 * 		<li>Type - reference to the product type information for the element
 * 		  occurrence</li>
 * 		<li>Properties - reference to all attached properties, including
 * 		  quantities</li>
 * 		<li>Cost control - reference to cost elements associated with this
 * 		  distribution element</li>
 * 		<li>Work processes - reference to work tasks, in which this
 * 		distribution
 * 		  element participates</li>
 * 		<li>Aggregation - aggregated together with other elements to form an
 * 		  aggregate </li>
 * 		<li>Connection - connectivity to other elements, including the
 * 		definition
 * 		  of the connection or joint</li>
 * 	 </ul>
 *     <p>The <i>IfcDistributionFlowElement</i> defines the occurrence of a
 *     distribution element within the spatial context of a project. The
 *     parameters that define the type of the distribution element and/or its
 *     shape are defined by the <i>IfcDistributionFlowElementType</i> subtypes,
 *     which is related by the inverse relationship IsDefinedBy pointing to
 *     <i>IfcRelDefinesByType</i>.
 * <p><b><u>Property Set Use Definition</u></b>:</p>
 * <p>The property sets relating to this entity are defined by the
 * <i>IfcPropertySet</i>
 * and attached by the <i>IfcRelDefinesByProperties</i> relationship. It is
 * accessible by the inverse <i>IsDefinedBy</i> relationship. The following
 * property set definitions specific to this entity are part of this IFC
 * release: </p>
 * <ul>
 *  <li><a href="../../psd/IfcSharedBldgServiceElements/Pset_DistributionFlowElementCommon.xml"
 *      target=SOURCE>Pset_DistributionFlowElementCommon</a>: common property
 *      set for
 *      distribution flow element occurrences </li>
 * </ul>
 * 	<p><b><u>Containment Use Definition</u></b></p>
 * 	<p>The <i>IfcDistributionFlowElement</i>, like any subtype of
 * 	<i>IfcBuildingElement</i>, may
 * participate in two different containment relationships. The first (and in
 * most implementation
 * scenarios mandatory) relationship is the hierachical spatial containment,
 * the second (optional)
 * relationship is the aggregation within an element assembly.</p>
 * <ul>
 * <li><p>The <i>IfcDistributionFlowElement</i> is placed within the project
 * spatial hierarchy using the
 * objectified relationship <i>IfcRelContainedInSpatialStructure</i>,
 * referring to it by its inverse
 * attribute <i>SELF\IfcElement.ContainedInStructure</i>. Subtypes of
 * <i>IfcSpatialStructureElement</i>
 * are valid spatial containers, with <i>IfcBuildingStorey</i> being the
 * default container.</li>
 * <li><p>The <i>IfcDistributionFlowElement</i> may be aggregated into an
 * element assembly using the
 * objectified relationship <i>IfcRelAggregates</i>, referring to it by its
 * inverse attribute
 * <i>SELF\IfcObjectDefinition.Decomposes</i>. Any subtype of
 * <i>IfcElement</i> can be an element
 * assembly, with <i>IfcElementAssembly</i> as a special focus subtype.</p>
 * <p>In this case it should not be additionally contained in the project
 * spatial hierarchy, i.e.
 * SELF\IfcElement.ContainedInStructure should be <i>NIL</i>.</p></li>
 * </ul>
 * 	<p><b><u>Geometry Use Definitions</u></b></p>
 * 	<p>The geometric representation of <i>IfcDistributionFlowElement</i> is
 * 	given
 *     by the <i>IfcProductDefinitionShape</i>, allowing multiple geometric
 *     representations. If an <i>IfcRepresentationMap</i> is defined for the
 *     <i>IfcFlowElementType</i> or one of its subtypes, then the
 *     <i>IfcDistributionFlowELement</i>
 * utilizes it
 *     through the <i>IfcMappedItem</i>. Included geometric representations
 *     are:</p>
 * 	<p><b>Local Placement</b></p>
 * 	<p>The local placement is defined in the supertype <i>IfcProduct</i>. It
 *     is defined by the <i>IfcLocalPlacement</i> which can define
 *     an absolute placement, relative placement, or grid reference, with each
 *     defining the local coordinate system referenced by all geometric
 *     representations. If given, the PlacementRelTo relationship of
 *     <i>IfcLocalPlacement</i>,
 *     shall point to the referenced <i>IfcProduct</i>. If the relative
 *     placement is not used, the absolute placement is defined
 * 	within the world coordinate system.</p>
 * 	<p><u>Informal propositions for local placement</u>: </p>
 * 	<ol>
 * 		<li>If the LocalPlacement is specified,
 *         then all aggregated components should use this placement as
 * 	  	their relative placement.</li>
 * 	</ol>
 * 	<p><b>Geometric Representations</b></p>
 * 	<p>Currently, the use of 'SweptSolid', 'Clipping', 'Brep' and
 * 	'MappedRepresentation'
 * representations are supported. In addition, the general representation
 * types 'SurfaceModel' and
 * 'BoundingBox' are allowed. The geometry use definitions for 'BoundingBox',
 * 'SurfaceModel' and 'Brep'
 * are explained at <i>IfcBuildingElement</i>. The geometry use definitions
 * for 'SweptSolid' and
 * 'Clipping' are identical to those explained in detail at <i>IfcBeam</i>.<p>
 * <p><b>MappedRepresentation</b></p>
 * <p>In addition to the standard and advanced geometric representation of
 * <i>IfcFlowDistributionElement</i> that is defined using the SweptSolid or
 * Clipping geometry, the
 * MappedRepresentation shall be supported as it allows for reusing the
 * geometry definition of the flow
 * distribution element type at all occurrences of the same type. The
 * following attribute values for the
 * <i>IfcShapeRepresentation</i> holding this geometric representation shall
 * be used:</p>
 * <ul>
 * <li><i>RepresentationIdentifier</i>: 'Body'</li>
 * <li><i>RepresentationType</i>: 'MappedRepresentation'</li>
 * </ul>
 * <p>The same constraints as those given for the standard SweptSolid and the
 * advanced SweptSolid and
 * Clipping geometric representations shall apply to the MappedRepresentation
 * of the
 * <i>IfcRepresentationMap</i>.</p>
 */
@ToString(callSuper = true)
public class IfcDistributionFlowElement extends IfcDistributionElement {
    /**
     * Creates a new IfcDistributionFlowElement, using the provided globalId.
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
    public IfcDistributionFlowElement(@NonNull IfcGloballyUniqueId globalId,
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
