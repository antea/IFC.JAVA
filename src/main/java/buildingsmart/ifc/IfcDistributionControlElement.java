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
 * The distribution element <i>IfcDistributionControlElement</i> defines
 * occurrence elements of a building automation control system that are used to
 * impart control over elements of a distribution system.</p>
 * <p>This class defines elements of a building automation control system.
 * These are typically used to control distribution system elements to maintain
 * emperature, humidity, pressure, low, power, lighting levels, etc., through
 * the modulation, staging or equencing f mechanical or electrical devices. The
 * three general functional ategories of ontrolElements as defined for this
 * release of the IFC model are as ollows:</p>
 * <ul>
 * 	<li>Impart control over flow control elements
 * 	(<i>IfcFlowController</i>) in a
 *        distribution system such as dampers, valves, relays, etc.,
 *        typically through
 *        the use of actuation (<i>IfcActuator</i>: See <i>IfcHvacDomain</i>
 *        schema).</li>
 *        <li>Sensing elements (<i>IfcSensor</i>: See <i>IfcHvacDomain</i>
 *        schema) that measure changes
 *        in the controlled variable (e.g., temperature, humidity, pressure,
 *        flow,
 *        etc.).</li>
 * 	<li>Controllers (<i>IfcController</i>: See <i>IfcHvacDomain</i>
 * 	schema) typically classified
 *        according to the control action they seek to perform and generally
 *        responsible
 *        for making decisions about the elements under control.</li>
 * </ul>
 * <p>Since this class and its subtypes typically relate to many different
 * distribution
 *    flow elements (<i>IfcDistributionFlowElement</i>), the objectified
 *    relationship
 *    <i>IfcRelFlowControlElements</i> has been provided to relate control
 *    and flow elements
 *    as required.</p>
 * <p><u>Geometry Use Definitions</u></p>
 * <p>The geometric representation of <i>IfcControlElement</i> is given
 *    by the <i>IfcProductDefinitionShape</i>, allowing multiple geometric
 *    representations. Included are:</p>
 * <p><b>Local Placement</b></p>
 * <p>The local placement is defined in the supertype <i>IfcProduct</i>. It
 *    is defined by a subtype of <i>IfcObjectPlacement</i> which can define
 *    an absolute placement, relative placement, or grid reference, with each
 *    defining the local coordinate system referenced by all geometric
 *    representations. The PlacementRelTo relationship of
 *    <i>IfcLocalPlacement</i>,
 *    if given, shall point to the same <i>IfcSpatialStructureElement</i>
 *    which
 *    is used in the ContainedInStructure inverse attribute, or to a referenced
 *    spatial structure element at a higher level. If the relative
 *    placement is not used, the absolute placement is defined
 * within the world coordinate system.</p>
 * <p><u>Informal propositions for local placement</u>: </p>
 * <ol>
 * 	<li>If the LocalPlacement is specified,
 *        then all aggregated components should use this placement as
 *   	their relative placement.</li>
 * </ol>
 * <p><b>Standard Geometric Representation</b></p>
 * <p>Currently, the use of attribute driven geometry for this class
 *    is not supported.  The standard geometric representation is
 *    defined using explicit geometry.</p>
 * <p><b>B-Rep Representation </b></p>
 * <p>The faceted B-Rep capabilities (with or without voids) shall be
 *    supported for B-Rep representation.</p>
 */
@ToString(callSuper = true)
public class IfcDistributionControlElement extends IfcDistributionElement {
    @Attribute(8)
    private final IfcIdentifier controlElementId;

    /**
     * Creates a new IfcDistributionControlElement, using the provided globalId.
     *
     * @param globalId         Assignment of a globally unique identifier within
     *                         the entire software world.
     * @param ownerHistory     Assignment of the information about the current
     *                         ownership of that object, including owning actor,
     *                         application, local identification and information
     *                         captured about the recent changes of the object,
     *                         NOTE: only the last modification in stored.
     * @param name             Optional name for use by the participating
     *                         software systems or users. For some subtypes of
     *                         IfcRoot the insertion of the Name attribute may
     *                         be required . This would be enforced by a where
     *                         rule.
     * @param description      Optional description, provided for exchanging
     *                         informative comments.
     * @param objectType       The type denotes a particular type that indicates
     *                         the object further. The use has to be established
     *                         at the level of instantiable subtypes. In
     *                         particular it holds the user defined type, if the
     *                         enumeration of the attribute PredefinedType is
     *                         set to USERDEFINED.
     * @param objectPlacement  Placement of the product in space, the placement
     *                         can either be absolute (relative to the world
     *                         coordinate system), relative (relative to the
     *                         object placement of another product), or
     *                         constraint (e.g. relative to grid axes). It is
     *                         determined by the various subtypes of
     *                         IfcObjectPlacement, which includes the axis
     *                         placement information to determine the
     *                         transformation for the object coordinate system.
     * @param representation   Reference to the representations of the product,
     *                         being either a representation
     *                         (IfcProductRepresentation)
     *                         or as a special case a shape representations
     *                         (IfcProductDefinitionShape). The product
     *                         definition shape provides for multiple geometric
     *                         representations of the shape property of the
     *                         object within the same object coordinate system,
     *                         defined by the object placement.
     * @param tag              The tag (or label) identifier at the particular
     *                         instance of a product, e.g. the serial number, or
     *                         the position number. It is the identifier at the
     *                         occurrence level.
     * @param controlElementId The ControlElement Point Identification assigned
     *                         to this control element by the Building
     *                         Automation System.
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    @Builder
    public IfcDistributionControlElement(@NonNull IfcGloballyUniqueId globalId,
                                         @NonNull IfcOwnerHistory ownerHistory,
                                         IfcLabel name,
                                         IfcText description,
                                         IfcLabel objectType,
                                         IfcObjectPlacement objectPlacement,
                                         IfcProductRepresentation representation,
                                         IfcIdentifier tag,
                                         IfcIdentifier controlElementId) {
        super(globalId,
              ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation,
              tag);
        this.controlElementId = controlElementId;
    }
}
