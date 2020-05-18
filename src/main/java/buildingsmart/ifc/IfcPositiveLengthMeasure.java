package buildingsmart.ifc;

/**
 * A positive length measure is a length measure that is greater than zero.
 */
public class IfcPositiveLengthMeasure extends IfcLengthMeasure {

    /**
     * @param value The value of the distance.
     * @throws IllegalArgumentException If value is not bigger than zero.
     */
    public IfcPositiveLengthMeasure(double value) {
        super(value);
        if (value <= 0) {
            throw new IllegalArgumentException(
                    "value must be bigger than zero");
        }
    }
}
