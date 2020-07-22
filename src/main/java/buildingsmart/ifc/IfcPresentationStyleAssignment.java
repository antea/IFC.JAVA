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
import buildingsmart.io.IfcEntity;
import buildingsmart.io.Order;
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The presentation style assignment is a set of styles which are assigned to
 * styled items for the purpose of presenting these styled items.
 */
public class IfcPresentationStyleAssignment extends IfcEntity {
    @Attribute
    @Order(0)
    private final Set<IfcPresentationStyleSelect> styles;

    /**
     * @param styles A set of presentation styles that are assigned to styled
     *               items.
     * @throws IllegalArgumentException If styles is null, or its size is lower
     *                                  than 1.
     */
    public IfcPresentationStyleAssignment(
            @NonNull Set<IfcPresentationStyleSelect> styles) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        if (styles.size() < 1) {
            throw new IllegalArgumentException(
                    "size of syles must be at least 1");
        }
        this.styles = styles;
    }

    /**
     * @param styles A set of presentation styles that are assigned to styled
     *               items.
     * @throws IllegalArgumentException If styles is null, or the size of the
     *                                  Set containing the unique elements of
     *                                  styles has size lower than 1.
     */
    public IfcPresentationStyleAssignment(
            @NonNull IfcPresentationStyleSelect... styles) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        Set<IfcPresentationStyleSelect> stylesSet =
                new HashSet<>(Arrays.asList(styles));
        if (stylesSet.size() < 1) {
            throw new IllegalArgumentException(
                    "size of syles must be at least 1");
        }
        this.styles = stylesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPresentationStyleAssignment that =
                (IfcPresentationStyleAssignment) o;
        return styles.equals(that.styles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styles);
    }
}
