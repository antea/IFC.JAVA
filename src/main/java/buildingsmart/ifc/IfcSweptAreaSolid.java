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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * The swept area solid entity collects the entities which are defined
 * procedurally by sweeping action on planar bounded surfaces. The position is
 * space of the swept solid will be dependent upon the position of the swept
 * area. The swept area will be a face of the resulting swept area solid, except
 * for the case of a revolved area solid with angle equal to 2 p (or 360
 * degrees).
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcSweptAreaSolid extends IfcSolidModel {
    @Attribute(0)
    private final IfcProfileDef sweptArea;
    @Attribute(1)
    private final IfcAxis2Placement3D position;

    /**
     * @param sweptArea The surface defining the area to be swept. It is given
     *                  as a profile definition within the xy plane of the
     *                  position coordinate system. Its profileType must be
     *                  AREA.
     * @param position  Position coordinate system for the swept area.
     * @throws NullPointerException     If sweptArea or position are null.
     * @throws IllegalArgumentException If sweptArea.profileType is not AREA.
     */
    public IfcSweptAreaSolid(@NonNull IfcProfileDef sweptArea,
                             @NonNull IfcAxis2Placement3D position) {
        if (sweptArea.getProfileType() != IfcProfileTypeEnum.AREA) {
            throw new IllegalArgumentException(
                    "profileType of sweptArea must be AREA");
        }
        this.sweptArea = sweptArea;
        this.position = position;
    }
}
