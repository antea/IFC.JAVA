package buildingsmart.ifc;

/**
 * Enumeration that provides an indication, whether the spatial structure
 * element or proxy esents a:
 * </p>
 * <ul>
 *   <li>COMPLEX - a group or aggregation of similar elements
 *   </li>
 *   <li>ELEMENT - a (undivided) element itself
 *   </li>
 *   <li>PARTIAL - a subelement or part
 *   </li>
 * </ul>
 */
public enum IfcElementCompositionEnum implements IfcDefinedType {
    COMPLEX, ELEMENT, PARTIAL;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
