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
import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This entity defines a general direction vector in two or three dimensional space. The actual magnitudes of the
 * components have no effect upon the direction being defined, only the ratios X:Y:Z or X:Y are significant.
 */
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class IfcDirection extends IfcGeometricRepresentationItem implements IfcVectorOrDirection, Serializable {
    @Getter
    @Attribute(0)
    private final List<IfcReal> directionRatios;
    /**
     * This field is not part of the IFC specification, its only purpose is being used in equals() and hashCode() to
     * avoid writing multiple IfcDirection in the output IFC file when different IfcDirection objects actually represent
     * the same direction.
     */
    @Getter
    @EqualsAndHashCode.Include
    private final List<IfcReal> normalisedDirectionRatios;
    @Getter
    private final IfcDimensionCount dim; // derived attribute

    /**
     * @param directionRatios The components in the direction of X axis (DirectionRatios[1]), of Y axis
     *                        (DirectionRatios[2]), and of Z axis (DirectionRatios[3]). The size of this list must be
     *                        either 2 or 3, and it cannot be null.
     * @throws NullPointerException     If directionRatios is null.
     * @throws IllegalArgumentException If the size of directionRatios is not 2 or 3.
     */
    public IfcDirection(@NonNull List<IfcReal> directionRatios) {

        if (directionRatios.size() < 2 || directionRatios.size() > 3) {
            throw new IllegalArgumentException("size of directionRatios must be 2 or 3");
        }
        this.directionRatios =
                directionRatios.stream().map(dirRatio -> new IfcReal(Functions.round(dirRatio.getValue())))
                        .collect(Collectors.toUnmodifiableList());
        this.dim = new IfcDimensionCount(this.directionRatios.size());

        double[] dirRatios = this.directionRatios.stream().mapToDouble(IfcReal::getValue).toArray();

        if (Functions.alreadyNormalised(dirRatios)) {
            this.normalisedDirectionRatios = this.directionRatios;

        } else {
            double[] normalisedDirRatios = Functions.ifcNormalise(dirRatios);
            this.normalisedDirectionRatios = normalisedDirRatios == null ? null :
                    Arrays.stream(normalisedDirRatios).mapToObj(IfcReal::new).collect(Collectors.toUnmodifiableList());
        }
    }

    /**
     * @param directionRatios The components in the direction of X axis (DirectionRatios[1]), of Y axis
     *                        (DirectionRatios[2]), and of Z axis (DirectionRatios[3]). The size of this array must be
     *                        either 2 or 3, and it cannot be null.
     * @throws NullPointerException     If directionRatios is null.
     * @throws IllegalArgumentException If the size of directionRatios is not 2 or 3.
     */
    public IfcDirection(double @NonNull ... directionRatios) {

        if (directionRatios.length < 2 || directionRatios.length > 3) {
            throw new IllegalArgumentException("size of directionRatios must be 2 or 3");
        }
        this.directionRatios = Arrays.stream(directionRatios).mapToObj(d -> new IfcReal(Functions.round(d)))
                .collect(Collectors.toUnmodifiableList());
        this.dim = new IfcDimensionCount(this.directionRatios.size());

        double[] dirRatios = this.directionRatios.stream().mapToDouble(IfcReal::getValue).toArray();

        if (Functions.alreadyNormalised(dirRatios)) {
            this.normalisedDirectionRatios = this.directionRatios;

        } else {
            double[] normalisedDirRatios = Functions.ifcNormalise(dirRatios);
            this.normalisedDirectionRatios = normalisedDirRatios == null ? null :
                    Arrays.stream(normalisedDirRatios).mapToObj(IfcReal::new).collect(Collectors.toUnmodifiableList());
        }
    }

    @Override
    public String toString() {
        return "IfcDirection(directionRatios=" + directionRatios + ", normalisedDirectionRatios=" +
                normalisedDirectionRatios + ")";
    }
}
