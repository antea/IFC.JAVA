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

import java.util.Objects;

/**
 * An abstract generalization of style table for presentation information
 * assigned to geometric representation items. It includes styles for curves,
 * areas, surfaces, text and symbols. Style information may include colour,
 * hatching, rendering, text fonts, etc.</p>
 * <p>Each subtype of&nbsp; <i>IfcPresentationStyle</i>
 * can be assigned to <i>IfcGeometricRepresentationItem</i>'s via the
 * <i>IfcPresentationStyleAssignment</i> through an intermediate
 * <i>IfcStyledItem</i> or one of its subtypes.</p>
 */
public abstract class IfcPresentationStyle extends IfcEntity {
    @Attribute(0)
    private final IfcLabel name;

    /**
     * @param name Name of the presentation style.
     */
    public IfcPresentationStyle(IfcLabel name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPresentationStyle that = (IfcPresentationStyle) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
