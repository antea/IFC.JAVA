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
import lombok.ToString;

/**
 * A representation context is a context in which a set of representation items
 * are related.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcRepresentationContext extends Entity {
    @Attribute(0)
    private final IfcLabel contextIdentifier;
    @Attribute(1)
    private final IfcLabel contextType;

    /**
     * @param contextIdentifier The optional identifier of the representation
     *                          context as used within a project.
     * @param contextType       The description of the type of a representation
     *                          context. The supported values for context type
     *                          are to be specified by implementers agreements.
     */
    public IfcRepresentationContext(IfcLabel contextIdentifier,
                                    IfcLabel contextType) {
        this.contextIdentifier = contextIdentifier;
        this.contextType = contextType;
    }
}
