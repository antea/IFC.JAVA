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
 * A half space solid is defined by the half space which is the regular subset
 * of the domain which lies on one side of an unbounded surface. The side of the
 * surface which is in the half space is determined by the surface normal and
 * the agreement flag. If the agreement flag is TRUE, then the subset is the one
 * the normal points away from. If the agreement flag is FALSE, then the subset
 * is the one the normal points into. For a valid half space solid the surface
 * shall divide the domain into exactly two subsets. Also, within the domain the
 * surface shall be manifold and all surface normals shall point into the same
 * subset. </P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE A half space is not a subtype of solid model
 * (<I>IfcSolidModel</I>), half space solids are only useful as operands in
 * Boolean expressions.</FONT></P></BLOCKQUOTE>
 * <P><U>Informal propositions:</U></P>
 * <OL>
 * <LI>The base surface shall divide the domain into exactly two subsets
 * . If the half space solid is of subtype boxed half space
 * (<I>IfcBoxedHalfSpace</I>), the domain in question is that of the attribute
 * enclosure. In all other cases the domain is all of space and the base surface
 * shall be unbounded .</LI>
 * <LI>The base surface shall be an unbounded surface (subtype of
 * <I>IfcElementarySurface</I>).</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcHalfSpaceSolid extends IfcGeometricRepresentationItem
        implements IfcBooleanOperand {
    // derived attribute
    private static final IfcDimensionCount DIM = new IfcDimensionCount(3);
    @Attribute(0)
    private final IfcSurface baseSurface;
    @Attribute(1)
    private final IfcBoolean agreementFlag;

    /**
     * @param baseSurface   Surface defining side of half space.
     * @param agreementFlag The agreement flag is TRUE if the normal to the
     *                      BaseSurface points away from the material of the
     *                      IfcHalfSpaceSolid. Otherwise it is FALSE.
     * @throws NullPointerException If any of the arguments are null.
     */
    public IfcHalfSpaceSolid(@NonNull IfcSurface baseSurface,
                             @NonNull IfcBoolean agreementFlag) {
        this.baseSurface = baseSurface;
        this.agreementFlag = agreementFlag;
    }

    /**
     * @return The space dimensionality of this class, it is always 3.
     */
    @Override
    public IfcDimensionCount getDim() {
        return DIM;
    }
}
