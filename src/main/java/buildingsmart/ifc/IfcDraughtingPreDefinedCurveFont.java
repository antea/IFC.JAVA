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

import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

/**
 * <P>The draughting predefined curve font type
 * defines a selection of widely used curve fonts for draughting purposes by name.</P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE: The <I>IfcDraughtingPreDefinedCurveFont</I> is
 * an entity that had been adopted from ISO 10303, Industrial automation systems and integration&#151;Product data
 * representation and exchange, Part 46 Technical Corrigendum 2: Integrated generic resources: Visual
 * presentation.</FONT></P></BLOCKQUOTE>
 * <BLOCKQUOTE><FONT SIZE="-1">NOTE: If the
 * <I>IfcDraughtingPreDefinedCurveFont</I> is used within an
 * <I>IfcCurveStyleFontAndScaling</I> then the segment and space lengths that are
 * given in the table are as such for the scale factor 1.0</FONT></BLOCKQUOTE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcDraughtingPreDefinedCurveFont extends IfcPreDefinedCurveFont {
    private static final Set<String> NAMES = Collections.unmodifiableSet(Sets.newHashSet("continuous",
                                                                                         "chain",
                                                                                         "chain double dash",
                                                                                         "dashed",
                                                                                         "dotted",
                                                                                         "by layer"));

    /**
     * @param name The string by which the pre defined item is identified. Allowable values for the string are declared
     *             at the level of subtypes.
     * @throws NullPointerException     If {@code name} is {@code null}.
     * @throws IllegalArgumentException If {@code name} is not one of "continuous", "chain", "chain double dash",
     *                                  "dashed", "dotted", "by layer".
     */
    public IfcDraughtingPreDefinedCurveFont(@NonNull IfcLabel name) {
        super(name);
        if (!NAMES.contains(name.getValue())) {
            throw new IllegalArgumentException(
                    "name must be either \"continuous\", \"chain\", \"chain double dash\", \"dashed\", \"dotted\" or " +
                            "\"by layer\"");
        }
    }
}
