package buildingsmart.ifc;

public class IfcTextStyle extends IfcPresentationStyle
        implements IfcPresentationStyleSelect {
    private IfcCharacterStyleSelect TextCharacterAppearance;
    private IfcTextStyleSelect TextStyle;
    private IfcTextFontSelect TextFontStyle;

    public IfcTextStyle(IfcLabel name) {
        super(name);
    }
}
