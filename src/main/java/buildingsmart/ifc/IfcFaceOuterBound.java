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

import lombok.NonNull;

public abstract class IfcFaceOuterBound extends IfcFaceBound {
    /**
     * @param bound       The loop which will be used as a face boundary.
     * @param orientation This indicated whether (TRUE) or not (FALSE) the
     *                    loop has
     *                    the same sense when used to bound the face as when
     *                    first
     *                    defined. If sense is FALSE the senses of all its
     *                    component
     *                    oriented edges are implicitly reversed when used in
     *                    the
     *                    face.
     * @throws NullPointerException If any of the arguments are {@code null}.
     */
    public IfcFaceOuterBound(@NonNull IfcLoop bound,
                             @NonNull IfcBoolean orientation) {
        super(bound, orientation);
    }
}
