/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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

/**
 * <P>A property with a single value
 * (<I>IfcPropertySingleValue</I>) defines a property object which has a single (numeric or descriptive) value assigned.
 * It defines a property - single value combination for which the property name, the value with measure type (and
 * optionally the unit) is given.</P>
 * <P>The unit is handled by the <I>Unit</I> attribute:</P>
 * <UL>
 * <LI>If the <I>Unit</I> attribute is not given, then the unit is already
 * implied by the type of <I>IfcMeasureValue</I> or <I>IfcDerivedMeasureValue</I>. The associated unit can be found at
 * the <I>IfcUnitAssignment</I> globally defined at the project level (<I>IfcProject.UnitsInContext</I>).</LI>
 * <LI> If the <I>Unit</I> attribute is given, then the unit assigned by the
 * <I>Unit</I> attribute overrides the globally assigned unit.</LI>
 * </UL>
 * <P>Examples of a property with single value are:</P>
 * <TABLE CELLPADDING="2" CELLSPACING="2" BORDER="1">
 * <TR VALIGN="TOP">
 * <TD VALIGN="TOP" WIDTH="15%"><B>Name</B></TD>
 * <TD VALIGN="TOP" WIDTH="30%"><B>NominalValue</B></TD>
 * <TD VALIGN="TOP" WIDTH="25%"><B>Type <BR></B><FONT SIZE="-1">(through
 * <I>IfcValue</I>)</FONT></TD>
 * <TD VALIGN="TOP" WIDTH="30%"><B>Unit <BR></B></TD>
 * </TR>
 * <TR>
 * <TD WIDTH="15%">Description</TD>
 * <TD WIDTH="30%">Manufacturer "A" door</TD>
 * <TD WIDTH="25%"><I>IfcLabel</I></TD>
 * <TD WIDTH="30%">-</TD>
 * </TR>
 * <TR>
 * <TD WIDTH="15%">PanelThickness</TD>
 * <TD WIDTH="30%">0.12</TD>
 * <TD WIDTH="25%"><I>IfcPositiveLengthMeasure</I></TD>
 * <TD WIDTH="30%">- </TD>
 * </TR>
 * <TR>
 * <TD WIDTH="15%">ThermalTransmittance</TD>
 * <TD WIDTH="30%">2.6</TD>
 * <TD WIDTH="25%"><I>IfcThermalTransmittanceMeasure</I></TD>
 * <TD WIDTH="30%">W/(m<SUP>2</SUP>K)</TD>
 * </TR>
 * </TABLE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcPropertySingleValue extends IfcSimpleProperty {

    @Attribute(2)
    private final IfcValue nominalValue;
    @Attribute(3)
    private final IfcUnit unit;

    /**
     * @param name         Name for this property. This label is the significant name string that defines the semantic
     *                     meaning for the property.
     * @param description  Informative text to explain the property.
     * @param nominalValue Value and measure type of this property.
     *                     <blockquote><small>
     *                     NOTE&nbsp; By virtue of the defined data type, that is selected from the SELECT
     *                     <i>IfcValue</i>, the appropriate unit can be found within the {@link IfcUnitAssignment},
     *                     defined for the project if no value for the unit attribute is given.<br>
     *                     </small></blockquote>
     * @param unit         Unit for the nominal value, if not given, the default value for the measure type (given by
     *                     the TYPE of nominal value) is used as defined by the global unit assignment at IfcProject.
     * @throws NullPointerException If {@code name} is {@code null}.
     */
    public IfcPropertySingleValue(@NonNull IfcIdentifier name,
                                  IfcText description,
                                  IfcValue nominalValue,
                                  IfcUnit unit) {
        super(name, description);
        this.nominalValue = nominalValue;
        this.unit = unit;
    }
}
