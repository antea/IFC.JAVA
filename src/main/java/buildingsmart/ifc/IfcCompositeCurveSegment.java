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
import lombok.*;

/**
 * A composite curve segment (IfcCompositeCurveSegment) is a bounded curve
 * together with transition information which is used to construct a composite
 * curve ({@link IfcCompositeCurve}).
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcCompositeCurveSegment extends IfcGeometricRepresentationItem {
    @Getter(AccessLevel.PROTECTED)
    @Attribute(0)
    private final IfcTransitionCode transition;
    @Attribute(1)
    private final IfcBoolean sameSense;
    @Attribute(2)
    private final IfcBoundedCurve parentCurve;

    // derived attributes
    @Getter(AccessLevel.PROTECTED)
    @EqualsAndHashCode.Exclude
    private final IfcDimensionCount dim;

    /**
     * @param transition  The state of transition (i.e., geometric continuity
     *                    from the last point of this segment to the first point
     *                    of the next segment) in a composite curve.
     * @param sameSense   An indicator of whether or not the sense of the
     *                    segment agrees with, or opposes, that of the parent
     *                    curve. If SameSense is false, the point with highest
     *                    parameter value is taken as the first point of the
     *                    segment.
     * @param parentCurve The bounded curve which defines the geometry of the
     *                    segment.
     * @throws NullPointerException If any of the arguments are null.
     */
    public IfcCompositeCurveSegment(@NonNull IfcTransitionCode transition,
                                    @NonNull IfcBoolean sameSense,
                                    @NonNull IfcBoundedCurve parentCurve) {
        this.transition = transition;
        this.sameSense = sameSense;
        this.parentCurve = parentCurve;
        this.dim = parentCurve.getDim();
    }
}
