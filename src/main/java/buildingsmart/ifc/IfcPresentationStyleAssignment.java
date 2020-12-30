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

import buildingsmart.io.Attribute;
import buildingsmart.io.Entity;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The presentation style assignment is a set of styles which are assigned to styled items for the purpose of presenting
 * these styled items.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcPresentationStyleAssignment extends Entity {
    @Attribute(0)
    private final Set<IfcPresentationStyleSelect> styles;

    /**
     * @param styles A set of presentation styles that are assigned to styled items.
     * @throws NullPointerException     If styles is null.
     * @throws IllegalArgumentException If styles' size is lower than 1.
     */
    public IfcPresentationStyleAssignment(@NonNull Set<IfcPresentationStyleSelect> styles) {
        if (styles.size() < 1) {
            throw new IllegalArgumentException("size of syles must be at least 1");
        }
        this.styles = styles;
    }

    /**
     * @param styles A set of presentation styles that are assigned to styled items.
     * @throws NullPointerException     If styles is null.
     * @throws IllegalArgumentException If the size of styles is lower than 1.
     */
    public IfcPresentationStyleAssignment(@NonNull IfcPresentationStyleSelect... styles) {
        if (styles.length < 1) {
            throw new IllegalArgumentException("size of syles must be at least 1");
        }
        this.styles = new HashSet<>(Arrays.asList(styles));
    }

    public Set<IfcPresentationStyleSelect> getStyles() {
        return Collections.unmodifiableSet(styles);
    }
}
