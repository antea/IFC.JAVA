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
 * <P>A loop is a topological
 * entity constructed from a single vertex, or by stringing together connected
 * (oriented) edges, or linear segments beginning and ending at the same vertex.
 * It is typically used to bound a face lying on a surface. A loop has
 * dimensionality of 0 or 1. The domain of a 0-dimensional loop is a single
 * point. The domain of a 1-dimensional loop is a connected, oriented curve, but
 * need not to be manifold. As the loop is a circle, the location of its
 * beginning/ending point is arbitrary. The domain of the loop includes its
 * bounds, an 0 &le; &Xi; &lt; &infin;. </P>
 * <P>A loop is represented by a single vertex, or by an ordered
 * collection of oriented edges, or by an ordered collection of points. A loop
 * is a graph, so
 * <I>M</I> and the graph genus <I>G<SUP>l</SUP></I> may be determined by
 * the graph traversal algorithm. </P>
 * <P>Informal propositions:</P>
 * <OL>
 * <LI>A loop has a finite extent.</LI>
 * <LI>A loop describes a closed (topological) curve with coincident start
 * and end vertices.</LI>
 * </OL>
 */
public abstract class IfcLoop extends IfcTopologicalRepresentationItem {}
