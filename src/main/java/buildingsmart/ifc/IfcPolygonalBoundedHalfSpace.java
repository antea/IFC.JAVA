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

import lombok.NonNull;

public abstract class IfcPolygonalBoundedHalfSpace extends IfcHalfSpaceSolid {
    private IfcAxis2Placement3D Position;
    private IfcBoundedCurve PolygonalBoundary;

    /**
     * @param baseSurface   Surface defining side of half space.
     * @param agreementFlag The agreement flag is TRUE if the normal to the
     *                      BaseSurface points away from the material of the
     *                      IfcHalfSpaceSolid. Otherwise it is FALSE.
     * @throws NullPointerException If any of the arguments are null.
     */
    public IfcPolygonalBoundedHalfSpace(@NonNull IfcSurface baseSurface,
                                        @NonNull IfcBoolean agreementFlag) {
        super(baseSurface, agreementFlag);
    }
}
