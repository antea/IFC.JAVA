package buildingsmart.ifc;

/**
 * The enumeration defines whether the definition of a profile shape shall be
 * geometrically resolved into a curve or into a surface.
 */
public enum IfcProfileTypeEnum implements IfcDefinedType {
    CURVE, AREA;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
