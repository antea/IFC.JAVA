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
 * <P>The predefined curve font
 * type is an abstract supertype provided to define an application specific curve font. The name label shall be
 * constrained in the application protocol to values that are given specific meaning for curve fonts in that application
 * protocol.</P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE: The <I>IfcPreDefinedCurveFont</I> is an abstract
 * entity, subtypes of it provide the predefined curve font by agreement of the values of the inherited <I>Name</I>
 * attribute. Currently the only subtype provided is <I>IfcDraughtingPreDefinedCurveFont</I>.</FONT></P></BLOCKQUOTE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcPreDefinedCurveFont extends IfcPreDefinedItem implements IfcCurveStyleFontSelect {
    /**
     * @param name The string by which the pre defined item is identified. Allowable values for the string are
     *             declared at
     *             the level of subtypes.
     * @throws NullPointerException If {@code name} is {@code null}.
     */
    public IfcPreDefinedCurveFont(@NonNull IfcLabel name) {
        super(name);
    }
}
