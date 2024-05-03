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

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <P>A connected_face_set is a
 * set of faces such that the domain of faces together with their bounding edges
 * and vertices is connected. </P>
 * <P>Informal proposition:</P>
 * <OL>
 * <LI>The union of the domains of the faces and their bounding loops
 * shall be arcwise connected.</LI>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcConnectedFaceSet extends IfcTopologicalRepresentationItem {
    @Attribute(0)
    private final Set<IfcFace> cfsFaces;

    /**
     * @param cfsFaces The set of faces arcwise connected along common edges or
     *                 vertices.
     * @throws NullPointerException     If {@code cfsFaces} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code cfsFaces} is less
     *                                  than 1.
     */
    public IfcConnectedFaceSet(@NonNull Set<IfcFace> cfsFaces) {
        if (cfsFaces.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of cfsFaces must be at least 1");
        }
        this.cfsFaces = cfsFaces;
    }

    /**
     * @param cfsFaces The set of faces arcwise connected along common edges or
     *                 vertices.
     * @throws NullPointerException     If {@code cfsFaces} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code cfsFaces} is less
     *                                  than 1.
     */
    public IfcConnectedFaceSet(@NonNull IfcFace... cfsFaces) {
        this(new HashSet<>(Arrays.asList(cfsFaces)));
    }
}
