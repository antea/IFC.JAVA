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

import java.util.List;

/**
 * This select type collects together both versions of the placement as used in
 * two dimensional or in three dimensional Cartesian space. This enables
 * entities requiring this information to reference them without specifying the
 * space dimensionality.
 */
public interface IfcAxis2Placement {

    /**
     * @return The space dimensionality of this class, derived from the
     * dimensionality of the location.
     */
    IfcDimensionCount getDim();

    /**
     * @return The geometric position of a reference point, such as the center
     * of a circle, of the item to be located.
     */
    IfcCartesianPoint getLocation();

    /**
     * @return The normalized directions of the axes.
     */
    List<IfcDirection> getP();
}
