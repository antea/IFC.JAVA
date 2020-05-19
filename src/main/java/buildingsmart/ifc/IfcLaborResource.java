package buildingsmart.ifc;

public class IfcLaborResource extends IfcConstructionResource {
    private final String SkillSet;

    public IfcLaborResource(IfcGloballyUniqueId globalId,
                            IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, IfcLabel objectType,
                            IfcRelAssignsToResource[] resourceOf,
                            String resourceIdentifier, String skillSet) {

        super(globalId, ownerHistory, name, description, objectType, resourceOf,
                resourceIdentifier);
        SkillSet = skillSet;
    }
}
