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

import buildingsmart.io.DefinedType;

/**
 * This type conveys the continuity properties of a composite curve or surface.
 * The continuity referred to is geometric, not parametric continuity. For
 * example, in ContSameGradient the tangent vectors of successive segments will
 * have the same direction, but may have different magnitude.
 * <UL>
 * <LI><B>Discontinuous</B>: The segments do not join. This is permitted
 * only at the boundary of the curve or surface to indicate that it is not
 * closed.
 * </LI>
 * <LI><B>Continuous</B>: The segments join but no condition on their
 * tangents is implied. </LI>
 * <LI><B>ContSameGradient</B>: The segments join and their tangent
 * vectors or tangent planes are parallel and have the same direction at the
 * joint: equality of derivatives is not required. </LI>
 * <LI><B>ContSameGradientSameCurvature</B>: For a curve, the segments
 * join, their tangent vectors are parallel and in the same direction and their
 * curvatures are equal at the joint: equality of derivatives is not required.
 * For a surface this implies that the principle curvatures are the same and the
 * principle directions are coincident along the common boundary. </LI>
 * </UL>
 */
public enum IfcTransitionCode implements DefinedType {
    DISCONTINUOUS, CONTINUOUS, CONTSAMEGRADIENT, CONTSAMEGRADIENTSAMECURVATURE;

    /**
     * @return The representation of the Defined Type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
