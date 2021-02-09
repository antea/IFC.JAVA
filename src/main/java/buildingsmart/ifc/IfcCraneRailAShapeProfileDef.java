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

public class IfcCraneRailAShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure OverallHeight;
    private IfcLengthMeasure BaseWidth2;
    private IfcLengthMeasure Radius;
    private IfcLengthMeasure HeadWidth;
    private IfcLengthMeasure HeadDepth2;
    private IfcLengthMeasure HeadDepth3;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure BaseWidth4;
    private IfcLengthMeasure BaseDepth1;
    private IfcLengthMeasure BaseDepth2;
    private IfcLengthMeasure BaseDepth3;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcCraneRailAShapeProfileDef(IfcProfileTypeEnum profileType, IfcLabel profileName,
                                        IfcAxis2Placement2D position,
                                        IfcLengthMeasure overallHeight) {

        super(profileType, profileName, position);
        OverallHeight = overallHeight;
    }
}
