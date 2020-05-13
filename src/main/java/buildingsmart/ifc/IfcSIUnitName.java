package buildingsmart.ifc;

/**
 * An SI unit name is the name of an SI unit. The definitions of the names of SI
 * units are specified in ISO 1000 (clause 2).
 */
public enum IfcSIUnitName implements IfcDefinedType {
    AMPERE, BECQUEREL, CANDELA, COULOMB, CUBIC_METRE, DEGREE_CELSIUS, FARAD,
    GRAM, GRAY, HENRY, HERTZ, JOULE, KELVIN, LUMEN, LUX, METRE, MOLE, NEWTON,
    OHM, PASCAL, RADIAN, SECOND, SIEMENS, SIEVERT, SQUARE_METRE, STERADIAN,
    TESLA, VOLT, WATT, WEBER;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
