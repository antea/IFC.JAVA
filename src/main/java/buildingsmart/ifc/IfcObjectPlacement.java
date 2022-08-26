/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2022 Antea S.r.l.
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

import buildingsmart.io.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;

/**
 * Abstract supertype for the special types defining the object coordinate
 * system. The
 * <i>IfcObjectPlacement</i>
 * has to be provided for each product that has a shape representation.</p>
 * <p>The object placement can be given:</p>
 * <ul>
 *   <li>absolute, i.e. by an axis2 placement, relative to the world
 * coordinate system,</li>
 *   <li>relative, i.e. by an axis2 placement, relative to the
 * object placement of another product,</li>
 *   <li>by grid reference, i.e. by the virtual intersection and
 * reference direction given by two axes of a design grid.</li>
 * </ul>
 * <p>In any case the object placement has to unambiguously define
 * the object coordinate system as either two-dimensional axis placement
 * (<i>IfcAxis2Placement2D</i>)
 * or three-dimensional axis placement (<i>IfcAxis2Placement3D</i>).
 * The axis placement may have to be calculated.</p>
 * <p>Informal proposition</p>
 * <ol>
 *   <li>No two or more elements (subtypes of <i>IfcProduct</i>)
 * shall share the same instance of <i>IfcObjectPlacement</i>.&nbsp;</li>
 * </ol>
 */
@EqualsAndHashCode(callSuper = false)
public abstract class IfcObjectPlacement extends Entity implements Serializable {

    // This is an @InverseRelationship, but since the IfcProduct will be serialized anyway, there's no need to use it
    // and make serialization slower
    @Setter(AccessLevel.PROTECTED)
    private IfcProduct placesObject;

    //private IfcLocalPlacement[] ReferencedByPlacements;

    /**
     * @return The MD5 digest of the serialization of this class.
     */
    protected abstract byte[] getMd5();
}
