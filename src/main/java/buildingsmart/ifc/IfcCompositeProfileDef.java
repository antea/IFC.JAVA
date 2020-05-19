package buildingsmart.ifc;

public class IfcCompositeProfileDef extends IfcProfileDef {
    private final IfcProfileDef[] Profiles;
    private String Label;

    public IfcCompositeProfileDef(IfcProfileTypeEnum profileType,
                                  IfcLabel profileName,
                                  IfcProfileDef[] profiles) {
        super(profileType, profileName);
        Profiles = profiles;
    }
}
