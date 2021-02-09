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

/**
 * <P><U>Definition from ISO/CD 10303-42:1992</U>: An ellipse (IfcEllipse) is
 * a conic section defined by the lengths of the semi-major and semi-minor
 * diameters and the position (center or mid point of the line joining the foci)
 * and orientation of the curve. Interpretation of the data shall be as
 * follows:
 * </P>
 * <BLOCKQUOTE>
 * <BLOCKQUOTE>
 * <PRE>C  = SELF\IfcConic.Position.Location </PRE>
 * <PRE>x  = SELF\IfcConic.Position.P[1] </PRE>
 * <PRE>y  = SELF\IfcConic.Position.P[2] </PRE>
 * <PRE>z  = SELF\IfcConic.Position.P[3] </PRE>
 * <PRE>R1 = SemiAxis1 </PRE>
 * <PRE>R2 = SemiAxis2</PRE></BLOCKQUOTE></BLOCKQUOTE>
 * <P>The parameterization range is 0 <FONT FACE="Symbol">&pound;</FONT>
 * <I>u</I> <FONT FACE="Symbol">&pound;</FONT> 2<FONT
 * FACE="Symbol">p</FONT> (or 0
 * <FONT FACE="Symbol">&pound;</FONT> <I>u</I> <FONT
 * FACE="Symbol">&pound;</FONT> 360 degree).</P>
 * <P> The inherited Position.Location from IfcConic is the center of the
 * IfcEllipse, and the inherited Position.P[1] from IfcConic the direction of
 * the SemiAxis1. </P>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcEllipse extends IfcConic {
    @Attribute(1)
    private final IfcPositiveLengthMeasure semiAxis1;
    @Attribute(2)
    private final IfcPositiveLengthMeasure semiAxis2;

    /**
     * @param position  The location and orientation of the conic. Further
     *                  details of the interpretation of this attribute are
     *                  given for the individual subtypes.
     * @param semiAxis1 The first radius of the ellipse which shall be positive.
     *                  Its direction is the same of the x axis of {@code
     *                  position}.
     * @param semiAxis2 The second radius of the ellipse which shall be
     *                  positive.
     * @throws NullPointerException If position, semiAxis1 or semiAxis2 are
     *                              null.
     */
    public IfcEllipse(@NonNull IfcAxis2Placement position,
                      @NonNull IfcPositiveLengthMeasure semiAxis1,
                      @NonNull IfcPositiveLengthMeasure semiAxis2) {
        super(position);
        this.semiAxis1 = semiAxis1;
        this.semiAxis2 = semiAxis2;
    }

    /**
     * @return The space dimensionality of this IfcCurve.
     */
    @Override
    public IfcDimensionCount getDim() {
        return getPosition().getDim();
    }
}
