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

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The swept area solid entity collects the entities which are defined
 * procedurally by sweeping action on planar bounded surfaces. The position is
 * space of the swept solid will be dependent upon the position of the swept
 * area. The swept area will be a face of the resulting swept area solid, except
 * for the case of a revolved area solid with angle equal to 2 p (or 360
 * degrees).
 */
public abstract class IfcSweptAreaSolid extends IfcSolidModel {
    private final IfcProfileDef sweptArea;
    private final IfcAxis2Placement3D position;

    /**
     * @param sweptArea The surface defining the area to be swept. It is given
     *                  as a profile definition within the xy plane of the
     *                  position coordinate system. Its profileType must be
     *                  AREA.
     * @param position  Position coordinate system for the swept area.
     * @throws IllegalArgumentException If sweptArea or position are null, and
     *                                  if sweptArea.profileType is not AREA.
     */
    public IfcSweptAreaSolid(@NotNull IfcProfileDef sweptArea,
                             @NotNull IfcAxis2Placement3D position) {
        if (sweptArea == null) {
            throw new IllegalArgumentException("sweptArea cannot be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("position cannot be null");
        }
        if (sweptArea.getProfileType() != IfcProfileTypeEnum.AREA) {
            throw new IllegalArgumentException(
                    "profileType of sweptArea must be AREA");
        }
        this.sweptArea = sweptArea;
        this.position = position;
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
        IfcSweptAreaSolid that = (IfcSweptAreaSolid) o;
        return sweptArea.equals(that.sweptArea) && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sweptArea, position);
    }
}
