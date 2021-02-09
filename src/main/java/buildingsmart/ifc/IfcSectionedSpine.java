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

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

/**
 * <P>A sectioned spine (<I>IfcSectionedSpine</I>)
 * is a representation of the shape of a three dimensional object composed by a
 * number of planar cross sections, and a spine curve. The shape is defined
 * between the first element of cross sections and the last element of the cross
 * sections. A sectioned spine may be used to represent a surface or a solid but
 * the interpolation of the shape between the cross sections is not defined.
 * </P>
 * <P>For the representation of a solid all cross sections are areas. For
 * representation of a surface all cross sections are curves. The cross sections
 * are defined as profiles, whereas the consecutive profiles may be derived by a
 * transformation of the start profile or the previous consecutive profile.</P>
 * <P>The spine curve shall be of type <I>IfcCompositeCurve</I>, each of its
 * segments (<I>IfcCompositeCurveSegment</I>) shall correspond to the part
 * between exactly two consecutive cross-sections.</P>
 * <P><U>Informal propositions</U></P>
 * <OL>
 * <LI> non of the cross sections, after being placed by the cross section
 * positions, shall intersect </LI>
 * <LI>non of the cross sections, after being placed by the cross section
 * positions, shall lie in the same plane</LI>
 * <LI>the local origin of each cross section position shall lie at the
 * beginning or end of a composite curve segment.</LI>
 * </OL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcSectionedSpine extends IfcGeometricRepresentationItem {
    @Attribute(0)
    private final IfcCompositeCurve spineCurve;
    @Attribute(1)
    private final List<IfcProfileDef> crossSections;
    @Attribute(2)
    private final List<IfcAxis2Placement3D> crossSectionPositions;

    /**
     * @param spineCurve            A single composite curve, that defines the
     *                              spine curve. Each of the composite curve
     *                              segments correspond to the part between two
     *                              cross-sections.
     * @param crossSections         A list of at least two cross sections, each
     *                              defined within the xy plane of the position
     *                              coordinate system of the cross section. The
     *                              position coordinate system is given by the
     *                              corresponding list CrossSectionPositions.
     * @param crossSectionPositions Position coordinate systems for the cross
     *                              sections that form the sectioned spine. The
     *                              profiles defining the cross sections are
     *                              positioned within the xy plane of the
     *                              corresponding position coordinate system.
     * @throws NullPointerException     If any of the arguments are null.
     * @throws IllegalArgumentException If the size of crossSections is less
     *                                  than 2. if the sizes of crossSections
     *                                  and crossSectionPositions differ, if
     *                                  spineCurve is not three-dimensional, if
     *                                  profileType is not the same for all
     *                                  elements of crossSections.
     */
    public IfcSectionedSpine(@NonNull IfcCompositeCurve spineCurve,
                             @NonNull List<IfcProfileDef> crossSections,
                             @NonNull List<IfcAxis2Placement3D> crossSectionPositions) {
        if (crossSections.size() < 2) {
            throw new IllegalArgumentException(
                    "size of crossSections must be at least 2");
        }
        if (crossSections.size() != crossSectionPositions.size()) {
            throw new IllegalArgumentException(
                    "size of crossSections and crossSectionPositions must be " +
                            "the same");
        }
        if (spineCurve.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "dimensionality of spineCurve must be 3");
        }
        IfcProfileTypeEnum profileType = crossSections.get(0).getProfileType();
        if (crossSections.stream()
                .anyMatch(crossSection -> crossSection.getProfileType() !=
                        profileType)) {
            throw new IllegalArgumentException(
                    "profile type (either AREA or CURVE) must be " +
                            "the same within elements of crossSections");
        }
        this.spineCurve = spineCurve;
        this.crossSections = crossSections;
        this.crossSectionPositions = crossSectionPositions;
    }
}
