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
import lombok.ToString;

/**
 * A solid represented as a CSG model is defined by a collection of so-called
 * primitive solids, combined using regularized Boolean operations. The allowed
 * operations are intersection, union, and difference. As a special case a CSG
 * solid can also consists of a single CSG primitive.</P>
 * <UL>
 * <LI> A CSG solid requires two kinds of information for its complete
 * definition: geometric and structural. The geometric information is conveyed
 * by solid models. These typically primitive volumes such as cylinders, wedges
 * and extrusions, but can include general B-Rep models. There can also be solid
 * replicas (not in current IFC release) and half space solids. </LI>
 * <LI>The structural information is in a tree (strictly an acyclic
 * directed graph) of Boolean result and CSG solids, which represent a &#145;
 * recipe&#146; for building the solid. The terminal nodes are the geometric
 * primitives and other solids. Every CSG solid has precisely one Boolean result
 * associated with it which is the root of the tree that defines the solid.
 * (There may be further Boolean results within the tree as operands). The
 * significance of a CSG solid entity is that the solid defined by the
 * associated tree is thus identified as a significant object itself, and in
 * this way it is distinguished from other Boolean result entities representing
 * intermediate results during the construction process. </LI>
 * </UL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcCsgSolid extends IfcSolidModel {
    private IfcCsgSelect treeRootExpression;
}
