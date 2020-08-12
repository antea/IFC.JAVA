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
 * A revolved area solid (<I>IfcRevolvedAreaSolid</I>) is a solid created by
 * revolving a planar bounded surface about an axis. Both, the axis and planar
 * bounded surface shall be in the same plane and the axis shall not intersect
 * the interior of the swept area. If the swept area has inner boundaries, i.e.
 * holes defined, then those holes shall be swept into holes of the solid. The
 * direction of revolution is clockwise when viewed along the axis in the
 * positive direction. </P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE: The <I>IfcRevolvedAreaSolid</I> now directly
 * defines the revolution of a cross section (also referred to as profile). It
 * thereby combines the functionality of the previous
 * IfcAttDrivenRevolvedSegment
 * entity. In contrary to the previous IfcAttDrivenRevolvedSegment, the
 * additional start angle attribute has been omitted. The axis is required to
 * lie in the xy plane of the position coordinate system
 * .</FONT></P></BLOCKQUOTE>
 * <BLOCKQUOTE>
 * <P><FONT COLOR="#0000FF" SIZE="-1">NOTE: Corresponding STEP entity:
 * revolved_area_solid. Please refer to ISO/IS 10303-42:1994, p. 184 for the
 * final definition of the formal standard. The data type of the inherited
 * <I>SweptArea</I> attribute is different, i.e. of type
 * <I>IfcProfileDef</I>. The
 * position attribute has been added to position the cross section used for the
 * revolution. </FONT></P>
 * <P><FONT COLOR="#0000FF" SIZE="-1">HISTORY: New entity in IFC Release
 * 1.5, capabilities of this entity have been enhanced in IFC Release 2x.
 * </FONT></P> </BLOCKQUOTE>
 * <P><U>Informal propositions:</U></P>
 * <OL>
 * <LI>The <I>AxisLine</I> shall lie in the plane of the <I>SweptArea</I>
 * (as defined at supertype <I>IfcSweptAreaSolid</I>).</LI>
 * <LI>The <I>AxisLine</I> shall not intersect the interior of the
 * <I>SweptArea</I> (as defined at supertype <I>IfcSweptAreaSolid</I>)
 * .</LI>
 * <LI>The <I>Angle</I> shall be between 0&deg; and 360&deg;, or 0 and
 * 2<FONT FACE="Symbol">p</FONT> (depending on the unit type for
 * <I>IfcPlaneAngleMeasure</I>).</LI>
 * </OL>
 * <TABLE CELLPADDING="2" CELLSPACING="2" BORDER="0">
 * <TR>
 * <TD VALIGN="TOP" ALIGN="LEFT">
 * <P><FONT SIZE="-1"><U>Purpose</U><BR>The revolved area solid
 * defines the revolution of a 2D area (given by a profile definition) by an
 * axis and angle. The result is a solid.</FONT></P>
 * <P><FONT SIZE="-1"><U>Parameter</U><BR>The swept area is given
 * by a profile definition. This profile is defined </FONT></P>
 * <UL>
 * <LI><FONT SIZE="-1">as a 2D bounded curve within the xy
 * plane of the position coordinate system, </FONT></LI>
 * <LI><FONT SIZE="-1">as a 2D bounded curve with holes within
 * the xy plane of the position coordinate system, </FONT></LI>
 * <LI><FONT SIZE="-1">or as a 2D primitive, defined within a 2D
 * position coordinate system, that is placed relative to the xy plane of the
 * position coordinate system</FONT></LI>
 * </UL></TD>
 * </TR>
 * </TABLE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcRevolvedAreaSolid extends IfcSweptAreaSolid {
    @Attribute(2)
    private final IfcAxis1Placement axis;
    @Attribute(3)
    private final IfcPlaneAngleMeasure angle;

    /**
     * @param sweptArea The surface defining the area to be swept. It is given
     *                  as a profile definition within the xy plane of the
     *                  position coordinate system. Its profileType must be
     *                  AREA.
     * @param position  Position coordinate system for the swept area.
     * @param axis      Axis about which revolution will take place.
     * @param angle     Angle through which the sweep will be made. This angle
     *                  is measured from the plane of the sweep.
     * @throws NullPointerException     If sweptArea, position, axis or angle is
     *                                  null.
     * @throws IllegalArgumentException If sweptArea.profileType is not AREA.
     */
    public IfcRevolvedAreaSolid(@NonNull IfcProfileDef sweptArea,
                                @NonNull IfcAxis2Placement3D position,
                                @NonNull IfcAxis1Placement axis,
                                @NonNull IfcPlaneAngleMeasure angle) {
        super(sweptArea, position);
        if (axis.getLocation().getCoordinates().get(2).getValue() != 0) {
            throw new IllegalArgumentException(
                    "The last element of axis.location.coordinates must be 0 " +
                            "(the axis must be on the xy plane)");
        }
        if (axis.getZ().getDirectionRatios().get(2).getValue() != 0) {
            throw new IllegalArgumentException(
                    "The last element of axis.z.directionRatios must be 0 " +
                            "(the axis must be on the xy plane)");
        }
        this.axis = axis;
        this.angle = angle;
    }
}
