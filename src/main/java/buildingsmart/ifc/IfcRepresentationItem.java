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

import buildingsmart.io.Entity;
import buildingsmart.io.InverseRelationship;

/**
 * A representation item is an element of product data that participates in one
 * or more representations or contributes to the definition of another
 * representation item. A representation item contributes to the definition of
 * another representation item when it is referenced by that representation
 * item.
 */
public abstract class IfcRepresentationItem extends Entity {
    @InverseRelationship
    private IfcStyledItem styledByItem;

    /**
     * @param styledByItem Reference to the IfcStyledItem that provides
     *                     presentation information to the representation, e.g.
     *                     a curve style, including colour and thickness to a
     *                     geometric curve.
     */
    protected void setStyledByItem(IfcStyledItem styledByItem) {
        this.styledByItem = styledByItem;
    }
}
