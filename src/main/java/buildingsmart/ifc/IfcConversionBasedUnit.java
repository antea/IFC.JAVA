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
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A conversion based unit is a unit that is defined based on a measure with
 * unit.
 * <p>
 * An inch is a converted unit. It is from the Imperial system, its name is
 * "inch" and it can be related to the si unit, millimetre, through a measure
 * with unit whose value is 25.4 millimetre. A foot is also a converted unit. It
 * is from the Imperial system, its name is "foot" and it can be related to an
 * si unit, millimetre, either directly or through the unit called "inch".
 * <p>To identify some commonly used conversion based units the standard
 * designations (case insensitive) for the Name attribute include the
 * following:
 * <TABLE BORDER="1">
 * <TR>
 * <TD><I><B>Name</B></I></TD>
 * <TD><I><B>Description</B></I></TD>
 * </TR>
 * <TR>
 * <TD>'inch'</TD>
 * <TD>Length measure equal to 25.4 mm</TD>
 * </TR>
 * <TR>
 * <TD>'foot'</TD>
 * <TD>Length measure equal to 30.48 mm</TD>
 * </TR>
 * <TR>
 * <TD>'yard'</TD>
 * <TD>Length measure equal to 914 mm</TD>
 * </TR>
 * <TR>
 * <TD>'mile'</TD>
 * <TD>Length measure equal to 1609 m</TD>
 * </TR>
 * <TR>
 * <TD>'acre'</TD>
 * <TD>Area measure equal to 4046,86 square meters</TD>
 * </TR>
 * <TR>
 * <TD>'litre'</TD>
 * <TD>Volume measure equal to 0.001 cubic meters</TD>
 * </TR>
 * <TR>
 * <TD>'pint UK'</TD>
 * <TD>Volume measure equal to 0.000568 cubic meters</TD>
 * </TR>
 * <TR>
 * <TD>'pint US'</TD>
 * <TD>Volume measure equal to 0.000473 cubic meters</TD>
 * </TR>
 * <TR>
 * <TD>'gallon UK'</TD>
 * <TD>Volume measure equal to 0.004546 cubic meters</TD>
 * </TR>
 * <TR>
 * <TD>'gallon US'</TD>
 * <TD>Volume measure equal to 0.003785 cubic meters</TD>
 * </TR>
 * <TR>
 * <TD>'ounce'</TD>
 * <TD>Weight measure equal to 28.35 g</TD>
 * </TR>
 * <TR>
 * <TD>'pound'</TD>
 * <TD>Weight measure equal to 0.454 kg</TD>
 * </TR>
 * </TABLE>
 */
public class IfcConversionBasedUnit extends IfcNamedUnit {
    @Attribute
    @Order(2)
    private final IfcLabel name;
    @Attribute
    @Order(3)
    private final IfcMeasureWithUnit conversionFactor;

    /**
     * @param dimensions       The dimensional exponents of the SI base units by
     *                         which the named unit is defined.
     * @param unitType         The type of the unit.
     * @param name             The word, or group of words, by which the
     *                         conversion based unit is referred to.
     * @param conversionFactor The physical quantity from which the converted
     *                         unit is derived.
     * @throws IllegalArgumentException If any of the parameters are {@code
     *                                  null}, or if dimensions is wrong for the
     *                                  given unitType.
     * @see buildingsmart.util.Functions#ifcCorrectDimensions(IfcUnitEnum,
     * IfcDimensionalExponents)
     */
    public IfcConversionBasedUnit(@NotNull IfcDimensionalExponents dimensions,
                                  @NotNull IfcUnitEnum unitType,
                                  @NotNull IfcLabel name, @NotNull
                                          IfcMeasureWithUnit conversionFactor) {
        super(dimensions, unitType);
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (conversionFactor == null) {
            throw new IllegalArgumentException(
                    "conversionFactor cannot be null");
        }
        this.name = name;
        this.conversionFactor = conversionFactor;
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
        IfcConversionBasedUnit that = (IfcConversionBasedUnit) o;
        return name.equals(that.name) &&
                conversionFactor.equals(that.conversionFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, conversionFactor);
    }
}
