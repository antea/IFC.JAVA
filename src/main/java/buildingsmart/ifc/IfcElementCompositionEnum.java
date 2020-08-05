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
 * Enumeration that provides an indication, whether the spatial structure
 * element or proxy esents a:
 * </p>
 * <ul>
 *   <li>COMPLEX - a group or aggregation of similar elements
 *   </li>
 *   <li>ELEMENT - a (undivided) element itself
 *   </li>
 *   <li>PARTIAL - a subelement or part
 *   </li>
 * </ul>
 */
public enum IfcElementCompositionEnum implements DefinedType {
    COMPLEX, ELEMENT, PARTIAL;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
