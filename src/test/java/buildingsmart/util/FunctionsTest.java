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
import org.junit.Test;

import java.util.*;

import static buildingsmart.util.Functions.DELTA;
import static buildingsmart.util.Functions.ifcDotProduct;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionsTest {
    private static final IfcAxis2Placement3D valid3DRelativePlacement =
            new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0), null, null);

    /**
     * @param directions The directions on which to perform the check.
     * @return {@code true} if the supplied directions are all orthogonal to
     * each other, {@code false} otherwise.
     * @throws NullPointerException If directions is null.
     */
    private static boolean checkOrtogonality(List<IfcDirection> directions) {
        List<List<IfcDirection>> combinations = getCombinations(directions, 2);
        for (List<IfcDirection> pair : combinations) {
            double dotProduct =
                    ifcDotProduct(pair.get(0), pair.get(1)).getValue();
            if (Math.abs(dotProduct) > DELTA) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns all possible combinations for the given items and subset size.
     *
     * @param items The list of objects for which to get all possible
     *              combinations of the given size.
     * @param size  The size of each combination.
     * @param <T>   The type of the objects contained in items.
     * @return A List containing all subsets of items of the given size.
     * @throws NullPointerException If items is null.
     */
    private static <T> List<List<T>> getCombinations(List<T> items, int size) {
        if (0 == size) {
            return Collections.singletonList(Collections.emptyList());
        }
        if (items.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<T>> combination = new LinkedList<>();
        T currentItem = items.iterator().next();
        List<T> subSet = new LinkedList<>(items);
        subSet.remove(currentItem);
        List<List<T>> subSetCombination = getCombinations(subSet, size - 1);
        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<>(set);
            newSet.add(0, currentItem);
            combination.add(newSet);
        }
        combination.addAll(getCombinations(subSet, size));
        return combination;
    }

    @Test
    public void ifcCrossProduct_nullDirection() {
        assertNull(Functions.ifcCrossProduct(null, new IfcDirection(1, 2, 3)));
    }

    @Test
    public void ifcCrossProduct_bidimensionalDirection() {
        assertNull(Functions.ifcCrossProduct(new IfcDirection(2, 6, 3),
                new IfcDirection(1, 9)));
    }

    @Test
    public void ifcCrossProduct_componentsAreZero_returnsZeroMagnitudeVector() {
        IfcVector vector = Functions.ifcCrossProduct(new IfcDirection(0, 0, 0),
                new IfcDirection(1, 2, 3));
        assertEquals(0, vector.getMagnitude().getValue(), DELTA);
    }

    @Test
    public void ifcCrossProduct_parallelDirections_returnsZeroMagnitudeVector() {
        IfcVector vector = Functions.ifcCrossProduct(new IfcDirection(2, 4, 8),
                new IfcDirection(1, 2, 4));
        assertEquals(0, vector.getMagnitude().getValue(), DELTA);
    }

    @Test
    public void ifcCrossProduct_antiParallelDirections_returnsZeroMagnitudeVector() {
        IfcVector vector = Functions
                .ifcCrossProduct(new IfcDirection(-1, -2, -4),
                        new IfcDirection(1, 2, 4));
        assertEquals(0, vector.getMagnitude().getValue(), DELTA);
    }

    @Test
    public void ifcCrossProduct_regularDirections() {
        IfcDirection dir1 = new IfcDirection(3, 4, 0);
        IfcDirection dir2 = new IfcDirection(2, -5, 0);
        IfcDirection dir1Norm = Functions.ifcNormalise(dir1);
        IfcDirection dir2Norm = Functions.ifcNormalise(dir2);
        double expectedComp = dir1Norm.getDirectionRatios().get(0).getValue() *
                dir2Norm.getDirectionRatios().get(1).getValue() -
                dir1Norm.getDirectionRatios().get(1).getValue() *
                        dir2Norm.getDirectionRatios().get(0).getValue();

        IfcVector vector = Functions.ifcCrossProduct(dir1, dir2);

        // since the two input directions are on the same plane, we expect
        // the result to be on the axis perpendicular to that plane
        assertEquals(0, vector.getDirectionRatios().get(0).getValue(), DELTA);
        assertEquals(0, vector.getDirectionRatios().get(1).getValue(), DELTA);
        assertEquals(expectedComp,
                vector.getDirectionRatios().get(2).getValue(), DELTA);
        assertEquals(vector.getMagnitude().getValue(),
                abs(vector.getDirectionRatios().get(2).getValue()), DELTA);
    }

    @Test
    public void ifcNormaliseIfcDirection_nullDirection_returnsNull() {
        assertNull(Functions.ifcNormalise((IfcDirection) null));
    }

    @Test
    public void ifcNormaliseIfcDirection_componentsAreZero_returnsNull() {
        IfcDirection zero = new IfcDirection(0, 0, 0);
        assertNull(Functions.ifcNormalise(zero));
    }

    @Test
    public void ifcNormaliseIfcDirection_regularDirection_isNormalized() {
        IfcDirection direction = new IfcDirection(4, 4, 4);
        // components of a normalized vector have a sum of squares of 1
        double expectedComponents = sqrt(1.0 / direction.getDim().getValue());

        IfcDirection normalisedDirection = Functions.ifcNormalise(direction);

        for (IfcReal comp : normalisedDirection.getDirectionRatios()) {
            assertEquals(expectedComponents, comp.getValue(), DELTA);
        }
    }

    @Test
    public void ifcNormaliseIfcVector_nullVector_returnsNull() {
        assertNull(Functions.ifcNormalise((IfcVector) null));
    }

    @Test
    public void ifcNormaliseIfcVector_directionComponentsAreZero_returnsNull() {
        IfcVector zeroDirection = new IfcVector(new IfcDirection(0, 0, 0),
                new IfcLengthMeasure(3));
        assertNull(Functions.ifcNormalise(zeroDirection));
    }

    @Test
    public void ifcNormaliseIfcVector_zeroMagnitude_returnsNull() {
        IfcVector zeroMagnitude = new IfcVector(new IfcDirection(4, 3, 1),
                new IfcLengthMeasure(0));
        assertNull(Functions.ifcNormalise(zeroMagnitude));
    }

    @Test
    public void ifcNormaliseIfcVector_regularVector_isNormalized() {
        IfcVector vector = new IfcVector(new IfcDirection(4, 4, 4),
                new IfcLengthMeasure(10));
        // components of a normalized vector have a sum of squares of 1
        double expectedComponents = sqrt(1.0 / vector.getDim().getValue());
        double expectedMagnitude = 1;

        IfcVector normalisedVector = Functions.ifcNormalise(vector);

        for (IfcReal comp : normalisedVector.getDirectionRatios()) {
            assertEquals(expectedComponents, comp.getValue(), DELTA);
        }
        assertEquals(expectedMagnitude,
                normalisedVector.getMagnitude().getValue(), DELTA);
    }

    @Test
    public void ifcBuildAxes_bothNull() {
        List<IfcDirection> directions = Functions.ifcBuildAxes(null, null);
        assertTrue(checkOrtogonality(directions));
    }

    @Test
    public void ifcBuildAxes_notNullArguments() {
        List<IfcDirection> directions = Functions
                .ifcBuildAxes(new IfcDirection(3, 10, 8),
                        new IfcDirection(9, 0, 1));
        assertTrue(checkOrtogonality(directions));
    }

    @Test
    public void ifcCorrectDimensions_userDefined() {
        assertNull(Functions.ifcCorrectDimensions(IfcUnitEnum.USERDEFINED,
                new IfcDimensionalExponents(123, 456, 678, 0, 0, 0, 0)));
    }

    @Test(expected = NullPointerException.class)
    public void ifcCorrectDimensions_nullUnit() {
        //noinspection ConstantConditions
        Functions.ifcCorrectDimensions(null,
                new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void ifcCorrectDimensions_nullExponents() {
        Functions.ifcCorrectDimensions(IfcUnitEnum.LENGTHUNIT, null);
    }

    @Test
    public void ifcCorrectDimensions_wrongExponents() {
        //noinspection ConstantConditions
        assertFalse(Functions.ifcCorrectDimensions(IfcUnitEnum.LENGTHUNIT,
                new IfcDimensionalExponents(0, 345, 112, 0, 0, 345, 0)));
    }

    @Test
    public void ifcCorrectDimensions_correctExponents() {
        //noinspection ConstantConditions
        assertTrue(Functions.ifcCorrectDimensions(IfcUnitEnum.LENGTHUNIT,
                new IfcDimensionalExponents(1, 0, 0, 0, 0, 0, 0)));
    }

    @Test(expected = NullPointerException.class)
    public void ifcDimensionsForSiUnit_null() {
        Functions.ifcDimensionsForSiUnit(null);
    }

    @Test(expected = NullPointerException.class)
    public void ifcCorrectUnitAssignment_null() {
        Functions.ifcCorrectUnitAssignment(null);
    }

    /**
     * Tests whether adding multiple IfcNamedUnits with the same unitType to the
     * Set passed to ifcCorrectUnitAssignment() makes it return {@code false}.
     */
    @Test
    public void ifcCorrectUnitAssignment_duplicateType() {
        Set<IfcUnit> units = new HashSet<>();
        IfcSIUnit radian = new IfcSIUnit(IfcUnitEnum.PLANEANGLEUNIT, null,
                IfcSIUnitName.RADIAN);
        units.add(radian);
        IfcMeasureWithUnit conversionFactor = new IfcMeasureWithUnit(
                new IfcPlaneAngleMeasure(0.017453292519943295), radian);
        IfcConversionBasedUnit degree = new IfcConversionBasedUnit(
                new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0),
                IfcUnitEnum.PLANEANGLEUNIT, new IfcLabel("DEGREE"),
                conversionFactor);
        units.add(degree);
        assertFalse(Functions.ifcCorrectUnitAssignment(units));
    }

    /**
     * Tests whether adding multiple IfcNamedUnits with different unitType to
     * the Set passed to ifcCorrectUnitAssignment() makes it return {@code
     * true}.
     */
    @Test
    public void ifcCorrectUnitAssignment_differentType() {
        Set<IfcUnit> units = new HashSet<>();
        IfcSIUnit radian = new IfcSIUnit(IfcUnitEnum.PLANEANGLEUNIT, null,
                IfcSIUnitName.RADIAN);
        units.add(radian);
        IfcSIUnit newton = new IfcSIUnit(IfcUnitEnum.FORCEUNIT, null,
                IfcSIUnitName.NEWTON);
        units.add(newton);
        assertTrue(Functions.ifcCorrectUnitAssignment(units));
    }

    @Test(expected = NullPointerException.class)
    public void ifcDotProduct_nullArgument() {
        Functions.ifcDotProduct(null, new IfcDirection(0, 0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifcDotProduct_differentDimensions() {
        Functions.ifcDotProduct(new IfcDirection(0, 1),
                new IfcDirection(0, 0, 1));
    }

    @Test
    public void ifcDotProduct_sameDimensions() {
        double expectedResult = 0.33833126393397145;
        double result = Functions.ifcDotProduct(new IfcDirection(3.5, 1),
                new IfcDirection(8.234, 123)).getValue();
        assertEquals(expectedResult, result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifcCorrectLocalPlacement_nullRelativePlacement() {
        IfcLocalPlacement placementRelTo = mock(IfcLocalPlacement.class);
        Functions.ifcCorrectLocalPlacement(null, placementRelTo);
    }

    @Test
    public void ifcCorrectLocalPlacement_nullPlacementRelTo() {
        assertTrue(Functions
                .ifcCorrectLocalPlacement(valid3DRelativePlacement, null));
    }

    @Test
    public void ifcCorrectLocalPlacement_placementRelTo_instanceof_IfcGridPlacement() {
        IfcAxis2Placement3D relativePlacement =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0), null,
                        null);
        assertNull(Functions.ifcCorrectLocalPlacement(valid3DRelativePlacement,
                new IfcGridPlacement() {
                    @Override
                    public boolean equals(Object o) {
                        return false;
                    }

                    @Override
                    public int hashCode() {
                        return 0;
                    }
                }));
    }

    @Test
    public void ifcCorrectLocalPlacement_3DrelativePlacement_3DplacementRelTo() {
        IfcLocalPlacement mock3DplacementRelTo = mock(IfcLocalPlacement.class);
        when(mock3DplacementRelTo.getRelativePlacement())
                .thenReturn(valid3DRelativePlacement);
        assertTrue(Functions.ifcCorrectLocalPlacement(valid3DRelativePlacement,
                mock3DplacementRelTo));
    }

    @Test
    public void ifcCorrectLocalPlacement_3DrelativePlacement_2DplacementRelTo() {
        IfcAxis2Placement2D valid2DRelativePlacement =
                new IfcAxis2Placement2D(new IfcCartesianPoint(0, 0), null);
        IfcLocalPlacement mock2DplacementRelTo = mock(IfcLocalPlacement.class);
        when(mock2DplacementRelTo.getRelativePlacement())
                .thenReturn(valid2DRelativePlacement);
        assertFalse(Functions.ifcCorrectLocalPlacement(valid3DRelativePlacement,
                mock2DplacementRelTo));
    }

    @Test(expected = NullPointerException.class)
    public void ifcShapeRepresentationTypes_nullRepType() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        items.add(mock(IfcRepresentationItem.class));
        Functions.ifcShapeRepresentationTypes(null, items);
    }

    @Test(expected = NullPointerException.class)
    public void ifcShapeRepresentationTypes_knownRepType_nullItems() {
        Functions.ifcShapeRepresentationTypes(new IfcLabel("GeometricSet"),
                null);
    }

    @Test
    public void ifcShapeRepresentationTypes_unknownRepType_nullItems() {
        assertNull(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("Agagagaga"), null));
    }

    /**
     * Tests whether a Set of bi-dimensional IfcCurve objects with
     * representationType "Curve2D" contains only appropriate items.
     */
    @Test
    public void ifcShapeRepresentationTypes_correctCurve2D() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        IfcDimensionCount bidimensional = new IfcDimensionCount(2);
        IfcEllipse ellipse = mock(IfcEllipse.class);
        IfcLine line = mock(IfcLine.class);
        IfcPolyline polyline = mock(IfcPolyline.class);
        when(ellipse.getDim()).thenReturn(bidimensional);
        when(line.getDim()).thenReturn(bidimensional);
        when(polyline.getDim()).thenReturn(bidimensional);
        items.add(ellipse);
        items.add(line);
        items.add(polyline);

        assertTrue(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("Curve2D"), items));
    }

    /**
     * Tests whether a Set of bi-dimensional and three-dimensional IfcCurve
     * objects with representationType "Curve2D" contains any wrong items.
     */
    @Test
    public void ifcShapeRepresentationTypes_threeDimensionalCurve2D() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        IfcDimensionCount bidimensional = new IfcDimensionCount(2);
        IfcDimensionCount threedimensional = new IfcDimensionCount(3);
        IfcEllipse ellipse = mock(IfcEllipse.class);
        IfcLine line = mock(IfcLine.class);
        IfcPolyline polyline = mock(IfcPolyline.class);
        when(ellipse.getDim()).thenReturn(threedimensional);
        when(line.getDim()).thenReturn(bidimensional);
        when(polyline.getDim()).thenReturn(bidimensional);
        items.add(ellipse);
        items.add(line);
        items.add(polyline);

        assertFalse(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("Curve2D"), items));
    }

    /**
     * Tests whether a Set of bi-dimensional IfcCurve objects and a solid with
     * representationType "Curve2D" contains any wrong items.
     */
    @Test
    public void ifcShapeRepresentationTypes_wrongCurve2D() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        IfcDimensionCount bidimensional = new IfcDimensionCount(2);
        IfcExtrudedAreaSolid solid = mock(IfcExtrudedAreaSolid.class);
        IfcLine line = mock(IfcLine.class);
        IfcPolyline polyline = mock(IfcPolyline.class);
        when(line.getDim()).thenReturn(bidimensional);
        when(polyline.getDim()).thenReturn(bidimensional);
        items.add(solid);
        items.add(line);
        items.add(polyline);

        assertFalse(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("Curve2D"), items));
    }

    /**
     * Tests whether a Set of bi-dimensional IfcCurve and IfcPoint objects with
     * representationType "GeometricCurveSet" contains only appropriate items.
     */
    @Test
    public void ifcShapeRepresentationTypes_correctGeometricCurveSet() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        IfcCartesianPoint mockPoint = mock(IfcCartesianPoint.class);
        IfcLine mockLine = mock(IfcLine.class);
        when(mockPoint.getDim()).thenReturn(new IfcDimensionCount(2));
        when(mockLine.getDim()).thenReturn(new IfcDimensionCount(2));
        IfcGeometricSet geometricSet = new IfcGeometricSet(mockPoint, mockLine);
        items.add(geometricSet);
        items.add(mock(IfcLine.class));
        items.add(mock(IfcPolyline.class));
        items.add(mock(IfcCartesianPoint.class));

        assertTrue(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("GeometricCurveSet"),
                        items));
    }

    /**
     * Tests whether a Set of bi-dimensional IfcCurve, IfcPoint and IfcSurface
     * objects with representationType "GeometricCurveSet" contains any wrong
     * items.
     */
    @Test
    public void ifcShapeRepresentationTypes_GeometricCurveSet_geometricSetwithSurfaces() {
        Set<IfcRepresentationItem> items = new HashSet<>();
        IfcSurface mockSurface = mock(IfcSurfaceOfRevolution.class);
        IfcLine mockLine = mock(IfcLine.class);
        when(mockSurface.getDim()).thenReturn(new IfcDimensionCount(2));
        when(mockLine.getDim()).thenReturn(new IfcDimensionCount(2));
        IfcGeometricSet geometricSet =
                new IfcGeometricSet(mockSurface, mockLine);
        items.add(mock(IfcLine.class));
        items.add(mock(IfcPolyline.class));
        items.add(mock(IfcCartesianPoint.class));
        items.add(geometricSet);

        assertFalse(Functions
                .ifcShapeRepresentationTypes(new IfcLabel("GeometricCurveSet"),
                        items));
    }
}