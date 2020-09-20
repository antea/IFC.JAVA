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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * <p>A
 * poly loop is a loop with straight edges bounding a planar region in space. A
 * poly loop is a loop of genus 1 where the loop is represented by an ordered
 * coplanar collection of points forming the vertices of the loop. The loop is
 * composed of straight line segments joining a point in the collection to the
 * succeeding point in the collection. The closing segment is from the last to
 * the first point in the collection.&nbsp;</p>
 * <p>The direction of the loop is in the direction of the line
 * segments. </p>
 * <blockquote>
 * <small>NOTE &nbsp;This entity
 * exists primarily to facilitate the efficient communication of faceted B-rep
 * models. </small>
 * </blockquote>
 * <p>A poly loop shall conform to the following topological
 * constraints:</p>
 * <dl>
 *   <dd>- the loop has the genus of one.</dd>
 *   <dd>- the following equation shall be satisfied
 *     <dl>
 *       <dd> <img src="figures/IfcPolyLoop-Math1.gif"
 *  height="26" width="138"></dd>
 *     </dl>
 *   </dd>
 * </dl>
 * <p>Informal propositions: </p>
 * <ol>
 *   <li>All the points in the polygon defining the poly loop shall
 * be coplanar.</li>
 *   <li>The first and the last <i>Polygon</i> shall be
 * different by value.</li>
 * </ol>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcPolyLoop extends IfcLoop {
    @Attribute(0)
    private final List<IfcCartesianPoint> polygon;

    /**
     * @param polygon List of points defining the loop. There are no repeated
     *                points in the list.
     * @throws NullPointerException     If {@code polygon} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code polygon} is
     *                                  smaller than 3, if {@code polygon}
     *                                  contains duplicate points, if points in
     *                                  {@code polygon} don't have the same
     *                                  dimensionality.
     */
    public IfcPolyLoop(@NonNull List<IfcCartesianPoint> polygon) {
        if (polygon.size() < 3) {
            throw new IllegalArgumentException(
                    "size of polygon must be at least 3");
        }
        if (polygon.stream().distinct().count() != polygon.size()) {
            throw new IllegalArgumentException(
                    "polygon cannot contain duplicate points");
        }
        IfcDimensionCount firstPointDim = polygon.get(0).getDim();
        if (polygon.stream()
                .anyMatch(point -> !point.getDim().equals(firstPointDim))) {
            throw new IllegalArgumentException(
                    "dimensionality of all points in polygon must be the same");
        }
        this.polygon = polygon;
    }

    /**
     * @param polygon List of points defining the loop. There are no repeated
     *                points in the list.
     * @throws NullPointerException     If {@code polygon} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code polygon} is
     *                                  smaller than 3, if {@code polygon}
     *                                  contains duplicate points, if points in
     *                                  {@code polygon} don't have the same
     *                                  dimensionality.
     */
    public IfcPolyLoop(@NonNull IfcCartesianPoint... polygon) {
        this(Arrays.asList(polygon));
    }
}
