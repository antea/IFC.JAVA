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
import buildingsmart.io.Entity;
import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * A set of units which may be assigned. Within an IfcUnitAssigment each unit
 * definition shall be unique. I.e. there shall be no redundant unit definitions
 * for the same unit type, like length unit, area unit etc. For currencies,
 * there shall be only a single IfcMonetaryUnit within an IfcUnitAssignment.
 * <p>
 * <small>NOTE:  A project (IfcProject) has a unit assignment which establishes
 * a set of units which will be used globally within the project, if not
 * otherwise defined. Other objects may have local unit assignments if there is
 * a requirement for them to make use of units which do not fall within the
 * project unit assignment.</small>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcUnitAssignment extends Entity {
    @Attribute(0)
    private final Set<IfcUnit> units;

    /**
     * @param units Units to be included within a unit assignment.
     * @throws NullPointerException     If units is null.
     * @throws IllegalArgumentException If units has size equal to zero, doesn't
     *                                  only include units with a different
     *                                  unitType (for IfcNamedUnit and
     *                                  IfcDerivedUnit), or includes more than
     *                                  one IfcMonetaryUnit.
     */
    public IfcUnitAssignment(@NonNull Set<IfcUnit> units) {
        if (units.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of units must be at least 1");
        }
        if (!Functions.ifcCorrectUnitAssignment(units)) {
            throw new IllegalArgumentException(
                    "units can contain at most 1 IfcMonetaryUnit, cannot " +
                            "include multiple IfcNamedUnit with the same " +
                            "unitType, and cannot include multiple " +
                            "IfcDerivedUnit with the same unitType");
        }
        this.units = units;
    }

    /**
     * @param units Units to be included within a unit assignment.
     * @throws NullPointerException     If units is null.
     * @throws IllegalArgumentException If units has size equal to zero, doesn't
     *                                  only include units with a different
     *                                  unitType (for IfcNamedUnit and
     *                                  IfcDerivedUnit), or includes more than
     *                                  one IfcMonetaryUnit.
     */
    public IfcUnitAssignment(@NonNull IfcUnit... units) {
        this(new CopyOnWriteArraySet<>(Arrays.asList(units)));
    }
}
