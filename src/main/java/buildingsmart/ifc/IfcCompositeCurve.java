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

import java.util.Collections;
import java.util.List;

/**
 * A composite curve (IfcCompositeCurve) is a collection of curves joined
 * end-to-end. The individual segments of the curve are themselves defined as
 * composite curve segments. The parameterization of the composite curve is an
 * accumulation of the parametric ranges of the referenced bounded curves.
 * <P><U>Informal Propositions</U>:</P>
 * <OL>
 * <LI>The SameSense attribute of each segment correctly specifies the
 * senses of the component curves. When traversed in the direction indicated by
 * SameSense, the segments shall join end-to-end.</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcCompositeCurve extends IfcBoundedCurve {
    @Attribute(0)
    private final List<IfcCompositeCurveSegment> segments;
    @Attribute(1)
    private final IfcLogical selfIntersect;

    /**
     * @param segments      The component bounded curves, their transitions and
     *                      senses. The transition attribute for the last
     *                      segment defines the transition between the end of
     *                      the last segment and the start of the first; this
     *                      transition attribute may take the value
     *                      discontinuous, which indicates an open curve.
     * @param selfIntersect Indication of whether the curve intersects itself or
     *                      not; this is for information only.
     * @throws NullPointerException     If any of the arguments are null.
     * @throws IllegalArgumentException If the size of segments is 0, if any
     *                                  element of segments (except the last
     *                                  one) has {@code transition ==
     *                                  DISCONTINUOUS}, if the segments have
     *                                  different dimensionality.
     */
    public IfcCompositeCurve(@NonNull List<IfcCompositeCurveSegment> segments,
                             @NonNull IfcLogical selfIntersect) {
        if (segments.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of segments must be at least 1");
        }
        IfcDimensionCount firstSegmentDim = segments.get(0).getDim();
        for (int i = 0; i < segments.size(); i++) {
            IfcCompositeCurveSegment current = segments.get(i);
            if (current.getTransition() == IfcTransitionCode.DISCONTINUOUS &&
                    i != segments.size() - 1) {
                throw new IllegalArgumentException(
                        "only the last segment can have transition == " +
                                "DISCONTINUOUS");
            }
            if (!current.getDim().equals(firstSegmentDim)) {
                throw new IllegalArgumentException(
                        "dimension of all segments must be the same");
            }
        }
        this.segments = Collections.unmodifiableList(segments);
        this.selfIntersect = selfIntersect;
    }

    /**
     * @return The space dimensionality of this IfcCurve.
     */
    @Override
    public IfcDimensionCount getDim() {
        return segments.get(0).getDim();
    }
}
