package buildingsmart.ifc;

public class IfcCurveStyle extends IfcPresentationStyle
        implements IfcPresentationStyleSelect {
    private IfcCurveFontOrScaledCurveFontSelect CurveFont;
    private IfcSizeSelect CurveWidth;
    private IfcColour CurveColour;

    public IfcCurveStyle(IfcLabel name) {
        super(name);
    }
}
