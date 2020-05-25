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
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The <i>IfcStyledItem</i> holds presentation style information for products,
 * either explicitly for an
 * <i>IfcGeometricRepresentationItem</i>
 * being part of an <i>IfcShapeRepresentation</i> assigned to a product, or by
 * assigning presentation information to <i>IfcMaterial</i> being assigned as
 * other representation for a product.</p>
 */
public class IfcStyledItem extends IfcRepresentationItem {
    @Attribute
    @Order(value = 0)
    private final IfcRepresentationItem item;
    @Attribute
    @Order(value = 1)
    private final Set<IfcPresentationStyleAssignment> styles;
    @Attribute
    @Order(value = 2)
    private final IfcLabel name;

    /**
     * Creates a new IfcStyledItem and assigns it to item.styledByItem.
     *
     * @param item   A geometric representation item to which the style is
     *               assigned.
     * @param styles Representation style assignments which are assigned to an
     *               item. NOTE: In current IFC release only one presentation
     *               style assignment shall be assigned.
     * @param name   The word, or group of words, by which the styled item is
     *               referred to.
     * @throws IllegalArgumentException If styles is null or if its size is not
     *                                  1; if item is of type IfcStyledItem.
     */
    public IfcStyledItem(IfcRepresentationItem item,
                         @NotNull Set<IfcPresentationStyleAssignment> styles,
                         IfcLabel name) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        if (styles.size() != 1) {
            throw new IllegalArgumentException("size of styles must be 1");
        }
        if (item instanceof IfcStyledItem) {
            throw new IllegalArgumentException(
                    "item cannot be of type IfcStyledItem");
        }
        this.item = item;
        item.setStyledByItem(this);
        this.styles = styles;
        this.name = name;
    }

    /**
     * Creates a new IfcStyledItem and assigns it to item.styledByItem.
     *
     * @param item   A geometric representation item to which the style is
     *               assigned.
     * @param styles Representation style assignments which are assigned to an
     *               item. NOTE: In current IFC release only one presentation
     *               style assignment shall be assigned.
     * @param name   The word, or group of words, by which the styled item is
     *               referred to.
     * @throws IllegalArgumentException If styles is null; if item is of type
     *                                  IfcStyledItem.
     */
    public IfcStyledItem(IfcRepresentationItem item,
                         @NotNull IfcPresentationStyleAssignment styles,
                         IfcLabel name) {
        this(item, new CopyOnWriteArraySet<>(Collections.singletonList(styles)),
                name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        IfcStyledItem that = (IfcStyledItem) o;
        return Objects.equals(item, that.item) && styles.equals(that.styles) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), item, styles, name);
    }
}
