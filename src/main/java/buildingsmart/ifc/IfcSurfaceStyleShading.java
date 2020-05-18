package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The entity IfcSurfaceStyleShading allows for colour information used for
 * shading, whereas subtypes provide data for more sophisticated rendering
 * techniques. The surface colour is used for colouring or simple shading of the
 * assigned surfaces.
 */
public class IfcSurfaceStyleShading extends IfcEntity
        implements IfcSurfaceStyleElementSelect {
    private final IfcColourRgb surfaceColour;

    /**
     * @param surfaceColour The colour used to render the surface. The surface
     *                      colour for visualisation is defined by specifying
     *                      the intensity of red, green and blue.
     * @throws IllegalArgumentException If surfaceColour is null.
     */
    public IfcSurfaceStyleShading(@NotNull IfcColourRgb surfaceColour) {
        if (surfaceColour == null) {
            throw new IllegalArgumentException("surfaceColour cannot be null");
        }
        this.surfaceColour = surfaceColour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcSurfaceStyleShading that = (IfcSurfaceStyleShading) o;
        return surfaceColour.equals(that.surfaceColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surfaceColour);
    }
}
