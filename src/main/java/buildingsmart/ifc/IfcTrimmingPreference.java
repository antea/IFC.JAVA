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

import buildingsmart.io.DefinedType;

/**
 * This type is used to describe the preferred way of trimming a parametric
 * curve where the trimming is multiply defined.
 * <UL>
 * <LI><B>Cartesian</B> Indicates that trimming by Cartesian point is
 * preferred.</LI>
 * <LI><B>Parameter</B> Indicates the preference for the parameter
 * value.</LI>
 * <LI><B>Unspecified</B> Indicates that no preference is communicated
 * .</LI>
 * </UL>
 */
public enum IfcTrimmingPreference implements DefinedType {
    CARTESIAN, PARAMETER, UNSPECIFIED;

    /**
     * @return The representation of the Defined Type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
