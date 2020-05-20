package buildingsmart.ifc;

import java.util.Set;

public class IfcTopologyRepresentation extends IfcShapeModel {
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
    public IfcTopologyRepresentation(IfcRepresentationContext contextOfItems,
                                     IfcLabel representationIdentifier,
                                     IfcLabel representationType,
                                     Set<IfcRepresentationItem> items) {
        super(contextOfItems, representationIdentifier, representationType,
                items);
    }
}
