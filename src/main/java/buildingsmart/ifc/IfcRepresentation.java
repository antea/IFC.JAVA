package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A representation is one or more representation items that are related in a
 * specified representation context as the representation of some concept.
 */
public class IfcRepresentation extends IfcEntity {
    private final IfcRepresentationContext contextOfItems;
    private final IfcLabel representationIdentifier;
    private final IfcLabel representationType;
    private final Set<IfcRepresentationItem> items;

    // inverse attributes
    protected IfcRepresentationMap representationMap;
    //private IfcPresentationLayerAssignment[] layerAssignments;
    protected IfcProductRepresentation ofProductRepresentation;

    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws IllegalArgumentException If contextOfItems or items are null; if
     *                                  the size of items is lower than 1.
     */
    public IfcRepresentation(@NotNull IfcRepresentationContext contextOfItems,
                             IfcLabel representationIdentifier,
                             IfcLabel representationType,
                             @NotNull Set<IfcRepresentationItem> items) {
        if (contextOfItems == null) {
            throw new IllegalArgumentException("contextOfItems cannot be null");
        }
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }
        if (items.size() < 1) {
            throw new IllegalArgumentException(
                    "size of items must be at least 1");
        }
        this.contextOfItems = contextOfItems;
        this.representationIdentifier = representationIdentifier;
        this.representationType = representationType;
        this.items = items;
    }

    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws IllegalArgumentException If contextOfItems or items are null; if
     *                                  the size of items is lower than 1.
     */
    public IfcRepresentation(@NotNull IfcRepresentationContext contextOfItems,
                             IfcLabel representationIdentifier,
                             IfcLabel representationType,
                             @NotNull IfcRepresentationItem... items) {
        this(contextOfItems, representationIdentifier, representationType,
                new HashSet<>(Arrays.asList(items)));
    }

    /**
     * @param representationMap Use of the representation within an
     *                          IfcRepresentationMap. If used, this
     *                          IfcRepresentation may be assigned to many
     *                          representations as one of its Items using an
     *                          IfcMappedItem. Using IfcRepresentationMap is the
     *                          way to share one representation (often of type
     *                          IfcShapeRepresentation) by many products.
     */
    public void setRepresentationMap(IfcRepresentationMap representationMap) {
        this.representationMap = representationMap;
    }

    /**
     * @param ofProductRepresentation Reference to the product shape, for which
     *                                it is the shape representation.
     */
    public void setOfProductRepresentation(
            IfcProductRepresentation ofProductRepresentation) {
        this.ofProductRepresentation = ofProductRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcRepresentation that = (IfcRepresentation) o;
        return contextOfItems.equals(that.contextOfItems) &&
                Objects.equals(representationIdentifier,
                        that.representationIdentifier) &&
                Objects.equals(representationType, that.representationType) &&
                items.equals(that.items) &&
                Objects.equals(representationMap, that.representationMap) &&
                Objects.equals(ofProductRepresentation,
                        that.ofProductRepresentation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextOfItems, representationIdentifier,
                representationType, items, representationMap,
                ofProductRepresentation);
    }
}
