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

public class IfcFluidFlowProperties extends IfcPropertySetDefinition {
    private final IfcPropertySourceEnum PropertySource;
    private IfcTimeSeries FlowConditionTimeSeries;
    private IfcTimeSeries VelocityTimeSeries;
    private IfcTimeSeries FlowrateTimeSeries;
    private IfcMaterial Fluid;
    private IfcTimeSeries PressureTimeSeries;
    private String UserDefinedPropertySource;
    private double TemperatureSingleValue;
    private double WetBulbTemperatureSingleValue;
    private IfcTimeSeries WetBulbTemperatureTimeSeries;
    private IfcTimeSeries TemperatureTimeSeries;
    private IfcDerivedMeasureValue FlowrateSingleValue;
    private IfcRatioMeasure FlowConditionSingleValue;
    private double VelocitySingleValue;
    private double PressureSingleValue;

    public IfcFluidFlowProperties(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcRelAssociates[] hasAssociations,
                                  IfcRelDefinesByProperties[] propertyDefinitionOf,
                                  IfcPropertySourceEnum propertySource) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        PropertySource = propertySource;
    }
}
