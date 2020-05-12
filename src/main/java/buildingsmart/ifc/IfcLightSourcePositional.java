package buildingsmart.ifc;

public abstract class IfcLightSourcePositional extends IfcLightSource {
    private IfcCartesianPoint Position;
    private IfcLengthMeasure Radius;
    private double ConstantAttenuation;
    private double DistanceAttenuation;
    private double QuadricAttenuation;
}