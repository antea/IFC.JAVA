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

import java.util.Set;

/**
 * <P> A closed shell is a shell
 * of the dimensionality 2 which typically serves as a bound for a region in R3.
 * A closed shell has no boundary, and has non-zero finite extent. If the shell
 * has a domain with coordinate space R3, it divides that space into two
 * connected regions, one finite and the other infinite. In this case, the
 * topological normal of the shell is defined as being directed from the finite
 * to the infinite region. </P>
 * <P>The shell is represented by a collection of faces. The domain of the
 * shell, if present, contains all those faces, together with their bounds.
 * Associated with each face in the shell is a logical value which indicates
 * whether the face normal agrees with (TRUE) or is opposed to (FALSE) the shell
 * normal. The logical value can be applied directly as a BOOLEAN attribute of
 * an oriented face, or be defaulted to TRUE if the shell boundary attribute
 * member is a face without the orientation attribute. </P>
 * <P>The combinatorial restrictions on closed shells and geometrical
 * restrictions on their domains are designed to ensure that any domain
 * associated with a closed shell is a closed, orientable manifold. The domain
 * of a closed shell, if present, is a connected, closed, oriented 2-manifold.
 * It is always topologically equivalent to an <I>H</I>-fold torus for some
 * <I>H</I>
 * <FONT FACE="Symbol">&sup3;</FONT> 0. The number <I>H</I> is referred
 * to as the surface genus of the shell. If a shell of genus <I>H</I> has a
 * domain within coordinate space <I>R<SUP>3</SUP></I>, then the finite region
 * of space inside it is topologically equivalent to a solid ball with <I>H</I>
 * tunnels drilled through it.</P>
 * <P>The Euler equation (7) applies with <I>B</I>=0, because in this case
 * there are no holes. As in the case of open shells, the surface genus
 * <I>H</I>
 * may not be known a priori, but shall be an integer <FONT
 * FACE="Symbol">&sup3;</FONT> 0. Thus a necessary, but not sufficient,
 * condition for a well-formed closed shell is the following:</P>
 * <BLOCKQUOTE>
 * <BLOCKQUOTE>
 * <P><I>V - E - L</I> shall be even and <= 2 - 2 <I>F</I></P></BLOCKQUOTE></BLOCKQUOTE>
 * <P><U>Informal propositions</U>: </P>
 * <OL>
 * <LI>Every edge shall be referenced exactly twice by the loops of the
 * face. </LI>
 * <LI>Each oriented edge shall be unique. </LI>
 * <LI>No edge shall be referenced by more than two faces. </LI>
 * <LI>Distinct faces of the shell do not intersect, but may share
 * edges or vertices. </LI>
 * <LI>Distinct edges do not intersect but may share vertices. </LI>
 * <LI>Each face reference shall be unique. </LI>
 * <LI>The loops of the shell shall not be a mixture of poly loop and
 * other loop types. Note: this is given, since only poly loop is defined as
 * face bound definition. </LI>
 * <LI>The closed shell shall be an oriented arcwise connected 2-manifold.
 * </LI>
 * <LI>The Euler equation shall be satisfied. Note: Please refer to ISO/IS
 * 10303-42:1994, p.149 for the equation.</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcClosedShell extends IfcConnectedFaceSet {
    /**
     * @param cfsFaces The set of faces arcwise connected along common edges or
     *                 vertices.
     * @throws NullPointerException If {@code cfsFaces} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code cfsFaces} is less than 1.
     */
    public IfcClosedShell(@NonNull Set<IfcFace> cfsFaces) {
        super(cfsFaces);
    }

    /**
     * @param cfsFaces The set of faces arcwise connected along common edges or
     *                 vertices.
     * @throws NullPointerException If {@code cfsFaces} is {@code null}.
     * @throws IllegalArgumentException If the size of {@code cfsFaces} is less than 1.
     */
    public IfcClosedShell(@NonNull IfcFace... cfsFaces) {
        super(cfsFaces);
    }
}
