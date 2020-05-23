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

package buildingsmart.ifc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IfcVectorTest {

    /**
     * Tests whether two vectors with different orientation but magnitude equal
     * to 0 are considered equal.
     */
    @Test
    public void testZeroMagEquals() {
        IfcVector zeroMag1 = new IfcVector(new IfcDirection(1, 1), new IfcLengthMeasure(0));
        IfcVector zeroMag2 = new IfcVector(new IfcDirection(3, 7), new IfcLengthMeasure(0));
        assertEquals(zeroMag1, zeroMag2);
    }

    @Test
    public void testNonEqual() {
        IfcVector v1 = new IfcVector(new IfcDirection(1, 1), new IfcLengthMeasure(5));
        IfcVector v2 = new IfcVector(new IfcDirection(3, 7), new IfcLengthMeasure(7));
        assertNotEquals(v1, v2);

    }
}