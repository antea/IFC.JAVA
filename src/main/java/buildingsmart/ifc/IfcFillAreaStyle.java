package buildingsmart.ifc;

public class IfcFillAreaStyle extends IfcPresentationStyle
        implements IfcPresentationStyleSelect {
    private IfcFillStyleSelect[] FillStyles;

    public IfcFillAreaStyle(IfcLabel name) {
        super(name);
    }
}
