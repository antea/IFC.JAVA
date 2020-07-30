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

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A solid model is a complete representation of the nominal shape of a product
 * such that all points in the interior are connected. Any point can be
 * classified as being inside, outside, or on the boundary of a solid. There are
 * several different types of solid model representations.
 */
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public abstract class IfcSolidModel extends IfcGeometricRepresentationItem {
    private final IfcDimensionCount dim = new IfcDimensionCount((byte) 3);
    // derived attribute
}
