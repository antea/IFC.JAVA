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

/**
 * A surface can be envisioned as a set of connected points in 3-dimensional
 * space which is always locally 2-dimensional, but need not be a manifold.
 * <P><U>Informal proposition</U>:</P>
 * <OL>
 * <LI>A surface has non zero area.</LI>
 * <LI>A surface is arcwise connected.</LI>
 * </OL>
 */
public abstract class IfcSurface extends IfcGeometricRepresentationItem
        implements IfcGeometricSetSelect {}
