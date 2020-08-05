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

import buildingsmart.io.InverseRelationship;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Set;

/**
 * The <i>IfcShapeModel</i> represents the concept of a particular geometric
 * and/or topological representation of a product's shape or a product
 * component's shape within a representation context. This representation
 * context has to be a geometric representation context (with the exception of
 * topology representations without associated geometry). The two subtypes are
 * <i>IfcShapeRepresentation</i>
 * to cover the geometric models (or sets) that represent a shape, and
 * <i>IfcTopologyRepresentation</i>
 * to cover the conectivity of a product or product component. The topology may
 * or may not have geometry associated.</p> The <i>IfcShapeModel</i> can be a
 * shape representation (geometric and/or topologogical) of a product (via
 * <i>IfcProductDefinitionShape</i>),
 * or a shape representation (geometric and/or topologogical) &nbsp;of a
 * component of a product shape (via <i>IfcShapeAspect</i>).<br>
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class IfcShapeModel extends IfcRepresentation {
    @InverseRelationship
    private IfcShapeAspect ofShapeAspect;

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
    public IfcShapeModel(@NonNull IfcRepresentationContext contextOfItems,
                         IfcLabel representationIdentifier,
                         IfcLabel representationType,
                         @NonNull Set<IfcRepresentationItem> items) {
        super(contextOfItems,
              representationIdentifier,
              representationType,
              items);
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
    public IfcShapeModel(@NonNull IfcRepresentationContext contextOfItems,
                         IfcLabel representationIdentifier,
                         IfcLabel representationType,
                         @NonNull IfcRepresentationItem... items) {
        super(contextOfItems,
              representationIdentifier,
              representationType,
              items);
    }

    /**
     * @param ofShapeAspect Reference to the shape aspect, for which it is the
     *                      shape representation.
     * @throws IllegalStateException If ofProductRepresentation or
     *                               representationMap is already set (the
     *                               IfcShapeModel shall be used by an
     *                               IfcProductRepresentation, by an
     *                               IfcRepresentationMap or by an
     *                               IfcShapeAspect).
     */
    public void setOfShapeAspect(IfcShapeAspect ofShapeAspect) {
        if ((this.ofProductRepresentation != null ||
                this.representationMap != null) && ofShapeAspect != null) {
            throw new IllegalStateException(
                    "ofProductRepresentation or representationMap is already" +
                            " set!");
        }
        this.ofShapeAspect = ofShapeAspect;
    }

    /**
     * @param representationMap Use of the representation within an
     *                          IfcRepresentationMap. If used, this
     *                          IfcRepresentation may be assigned to many
     *                          representations as one of its Items using an
     *                          IfcMappedItem. Using IfcRepresentationMap is the
     *                          way to share one representation (often of type
     *                          IfcShapeRepresentation) by many products.
     * @throws IllegalStateException If ofProductRepresentation or ofShapeAspect
     *                               is already set (the IfcShapeModel shall be
     *                               used by an IfcProductRepresentation, by an
     *                               IfcRepresentationMap or by an
     *                               IfcShapeAspect).
     */
    @Override
    public void setRepresentationMap(IfcRepresentationMap representationMap) {
        if ((this.ofProductRepresentation != null ||
                this.ofShapeAspect != null) && representationMap != null) {
            throw new IllegalStateException(
                    "ofProductRepresentation or ofShapeAspect is already" +
                            " set!");
        }
        super.setRepresentationMap(representationMap);
    }

    /**
     * @param ofProductRepresentation Reference to the product shape, for which
     *                                it is the shape representation.
     * @throws IllegalStateException If ofShapeAspect or representationMap is
     *                               already set (the IfcShapeModel shall be
     *                               used by an IfcProductRepresentation, by an
     *                               IfcRepresentationMap or by an
     *                               IfcShapeAspect).
     */
    @Override
    public void setOfProductRepresentation(IfcProductRepresentation ofProductRepresentation) {
        if ((this.ofShapeAspect != null || this.representationMap != null) &&
                ofProductRepresentation != null) {
            throw new IllegalStateException(
                    "ofShapeAspect or representationMap is already" + " set!");
        }
        super.setOfProductRepresentation(ofProductRepresentation);
    }
}
