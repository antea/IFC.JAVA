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

import lombok.NonNull;
import org.junit.Test;

public class IfcNamedUnitTest {

    @Test
    public void constructor_correctDimensions() {
        new ConcreteIfcNamedUnit(
                new IfcDimensionalExponents(0, 0, 1, 0, 0, 0, 0),
                IfcUnitEnum.TIMEUNIT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_wrongDimensions() {
        new ConcreteIfcNamedUnit(
                new IfcDimensionalExponents(234, 1000, 22, 0, 47645, 5, 1),
                IfcUnitEnum.TIMEUNIT);
    }

    @Test
    public void constructor_userDefinedUnitType() {
        new ConcreteIfcNamedUnit(
                new IfcDimensionalExponents(234, 1000, 22, 0, 47645, 5, 1),
                IfcUnitEnum.USERDEFINED);
    }

    private static class ConcreteIfcNamedUnit extends IfcNamedUnit {

        /**
         * @param dimensions The dimensional exponents of the SI base units by
         *                   which the named unit is defined.
         * @param unitType   The type of the unit.
         * @throws IllegalArgumentException If any of the parameters are {@code
         *                                  null}, or if dimensions is wrong for
         *                                  the given unitType.
         * @see buildingsmart.util.Functions#ifcCorrectDimensions(IfcUnitEnum,
         * IfcDimensionalExponents)
         */
        public ConcreteIfcNamedUnit(@NonNull IfcDimensionalExponents dimensions,
                                    @NonNull IfcUnitEnum unitType) {
            super(dimensions, unitType);
        }
    }

}