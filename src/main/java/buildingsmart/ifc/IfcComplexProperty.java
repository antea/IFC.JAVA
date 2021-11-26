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
import lombok.NonNull;
import lombok.Builder;

public class IfcComplexProperty extends IfcProperty {
    @Attribute(2)
    private String UsageName;
    @Attribute(3)
    private IfcProperty[] HasProperties;

    @Builder
    public IfcComplexProperty(String Name, String Description,
            IfcPropertyDependencyRelationship[] PropertyForDependance,
            IfcPropertyDependencyRelationship[] PropertyDependsOn, IfcComplexProperty[] PartOfComplex,
            String usageName, IfcProperty[] hasProperties) {
        super(Name, Description, PropertyForDependance, PropertyDependsOn, PartOfComplex);
        UsageName = usageName;
        HasProperties = hasProperties;
    }
}
