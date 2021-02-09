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
import lombok.ToString;

/**
 * <p>An <i>IfcCurveStyle</i>
 * provides the style table for presentation information assigned to geometric curves. The style is defined by a color,
 * a font and a width. <font color="#0000ff">The <i>IfcCurveStyle&nbsp;</i>defines curve patterns as model patterns,
 * i.e. the distance between visible and invisible segments of curve patterns are given in model space dimensions (that
 * have to be scaled using the target plot scale). </font></p><p>Styles are intended to be shared by multiple
 * <i>IfcStyledItem</i>'s, assigning the style to occurrences of (subtypes of) <i>IfcGeometricRepresentationItem</i>'s.
 * Measures given to a font pattern or a curve width are given in global drawing length units. <br>
 * </p><blockquote> <p><small>NOTE&nbsp;
 * global units are defined at the single <i>IfcProject</i> instance, given by <i>UnitsInContext:IfcUnitAssignment</i>,
 * the same units are used for the geometric representation items and for the style definitions.</small></p>
 * </blockquote><p>The measure values for font
 * pattern and curve width <font color="#0000ff">apply to the model space </font>with a target plot scale provided for
 * the correct appearance in the default plot scale.. For different scale and projection dependent curve styles a
 * different instance of <i>IfcCurveStyle</i> needs to be used by <i>IfcPresentationStyleAssignment</i> for different
 * <i>IfcGeometricRepresentationSubContext</i> dependent representations.</p>
 * <blockquote> <small>NOTE&nbsp;
 * the target plot scale is given by <i>IfcGeometricRepresentationSubContext.TargetScale</i>.</small><br>
 * </blockquote><p>An <i>IfcCurveStyle</i> can be
 * assigned to <i>IfcGeometricRepresentationItem</i>'s via the <i>IfcPresentationStyleAssignment</i> through an
 * intermediate <i>IfcStyledItem</i> or <i>IfcAnnotationCurveOccurrence</i>.</p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcCurveStyle extends IfcPresentationStyle implements IfcPresentationStyleSelect {
    @Attribute(1)
    private final IfcCurveFontOrScaledCurveFontSelect curveFont;
    @Attribute(2)
    private final IfcSizeSelect curveWidth;
    @Attribute(3)
    private final IfcColour curveColour;

    /**
     * @param name        Name of the presentation style.
     * @param curveFont   A curve style font which is used to present a curve. It can either be a predefined curve font,
     *                    or an explicitly defined curve font. Both may be scaled. If not given, then the curve font
     *                    should be taken from the layer assignment with style, if that is not given either, then the
     *                    default curve font applies.
     * @param curveWidth  A positive length measure in units of the presentation area for the width of a presented
     *                    curve. If not given, then the style should be taken from the layer assignment with style, if
     *                    that is not given either, then the default style applies.
     * @param curveColour The colour of the visible part of the curve. If not given, then the colour should be taken
     *                    from the layer assignment with style, if that is not given either, then the default colour
     *                    applies.
     * @throws IllegalArgumentException If {@code curveWidth} is not null, and it is neither an instance of {@link
     *                                  IfcPositiveLengthMeasure} nor an {@link IfcDescriptiveMeasure} with value {@code
     *                                  "by layer"}.
     */
    public IfcCurveStyle(IfcLabel name,
                         IfcCurveFontOrScaledCurveFontSelect curveFont,
                         IfcSizeSelect curveWidth,
                         IfcColour curveColour) {
        super(name);
        if (curveWidth != null && !(curveWidth instanceof IfcPositiveLengthMeasure) &&
                !(curveWidth instanceof IfcDescriptiveMeasure &&
                        ((IfcDescriptiveMeasure) curveWidth).getValue().equals(("by layer")))) {
            throw new IllegalArgumentException(
                    "if curveWidth is not null, it must be either an instance of IfcPositiveLengthMeasure, or an " +
                            "IfcDescriptiveMeasure with value \"by layer\"");
        }
        this.curveFont = curveFont;
        this.curveWidth = curveWidth;
        this.curveColour = curveColour;
    }
}
