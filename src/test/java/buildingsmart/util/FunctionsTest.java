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

import buildingsmart.ifc.IfcDirection;
import buildingsmart.ifc.IfcLengthMeasure;
import buildingsmart.ifc.IfcReal;
import buildingsmart.ifc.IfcVector;
import org.junit.Test;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FunctionsTest {
    // max allowed difference for doubles to be considered equal in comparisons
    private static final double DELTA = 0.000000000000001;

    @Test(expected = IllegalArgumentException.class)
    public void ifcCrossProduct_nullDirection() {
        Functions.ifcCrossProduct(null, new IfcDirection(1, 2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifcCrossProduct_bidimensionalDirection() {
        Functions.ifcCrossProduct(new IfcDirection(2, 6, 3), new IfcDirection(1, 9));
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
                .ifcCrossProduct(new IfcDirection(-1, -2, -4), new IfcDirection(1, 2, 4));
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
        assertEquals(expectedComp, vector.getDirectionRatios().get(2).getValue(), DELTA);
        assertEquals(vector.getMagnitude().getValue(), abs(vector.getDirectionRatios().get(2).getValue()), DELTA);
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
        IfcVector zeroDirection = new IfcVector(new IfcDirection(0, 0, 0), new IfcLengthMeasure(3));
        assertNull(Functions.ifcNormalise(zeroDirection));
    }

    @Test
    public void ifcNormaliseIfcVector_zeroMagnitude_returnsNull() {
        IfcVector zeroMagnitude = new IfcVector(new IfcDirection(4, 3, 1), new IfcLengthMeasure(0));
        assertNull(Functions.ifcNormalise(zeroMagnitude));
    }

    @Test
    public void ifcNormaliseIfcVector_regularVector_isNormalized() {
        IfcVector vector = new IfcVector(new IfcDirection(4, 4, 4), new IfcLengthMeasure(10));
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
}