package buildingsmart.util;

import buildingsmart.ifc.*;
import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.sqrt;

public class Functions {

    /**
     * @param arg1 The first input direction.
     * @param arg2 The second input direction.
     * @return The vector (or cross) product of two input directions, after
     * normalizing them. The input directions must be three-dimensional. The
     * result is always a vector which is unitless. If one of the input
     * directions has all components equal to zero, or if they are either
     * parallel or anti-parallel, a vector of zero magnitude is returned.
     * @throws IllegalArgumentException If at least one of the arguments is
     *                                  null, or if at least one is not
     *                                  three-dimensional.
     * @see Functions#ifcNormalise(IfcDirection)
     */
    public static IfcVector ifcCrossProduct(@NotNull IfcDirection arg1,
                                            @NotNull IfcDirection arg2) {
        if (arg1 == null || arg2 == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (arg1.getDim().getValue() != 3 || arg2.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "arguments must be three-dimensional");
        }
        // default result if the directions are parallel, anti-parallel or
        // one of them has components that are all zero
        IfcVector result = new IfcVector(arg1, new IfcLengthMeasure(0));
        List<IfcReal> v1Real;
        List<IfcReal> v2Real;
        try {
            v1Real = ifcNormalise(arg1).getDirectionRatios();
            v2Real = ifcNormalise(arg2).getDirectionRatios();
        } catch (NullPointerException e) {
            return result;
        }
        double[] v1 = new double[v1Real.size()];
        double[] v2 = new double[v2Real.size()];
        for (byte i = 0; i < v1.length; i++) {
            v1[i] = v1Real.get(i).getValue();
            v2[i] = v2Real.get(i).getValue();
        }

        double[] res = new double[v1.length];
        res[0] = v1[1] * v2[2] - v1[2] * v2[1];
        res[1] = v1[2] * v2[0] - v1[0] * v2[2];
        res[2] = v1[0] * v2[1] - v1[1] * v2[0];
        double magnitude = 0;
        for (double component : res) {
            magnitude += component * component;
        }
        if (magnitude > 0) {
            result = new IfcVector(new IfcDirection(res),
                    new IfcLengthMeasure(sqrt(magnitude)));
        }
        return result;
    }

    /**
     * @param direction The IfcDirection of which the components should be
     *                  normalized.
     * @return IfcDirection whose components are normalized to have a sum of
     * squares of 1.0. If the input argument is null or its components are all
     * zero then the output is null.
     */
    public static IfcDirection ifcNormalise(IfcDirection direction) {
        if (direction == null) {
            return null;
        }
        double magnitude = 0;
        byte dim = direction.getDim().getValue();

        for (byte i = 0; i < dim; i++) {
            double component = direction.getDirectionRatios().get(i).getValue();
            magnitude += (component * component);
        }
        if (magnitude > 0) {
            magnitude = sqrt(magnitude);
            double[] directionRatios = new double[dim];
            for (byte i = 0; i < dim; i++) {
                double component =
                        direction.getDirectionRatios().get(i).getValue();
                directionRatios[i] = component / magnitude;
            }
            return new IfcDirection(directionRatios);
        }
        return null;
    }

    /**
     * @param vector The IfcVector of which the components should be
     *               normalized.
     * @return IfcVector whose components are normalized to have a sum of
     * squares of 1.0. If the input argument is null or its components are all
     * zero then the output is null.
     */
    public static IfcVector ifcNormalise(IfcVector vector) {
        if (vector == null || vector.getMagnitude().getValue() == 0) {
            return null;
        }
        double magnitude = 0;
        byte dim = vector.getDim().getValue();

        for (byte i = 0; i < dim; i++) {
            double component = vector.getDirectionRatios().get(i).getValue();
            magnitude += (component * component);
        }
        if (magnitude > 0) {
            magnitude = sqrt(magnitude);
            double[] directionRatios = new double[dim];
            for (byte i = 0; i < dim; i++) {
                double component =
                        vector.getDirectionRatios().get(i).getValue();
                directionRatios[i] = component / magnitude;
            }
            return new IfcVector(new IfcDirection(directionRatios),
                    new IfcLengthMeasure(1));
        }
        return null;
    }
    //TODO: test functions after this comment

    /**
     * Tests whether the dimensional exponents are correct for the given unit
     * type.
     *
     * @param unit The name of the unit type for which the dimensional exponents
     *             are tested.
     * @param dim  The dimensional exponents to be tested against corresponding
     *             unit type name.
     * @return {@code true} if the dimensional exponents for the given unit type
     * are correct, {@code false} otherwise. If the given unit type is
     * USERDEFINED, this method returns {@code null}.
     */
    public static Boolean ifcCorrectDimensions(IfcUnitEnum unit,
                                               IfcDimensionalExponents dim) {
        switch (unit) {
            case LENGTHUNIT:
                return dim.equals(new IfcDimensionalExponents(1, 0, 0, 0, 0, 0,
                        0));
            case MASSUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 1, 0, 0, 0, 0,
                        0));
            case TIMEUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 1, 0, 0, 0,
                        0));
            case ELECTRICCURRENTUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 1, 0, 0,
                        0));
            case THERMODYNAMICTEMPERATUREUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 1, 0,
                        0));
            case AMOUNTOFSUBSTANCEUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 1,
                        0));
            case LUMINOUSINTENSITYUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0,
                        1));
            case PLANEANGLEUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0,
                        0));
            case SOLIDANGLEUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0,
                        0));
            case AREAUNIT:
                return dim.equals(new IfcDimensionalExponents(2, 0, 0, 0, 0, 0,
                        0));
            case VOLUMEUNIT:
                return dim.equals(new IfcDimensionalExponents(3, 0, 0, 0, 0, 0,
                        0));
            case ABSORBEDDOSEUNIT:
                return dim.equals(new IfcDimensionalExponents(2, 0, -2, 0, 0, 0,
                        0));
            case RADIOACTIVITYUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, -1, 0, 0, 0,
                        0));
            case ELECTRICCAPACITANCEUNIT:
                return dim.equals(new IfcDimensionalExponents(-2, 1, 4, 1, 0, 0,
                        0));
            case DOSEEQUIVALENTUNIT:
                return dim.equals(new IfcDimensionalExponents(2, 0, -2, 0, 0, 0,
                        0));
            case ELECTRICCHARGEUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 1, 1, 0, 0,
                        0));
            case ELECTRICCONDUCTANCEUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(-2, -1, 3, 2, 0, 0,
                                0));
            case ELECTRICVOLTAGEUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(2, 1, -3, -1, 0, 0,
                                0));
            case ELECTRICRESISTANCEUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(2, 1, -3, -2, 0, 0,
                                0));
            case ENERGYUNIT:
                return dim.equals(new IfcDimensionalExponents(2, 1, -2, 0, 0, 0,
                        0));
            case FORCEUNIT:
                return dim.equals(new IfcDimensionalExponents(1, 1, -2, 0, 0, 0,
                        0));
            case FREQUENCYUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, -1, 0, 0, 0,
                        0));
            case INDUCTANCEUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(2, 1, -2, -2, 0, 0,
                                0));
            case ILLUMINANCEUNIT:
                return dim.equals(new IfcDimensionalExponents(-2, 0, 0, 0, 0, 0,
                        1));
            case LUMINOUSFLUXUNIT:
                return dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0,
                        1));
            case MAGNETICFLUXUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(2, 1, -2, -1, 0, 0,
                                0));
            case MAGNETICFLUXDENSITYUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(0, 1, -2, -1, 0, 0,
                                0));
            case POWERUNIT:
                return dim.equals(new IfcDimensionalExponents(2, 1, -3, 0, 0, 0,
                        0));
            case PRESSUREUNIT:
                return dim
                        .equals(new IfcDimensionalExponents(-1, 1, -2, 0, 0, 0,
                                0));
        }
        return null;
    }

    /**
     * @param name The name of the unit for which the dimensional exponents will
     *             be returned. Cannot be null.
     * @return The dimensional exponents of the given SI-unit. If the unit is
     * unknown (which should not be possible, since this method deals with all
     * possible values of IfcSIUnitName), dimensional exponents all equal to
     * zero will be returned.
     * @throws IllegalArgumentException If name is null.
     */
    public static IfcDimensionalExponents ifcDimensionsForSiUnit(
            IfcSIUnitName name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        switch (name) {
            case METRE:
                return new IfcDimensionalExponents(1, 0, 0, 0, 0, 0, 0);
            case SQUARE_METRE:
                return new IfcDimensionalExponents(2, 0, 0, 0, 0, 0, 0);
            case CUBIC_METRE:
                return new IfcDimensionalExponents(3, 0, 0, 0, 0, 0, 0);
            case GRAM:
                return new IfcDimensionalExponents(0, 1, 0, 0, 0, 0, 0);
            case SECOND:
                return new IfcDimensionalExponents(0, 0, 1, 0, 0, 0, 0);
            case AMPERE:
                return new IfcDimensionalExponents(0, 0, 0, 1, 0, 0, 0);
            case KELVIN:
                return new IfcDimensionalExponents(0, 0, 0, 0, 1, 0, 0);
            case MOLE:
                return new IfcDimensionalExponents(0, 0, 0, 0, 0, 1, 0);
            case CANDELA:
                return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1);
            case RADIAN:
                return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
            case STERADIAN:
                return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
            case HERTZ:
                return new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0);
            case NEWTON:
                return new IfcDimensionalExponents(1, 1, -2, 0, 0, 0, 0);
            case PASCAL:
                return new IfcDimensionalExponents(-1, 1, -2, 0, 0, 0, 0);
            case JOULE:
                return new IfcDimensionalExponents(2, 1, -2, 0, 0, 0, 0);
            case WATT:
                return new IfcDimensionalExponents(2, 1, -3, 0, 0, 0, 0);
            case COULOMB:
                return new IfcDimensionalExponents(0, 0, 1, 1, 0, 0, 0);
            case VOLT:
                return new IfcDimensionalExponents(2, 1, -3, -1, 0, 0, 0);
            case FARAD:
                return new IfcDimensionalExponents(-2, -1, 4, 1, 0, 0, 0);
            case OHM:
                return new IfcDimensionalExponents(2, 1, -3, -2, 0, 0, 0);
            case SIEMENS:
                return new IfcDimensionalExponents(-2, -1, 3, 2, 0, 0, 0);
            case WEBER:
                return new IfcDimensionalExponents(2, 1, -2, -1, 0, 0, 0);
            case TESLA:
                return new IfcDimensionalExponents(0, 1, -2, -1, 0, 0, 0);
            case HENRY:
                return new IfcDimensionalExponents(2, 1, -2, -2, 0, 0, 0);
            case DEGREE_CELSIUS:
                return new IfcDimensionalExponents(0, 0, 0, 0, 1, 0, 0);
            case LUMEN:
                return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1);
            case LUX:
                return new IfcDimensionalExponents(-2, 0, 0, 0, 0, 0, 1);
            case BECQUEREL:
                return new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0);
            case GRAY:
                return new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0);
            case SIEVERT:
                return new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0);
        }
        return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * @param units The Set of IfcUnit for which to check if it's an appropriate
     *              parameter for
     *              {@link IfcUnitAssignment#IfcUnitAssignment(Set)}.
     * @return {@code true}, if the Set of IfcUnit only includes units with
     * different unitType (for IfcNamedUnit and IfcDerivedUnit), and a maximum
     * of one IfcMonetaryUnit.
     * @throws IllegalArgumentException If units is null or has size zero.
     */
    public static boolean ifcCorrectUnitAssignment(Set<IfcUnit> units) {
        if (units == null) {
            throw new IllegalArgumentException("units cannot be null");
        }
        if (units.size() < 1) {
            throw new IllegalArgumentException(
                    "size of units must be at least 1");
        }

        int namedUnitNumber = 0;
        int derivedUnitNumber = 0;
        int monetaryUnitNumber = 0;
        Set<IfcUnitEnum> namedUnitTypes = new HashSet<>();
        Set<IfcDerivedUnitEnum> derivedUnitTypes = new HashSet<>();

        for (IfcUnit unit : units) {
            if (unit instanceof IfcNamedUnit &&
                    ((IfcNamedUnit) unit).getUnitType() !=
                            IfcUnitEnum.USERDEFINED) {
                namedUnitNumber++;
                namedUnitTypes.add(((IfcNamedUnit) unit).getUnitType());
            } else {
                if (unit instanceof IfcDerivedUnit &&
                        ((IfcDerivedUnit) unit).getUnitType() !=
                                IfcDerivedUnitEnum.USERDEFINED) {
                    derivedUnitNumber++;
                    derivedUnitTypes.add(((IfcDerivedUnit) unit).getUnitType());
                } else {
                    if (unit instanceof IfcMonetaryUnit) {
                        monetaryUnitNumber++;
                    }
                }
            }
        }
        return namedUnitNumber == namedUnitTypes.size() &&
                derivedUnitNumber == derivedUnitTypes.size() &&
                monetaryUnitNumber <= 1;
    }

    /**
     * @param arg1 A direction in either two- or three-dimensional space.
     * @param arg2 A direction in either two- or three-dimensional space.
     * @return The scalar (or dot) product of the two directions.
     * @throws IllegalArgumentException If the two directions have different
     *                                  dimensionality, or at least one of them
     *                                  is null.
     */
    public static IfcReal ifcDotProduct(@NotNull IfcDirection arg1,
                                        @NotNull IfcDirection arg2) {
        if (arg1 == null || arg2 == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (arg1.getDim() != arg2.getDim()) {
            throw new IllegalArgumentException(
                    "the two arguments must have the same dimensionality");
        }

        double scalar = 0;
        IfcDirection vec1 = ifcNormalise(arg1);
        IfcDirection vec2 = ifcNormalise(arg2);
        byte dim = arg1.getDim().getValue();

        for (byte i = 0; i < dim; i++) {
            scalar += vec1.getDirectionRatios().get(i).getValue() *
                    vec2.getDirectionRatios().get(i).getValue();
        }
        return new IfcReal(scalar);
    }
}
