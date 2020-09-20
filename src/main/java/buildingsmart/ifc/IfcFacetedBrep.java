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


import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * <P>A faceted brep is a simple
 * form of boundary representation model in which all faces are planar and all
 * edges are straight lines. Unlike the B-rep model, edges and vertices are not
 * represented explicitly in the model but are implicitly available through the
 * poly loop entity. A faceted B-rep has to meet the same topological
 * constraints as the manifold solid Brep. </P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE: The faceted brep has been introduced in
 * order to support the larger number of systems that allow boundary type solid
 * representations with planar surfaces only.</FONT></P></BLOCKQUOTE>
 * <P><U>Informal proposition:</U></P>
 * <OL>
 * <LI>All the bounding loops of all the faces of all the shells in the
 * <I>IfcFacetedBrep</I> shall be of type <I>IfcPolyLoop</I>.</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcFacetedBrep extends IfcManifoldSolidBrep {
    /**
     * @param outer A closed shell defining the exterior boundary of the solid.
     *              The shell normal shall point away from the interior of the
     *              solid.
     * @throws NullPointerException If {@code outer} is {@code null}.
     */
    public IfcFacetedBrep(@NonNull IfcClosedShell outer) {
        super(outer);
    }
}
