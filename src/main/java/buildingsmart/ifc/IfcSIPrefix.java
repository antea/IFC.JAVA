package buildingsmart.ifc;

/**
 * An SI prefix is the name of a prefix that may be associated with an SI unit.
 * The definitions of SI prefixes are specified in ISO 1000 (clause 3).
 */
public enum IfcSIPrefix implements IfcDefinedType {
    EXA, PETA, TERA, GIGA, MEGA, KILO, HECTO, DECA, DECI, CENTI, MILLI, MICRO,
    NANO, PICO, FEMTO, ATTO;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
