/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2022 Antea S.r.l.
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
import buildingsmart.io.Entity;
import buildingsmart.io.InverseRelationship;
import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A representation is one or more representation items that are related in a
 * specified representation context as the representation of some concept.
 */
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class IfcRepresentation extends Entity {
    @Attribute(0)
    private final IfcRepresentationContext contextOfItems;
    @Attribute(1)
    private final IfcLabel representationIdentifier;
    @Attribute(2)
    private final IfcLabel representationType;
    @Attribute(3)
    private final Set<IfcRepresentationItem> items;

    // The following 2 fields are needed to allow serialization of duplicate IfcRepresentations, otherwise output IFC
    // files might not be compliant with the specification.
    private static int idCounter = 0;
    @EqualsAndHashCode.Include
    private final int id = idCounter++;
    @ToString.Exclude
    @InverseRelationship
    @Setter(AccessLevel.PROTECTED)
    protected IfcRepresentationMap representationMap;
    @ToString.Exclude
    @InverseRelationship
    @Setter(AccessLevel.PROTECTED)
    protected IfcProductRepresentation ofProductRepresentation;

    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws NullPointerException     If contextOfItems or items are null.
     * @throws IllegalArgumentException If the size of items is lower than 1.
     */
    public IfcRepresentation(@NonNull IfcRepresentationContext contextOfItems,
                             IfcLabel representationIdentifier,
                             IfcLabel representationType,
                             @NonNull Set<IfcRepresentationItem> items) {
        if (items.size() < 1) {
            throw new IllegalArgumentException(
                    "size of items must be at least 1");
        }
        this.contextOfItems = contextOfItems;
        this.representationIdentifier = representationIdentifier;
        this.representationType = representationType;
        this.items = items;
    }

    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws NullPointerException     If contextOfItems or items are null.
     * @throws IllegalArgumentException If the size of items is lower than 1.
     */
    public IfcRepresentation(@NonNull IfcRepresentationContext contextOfItems,
                             IfcLabel representationIdentifier,
                             IfcLabel representationType,
                             @NonNull IfcRepresentationItem... items) {
        this(contextOfItems,
             representationIdentifier,
             representationType,
             new HashSet<>(Arrays.asList(items)));
    }
}
