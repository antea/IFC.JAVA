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
 * A colour rgb as a subtype of colour specifications is defined by three colour
 * component values for red, green, and blue in the RGB colour model.</p>
 * <blockquote>
 * <p><small>NOTE &nbsp;In contrary to the usual value
 * range of colour components being integer from 0...255, the definition from
 * ISO10303-46 defines the colour components as real from 0.0 ... 1.0.
 * Applications need to execute this conversion before populating the colour RGB
 * values.</small></p>
 * </blockquote>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcColourRgb extends IfcColourSpecification
        implements IfcColourOrFactor {
    @Attribute(1)
    private final IfcNormalisedRatioMeasure red;
    @Attribute(2)
    private final IfcNormalisedRatioMeasure green;
    @Attribute(3)
    private final IfcNormalisedRatioMeasure blue;

    /**
     * @param name  Optional name given to a particular colour specification in
     *              addition to the colour components (like the RGB values).
     * @param red   The intensity of the red colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @param green The intensity of the green colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @param blue  The intensity of the blue colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @throws NullPointerException If at least one of the red, green and blue
     *                              parameters is null.
     */
    public IfcColourRgb(IfcLabel name,
                        @NonNull IfcNormalisedRatioMeasure red,
                        @NonNull IfcNormalisedRatioMeasure green,
                        @NonNull IfcNormalisedRatioMeasure blue) {
        super(name);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * @param name  Optional name given to a particular colour specification in
     *              addition to the colour components (like the RGB values).
     * @param red   The intensity of the red colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @param green The intensity of the green colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @param blue  The intensity of the blue colour component.
     *              <blockquote><small>
     *              NOTE &nbsp; The colour component value is given within the
     *              range of 0. .1, and not within the range of 0..255 as
     *              otherwise usual.
     *              </small></blockquote>
     * @throws NullPointerException If at least one of the values of the red,
     *                              green and blue parameters is negative or
     *                              bigger than 1.
     */
    public IfcColourRgb(IfcLabel name, double red, double green, double blue) {
        super(name);
        this.red = new IfcNormalisedRatioMeasure(red);
        this.green = new IfcNormalisedRatioMeasure(green);
        this.blue = new IfcNormalisedRatioMeasure(blue);
    }
}
