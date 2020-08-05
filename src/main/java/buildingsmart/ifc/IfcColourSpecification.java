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
import buildingsmart.io.Entity;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The colour specification entity contains a direct colour definition. Colour
 * component values refer directly to a specific colour space.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcColourSpecification extends Entity {
    @Attribute(0)
    private final IfcLabel name;

    /**
     * @param name Optional name given to a particular colour specification in
     *             addition to the colour components (like the RGB values).
     */
    public IfcColourSpecification(IfcLabel name) {
        this.name = name;
    }
}
