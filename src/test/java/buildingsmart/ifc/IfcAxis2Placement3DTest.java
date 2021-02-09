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

package buildingsmart.ifc;

import org.junit.Assert;
import org.junit.Test;

public class IfcAxis2Placement3DTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_bidimensionalLocation() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0), null,
                        null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_bidimensionalAxis() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(1, 0), new IfcDirection(0, 1, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_bidimensionalRefDirection() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(1, 0, 0), new IfcDirection(0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_parallelAxisAndRefDirection() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(1, 0, 0), new IfcDirection(2, 0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullAxis_nonNullRefDirection() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0), null,
                        new IfcDirection(2, 0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nonNullAxis_nullRefDirection() {
        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(1, 0, 0), null);
    }

    @Test
    public void equals() {
        IfcAxis2Placement3D a1 =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(0, 0, 2), new IfcDirection(4, 8, 0));
        IfcAxis2Placement3D a2 =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(0, 0, 1), new IfcDirection(2, 4, 0));
        Assert.assertEquals(a1, a2);
    }
}