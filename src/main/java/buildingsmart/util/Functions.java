/*
 * Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of IFC.JAVA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package buildingsmart.util;

import buildingsmart.ifc.*;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.sqrt;

public class Functions {

    /**
     * Max allowed difference for doubles to be considered equal in
     * comparisons.
     */
    protected static final double DELTA = 0.0000000000001;

    /**
     * String data types in a STEP file can contain characters "'" and "\" only
     * as "\'" and "\\". This method formats the given String so that it can be
     * serialized in a STEP file.
     *
     * @param toFormat The String to format.
     * @return The formatted string.
     */
    public static String formatForStepFile(String toFormat) {
        return toFormat.replaceAll("\\\\", "\\\\").replaceAll("'", "\\'");
    }

    /**
     * @param arg1 The first input direction. Must be three-dimensional.
     * @param arg2 The second input direction. Must be three-dimensional
     * @return The vector (or cross) product of two input directions, after
     * normalizing them. The result is always a vector which is unitless. If one
     * of the input directions has all components equal to zero, or if they are
     * either parallel or anti-parallel, a vector of zero magnitude is returned.
     * If at least one of the arguments is null or not three-dimensional, null
     * is returned.
     * @see Functions#ifcNormalise(IfcDirection)
     */
    public static IfcVector ifcCrossProduct(IfcDirection arg1,
                                            IfcDirection arg2) {
        if (arg1 == null || arg2 == null) {
            return null;
        }
        if (arg1.getDim().getValue() != 3 || arg2.getDim().getValue() != 3) {
            return null;
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
        if (alreadyNormalised(direction)) {
            return direction;
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
        if (alreadyNormalised(vector)) {
            return vector;
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

    /**
     * @param direction The direction on which to perform the check.
     * @return {@code true} if the direction is already normalized (meaning that
     * the sum of the squares of its components is 1), {@code false} otherwise.
     * @throws NullPointerException If direction is null.
     */
    private static boolean alreadyNormalised(IfcDirection direction) {
        double squaresSum = 0;
        for (IfcReal dirRatio : direction.getDirectionRatios()) {
            double component = dirRatio.getValue();
            squaresSum += component * component;
        }
        return Math.abs(squaresSum - 1) < DELTA;
    }

    /**
     * @param vector The vector on which to perform the check.
     * @return {@code true} if the vector is already normalized (meaning that
     * the sum of the squares of its components is 1), {@code false} otherwise.
     * @throws NullPointerException If vector is null.
     */
    private static boolean alreadyNormalised(IfcVector vector) {
        if (vector.getMagnitude().getValue() != 1) {
            return false;
        }
        return alreadyNormalised(vector.getOrientation());
    }

    /**
     * @param axis         The axis of the IfcAxis2Placement3D for which this
     *                     function was called. If this value is not null, then
     *                     refDirection must also be not null.
     * @param refDirection The refDirection of the IfcAxis2Placement3D for which
     *                     this function was called. If this value is not null,
     *                     then axis must also be not null.
     * @return Three normalized orthogonal directions. List[2] is the direction
     * of axis. List[0] is in the direction of the projection of ref_direction
     * onto the plane normal to List[2], List[1] is the cross product of List[2]
     * and List[0]. Default values are supplied if both arguments are null.
     */
    public static List<IfcDirection> ifcBuildAxes(IfcDirection axis,
                                                  IfcDirection refDirection) {
        IfcDirection normalisedAxis = ifcNormalise(axis);
        IfcDirection d1 = normalisedAxis == null ? new IfcDirection(0, 0, 1) :
                normalisedAxis;
        IfcDirection d2 = ifcFirstProjAxis(d1, refDirection);
        List<IfcDirection> result = new ArrayList<>(3);
        result.add(d2);
        result.add(ifcNormalise(ifcCrossProduct(d1, d2)).getOrientation());
        result.add(d1);
        return result;
    }

    /**
     * @param zAxis The direction onto whose normal plane the direction arg
     *              should be projected.
     * @param arg   The direction to project onto the plane normal to zAxis.
     * @return A three dimensional direction which is, with fully defined input,
     * the projection of arg onto the plane normal to zAxis. If arg is null, the
     * result is the projection of (1.0,0.0,0.0) onto this plane except that if
     * z-axis = (1.0,0.0,0.0) then (0.0,1.0,0.0) is used as initial value of
     * arg. If arg is in the same direction as the input zAxis, or it is not
     * three-dimensional, null will be returned. If zAxis is null, null will be
     * returned.
     */
    private static IfcDirection ifcFirstProjAxis(IfcDirection zAxis,
                                                 IfcDirection arg) {
        if (zAxis == null) {
            return null;
        }
        IfcDirection xAxis, v, z;
        IfcVector xVec;
        z = ifcNormalise(zAxis);
        if (arg == null) {
            v = z.equals(new IfcDirection(1, 0, 0)) ?
                    new IfcDirection(0, 1, 0) : new IfcDirection(1, 0, 0);
        } else {
            if (arg.getDim().getValue() != 3) {
                return null;
            }
            if (ifcCrossProduct(arg, z).getMagnitude().getValue() == 0) {
                return null;
            } else {
                v = ifcNormalise(arg);
            }
        }
        xVec = ifcScalarTimesVector(ifcDotProduct(v, z), z);
        xAxis = ifcVectorDifference(v, xVec).getOrientation();
        xAxis = ifcNormalise(xAxis);
        return xAxis;
    }

    /**
     * @param scalar The value by which vec should be multiplied.
     * @param vec    The vector to multiply.
     * @return The vector that is the scalar multiple of the input vector. The
     * output is an IfcVector of the same units as the input vector. If any of
     * the input arguments is null, returns null.
     */
    private static IfcVector ifcScalarTimesVector(IfcReal scalar,
                                                  IfcVector vec) {
        if (scalar == null || vec == null) {
            return null;
        }
        IfcDirection v = vec.getOrientation();
        double mag = scalar.getValue() * vec.getMagnitude().getValue();
        if (mag < 0) {
            List<IfcReal> directionRatios = v.getDirectionRatios();
            double[] negativeDirectionRatios =
                    new double[directionRatios.size()];
            for (int i = 0; i < directionRatios.size(); i++) {
                negativeDirectionRatios[i] = -directionRatios.get(i).getValue();
            }
            v = new IfcDirection(negativeDirectionRatios);
            mag = -mag;
        }
        return new IfcVector(ifcNormalise(v), new IfcLengthMeasure(mag));
    }

    /**
     * @param scalar The value by which dir should be multiplied.
     * @param dir    The vector to multiply.
     * @return The vector that is the scalar multiple of the input vector. The
     * output is unitless. If any of the input arguments is null, returns null.
     */
    public static IfcVector ifcScalarTimesVector(IfcReal scalar,
                                                 IfcDirection dir) {
        if (scalar == null || dir == null) {
            return null;
        }
        IfcDirection v = dir;
        double mag = scalar.getValue();
        if (mag < 0) {
            List<IfcReal> directionRatios = v.getDirectionRatios();
            double[] negativeDirectionRatios =
                    new double[directionRatios.size()];
            for (int i = 0; i < directionRatios.size(); i++) {
                negativeDirectionRatios[i] = -directionRatios.get(i).getValue();
            }
            v = new IfcDirection(negativeDirectionRatios);
            mag = -mag;
        }
        return new IfcVector(ifcNormalise(v), new IfcLengthMeasure(mag));
    }

    /**
     * @param arg1 The first argument of the subtraction {@code arg1 - arg2}. If
     *             both input arguments are IfcVectors, they must be expressed
     *             in the same units.
     * @param arg2 The second argument of the subtraction {@code arg1 - arg2}.
     *             If both input arguments are IfcVectors, they must be
     *             expressed in the same units.
     * @return The vector difference of the two input vectors. If both vectors
     * are IfcDirections, a unitless result is produced. A zero difference
     * vector produces a an IfcVector of zero magnitude.
     * @throws IllegalArgumentException If any of the arguments is null, or if
     *                                  they have different dimensionality.
     */
    private static IfcVector ifcVectorDifference(IfcVectorOrDirection arg1,
                                                 IfcVectorOrDirection arg2) {
        if (arg1 == null || arg2 == null ||
                !arg1.getDim().equals(arg2.getDim())) {
            return null;
        }
        IfcDirection vec1, vec2;
        double mag1, mag2;
        if (arg1 instanceof IfcVector) {
            mag1 = ((IfcVector) arg1).getMagnitude().getValue();
            vec1 = ((IfcVector) arg1).getOrientation();
        } else {
            mag1 = 1;
            vec1 = (IfcDirection) arg1;
        }
        if (arg2 instanceof IfcVector) {
            mag2 = ((IfcVector) arg2).getMagnitude().getValue();
            vec2 = ((IfcVector) arg2).getOrientation();
        } else {
            mag2 = 1;
            vec2 = (IfcDirection) arg2;
        }
        vec1 = ifcNormalise(vec1);
        vec2 = ifcNormalise(vec2);
        double mag = 0;
        double[] resultDirectionRatios = new double[vec1.getDim().getValue()];
        for (byte i = 0; i < resultDirectionRatios.length; i++) {
            resultDirectionRatios[i] =
                    mag1 * vec1.getDirectionRatios().get(i).getValue() -
                            mag2 * vec2.getDirectionRatios().get(i).getValue();
            mag += resultDirectionRatios[i] * resultDirectionRatios[1];
        }
        if (mag > 0) {
            return new IfcVector(new IfcDirection(resultDirectionRatios),
                    new IfcLengthMeasure(sqrt(mag)));
        }
        return new IfcVector(vec1, new IfcLengthMeasure(0));
    }

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
     * @throws NullPointerException If unit or dim is null.
     */
    public static Boolean ifcCorrectDimensions(@NonNull IfcUnitEnum unit,
                                               @NonNull IfcDimensionalExponents dim) {
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
     * @throws NullPointerException If name is null.
     */
    public static IfcDimensionalExponents ifcDimensionsForSiUnit(
            @NonNull IfcSIUnitName name) {
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
     * @throws NullPointerException If units is null or has size zero.
     */
    public static boolean ifcCorrectUnitAssignment(Set<IfcUnit> units) {
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
            } else if (unit instanceof IfcDerivedUnit &&
                    ((IfcDerivedUnit) unit).getUnitType() !=
                            IfcDerivedUnitEnum.USERDEFINED) {
                derivedUnitNumber++;
                derivedUnitTypes.add(((IfcDerivedUnit) unit).getUnitType());
            } else if (unit instanceof IfcMonetaryUnit) {
                monetaryUnitNumber++;
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
     *                                  dimensionality.
     * @throws NullPointerException     If at least one of the arguments is
     *                                  null.
     */
    public static IfcReal ifcDotProduct(@NonNull IfcDirection arg1,
                                        @NonNull IfcDirection arg2) {
        if (!arg1.getDim().equals(arg2.getDim())) {
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

    /**
     * The function checks that a relative placement (i.e. relative to another
     * local placement, and not grid placement) of a 3D local placement has to
     * be relative to a 3D parent placement (and not to a 2D parent placement).
     *
     * @param relativePlacement The local placement.
     * @param placementRelTo    The parent placement.
     * @return {@code true} if relativePlacement is bi-dimensional, if it is
     * three-dimensional and placementRelTo is also three-dimensional, if
     * placementRelTo is null. {@code null} if placementRelTo is a grid
     * placement. {@code false} otherwise.
     * @throws IllegalArgumentException If relativePlacement is {@code null}.
     */
    public static Boolean ifcCorrectLocalPlacement(
            IfcAxis2Placement relativePlacement,
            IfcObjectPlacement placementRelTo) {
        if (relativePlacement == null) {
            throw new IllegalArgumentException(
                    "relativePlacement cannot be null");
        }
        if (placementRelTo != null) {
            if (placementRelTo instanceof IfcGridPlacement) {
                return null;
            }
            if (placementRelTo instanceof IfcLocalPlacement) {
                if (relativePlacement instanceof IfcAxis2Placement2D) {
                    return true;
                } else { // relativePlacement is an instance of
                    // IfcAxis2Placement3D
                    return ((IfcLocalPlacement) placementRelTo)
                            .getRelativePlacement().getDim().getValue() == 3;
                }
            }
        }
        return true;
    }

    static Map<String, Predicate<IfcRepresentationItem>> map1 =
            new HashMap<String, Predicate<IfcRepresentationItem>>() {{
                put("Curve2D", item -> item instanceof IfcCurve &&
                        ((IfcCurve) item).getDim().getValue() == 2);
                put("Annotation2D",item->item instanceof IfcPoint || item instanceof IfcCurve ||
                        item instanceof IfcGeometricCurveSet ||
                        item instanceof IfcAnnotationFillArea ||
                        item instanceof IfcDefinedSymbol ||
                        item instanceof IfcTextLiteral ||
                        item instanceof IfcDraughtingCallout);
            }};

    /**
     * The function gets the representation type and the assigned set of
     * representation items as input and verifies whether the correct items are
     * assigned according to the representation type given.
     *
     * @param repType The representation type.
     * @param items   Items associated to repType.
     * @return {@code true} if the type of the objects contained in items is
     * correct according to the specification of class {@link
     * buildingsmart.ifc.IfcShapeRepresentation} or if items is empty; {@code
     * null} if repType is unknown; {@code false} otherwise.
     * @throws NullPointerException If repType is {@code null}, or if items is
     *                              null and repType is known.
     */
    public static Boolean ifcShapeRepresentationTypes(IfcLabel repType,
            Set<IfcRepresentationItem> items) {
        return items.stream().anyMatch(i->!map1.get(repType.getValue()).test(i));

        int correctItems = 0;
        switch (repType.getValue()) {
            case "Curve2D":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcCurve &&
                            ((IfcCurve) item).getDim().getValue() == 2) {
                        correctItems++;
                    }
                }
                break;
            case "Annotation2D":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcPoint || item instanceof IfcCurve ||
                            item instanceof IfcGeometricCurveSet ||
                            item instanceof IfcAnnotationFillArea ||
                            item instanceof IfcDefinedSymbol ||
                            item instanceof IfcTextLiteral ||
                            item instanceof IfcDraughtingCallout) {
                        correctItems++;
                    }
                }
                break;
            case "GeometricSet":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcGeometricSet ||
                            item instanceof IfcPoint ||
                            item instanceof IfcCurve ||
                            item instanceof IfcSurface) {
                        correctItems++;
                    }
                }
                break;
            case "GeometricCurveSet":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcGeometricSet ||
                            item instanceof IfcPoint ||
                            item instanceof IfcCurve) {
                        correctItems++;
                        if (item instanceof IfcGeometricSet) {
                            for (IfcGeometricSetSelect element :
                                    ((IfcGeometricSet) item)
                                    .getElements()) {
                                if (element instanceof IfcSurface) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                break;
            case "SurfaceModel":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcShellBasedSurfaceModel ||
                            item instanceof IfcFaceBasedSurfaceModel ||
                            item instanceof IfcFacetedBrep ||
                            item instanceof IfcFacetedBrepWithVoids) {
                        correctItems++;
                    }
                }
                break;
            case "SolidModel":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcSolidModel) {
                        correctItems++;
                    }
                }
                break;
            case "SweptSolid":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcSweptAreaSolid) {
                        correctItems++;
                    }
                }
                break;
            case "CSG":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcBooleanResult) {
                        correctItems++;
                    }
                }
                break;
            case "Clipping":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcBooleanClippingResult) {
                        correctItems++;
                    }
                }
                break;
            case "AdvancedSweptSolid":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcSurfaceCurveSweptAreaSolid ||
                            item instanceof IfcSweptDiskSolid) {
                        correctItems++;
                    }
                }
                break;
            case "Brep":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcFacetedBrep ||
                            item instanceof IfcFacetedBrepWithVoids) {
                        correctItems++;
                    }
                }
                break;
            case "BoundingBox":
                if (items.size() > 1) {
                    return false;
                }
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcBoundingBox) {
                        correctItems++;
                    }
                }
                break;
            case "SectionedSpine":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcSectionedSpine) {
                        correctItems++;
                    }
                }
                break;
            case "MappedRepresentation":
                for (IfcRepresentationItem item : items) {
                    if (item instanceof IfcMappedItem) {
                        correctItems++;
                    }
                }
                break;
            default:
                return null;
        }
        return correctItems == items.size();
    }
}
