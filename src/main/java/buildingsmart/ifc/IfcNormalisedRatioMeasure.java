package buildingsmart.ifc;

/**
 * Dimensionless measure to express ratio values ranging from 0.0 to 1.0.
 */
public class IfcNormalisedRatioMeasure extends IfcRatioMeasure
        implements IfcColourOrFactor {

    /**
     * @param value A non-negative value less than or equal to 1.0.
     * @throws IllegalArgumentException If value is negative or bigger than 1.
     */
    public IfcNormalisedRatioMeasure(double value) {
        super(value);
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException(
                    "value must be non-negative, and less than or equal to 1");
        }
    }
}
