/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

import buildingsmart.io.Attribute;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The vector is defined in terms of the direction and magnitude of the vector.
 * The value of the magnitude attribute defines the magnitude of the vector.
 */
@Getter
@ToString
public class IfcVector extends IfcGeometricRepresentationItem
        implements IfcVectorOrDirection, Serializable {
    @Attribute(0)
    private final IfcDirection orientation;
    @Attribute(1)
    private final IfcLengthMeasure magnitude;
    //private int dim;

    /**
     * @param orientation The direction of the vector. Cannot be null.
     * @param magnitude   The magnitude of the vector. All vectors of Magnitude
     *                    0.0 are regarded as equal in value regardless of the
     *                    orientation attribute. Cannot be null.
     * @throws NullPointerException     If orientation or magnitude are null.
     * @throws IllegalArgumentException If magnitude is not positive or zero.
     */
    public IfcVector(@NonNull IfcDirection orientation,
                     @NonNull IfcLengthMeasure magnitude) {
        if (magnitude.getValue() < 0) {
            throw new IllegalArgumentException(
                    "magnitude must be equal or higher than zero");
        }
        this.orientation = orientation;
        this.magnitude = magnitude;
    }

    /**
     * @return The space dimensionality of this class, defined by the number of
     * real in the list of DirectionRatios.
     */
    @Override
    public IfcDimensionCount getDim() {
        return orientation.getDim();
    }

    /**
     * @return The components of the direction in the direction of X axis
     * (DirectionRatios[1]), of Y axis (DirectionRatios[2]), and of Z axis
     * (DirectionRatios[3])
     */
    @Override
    public List<IfcReal> getDirectionRatios() {
        return orientation.getDirectionRatios();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcVector ifcVector = (IfcVector) o;
        return (magnitude.getValue() == 0 &&
                ifcVector.magnitude.getValue() == 0) ||
                (orientation.equals(ifcVector.orientation) &&
                        magnitude.equals(ifcVector.magnitude));
    }

    @Override
    public int hashCode() {
        if (magnitude.getValue() == 0) {
            return Objects.hash(magnitude);
        }
        return Objects.hash(orientation, magnitude);
    }
}
