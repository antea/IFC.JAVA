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
 * The distribution flow element <i>IfcFlowFitting</i> defines the occurrence of
 * a junction or transition in a flow distribution system (e.g., elbow, tee,
 * etc.). Its type is defined by
 * <i>IfcFlowFittingType</i> or
 * its subtypes.</p>
 * <p><u><b>Property Set Use Definition</b></u>:</p>
 * p>The property sets relating to this entity are defined by the
 * i>IfcPropertySet</i> and attached by the i>IfcRelDefinesByProperties</i>
 * elationship. It is accessible by the inverse <i>IsDefinedBy</i> elationship.
 * he following property set definitions specific to this entity are part f this
 * IFC release: /p>
 *    <ul>
 * <li><a href="../../psd/IfcSharedBldgServiceElements/Pset_DistributionFlowElementCommon.xml"
 *     target=SOURCE>Pset_DistributionFlowElementCommon</a>: common property
 *     set for
 *     distribution flow element occurrences </li>
 *       <li><a href="../../psd/IfcSharedBldgServiceElements/Pset_FlowFittingDuctFitting.xml"
 *         target="SOURCE">Pset_FlowFittingDuctFitting</a>: property set for
 *         duct fitting occurrences
 *       </li>
 *       <li><a href="../../psd/IfcSharedBldgServiceElements/Pset_FlowFittingPipeFitting.xml"
 *         target="SOURCE">Pset_FlowFittingPipeFitting</a>: property set for
 *         pipe fitting occurrences
 *       </li>
 *    </ul>
 * <p><u>Geometry Use Definitions</u></p>
 * <p>The geometric representation of <i>IfcFlowFitting</i> is given
 *    by the <i>IfcProductDefinitionShape</i>, allowing multiple geometric
 *    representations. Included are:</p>
 * <p><b>Local Placement</b></p>
 * <p>The use of local placement is defined at the supertype
 *    <i>IfcDistributionFlowElement</i>.</p>
 * <p><b>Standard Geometric Representation</b></p>
 * <p>The use of Standard Geometric Representations is defined at the
 * supertype
 *    <i>IfcDistributionFlowElement</i>.</p>
 */
@ToString(callSuper = true)
public class IfcFlowFitting extends IfcDistributionFlowElement {
    /**
     * Creates a new IfcFlowFitting, using the provided globalId.
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
    @Builder(builderMethodName = "flowFittingBuilder")
    public IfcFlowFitting(@NonNull IfcGloballyUniqueId globalId,
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
