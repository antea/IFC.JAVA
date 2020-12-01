/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2020 Antea S.r.l.
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Any object, or any aid to define, organize and annotate an object, that
 * relates to a geometric or spatial context. Subtypes of <i>IfcProduct</i>
 * usually hold a shape representation and a local placement within the project
 * structure. </p>
 * <p>This includes manufactured, supplied or created objects
 * (referred to as elements) for incorporation into an AEC/FM project. This also
 * includes objects that are created indirectly by other products, as spaces are
 * defined by bounding elements. Products can be designated for permanent use or
 * temporary use, an example for the latter is formwork. Products are defined by
 * their properties and representations.</p>
 * <p>In addition to physical products (covered by the subtype
 * <i>IfcElement</i>)
 * and spatial items (covered by the subtype <i>IfcSpatialStructureElement</i>)
 * the <i>IfcProduct</i> also includes non-physical items, that relate to a
 * geometric or spatial contexts, such as grid, port, annotation, structural
 * actions, etc.</p>
 * <p><b>Use Definition</b></p>
 * <p>Any instance of <i>IfcProduct</i> defines a
 * particular occurrence of a product, the common type information, that relates
 * to many similar (or identical) occurrences of <i>IfcProduct</i>, is handled
 * by the <i>IfcTypeProduct</i> (and its subtypes), assigned to one or many
 * occurrences of <i>IfcProduct</i> by using the objectified relationship
 * <i>IfcRelDefinesByType</i>. The <i>IfcTypeProduct</i> may provide, in
 * addition to common properties, also a common geometric representation for all
 * occurrences.</p>
 * <p>An <i>IfcProduct</i> occurs at a specific
 * location in space if it has a geometric representation assigned. It can be
 * placed relatively to other products, but ultimately relative to the world
 * coordinate system defined for this project.</p>
 * <p>The inherited <i>ObjectType</i> attribute can be
 * used to designate a particular type of the product instance. If subtypes of
 * <i>IfcProduct</i> have a <i>PredefinedType</i> defined, the
 * <i>ObjectType</i>
 * is used to provide the user defined, particular type of the product instance,
 * if the
 * <i>PredefinedType</i>
 * is set to <font size="-1">USERDEFINED</font>.</p>
 */
@ToString(callSuper = true)
public abstract class IfcProduct extends IfcObject {
    @Attribute(5)
    private final IfcObjectPlacement objectPlacement;
    @Getter(AccessLevel.PROTECTED)
    @Attribute(6)
    private final IfcProductRepresentation representation;

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
     *                        required. This would be enforced by a where rule.
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
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcProduct(@NonNull IfcGloballyUniqueId globalId,
                      @NonNull IfcOwnerHistory ownerHistory,
                      IfcLabel name,
                      IfcText description,
                      IfcLabel objectType,
                      IfcObjectPlacement objectPlacement,
                      IfcProductRepresentation representation) {
        super(globalId, ownerHistory, name, description, objectType);
        if (!(representation != null && objectPlacement != null ||
                representation != null &&
                        !(representation instanceof IfcProductDefinitionShape) ||
                representation == null)) {
            throw new IllegalArgumentException(
                    "If representation is not null, then objectPlacement must" +
                            " be not null too, except when representation is " +
                            "not of type IfcProductDefinitionShape");
        }
        this.objectPlacement = objectPlacement;
        this.representation = representation;
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
     *                        required. This would be enforced by a where rule.
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
     * @throws NullPointerException     If ownerHistory is null.
     * @throws IllegalArgumentException If representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcProduct(@NonNull IfcOwnerHistory ownerHistory,
                      IfcLabel name,
                      IfcText description,
                      IfcLabel objectType,
                      IfcObjectPlacement objectPlacement,
                      IfcProductRepresentation representation) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             objectType,
             objectPlacement,
             representation);
    }
}
