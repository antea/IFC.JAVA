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

import lombok.NonNull;

import java.util.List;

public abstract class Ifc2DCompositeCurve extends IfcCompositeCurve {
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
    public Ifc2DCompositeCurve(@NonNull List<IfcCompositeCurveSegment> segments,
                               @NonNull IfcLogical selfIntersect) {
        super(segments, selfIntersect);
    }
}
