package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The presentation style assignment is a set of styles which are assigned to
 * styled items for the purpose of presenting these styled items.
 */
public class IfcPresentationStyleAssignment extends IfcEntity {
    private final Set<IfcPresentationStyleSelect> styles;

    /**
     * @param styles A set of presentation styles that are assigned to styled
     *               items.
     * @throws IllegalArgumentException If styles is null, or its size is lower
     *                                  than 1.
     */
    public IfcPresentationStyleAssignment(
            @NotNull Set<IfcPresentationStyleSelect> styles) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        if (styles.size() < 1) {
            throw new IllegalArgumentException(
                    "size of syles must be at least 1");
        }
        this.styles = styles;
    }

    /**
     * @param styles A set of presentation styles that are assigned to styled
     *               items.
     * @throws IllegalArgumentException If styles is null, or the size of the
     *                                  Set containing the unique elements of
     *                                  styles has size lower than 1.
     */
    public IfcPresentationStyleAssignment(
            @NotNull IfcPresentationStyleSelect... styles) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        Set<IfcPresentationStyleSelect> stylesSet =
                new HashSet<>(Arrays.asList(styles));
        if (stylesSet.size() < 1) {
            throw new IllegalArgumentException(
                    "size of syles must be at least 1");
        }
        this.styles = stylesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPresentationStyleAssignment that =
                (IfcPresentationStyleAssignment) o;
        return styles.equals(that.styles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styles);
    }
}
