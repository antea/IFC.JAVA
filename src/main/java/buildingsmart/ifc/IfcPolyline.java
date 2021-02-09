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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * An IfcPolyline is a bounded curve of n -1 linear segments, defined by a list
 * of n points, P0, P1 ... Pn-1.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcPolyline extends IfcBoundedCurve {
    @Attribute(0)
    private final List<IfcCartesianPoint> points;

    /**
     * @param points The points defining the polyline.
     * @throws NullPointerException     If {@code points} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code points} is smaller
     *                                  than 2, if all {@code points} don't have
     *                                  the same dimensionality.
     */
    public IfcPolyline(@NonNull List<IfcCartesianPoint> points) {
        if (points.size() < 2) {
            throw new IllegalArgumentException(
                    "size of points must be at least 2");
        }
        IfcDimensionCount firstPointDim = points.get(0).getDim();
        if (!points.stream()
                .allMatch(point -> point.getDim().equals(firstPointDim))) {
            throw new IllegalArgumentException(
                    "dimensionality of all points must be the same");
        }
        this.points = points;
    }

    /**
     * @param points The points defining the polyline.
     * @throws NullPointerException     If {@code points} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code points} is smaller
     *                                  than 2, if all {@code points} don't have
     *                                  the same dimensionality.
     */
    public IfcPolyline(@NonNull IfcCartesianPoint... points) {
        this(Arrays.asList(points));
    }

    /**
     * @return The space dimensionality of this IfcCurve.
     */
    @Override
    public IfcDimensionCount getDim() {
        return points.get(0).getDim();
    }
}
