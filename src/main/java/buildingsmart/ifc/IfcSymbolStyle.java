package buildingsmart.ifc;

public class IfcSymbolStyle extends IfcPresentationStyle
        implements IfcPresentationStyleSelect {
    private IfcSymbolStyleSelect StyleOfSymbol;

    public IfcSymbolStyle(IfcLabel name) {
        super(name);
    }
}
