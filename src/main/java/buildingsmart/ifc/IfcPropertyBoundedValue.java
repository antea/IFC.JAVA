/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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

import lombok.NonNull;

public class IfcPropertyBoundedValue extends IfcSimpleProperty {
    private IfcValue UpperBoundValue;
    private IfcValue LowerBoundValue;
    private IfcUnit Unit;

    /**
     * @param name        Name for this property. This label is the significant name string that defines the semantic
     *                    meaning for the property.
     * @param description Informative text to explain the property.
     * @throws NullPointerException If {@code name} is {@code null}.
     */
    public IfcPropertyBoundedValue(@NonNull IfcIdentifier name, IfcText description) {
        super(name, description);
    }
}
