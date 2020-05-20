package buildingsmart.ifc;

import java.util.Set;

public class IfcStyledRepresentation extends IfcStyleModel {
    public IfcStyledRepresentation(IfcRepresentationContext contextOfItems,
                                   IfcLabel representationIdentifier,
                                   IfcLabel representationType,
                                   Set<IfcRepresentationItem> items) {
        super(contextOfItems, representationIdentifier, representationType,
                items);
    }
}
