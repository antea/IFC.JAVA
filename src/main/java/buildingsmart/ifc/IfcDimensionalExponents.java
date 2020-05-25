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

import buildingsmart.io.IfcEntity;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The dimensionality of any quantity can be expressed as a product of powers of
 * the dimensions of base quantities. The dimensional exponents entity defines
 * the powers of the dimensions of the base quantities. All the physical
 * quantities are founded on seven base quantities (ISO 10303-31 (clause 2)).
 * <p>
 * <small>NOTE: Length, mass, time, electric current, thermodynamic
 * temperature, amount of substance, and luminous intensity are the seven base
 * quantities.
 * <br>
 * EXAMPLE: A length of 2 millimetres has a length exponent of 1. The remaining
 * exponents are equal to 0.
 * <br>
 * EXAMPLE: A velocity of 2 millimetres per second has a length exponent of 1
 * and a time exponent of -1. The remaining exponents are equal to 0.</small>
 */
public class IfcDimensionalExponents extends IfcEntity {
    private final IfcInteger lengthExponent;
    private final IfcInteger massExponent;
    private final IfcInteger timeExponent;
    private final IfcInteger electricCurrentExponent;
    private final IfcInteger thermodynamicTemperatureExponent;
    private final IfcInteger amountOfSubstanceExponent;
    private final IfcInteger luminousIntensityExponent;

    /**
     * @param lengthExponent                   The power of the length base
     *                                         quantity.
     * @param massExponent                     The power of the mass base
     *                                         quantity.
     * @param timeExponent                     The power of the time base
     *                                         quantity.
     * @param electricCurrentExponent          The power of the electric current
     *                                         base quantity.
     * @param thermodynamicTemperatureExponent The power of the thermodynamic
     *                                         temperature base quantity.
     * @param amountOfSubstanceExponent        The power of the amount of
     *                                         substance base quantity.
     * @param luminousIntensityExponent        The power of the luminous
     *                                         intensity base quantity.
     * @throws IllegalArgumentException If any of the parameters are null.
     */
    public IfcDimensionalExponents(@NotNull IfcInteger lengthExponent,
                                   @NotNull IfcInteger massExponent,
                                   @NotNull IfcInteger timeExponent,
                                   @NotNull IfcInteger electricCurrentExponent,
                                   @NotNull IfcInteger thermodynamicTemperatureExponent,
                                   @NotNull IfcInteger amountOfSubstanceExponent,
                                   @NotNull IfcInteger luminousIntensityExponent) {
        if (lengthExponent == null || massExponent == null ||
                timeExponent == null || electricCurrentExponent == null ||
                thermodynamicTemperatureExponent == null ||
                amountOfSubstanceExponent == null ||
                luminousIntensityExponent == null) {
            throw new IllegalArgumentException(
                    "none of the parameters can be null");
        }
        this.lengthExponent = lengthExponent;
        this.massExponent = massExponent;
        this.timeExponent = timeExponent;
        this.electricCurrentExponent = electricCurrentExponent;
        this.thermodynamicTemperatureExponent = thermodynamicTemperatureExponent;
        this.amountOfSubstanceExponent = amountOfSubstanceExponent;
        this.luminousIntensityExponent = luminousIntensityExponent;
    }

    /**
     * @param lengthExponent                   The power of the length base
     *                                         quantity.
     * @param massExponent                     The power of the mass base
     *                                         quantity.
     * @param timeExponent                     The power of the time base
     *                                         quantity.
     * @param electricCurrentExponent          The power of the electric current
     *                                         base quantity.
     * @param thermodynamicTemperatureExponent The power of the thermodynamic
     *                                         temperature base quantity.
     * @param amountOfSubstanceExponent        The power of the amount of
     *                                         substance base quantity.
     * @param luminousIntensityExponent        The power of the luminous
     *                                         intensity base quantity.
     * @throws IllegalArgumentException If any of the parameters are null.
     */
    public IfcDimensionalExponents(@NotNull long lengthExponent, @NotNull long massExponent,
                                   @NotNull long timeExponent, @NotNull long electricCurrentExponent,
                                   @NotNull long thermodynamicTemperatureExponent,
                                   @NotNull long amountOfSubstanceExponent,
                                   @NotNull long luminousIntensityExponent) {
        this(new IfcInteger(lengthExponent), new IfcInteger(massExponent), new IfcInteger(timeExponent),
                new IfcInteger(electricCurrentExponent), new IfcInteger(thermodynamicTemperatureExponent),
                new IfcInteger(amountOfSubstanceExponent), new IfcInteger(luminousIntensityExponent));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcDimensionalExponents that = (IfcDimensionalExponents) o;
        return lengthExponent.equals(that.lengthExponent) && massExponent.equals(that.massExponent) &&
                timeExponent.equals(that.timeExponent) &&
                electricCurrentExponent.equals(that.electricCurrentExponent) &&
                thermodynamicTemperatureExponent.equals(that.thermodynamicTemperatureExponent) &&
                amountOfSubstanceExponent.equals(that.amountOfSubstanceExponent) &&
                luminousIntensityExponent.equals(that.luminousIntensityExponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lengthExponent, massExponent, timeExponent,
                electricCurrentExponent, thermodynamicTemperatureExponent,
                amountOfSubstanceExponent, luminousIntensityExponent);
    }
}
