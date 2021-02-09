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

import java.util.Set;

public abstract class IfcAnnotationOccurrence extends IfcStyledItem {
    /**
     * @param item   A geometric representation item to which the style is
     *               assigned.
     * @param styles Representation style assignments which are assigned to an
     *               item. NOTE: In current IFC release only one presentation
     *               style assignment shall be assigned.
     * @param name   The word, or group of words, by which the styled item is
     *               referred to.
     * @throws IllegalArgumentException If styles is null or if its size is not
     *                                  1; if item is of type IfcStyledItem.
     */
    public IfcAnnotationOccurrence(IfcRepresentationItem item, Set<IfcPresentationStyleAssignment> styles,
                                   IfcLabel name) {

        super(item, styles, name);
    }
}
