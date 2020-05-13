package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A measure with unit is the specification of a physical quantity as defined in
 * ISO 31 (clause 2).
 * <p>
 * IfcMeasureWithUnit has two usages:
 * <ol>
 * <li>For representing measure value together
 * with its unit on the entity type attribute level; thus overriding the IFC
 * model global unit assignments.</li>
 * <li>For conversion based unit to give the
 * conversion rate and its base.</li>
 * </ol>
 */
public class IfcMeasureWithUnit extends IfcEntity {
    private final IfcValue valueComponent;
    private final IfcUnit unitComponent;

    /**
     * @param valueComponent The value of the physical quantity when expressed
     *                       in the specified units.
     * @param unitComponent  The unit in which the physical quantity is
     *                       expressed.
     */
    public IfcMeasureWithUnit(@NotNull IfcValue valueComponent,
                              @NotNull IfcUnit unitComponent) {
        if (valueComponent == null) {
            throw new IllegalArgumentException("valueComponent cannot be null");
        }
        if (unitComponent == null) {
            throw new IllegalArgumentException("unitComponent cannot be null");
        }
        this.valueComponent = valueComponent;
        this.unitComponent = unitComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcMeasureWithUnit that = (IfcMeasureWithUnit) o;
        return valueComponent.equals(that.valueComponent) &&
                unitComponent.equals(that.unitComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueComponent, unitComponent);
    }
}
