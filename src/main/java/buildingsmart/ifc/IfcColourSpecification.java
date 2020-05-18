package buildingsmart.ifc;

import java.util.Objects;

/**
 * The colour specification entity contains a direct colour definition. Colour
 * component values refer directly to a specific colour space.
 */
public abstract class IfcColourSpecification extends IfcEntity {
    private final IfcLabel name;

    /**
     * @param name Optional name given to a particular colour specification in
     *             addition to the colour components (like the RGB values).
     */
    public IfcColourSpecification(IfcLabel name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcColourSpecification that = (IfcColourSpecification) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
