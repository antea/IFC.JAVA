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

public abstract class IfcGeometricRepresentationSubContext
        extends IfcGeometricRepresentationContext {
    private IfcGeometricRepresentationContext ParentContext;
    private IfcRatioMeasure TargetScale;
    private IfcGeometricProjectionEnum TargetView;
    private String UserDefinedTargetView;
    private IfcAxis2Placement WorldCoordinateSystem;
    private int CoordinateSpaceDimension;
    private IfcDirection TrueNorth;
    private double Precision;

    /**
     * @param contextIdentifier        The optional identifier of the
     *                                 representation context as used within a
     *                                 project.
     * @param contextType              The description of the type of a
     *                                 representation context. The supported
     *                                 values for context type are to be
     *                                 specified by implementers agreements.
     * @param coordinateSpaceDimension The integer dimension count of the
     *                                 coordinate space modeled in a geometric
     *                                 representation context.
     * @param precision                Value of the model precision for
     *                                 geometric models. It is a double value
     *                                 (REAL), typically in 1E-5 to 1E-8 range,
     *                                 that indicates the tolerance under which
     *                                 two given points are still assumed to be
     *                                 identical. The value can be used e.g. to
     *                                 sets the maximum distance from an edge
     *                                 curve to the underlying face surface in
     *                                 brep models.
     * @param worldCoordinateSystem    Establishment of the engineering
     *                                 coordinate system (often referred to as
     *                                 the world coordinate system in CAD) for
     *                                 all representation contexts used by the
     *                                 project.
     *                                 <br>
     *                                 <small>Note: it can be used to provide
     *                                 better numeric stability if the placement
     *                                 of the building(s) is far away from the
     *                                 origin. In most cases however it would be
     *                                 set to origin: (0.,0.,0.) and directions
     *                                 x(1.,0.,0.), y(0.,1.,0.), z(0 .,0.,1.)
     *                                 .</small>
     * @param trueNorth                Direction of the true north relative to
     *                                 the underlying coordinate system as
     *                                 established by the attribute
     *                                 WorldCoordinateSystem . It is given by a
     *                                 direction within the xy-plane of the
     *                                 underlying coordinate system. If not
     *                                 given, it defaults to the positive
     *                                 direction of the y-axis of the
     *                                 WorldCoordinateSystem.
     * @throws IllegalArgumentException If coordinateSpaceDimension or
     *                                  worldCoordinateSystem are null.
     */
    public IfcGeometricRepresentationSubContext(IfcLabel contextIdentifier,
                                                IfcLabel contextType,
                                                IfcDimensionCount coordinateSpaceDimension,
                                                IfcReal precision,
                                                IfcAxis2Placement worldCoordinateSystem,
                                                IfcDirection trueNorth) {
        super(contextIdentifier, contextType, coordinateSpaceDimension,
                precision, worldCoordinateSystem, trueNorth);
    }
}
