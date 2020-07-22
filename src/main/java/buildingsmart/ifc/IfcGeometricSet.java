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
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The IfcGeometricSet is used for the exchange of shape representations
 * consisting of (2D or 3D) points, curves, and/or surfaces, which do not have a
 * topological structure (such as connected face sets or shells) and are not
 * solid models (such as swept solids, CSG or Brep).
 */
public class IfcGeometricSet extends IfcGeometricRepresentationItem {
    @Attribute
    @Order(0)
    private final Set<IfcGeometricSetSelect> elements;
    //private int dim;

    /**
     * @param elements The geometric elements which make up the geometric set,
     *                 these may be points, curves or surfaces; but are required
     *                 to be of the same coordinate space dimensionality.
     * @throws IllegalArgumentException If elements is null or has size lower
     *                                  than 1 or if elements contains objects
     *                                  with different dimensions.
     */
    public IfcGeometricSet(@NonNull Set<IfcGeometricSetSelect> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements cannot be null");
        }
        if (elements.size() < 1) {
            throw new IllegalArgumentException(
                    "size of elements must be at least 1");
        }
        IfcDimensionCount dim = elements.iterator().next().getDim();
        for (IfcGeometricSetSelect element : elements) {
            if (!element.getDim().equals(dim)) {
                throw new IllegalArgumentException(
                        "dimensions of all elements in the Set must be the " +
                                "same");
            }
        }
        this.elements = elements;
    }

    /**
     * @param elements The geometric elements which make up the geometric set,
     *                 these may be points, curves or surfaces; but are required
     *                 to be of the same coordinate space dimensionality.
     * @throws IllegalArgumentException If elements is null or has size lower
     *                                  than 1 or if elements contains objects
     *                                  with different dimensions.
     */
    public IfcGeometricSet(@NonNull IfcGeometricSetSelect... elements) {
        this(new HashSet<>(Arrays.asList(elements)));
    }

    public Set<IfcGeometricSetSelect> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcGeometricSet that = (IfcGeometricSet) o;
        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
