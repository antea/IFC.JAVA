package buildingsmart.ifc;

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A named unit is a unit quantity associated with the word, or group of words,
 * by which the unit is identified.
 */
public abstract class IfcNamedUnit extends IfcEntity implements IfcUnit {
    private final IfcDimensionalExponents dimensions;
    private final IfcUnitEnum unitType;

    //TODO: test constructor

    /**
     * @param dimensions The dimensional exponents of the SI base units by which
     *                   the named unit is defined.
     * @param unitType   The type of the unit.
     * @throws IllegalArgumentException If any of the parameters are {@code
     *                                  null}, or if dimensions is wrong for the
     *                                  given unitType.
     * @see Functions#ifcCorrectDimensions(IfcUnitEnum, IfcDimensionalExponents)
     */
    public IfcNamedUnit(@NotNull IfcDimensionalExponents dimensions,
                        @NotNull IfcUnitEnum unitType) {
        if (dimensions == null) {
            throw new IllegalArgumentException("dimensions cannot be null");
        }
        if (unitType == null) {
            throw new IllegalArgumentException("unitType cannot be null");
        }
        try {
            if (!Functions.ifcCorrectDimensions(unitType, dimensions)) {
                throw new IllegalArgumentException(
                        "given dimensions for this unitType are wrong");
            }
        } catch (NullPointerException e) {
            // unitType is USERDEFINED, so we can't check if dimensions is
            // correct
        }
        this.dimensions = dimensions;
        this.unitType = unitType;
    }

    public IfcUnitEnum getUnitType() {
        return unitType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcNamedUnit that = (IfcNamedUnit) o;
        return dimensions.equals(that.dimensions) && unitType == that.unitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimensions, unitType);
    }
}
