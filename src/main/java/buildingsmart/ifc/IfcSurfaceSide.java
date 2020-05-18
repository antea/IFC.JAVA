package buildingsmart.ifc;

/**
 * Denotion of whether negative, positive or both sides of a surface are being
 * referenced.
 */
public enum IfcSurfaceSide implements IfcDefinedType {
    POSITIVE, NEGATIVE, BOTH;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
