/*
 * Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of ifc-java.
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

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class Functions {

    /**
     * Max allowed difference for doubles to be considered equal in comparisons. Used in this class,
     * {@link IfcDirection} and {@link IfcCartesianPoint}.
     */
    public static double delta = 0.00000001;

    private static final Map<IfcUnitEnum, Predicate<IfcDimensionalExponents>>
            ifcCorrectDimensions = Collections
            .unmodifiableMap(new HashMap<>() {{
                put(IfcUnitEnum.LENGTHUNIT, dim -> dim.equals(new IfcDimensionalExponents(1, 0, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.MASSUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 1, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.TIMEUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 0, 1, 0, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICCURRENTUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 1, 0, 0, 0)));
                put(IfcUnitEnum.THERMODYNAMICTEMPERATUREUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 1, 0, 0)));
                put(IfcUnitEnum.AMOUNTOFSUBSTANCEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 1, 0)));
                put(IfcUnitEnum.LUMINOUSINTENSITYUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1)));
                put(IfcUnitEnum.PLANEANGLEUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.SOLIDANGLEUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.AREAUNIT, dim -> dim.equals(new IfcDimensionalExponents(2, 0, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.VOLUMEUNIT, dim -> dim.equals(new IfcDimensionalExponents(3, 0, 0, 0, 0, 0, 0)));
                put(IfcUnitEnum.ABSORBEDDOSEUNIT, dim -> dim.equals(new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0)));
                put(IfcUnitEnum.RADIOACTIVITYUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICCAPACITANCEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(-2, 1, 4, 1, 0, 0, 0)));
                put(IfcUnitEnum.DOSEEQUIVALENTUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICCHARGEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 0, 1, 1, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICCONDUCTANCEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(-2, -1, 3, 2, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICVOLTAGEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(2, 1, -3, -1, 0, 0, 0)));
                put(IfcUnitEnum.ELECTRICRESISTANCEUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(2, 1, -3, -2, 0, 0, 0)));
                put(IfcUnitEnum.ENERGYUNIT, dim -> dim.equals(new IfcDimensionalExponents(2, 1, -2, 0, 0, 0, 0)));
                put(IfcUnitEnum.FORCEUNIT, dim -> dim.equals(new IfcDimensionalExponents(1, 1, -2, 0, 0, 0, 0)));
                put(IfcUnitEnum.FREQUENCYUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0)));
                put(IfcUnitEnum.INDUCTANCEUNIT, dim -> dim.equals(new IfcDimensionalExponents(2, 1, -2, -2, 0, 0, 0)));
                put(IfcUnitEnum.ILLUMINANCEUNIT, dim -> dim.equals(new IfcDimensionalExponents(-2, 0, 0, 0, 0, 0, 1)));
                put(IfcUnitEnum.LUMINOUSFLUXUNIT, dim -> dim.equals(new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1)));
                put(IfcUnitEnum.MAGNETICFLUXUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(2, 1, -2, -1, 0, 0, 0)));
                put(IfcUnitEnum.MAGNETICFLUXDENSITYUNIT,
                    dim -> dim.equals(new IfcDimensionalExponents(0, 1, -2, -1, 0, 0, 0)));
                put(IfcUnitEnum.POWERUNIT, dim -> dim.equals(new IfcDimensionalExponents(2, 1, -3, 0, 0, 0, 0)));
                put(IfcUnitEnum.PRESSUREUNIT, dim -> dim.equals(new IfcDimensionalExponents(-1, 1, -2, 0, 0, 0, 0)));
            }});
    private static final Map<IfcSIUnitName, IfcDimensionalExponents>
            ifcDimensionsForSiUnit = Collections
            .unmodifiableMap(new HashMap<>() {{
                put(IfcSIUnitName.METRE, new IfcDimensionalExponents(1, 0, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.SQUARE_METRE, new IfcDimensionalExponents(2, 0, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.CUBIC_METRE, new IfcDimensionalExponents(3, 0, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.GRAM, new IfcDimensionalExponents(0, 1, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.SECOND, new IfcDimensionalExponents(0, 0, 1, 0, 0, 0, 0));
                put(IfcSIUnitName.AMPERE, new IfcDimensionalExponents(0, 0, 0, 1, 0, 0, 0));
                put(IfcSIUnitName.KELVIN, new IfcDimensionalExponents(0, 0, 0, 0, 1, 0, 0));
                put(IfcSIUnitName.MOLE, new IfcDimensionalExponents(0, 0, 0, 0, 0, 1, 0));
                put(IfcSIUnitName.CANDELA, new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1));
                put(IfcSIUnitName.RADIAN, new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.STERADIAN, new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0));
                put(IfcSIUnitName.HERTZ, new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0));
                put(IfcSIUnitName.NEWTON, new IfcDimensionalExponents(1, 1, -2, 0, 0, 0, 0));
                put(IfcSIUnitName.PASCAL, new IfcDimensionalExponents(-1, 1, -2, 0, 0, 0, 0));
                put(IfcSIUnitName.JOULE, new IfcDimensionalExponents(2, 1, -2, 0, 0, 0, 0));
                put(IfcSIUnitName.WATT, new IfcDimensionalExponents(2, 1, -3, 0, 0, 0, 0));
                put(IfcSIUnitName.COULOMB, new IfcDimensionalExponents(0, 0, 1, 1, 0, 0, 0));
                put(IfcSIUnitName.VOLT, new IfcDimensionalExponents(2, 1, -3, -1, 0, 0, 0));
                put(IfcSIUnitName.FARAD, new IfcDimensionalExponents(-2, -1, 4, 1, 0, 0, 0));
                put(IfcSIUnitName.OHM, new IfcDimensionalExponents(2, 1, -3, -2, 0, 0, 0));
                put(IfcSIUnitName.SIEMENS, new IfcDimensionalExponents(-2, -1, 3, 2, 0, 0, 0));
                put(IfcSIUnitName.WEBER, new IfcDimensionalExponents(2, 1, -2, -1, 0, 0, 0));
                put(IfcSIUnitName.TESLA, new IfcDimensionalExponents(0, 1, -2, -1, 0, 0, 0));
                put(IfcSIUnitName.HENRY, new IfcDimensionalExponents(2, 1, -2, -2, 0, 0, 0));
                put(IfcSIUnitName.DEGREE_CELSIUS, new IfcDimensionalExponents(0, 0, 0, 0, 1, 0, 0));
                put(IfcSIUnitName.LUMEN, new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 1));
                put(IfcSIUnitName.LUX, new IfcDimensionalExponents(-2, 0, 0, 0, 0, 0, 1));
                put(IfcSIUnitName.BECQUEREL, new IfcDimensionalExponents(0, 0, -1, 0, 0, 0, 0));
                put(IfcSIUnitName.GRAY, new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0));
                put(IfcSIUnitName.SIEVERT, new IfcDimensionalExponents(2, 0, -2, 0, 0, 0, 0));
            }});
    private static final Map<String, Predicate<IfcRepresentationItem>>
            ifcShapeRepresentationTypes = Collections
            .unmodifiableMap(new HashMap<>() {{
                put("Curve2D", item -> item instanceof IfcCurve && ((IfcCurve) item).getDim().getValue() == 2);
                put("Annotation2D",
                    item -> item instanceof IfcPoint || item instanceof IfcCurve ||
                            item instanceof IfcGeometricCurveSet || item instanceof IfcAnnotationFillArea ||
                            item instanceof IfcDefinedSymbol || item instanceof IfcTextLiteral ||
                            item instanceof IfcDraughtingCallout);
                put("GeometricSet",
                    item -> item instanceof IfcGeometricSet || item instanceof IfcPoint || item instanceof IfcCurve ||
                            item instanceof IfcSurface);
                put("GeometricCurveSet", item -> {
                    if (item instanceof IfcGeometricSet && ((IfcGeometricSet) item).getElements().stream().anyMatch(element -> (element instanceof IfcSurface))) {
                        return false;
                    }
                    return item instanceof IfcGeometricSet || item instanceof IfcPoint || item instanceof IfcCurve;
                });
                put("SurfaceModel",
                    item -> item instanceof IfcShellBasedSurfaceModel || item instanceof IfcFaceBasedSurfaceModel ||
                            item instanceof IfcFacetedBrep || item instanceof IfcFacetedBrepWithVoids);
                put("SolidModel", item -> item instanceof IfcSolidModel);
                put("SweptSolid", item -> item instanceof IfcSweptAreaSolid);
                put("CSG", item -> item instanceof IfcBooleanResult);
                put("Clipping", item -> item instanceof IfcBooleanClippingResult);
                put("AdvancedSweptSolid",
                    item -> item instanceof IfcSurfaceCurveSweptAreaSolid || item instanceof IfcSweptDiskSolid);
                put("Brep", item -> item instanceof IfcFacetedBrep || item instanceof IfcFacetedBrepWithVoids);
                put("BoundingBox", item -> item instanceof IfcBoundingBox);
                put("SectionedSpine", item -> item instanceof IfcSectionedSpine);
                put("MappedRepresentation", item -> item instanceof IfcMappedItem);
            }});

    /**
     * This method formats the given String so that it can be serialized in an
     * ASCII STEP file. Characters "'" and "\" will be converted to their
     * representations "\'" and "\\". Characters from the Unicode table "C0
     * Controls and Basic Latin" (= ASCII characters) won't change, with the
     * exception of C0 controls and code point 0x007F. All other characters will
     * be substituted with the representation defined in ISO 10303-11.
     *
     * @param unformatted The String to format.
     * @return The formatted String.
     *
     * @throws NullPointerException If unformatted is null.
     */
    public static String formatForStepFile(@NonNull String unformatted) {
        String escaped = unformatted.replace("\\", "\\\\").replace("'", "''");
        ByteBuffer utf32Bytes =
                ByteBuffer.wrap(escaped.getBytes(Charset.forName("UTF-32")));
        return IntStream.range(0, utf32Bytes.capacity() / Integer.BYTES)
                .map(i -> utf32Bytes.getInt(i * Integer.BYTES))
                .mapToObj(codePoint -> {
                    if (codePoint >= 0x20 && codePoint < 0x7F) {
                        /* the comparison will return false if codePoint has
                        1 as its most significant bit, so we don't need to
                        use the unsigned comparison here */
                        return String.valueOf((char) codePoint);
                    }
                    if (Integer.compareUnsigned(codePoint, 0x100) < 0) {
                        return "\\X\\" + String.format("%02X", codePoint);
                    }
                    if (Integer.compareUnsigned(codePoint, 0x10000) < 0) {
                        return "\\X2\\" + String.format("%04X", codePoint) +
                                "\\X0\\";
                    }
                    return "\\X4\\" + String.format("%08X", codePoint) +
                            "\\X0\\";
                }).collect(Collectors.joining());
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
     *
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
        IfcDirection ifcDirection1 = ifcNormalise(arg1);
        IfcDirection ifcDirection2 = ifcNormalise(arg2);
        if (null == ifcDirection1 || null == ifcDirection2) {
            return result;
        }
        v1Real = ifcDirection1.getDirectionRatios();
        v2Real = ifcDirection2.getDirectionRatios();

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
     * @param components The components of the IfcDirection to be normalized.
     * @return The components, normalized to have a sum of squares of 1.0. If the input argument is null or its
     * components are all zero then the output is null.
     */
    public static double[] ifcNormalise(double @NonNull [] components) {
        double magnitude = 0;
        int dim = components.length;

        for (double component : components) {
            magnitude += (component * component);
        }

        if (magnitude > 0) {
            magnitude = sqrt(magnitude);
            double[] directionRatios = new double[dim];
            for (int i = 0; i < dim; i++) {
                directionRatios[i] = components[i] / magnitude;
            }
            return directionRatios;
        }
        // should only happen if all components are zero
        return null;
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
        double[] normalised =
                ifcNormalise(direction.getDirectionRatios().stream().mapToDouble(IfcReal::getValue).toArray());
        return normalised == null ? null : new IfcDirection(normalised);
    }

    /**
     * @param vector The IfcVector of which the components should be normalized.
     * @return IfcVector whose components are normalized to have a sum of squares of 1.0. If the input argument is null
     * or its components are all zero then the output is null.
     */
    public static IfcVector ifcNormalise(IfcVector vector) {
        if (vector == null || vector.getMagnitude().getValue() == 0) {
            return null;
        }
        if (alreadyNormalised(vector)) {
            return vector;
        }

        double[] normalised =
                ifcNormalise(vector.getDirectionRatios().stream().mapToDouble(IfcReal::getValue).toArray());
        return normalised == null ? null : new IfcVector(new IfcDirection(normalised), new IfcLengthMeasure(1));
    }

    /**
     * @param components The components of the IfcDirection on which to perform the check.
     * @return {@code true} if the components are already normalized (meaning that
     * the sum of their squares is 1), {@code false} otherwise.
     *
     * @throws NullPointerException If components is null.
     */
    public static boolean alreadyNormalised(double @NonNull [] components) {
        double squaresSum = 0;
        for (double component : components) {
            squaresSum += component * component;
        }
        return Math.abs(squaresSum - 1) <= delta;
    }

    /**
     * @param vector The vector on which to perform the check.
     * @return {@code true} if the vector is already normalized (meaning that
     * the sum of the squares of its components is 1), {@code false} otherwise.
     *
     * @throws NullPointerException If vector is null.
     */
    private static boolean alreadyNormalised(IfcVector vector) {
        if (vector.getMagnitude().getValue() != 1) {
            return false;
        }
        return alreadyNormalised(vector.getOrientation().getDirectionRatios().stream().mapToDouble(IfcReal::getValue)
                                         .toArray());
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
     * are correct, {@code false} otherwise. If the given unit type is {@link
     * IfcUnitEnum#USERDEFINED} or null, this method returns {@code null}.
     */
    public static Boolean ifcCorrectDimensions(IfcUnitEnum unit,
                                               IfcDimensionalExponents dim) {
        if (!ifcCorrectDimensions.containsKey(unit)) {
            return null;
        }
        try {
            return ifcCorrectDimensions.get(unit).test(dim);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * @param name The name of the unit for which the dimensional exponents will
     *             be returned.
     * @return The dimensional exponents of the given SI-unit. If the unit is
     * null, dimensional exponents all equal to zero will be returned.
     */
    public static IfcDimensionalExponents ifcDimensionsForSiUnit(IfcSIUnitName name) {
        if (name == null) {
            return new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
        }
        return ifcDimensionsForSiUnit.get(name);
    }

    /**
     * @param units The Set of IfcUnit for which to check if it's an appropriate
     *              parameter for
     *              {@link IfcUnitAssignment#IfcUnitAssignment(Set)}.
     * @return {@code true}, if the Set of IfcUnit only includes units with
     * different unitType (for IfcNamedUnit and IfcDerivedUnit), and a maximum
     * of one IfcMonetaryUnit.
     *
     * @throws IllegalArgumentException If units has size zero.
     */
    public static boolean ifcCorrectUnitAssignment(Set<IfcUnit> units) {
        if (units == null) {
            return true;
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
     * @return The scalar (or dot) product of the two directions, {@code null}
     * if at least one argument is {@code null}, or if the arguments have
     * different dimensionality.
     */
    public static IfcReal ifcDotProduct(IfcDirection arg1, IfcDirection arg2) {
        if (arg1 == null || arg2 == null ||
                !arg1.getDim().equals(arg2.getDim())) {
            return null;
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
     * three-dimensional and placementRelTo is also three-dimensional, if at
     * least one of the arguments is null; {@code null} if placementRelTo is a
     * grid placement; {@code false} otherwise.
     */
    public static Boolean ifcCorrectLocalPlacement(IfcAxis2Placement relativePlacement,
                                                   IfcObjectPlacement placementRelTo) {
        if (relativePlacement == null || placementRelTo == null) {
            return true;
        }
        if (placementRelTo instanceof IfcGridPlacement) {
            return null;
        }
        if (placementRelTo instanceof IfcLocalPlacement) {
            if (relativePlacement instanceof IfcAxis2Placement2D) {
                return true;
            } else { // relativePlacement is an instance of IfcAxis2Placement3D
                return ((IfcLocalPlacement) placementRelTo)
                        .getRelativePlacement().getDim().getValue() == 3;
            }
        }
        return null;
    }

    /**
     * The function gets the representation type and the assigned set of
     * representation items as input and verifies whether the correct items are
     * assigned according to the representation type given.
     *
     * @param repType The representation type.
     * @param items   Items associated to repType.
     * @return {@code true} if the type of the objects contained in items is
     * correct according to the specification of class {@link
     * buildingsmart.ifc.IfcShapeRepresentation}, or if {@code repType} is
     * defined in the specification and items is null or empty;</p> {@code null}
     * if {@code repType} is null or not defined in the specification;</p>
     * {@code false} otherwise.
     */
    public static Boolean ifcShapeRepresentationTypes(IfcLabel repType,
                                                      Set<IfcRepresentationItem> items) {
        if (repType == null ||
                !ifcShapeRepresentationTypes.containsKey(repType.getValue())) {
            return null;
        }
        Stream<IfcRepresentationItem> itemsStream;
        try {
            itemsStream = items.stream();
        } catch (NullPointerException e) {
            return true;
        }
        if (repType.getValue().equals("BoundingBox") && items.size() > 1) {
            return false;
        }
        return itemsStream.allMatch(item -> ifcShapeRepresentationTypes
                .get(repType.getValue()).test(item));
    }

    /**
     * @param refDirection The direction for which to find a perpendicular
     *                     direction. It must be two-dimensional.
     * @return This function returns two orthogonal directions. The first is in
     * the direction of refDirection and the second is perpendicular to the
     * first. A default value of (1.0,0.0) is supplied for refDirection if the
     * input data is incomplete.
     */
    public static List<IfcDirection> ifcBuild2Axes(IfcDirection refDirection) {
        IfcDirection d = ifcNormalise(refDirection);
        d = d != null ? d : new IfcDirection(1, 0);
        return Arrays.asList(d, ifcOrthogonalComplement(d));
    }

    /**
     * @param vec The direction for which to find a perpendicular direction. It
     *            must be two-dimensional
     * @return A direction perpendicular to the input direction, {@code null} if
     * the input direction is null or not two-dimensional.
     */
    private static IfcDirection ifcOrthogonalComplement(IfcDirection vec) {
        if (vec == null || vec.getDim().getValue() != 2) {
            return null;
        }
        return new IfcDirection(-vec.getDirectionRatios().get(1).getValue(),
                                vec.getDirectionRatios().get(0).getValue());
    }

    /**
     * @param value The value that might get rounded.
     * @return The closest integer to {@code value} (as described in the documentation of {@link Math#round(double)}) if
     * the difference between {@code value} and {@code Math.round(value)} is lower or equal to {@link #delta},
     * {@code value} otherwise.
     */
    public static double round(double value) {
        double roundedValue = Math.round(value);
        if (Math.abs(value - roundedValue) > delta) {
            return value;
        } else {
            return roundedValue;
        }
    }
}
