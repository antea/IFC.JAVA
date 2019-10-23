// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcQuantityResource;

import com.buildingsmart.tech.annotations.Description;
import com.buildingsmart.tech.annotations.Guid;
import com.buildingsmart.tech.annotations.Required;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Guid("bb60eefd-5d19-4646-9795-6bda10980402")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfcQuantityArea extends IfcPhysicalSimpleQuantity
{
	@JacksonXmlProperty(isAttribute=true, localName = "AreaValue")
	@Description("Area measure value of this quantity.")
	@Required()
	@Guid("01ac60b8-6a0e-4713-b657-70c3f0bb6c39")
	private double areaValue;

	@JacksonXmlProperty(isAttribute=true, localName = "Formula")
	@Description("A formula by which the quantity has been calculated. It can be assigned in addition to the actual value of the quantity. Formulas could be mathematic calculations (like width x height), database links, or a combination. The formula is for informational purposes only.  <blockquote class=\"change-ifc2x4\">IFC4 CHANGE  Attribute added to the end of the attribute list.</blockquote>")
	@Guid("52ac3915-b8c7-4adc-8d09-a872b27e4d0e")
	private String formula;


	public IfcQuantityArea()
	{
	}

	public IfcQuantityArea(String name, double areaValue)
	{
		super(name);
		this.areaValue = areaValue;
	}

	public double getAreaValue() {
		return this.areaValue;
	}

	public void setAreaValue(double areaValue) {
		this.areaValue = areaValue;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}


}
