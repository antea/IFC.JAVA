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
import lombok.NonNull;
import lombok.ToString;

/**
 * A plane is an unbounded surface with a constant normal. A plane is defined by
 * a point on the plane and the normal direction to the plane. The data is to be
 * interpreted as follows:
 * </P>
 * <BLOCKQUOTE>
 * <BLOCKQUOTE>
 * <PRE>
 * C = SELF\IfcElementarySurface.Position.Location
 * x = SELF\IfcElementarySurface.Position.P[1]
 * y = SELF\IfcElementarySurface.Position.P[2]
 * z = SELF\IfcElementarySurface.Position.P[3] =&gt; normal to plane
 * </PRE></BLOCKQUOTE></BLOCKQUOTE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcPlane extends IfcElementarySurface {

    /**
     * @param position The position and orientation of the surface. This
     *                 attribute is used in the definition of the
     *                 parameterization of the surface.
     * @throws NullPointerException If position is null.
     */
    public IfcPlane(@NonNull IfcAxis2Placement3D position) {
        super(position);
    }

    /**
     * @return The space dimensionality of this class, derived from the
     * dimensionality of the position.
     */
    @Override
    public IfcDimensionCount getDim() {
        return position.getDim();
    }
}
