/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2020 Antea S.r.l.
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

import buildingsmart.io.DefinedType;

/**
 * <P><U>Definition from IAI</U>: This enumeration defines the basic
 * configuration of the stair type in terms of the number of stair flights and the number of landings. The type also
 * distinguished turns by windings or by landings. In addition the subdivision of the straight and changing direction
 * stairs is included. The stair configurations are given for stairs without and with one, two or three landings.</P>
 * <P>Stairs which are subdivided into more than three landings have to be
 * defined by the geometry only. Also stairs with non-regular shapes have to be defined by the geometry only. The type
 * of such stairs is OTHEROPERATION.</P>
 * <P><U>Illustration</U></P>
 * <TABLE BORDER="1">
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT"><I>Enumerator</I></TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><I>Description</I></TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">StraightRunStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair extending from
 * one level to another without turns or winders. The stair consists of one straight flight.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">TwoStraightRunStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A straight stair
 * consisting of two straight flights without turns but with one landing.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">QuarterWindingStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair consisting of one
 * flight with a quarter winder, which is making a 90&deg; turn. The direction of the turn is determined by the walking
 * line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">QuarterTurnStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair making a 90&deg;
 * turn, consisting of two straight flights connected by a quarterspace landing. The direction of the turn is determined
 * by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">HalfWindingStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair consisting of one
 * flight with one half winder, which makes a 180&deg; turn. The orientation of the turn is determined by the walking
 * line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">HalfTurnStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair making a 180&deg;
 * turn, consisting of two straight flights connected by a halfspace landing. The orientation of the turn is determined
 * by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">TwoQuarterWindingStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair consisting of one
 * flight with two quarter winders, which make a 90&deg; turn. The stair makes a 180&deg; turn. The direction of the
 * turns is determined by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">TwoQuarterTurnStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair making a 180&deg;
 * turn, consisting of three straight flights connected by two quarterspace landings. The direction of the turns is
 * determined by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">ThreeQuarterWindingStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair consisting of one
 * flight with three quarter winders, which make a 90&deg; turn. The stair makes a 270&deg; turn. The direction of the
 * turns is determined by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">ThreeQuarterTurnStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair making a 270&deg;
 * turn, consisting of four straight flights connected by three quarterspace landings. The direction of the turns is
 * determined by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">SpiralStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair constructed with
 * winders around a circular newel often without landings. Depending on outer boundary it can be either a circular,
 * elliptical or rectangular spiral stair. The orientation of the winding stairs is determined by the walking
 * line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">DoubleReturnStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT"><BR>A stair having one
 * straight flight to a wide quarterspace landing, and two side flights from that landing into opposite directions. The
 * stair is making a 90&deg; turn. The direction of traffic is determined by the walking line.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">CurvedRunStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A stair extending from one
 * level to another without turns or winders. The stair is consisting of one curved flight.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">TwoCurvedRunStair</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">A curved stair consisting of
 * two curved flights without turns but with one landing.</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT"> OtherOperation</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">Free form stair (user defined
 * operation type)</TD>
 * </TR>
 * <TR VALIGN="TOP">
 * <TD WIDTH="30%" VALIGN="TOP" ALIGN="LEFT">NotDefined</TD>
 * <TD WIDTH="46%" VALIGN="TOP" ALIGN="LEFT">&nbsp;</TD>
 * </TR>
 * </TABLE>
 */
public enum IfcStairTypeEnum implements DefinedType {
    STRAIGHT_RUN_STAIR,
    TWO_STRAIGHT_RUN_STAIR,
    QUARTER_WINDING_STAIR,
    QUARTER_TURN_STAIR,
    HALF_WINDING_STAIR,
    HALF_TURN_STAIR,
    TWO_QUARTER_WINDING_STAIR,
    TWO_QUARTER_TURN_STAIR,
    THREE_QUARTER_WINDING_STAIR,
    THREE_QUARTER_TURN_STAIR,
    SPIRAL_STAIR,
    DOUBLE_RETURN_STAIR,
    CURVED_RUN_STAIR,
    TWO_CURVED_RUN_STAIR,
    USERDEFINED,
    NOTDEFINED;

    /**
     * @return The representation of the Defined Type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
