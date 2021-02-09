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
import java.util.HashSet;
import java.util.Set;

/**
 * <P><U>Definition from ISO/CD 10303-42:1992</U>: A face is a topological
 * entity of dimensionality 2 corresponding to the intuitive notion of a piece
 * of surface bounded by loops. Its domain, if present, is an oriented,
 * connected, finite 2-manifold in <I>R<SUP>m</SUP></I>. A face domain shall not
 * have handles but it may have holes, each hole bounded by a loop. The domain
 * of the underlying geometry of the face, if present, does not contain its
 * bounds, and 0 &lt; &Xi; &lt; &infin;.</P>
 * <P>A face is represented by its bounding loops, which are defined as face
 * bounds. A face has a topological normal n and the tangent to a loop is t. For
 * a loop bounding a face with defined geometry, the cross product n x t points
 * toward the interior of the face. That is, each loop runs counter-clockwise
 * around the face when viewed from above, if we consider the normal n to point
 * up. With each loop is associated a BOOLEAN flag to signify whether the loop
 * direction is oriented with respect to the face normal (TRUE) or should be
 * reversed (FALSE). </P>
 * <P>A face shall have at least one bound, and the loops shall not
 * intersect. One loop is optionally distinguished as the outer loop of the
 * face. If so, it establishes a preferred way of embedding the face domain in
 * the plane, in which the other bounding loops of the face are inside the outer
 * bound. Because the face domain is arcwise connected, no inner loop will
 * contain any other loop. This is true regardless of which embedding in the
 * plane is chosen.
 * </P>
 * <P>The edges and vertices referenced by the loops of a face form a graph,
 * of which the individual loops are the connected components. </P>
 * <P><U>Informal propositions</U>:</P>
 * <OL>
 * <LI>No edge shall be referenced by the face more than twice. </LI>
 * <LI>Distinct face bounds of the face shall have no common vertices.
 * </LI>
 * <LI>If geometry is present, distinct loops of the same face shall not
 * intersect. </LI>
 * <LI>The face shall satisfy the Euler Equation: (number of vertices) -
 * (number of edges) - (number of loops) + (sum of genus for loops) = 0 .</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcFace extends IfcTopologicalRepresentationItem {
    @Attribute(0)
    private final Set<IfcFaceBound> bounds;

    /**
     * @param bounds Boundaries of the face.
     * @throws NullPointerException     If {@code bounds} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code bounds} is smaller
     *                                  than 1, if {@code bounds} contains more
     *                                  than one instance of IfcFaceOuterBound.
     */
    public IfcFace(@NonNull Set<IfcFaceBound> bounds) {
        if (bounds.size() < 1) {
            throw new IllegalArgumentException(
                    "size of bounds must be at least 1");
        }
        if (bounds.stream().filter(bound -> bound instanceof IfcFaceOuterBound)
                .count() > 1) {
            throw new IllegalArgumentException(
                    "at most one of the bounds can be of type " +
                            "IfcFaceOuterBound");
        }
        this.bounds = bounds;
    }

    /**
     * @param bounds Boundaries of the face.
     * @throws NullPointerException     If {@code bounds} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code bounds} is smaller
     *                                  than 1, if {@code bounds} contains more
     *                                  than one instance of IfcFaceOuterBound.
     */
    public IfcFace(@NonNull IfcFaceBound... bounds) {
        this(new HashSet<>(Arrays.asList(bounds)));
    }
}
