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
import lombok.*;

/**
 * A conic (IfcConic) is a planar curve which could be produced by intersecting
 * a plane with a cone. A conic is defined in terms of its intrinsic geometric
 * properties rather than being described in terms of other geometry. A conic
 * class always has a placement coordinate system defined by a two or three
 * dimensional placement. The parametric representation is defined in terms of
 * this placement coordinate system.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcConic extends IfcCurve {
    @Getter(AccessLevel.PROTECTED)
    @Attribute(0)
    private final IfcAxis2Placement position;

    /**
     * @param position The location and orientation of the conic. Further
     *                 details of the interpretation of this attribute are given
     *                 for the individual subtypes.
     * @throws NullPointerException If position is null.
     */
    public IfcConic(@NonNull IfcAxis2Placement position) {
        this.position = position;
    }
}
