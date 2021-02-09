/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of ifc-java.
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
 * The <i>IfcProxy</i> is intended to be a kind of a container for wrapping
 * objects which are defined by associated properties, which may or may not have
 * a geometric representation and placement in space. A proxy may have a
 * semantic meaning, defined by the <i>Name</i> attribute, and property
 * definitions, attached through the property assignment relationship, which
 * definition may be outside of the definitions given by the current release of
 * IFC.</p>
 * <p>The <i>ProxyType</i> may give an indication to
 * which high level semantic breakdown of object the semantic definition of the
 * proxy relates to. the <i>Tag</i> attribute may be used to assign a human or
 * system interpretable identifier (such as a serial number or bar code).
 */
@ToString(callSuper = true)
public class IfcProxy extends IfcProduct {
    @Attribute(7)
    private final IfcObjectTypeEnum proxyType;
    @Attribute(8)
    private final IfcLabel tag;

    /**
     * Creates a new IfcProxy.
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
     * @param proxyType       High level (and only) semantic meaning attached to
     *                        the IfcProxy, defining the basic construct type
     *                        behind the Proxy, e.g. Product or Process.
     * @param tag             The tag (or label) identifier at the particular
     *                        instance of a product, e.g. the serial number, or
     *                        the position number. It is the identifier at the
     *                        occurrence level.
     * @throws NullPointerException     If globalId, ownerHistory, name or
     *                                  proxyType are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    @Builder
    public IfcProxy(@NonNull IfcGloballyUniqueId globalId,
                    @NonNull IfcOwnerHistory ownerHistory,
                    @NonNull IfcLabel name,
                    IfcText description,
                    IfcLabel objectType,
                    IfcObjectPlacement objectPlacement,
                    IfcProductRepresentation representation,
                    @NonNull IfcObjectTypeEnum proxyType,
                    IfcLabel tag) {
        super(globalId,
              ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation);
        this.proxyType = proxyType;
        this.tag = tag;
    }
}

