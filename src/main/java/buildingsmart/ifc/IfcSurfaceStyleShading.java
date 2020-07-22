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
import buildingsmart.io.Order;
import lombok.NonNull;

import java.util.Objects;

/**
 * The entity IfcSurfaceStyleShading allows for colour information used for
 * shading, whereas subtypes provide data for more sophisticated rendering
 * techniques. The surface colour is used for colouring or simple shading of the
 * assigned surfaces.
 */
public class IfcSurfaceStyleShading extends IfcEntity
        implements IfcSurfaceStyleElementSelect {
    @Attribute
    @Order(0)
    private final IfcColourRgb surfaceColour;

    /**
     * @param surfaceColour The colour used to render the surface. The surface
     *                      colour for visualisation is defined by specifying
     *                      the intensity of red, green and blue.
     * @throws IllegalArgumentException If surfaceColour is null.
     */
    public IfcSurfaceStyleShading(@NonNull IfcColourRgb surfaceColour) {
        if (surfaceColour == null) {
            throw new IllegalArgumentException("surfaceColour cannot be null");
        }
        this.surfaceColour = surfaceColour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcSurfaceStyleShading that = (IfcSurfaceStyleShading) o;
        return surfaceColour.equals(that.surfaceColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surfaceColour);
    }
}
