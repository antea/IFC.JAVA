package buildingsmart.ifc;

public class IfcContextDependentUnit extends IfcNamedUnit {
    private String Name;

    /**
     * @param dimensions The dimensional exponents of the SI base units by which
     *                   the named unit is defined.
     * @param unitType   The type of the unit.
     * @throws IllegalArgumentException If any of the parameters are {@code
     *                                  null}, or if dimensions is wrong for the
     *                                  given unitType.
     * @see Functions#ifcCorrectDimensions(IfcUnitEnum, IfcDimensionalExponents)
     */
    public IfcContextDependentUnit(IfcDimensionalExponents dimensions,
                                   IfcUnitEnum unitType) {
        super(dimensions, unitType);
    }
}
