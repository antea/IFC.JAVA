/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

import buildingsmart.io.Attribute;
import buildingsmart.util.Functions;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * The location and orientation in three dimensional space of three mutually
 * perpendicular axes. An axis2_placement_3D is defined in terms of a point
 * (inherited from placement supertype) and two (ideally orthogonal) axes. It
 * can be used to locate and originate an object in three dimensional space and
 * to define a placement coordinate system. The entity includes a point which
 * forms the origin of the placement coordinate system. Two direction vectors
 * are required to complete the definition of the placement coordinate system.
 * The axis is the placement Z axis direction and the ref_direction is an
 * approximation to the placement X axis direction.
 */
public class IfcAxis2Placement3D extends IfcPlacement
        implements IfcAxis2Placement {
    @Attribute(order = 1)
    private final IfcDirection axis;
    @Attribute(order = 2)
    private final IfcDirection refDirection;
    /**
     * The normalized directions of the placement X Axis (P[0]) and the
     * placement Y Axis (P[1]) and the placement Z Axis (P[2]).
     */
    private final List<IfcDirection> p;

    /**
     * @param location     The location of the three mutually perpendicular
     *                     axes. Cannot be null.
     * @param axis         The exact direction of the local Z Axis.
     * @param refDirection The direction used to determine the direction of the
     *                     local X Axis. If necessary an adjustment is made to
     *                     maintain orthogonality to the Axis direction. If Axis
     *                     and/or RefDirection is omitted, these directions are
     *                     taken from the geometric coordinate system.
     * @throws IllegalArgumentException If any of the following conditions is
     *                                  not met:
     *                                  <bl>
     *                                  <li>the dimensionality of the placement
     *                                  location shall be 3;</li>
     *                                  <li>the Axis (when given) should only
     *                                  reference a three-dimensional
     *                                  IfcDirection;</li>
     *                                  <li>the RefDirection (when given)
     *                                  should only reference a
     *                                  three-dimensional
     *                                  IfcDirection;</li>
     *                                  <li>the Axis and RefDirection shall not
     *                                  be parallel or anti-parallel;</li>
     *                                  <li>either both (Axis and RefDirection)
     *                                  are not given and therefore defaulted,
     *                                  or both shall be given.</li>
     *                                  </bl>
     */
    public IfcAxis2Placement3D(@NonNull IfcCartesianPoint location,
                               IfcDirection axis, IfcDirection refDirection) {
        super(location);
        if (super.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "location must have dimensionality equal to 3");
        }
        if ((axis == null && refDirection != null) ||
                (axis != null && refDirection == null)) {
            throw new IllegalArgumentException(
                    "either both axis and refDirection are set, or none " +
                            "should be set");
        }
        if (axis != null && axis.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "if axis is not null, it must have dimensionality equal " +
                            "to 3");
        }
        if (refDirection != null && refDirection.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "if refDirection is not null, it must have dimensionality" +
                            " equal to 3");
        }
        if (axis != null &&
                Functions.ifcCrossProduct(axis, refDirection).getMagnitude()
                        .getValue() <= 0) {
            throw new IllegalArgumentException(
                    "axis and refDirection cannot be parallel or " +
                            "anti-parallel");
        }
        this.axis = axis;
        this.refDirection = refDirection;
        p = Functions.ifcBuildAxes(axis, refDirection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        IfcAxis2Placement3D that = (IfcAxis2Placement3D) o;
        return Objects.equals(p, that.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), p);
    }
}
