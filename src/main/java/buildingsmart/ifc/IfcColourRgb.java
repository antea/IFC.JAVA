package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

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
public class IfcColourRgb extends IfcColourSpecification
        implements IfcColourOrFactor {
    private final IfcNormalisedRatioMeasure red;
    private final IfcNormalisedRatioMeasure green;
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
     * @throws IllegalArgumentException If at least one of the red, green and
     *                                  blue parameters is null.
     */
    public IfcColourRgb(IfcLabel name, @NotNull IfcNormalisedRatioMeasure red,
                        @NotNull IfcNormalisedRatioMeasure green,
                        @NotNull IfcNormalisedRatioMeasure blue) {
        super(name);
        if (red == null || green == null || blue == null) {
            throw new IllegalArgumentException(
                    "arguments red, green and blue cannot be null");
        }
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
     * @throws IllegalArgumentException If at least one of the values of the
     *                                  red, green and blue parameters is
     *                                  negative or bigger than 1.
     */
    public IfcColourRgb(IfcLabel name, double red, double green, double blue) {
        super(name);
        this.red = new IfcNormalisedRatioMeasure(red);
        this.green = new IfcNormalisedRatioMeasure(green);
        this.blue = new IfcNormalisedRatioMeasure(blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        IfcColourRgb that = (IfcColourRgb) o;
        return red.equals(that.red) && green.equals(that.green) &&
                blue.equals(that.blue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), red, green, blue);
    }
}
