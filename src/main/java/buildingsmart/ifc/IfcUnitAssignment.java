package buildingsmart.ifc;

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.Set;

/**
 * A set of units which may be assigned. Within an IfcUnitAssigment each unit
 * definition shall be unique. I.e. there shall be no redundant unit definitions
 * for the same unit type, like length unit, area unit etc. For currencies,
 * there shall be only a single IfcMonetaryUnit within an IfcUnitAssignment.
 * <p>
 * <small>NOTE:  A project (IfcProject) has a unit assignment which establishes
 * a set of units which will be used globally within the project, if not
 * otherwise defined. Other objects may have local unit assignments if there is
 * a requirement for them to make use of units which do not fall within the
 * project unit assignment.</small>
 */
public class IfcUnitAssignment extends IfcEntity {
    private final Set<IfcUnit> units;

    /**
     * @param units Units to be included within a unit assignment.
     * @throws IllegalArgumentException If units is null, has size equal to
     *                                  zero, doesn't only include units with a
     *                                  different unitType (for IfcNamedUnit and
     *                                  IfcDerivedUnit), or includes more than
     *                                  one IfcMonetaryUnit.
     */
    public IfcUnitAssignment(@NotNull Set<IfcUnit> units) {
        if (units == null) {
            throw new IllegalArgumentException("units cannot be null");
        }
        if (units.size() < 1) {
            throw new IllegalArgumentException(
                    "size of units must be at least 1");
        }
        if (!Functions.ifcCorrectUnitAssignment(units)) {
            throw new IllegalArgumentException("");
        }
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcUnitAssignment that = (IfcUnitAssignment) o;
        return units.equals(that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(units);
    }
}
