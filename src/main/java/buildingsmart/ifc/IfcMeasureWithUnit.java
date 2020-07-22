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
import buildingsmart.io.IfcEntity;
import buildingsmart.io.Order;
import lombok.NonNull;

import java.util.Objects;

/**
 * A measure with unit is the specification of a physical quantity as defined in
 * ISO 31 (clause 2).
 * <p>
 * IfcMeasureWithUnit has two usages:
 * <ol>
 * <li>For representing measure value together
 * with its unit on the entity type attribute level; thus overriding the IFC
 * model global unit assignments.</li>
 * <li>For conversion based unit to give the
 * conversion rate and its base.</li>
 * </ol>
 */
public class IfcMeasureWithUnit extends IfcEntity {
    @Attribute
    @Order(0)
    private final IfcValue valueComponent;
    @Attribute
    @Order(1)
    private final IfcUnit unitComponent;

    /**
     * @param valueComponent The value of the physical quantity when expressed
     *                       in the specified units.
     * @param unitComponent  The unit in which the physical quantity is
     *                       expressed.
     */
    public IfcMeasureWithUnit(@NonNull IfcValue valueComponent,
                              @NonNull IfcUnit unitComponent) {
        if (valueComponent == null) {
            throw new IllegalArgumentException("valueComponent cannot be null");
        }
        if (unitComponent == null) {
            throw new IllegalArgumentException("unitComponent cannot be null");
        }
        this.valueComponent = valueComponent;
        this.unitComponent = unitComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcMeasureWithUnit that = (IfcMeasureWithUnit) o;
        return valueComponent.equals(that.valueComponent) &&
                unitComponent.equals(that.unitComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueComponent, unitComponent);
    }
}
