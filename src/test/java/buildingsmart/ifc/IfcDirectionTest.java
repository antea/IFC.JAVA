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

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IfcDirectionTest {

    /**
     * Tests whether an object initialized with the varargs constructor is the
     * same as one initialized with the constructor that takes a List as input,
     * provided that the values passed in the varargs constructor are the ones
     * contained in the List.
     */
    @Test
    public void varArgsConstructor() {
        double d0 = 0.1;
        double d1 = 0.3;
        double d2 = 4;
        IfcReal[] directionRatios = new IfcReal[3];
        directionRatios[0] = new IfcReal(d0);
        directionRatios[1] = new IfcReal(d1);
        directionRatios[2] = new IfcReal(d2);
        IfcDirection expectedIfcDirection =
                new IfcDirection(Arrays.asList(directionRatios));

        IfcDirection ifcDirection = new IfcDirection(d0, d1, d2);

        assertEquals(expectedIfcDirection, ifcDirection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructor() {
        IfcDirection ifcDirection = new IfcDirection((List<IfcReal>) null);
    }

    @Test
    public void equals() {
        IfcDirection d1 = new IfcDirection(3, 6, 9);
        IfcDirection d2 = new IfcDirection(1, 2, 3);
        Assert.assertEquals(d1, d2);
    }
}