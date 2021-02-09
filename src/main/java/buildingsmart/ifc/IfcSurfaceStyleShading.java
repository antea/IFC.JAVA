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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * The entity IfcSurfaceStyleShading allows for colour information used for
 * shading, whereas subtypes provide data for more sophisticated rendering
 * techniques. The surface colour is used for colouring or simple shading of the
 * assigned surfaces.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcSurfaceStyleShading extends Entity
        implements IfcSurfaceStyleElementSelect {
    @Attribute(0)
    private final IfcColourRgb surfaceColour;

    /**
     * @param surfaceColour The colour used to render the surface. The surface
     *                      colour for visualisation is defined by specifying
     *                      the intensity of red, green and blue.
     * @throws NullPointerException If surfaceColour is null.
     */
    public IfcSurfaceStyleShading(@NonNull IfcColourRgb surfaceColour) {
        this.surfaceColour = surfaceColour;
    }
}
