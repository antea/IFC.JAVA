package buildingsmart.ifc;

import java.util.Objects;

/**
 * A representation context is a context in which a set of representation items
 * are related.
 */
public class IfcRepresentationContext extends IfcEntity {
    private final IfcLabel contextIdentifier;
    private final IfcLabel contextType;
    //private IfcRepresentation[] RepresentationsInContext;

    /**
     * @param contextIdentifier The optional identifier of the representation
     *                          context as used within a project.
     * @param contextType       The description of the type of a representation
     *                          context. The supported values for context type
     *                          are to be specified by implementers agreements.
     */
    public IfcRepresentationContext(IfcLabel contextIdentifier,
                                    IfcLabel contextType) {
        this.contextIdentifier = contextIdentifier;
        this.contextType = contextType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcRepresentationContext that = (IfcRepresentationContext) o;
        return Objects.equals(contextIdentifier, that.contextIdentifier) &&
                Objects.equals(contextType, that.contextType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextIdentifier, contextType);
    }
}
