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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * <P>A manifold solid B-rep is a
 * finite, arcwise connected volume bounded by one or more surfaces, each of
 * which is a connected, oriented, finite, closed 2-manifold. There is no
 * restriction on the genus of the volume, nor on the number of voids within the
 * volume.
 * </P>
 * <P>The Boundary Representation (B-rep) of a manifold solid utilizes a
 * graph of edges and vertices embedded in a connected, oriented, finite, closed
 * two manifold surface. The embedded graph divides the surface into arcwise
 * connected areas known as faces. The edges and vertices, therefore, form the
 * boundaries of the face and the domain of a face does not include its
 * boundaries. The embedded graph may be disconnected and may be a pseudo graph.
 * The graph is labeled; that is, each entity in the graph has a unique
 * identity. The geometric surface definition used to specify the geometry of a
 * face shall be 2-manifold embeddable in the plane within the domain of the
 * face. In other words, it shall be connected, oriented, finite,
 * non-self-intersecting, and of surface genus 0.
 * </P>
 * <P>Faces do not intersect except along their boundaries. Each edge along
 * the boundary of a face is shared by at most one other face in the assemblage.
 * The assemblage of edges in the B-rep do not intersect except at their
 * boundaries (i.e., vertices). The geometry curve definition used to specify
 * the geometry of an edge shall be arcwise connected and shall not self
 * intersect or overlap within the domain of the edge. The geometry of an edge
 * shall be consistent with the geometry of the faces of which it forms a
 * partial bound. The geometry used to define a vertex shall be consistent with
 * the geometry of the faces and edges of which it forms a partial bound. </P>
 * <P>A B-rep is represented by one or more closed shells which shall be
 * disjoint. One shell, the outer, shall completely enclose all the other shells
 * and no other shell may enclose a shell. The facility to define a B-rep with
 * one or more internal voids is provided by a subtype.
 * <P><U>Informal proposition</U>:</P>
 * <OL>
 * <LI>The dimensionality of a manifold solid brep shall be 3.</LI>
 * <LI>The extent of the manifold solid brep shall be finite and
 * non-zero.</LI>
 * <LI>All elements of the manifold solid brep shall have defined
 * associated geometry.</LI>
 * <LI>The shell normals shall agree with the B-rep normal and point away
 * from the solid represented by the B-rep.</LI>
 * <LI>Each face shall be referenced only once by the shells of the
 * manifold solid brep.</LI>
 * <LI>The Euler equation shall be satisfied for the boundary
 * representation, where the genus term "shell term" us the sum of the genus
 * values for the shells of the brep. </LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcManifoldSolidBrep extends IfcSolidModel {
    @Attribute(0)
    private final IfcClosedShell outer;

    /**
     * @param outer A closed shell defining the exterior boundary of the solid.
     *              The shell normal shall point away from the interior of the
     *              solid.
     * @throws NullPointerException If {@code outer} is {@code null}.
     */
    public IfcManifoldSolidBrep(@NonNull IfcClosedShell outer) {
        this.outer = outer;
    }
}
