package buildingsmart.ifc;

/**
 * The IfcReflectanceMethodEnum defines the range of different reflectance
 * methods available.
 */
public enum IfcReflectanceMethodEnum implements IfcDefinedType {
    BLINN, FLAT, GLASS, MATT, METAL, MIRROR, PHONG, PLASTIC, STRAUSS,
    NOTDEFINED;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
