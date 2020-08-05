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

import buildingsmart.io.DefinedType;

/**
 * An SI unit name is the name of an SI unit. The definitions of the names of SI
 * units are specified in ISO 1000 (clause 2).
 */
public enum IfcSIUnitName implements DefinedType {
    AMPERE,
    BECQUEREL,
    CANDELA,
    COULOMB,
    CUBIC_METRE,
    DEGREE_CELSIUS,
    FARAD,
    GRAM,
    GRAY,
    HENRY,
    HERTZ,
    JOULE,
    KELVIN,
    LUMEN,
    LUX,
    METRE,
    MOLE,
    NEWTON,
    OHM,
    PASCAL,
    RADIAN,
    SECOND,
    SIEMENS,
    SIEVERT,
    SQUARE_METRE,
    STERADIAN,
    TESLA,
    VOLT,
    WATT,
    WEBER;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
