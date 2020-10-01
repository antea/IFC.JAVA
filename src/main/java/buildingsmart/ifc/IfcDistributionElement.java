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
 * <p>
 * Generalization of all elements that participate in a distribution system.
 * Typical examples
 * <i>IfcDistributionElement</i> are (among others):
 * >
 *  <ul>
 *    <li>building service elements within a heating systems
 *    </li>
 *    <li>building service elements within a cooling system
 *    </li>
 *    <li>building service elements within a ventilation system
 *    </li>
 *    <li>sanitary elements
 *    </li>
 *    <li>electrical elements
 *    </li>
 *    <li>elements within a communication network
 *    </li>
 *  </ul>
 *  <p>
 *    The <i>IfcDistributionElement</i> is further specialized in
 *    the IFC model. Direct instantiation of
 *    <i>IfcDistributionElement</i> without an assigned subtype of
 *    <i>IfcDistributionElementType</i> provides the meaning of
 *    an distribution element proxy.
 *  </p>
 *  <p>
 *    <b><u>Type Use Definition</u></b>
 *  </p>
 *  <p>
 *    The <i>IfcDistributionElement</i> defines the occurrence of
 *    any HVAC, electrical, sanitary or other element within a
 *    distribution system. Common information about distribution
 *    element types (or styles) is handled by subtypes of
 *    <i>IfcDistributionElementType</i>. The
 *    <i>IfcDistributionElementType</i> (if present) may
 *    establish the common type name, usage (or predefined) type,
 *    common material, common set of properties and common shape
 *    representations (using <i>IfcRepresentationMap</i>). The
 *    <i>IfcDistributionElementType</i> is attached using the
 *    <i>IfcRelDefinedByType.RelatingType</i> objectified
 *    relationship and is accessible by the inverse
 *    <i>IsDefinedBy</i> attribute.
 *  </p>
 *  <p>
 *    The assignment of types to distribution element occurrences
 *    is vital for providing the additional meaning, or ontology,
 *    of the distribution element. Many specialized type are
 *    defined in other schemas of the IFC specification.
 *  </p>
 *  <p>
 *    <b><u>Quantity Use Definition</u></b>
 *  </p>
 *  <p>
 *    The quantities relating to the
 *    <i>IfcDistributionElement</i> are defined by the
 *    <i>IfcElementQuantity</i> and attached by the
 *    <i>IfcRelDefinesByProperties</i>. A detailed specification
 *    for individual quantities is introduced at the level of
 *    subtypes of <i>IfcDistributionElement</i>.
 *  </p>
 *  <p>
 *    <b><u>Geometry Use Definitions</u></b>
 *  </p>
 *  <p>
 *    The geometric representation of
 *    <i>IfcDistributionElement</i> is given by the
 *    <i>IfcProductDefinitionShape</i>, allowing multiple
 *    geometric representation.
 *  </p>
 *  <p>
 *    <b>Local Placement</b>
 *  </p>
 *  <p>
 *    The local placement for <i>IfcDistributionElement</i> is
 *    defined in its supertype <i>IfcProduct</i>. It is defined
 *    by the <i>IfcLocalPlacement</i>, which defines the local
 *    coordinate system that is referenced by all geometric
 *    representations.
 *  </p>
 *  <ul>
 *    <li>The <i>PlacementRelTo</i> relationship of
 *    <i>IfcLocalPlacement</i> shall point (if given) to the
 *    local placement of the same
 *    <i>IfcSpatialStructureElement</i> , which is used in the
 *    <i>ContainedInStructure</i> inverse attribute, or to a
 *    spatial structure element at a higher level, referenced by
 *    that.
 *    </li>
 *    <li>If the relative placement is not used, the absolute
 *    placement is defined within the world coordinate system.
 *    </li>
 *  </ul>
 *  <p>
 *    <b>Geometric Representations</b>
 *  </p>
 *  <p>
 *    The geometric representation of
 *    <i>IfcDistributionElement</i> is defined using different
 *    geometric representation types for the various subtypes.
 *    Only general recommendations are given at the level of the
 *    supertype, further constraints are defined at the level of
 *    its subtypes.
 *  </p>
 *  <ul>
 *    <li>all occurrences of <i>IfcDistributionElement</i> (and
 *    its subtypes) should (whenever possible) share a
 *    representation map established by the assigned type. The
 *    geometric representation of the occurrence is then an
 *    <i>IfcMappedItem</i>. The <i>IfcShapeRepresentation</i>
 *    has:
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'Body'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'MappedRepresentation'
 *        </li>
 *      </ul>
 *    </li>
 *  </ul>
 *  <p>
 *    The shared geometric representation of the distribution
 *    element type (or in some cases of the distribution element)
 *    should follow (if applicable) the the following guidelines:
 *  </p>
 *  <ul>
 *    <li>all fixtures (all non distribution flow elements, i.e.
 *    everything which is not a duct, a pipe, a cable, or a cable carrier)
 * should be defined by an b-rep geometry. This includes also the
 * complex flow fitting elements (e.g. Y branch or T branch) or
 * distribution flow elements with size changes (e.g. reducer).
 * The <i>IfcShapeRepresentation</i> has:
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'Body'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'Brep'<br>
 *          <br>
 *        </li>
 *      </ul>
 *  	</li>
 *    <li>if the geometric model consistency of a b-rep shape
 * representation can not be guaranteed (arcwise connected volume
 * bounded by faces, each being connected, oriented, finite, closed
 * 2-manifold), a surface representation based on open shells should
 * be used. The <i>IfcShapeRepresentation</i> then has:
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'Body'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'SurfaceModel'<br>
 *          <br>
 *        </li>
 *      </ul>
 *    </li>
 *    <li>all "simple" distribution flow elements (general ducts
 *    and pipes) are defined by sweep geometry. The
 *    <i>IfcShapeRepresentation</i> has:
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'Body'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'SweptSolid'<br>
 *          <br>
 *        </li>
 *      </ul>
 *    </li>
 *    <li>an additional representation type for all "simple"
 *    distribution flow elements (general ducts and pipes) is the
 *    ability to have a simple line based representation. The <i>
 *    IfcShapeRepresentation</i> has:
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'FootPrint'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'GeometricCurveSet'<br>
 *          <br>
 *        </li>
 *      </ul>
 *    </li>
 *    <li>if only the analytical shape is required for which the
 *    exact interpolation between the cross sections is not
 *    required, a sectioned spine can be used.
 *      <ul>
 *        <li>
 *          <i>RepresentationIdentifier</i> : 'Body'
 *        </li>
 *        <li>
 *          <i>RepresentationType</i> : 'SectionedSpine'<br>
 *        </li>
 *      </ul>
 *    </li>
 *  </ul>
 */
@ToString(callSuper = true)
public class IfcDistributionElement extends IfcElement {
    /**
     * Creates a new IfcDistributionElement, using the provided globalId.
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
    public IfcDistributionElement(@NonNull IfcGloballyUniqueId globalId,
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
