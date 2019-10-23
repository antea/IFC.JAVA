// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcConstructionMgmtDomain;

import com.buildingsmart.tech.annotations.Description;
import com.buildingsmart.tech.annotations.Guid;
import com.buildingsmart.tech.annotations.Required;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Guid("3c666024-11d1-4495-9763-09f6ca7f89e7")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfcLaborResourceType extends IfcConstructionResourceType
{
	@JacksonXmlProperty(isAttribute=true, localName = "PredefinedType")
	@Description("Defines types of labour resources.  <p></p>")
	@Required()
	@Guid("b175b20f-a6bc-4143-92dc-685e7d251824")
	private com.buildingsmart.tech.ifc.IfcConstructionMgmtDomain.IfcLaborResourceTypeEnum predefinedType;


	public IfcLaborResourceType()
	{
	}

	public IfcLaborResourceType(String globalId, com.buildingsmart.tech.ifc.IfcConstructionMgmtDomain.IfcLaborResourceTypeEnum predefinedType)
	{
		super(globalId);
		this.predefinedType = predefinedType;
	}

	public com.buildingsmart.tech.ifc.IfcConstructionMgmtDomain.IfcLaborResourceTypeEnum getPredefinedType() {
		return this.predefinedType;
	}

	public void setPredefinedType(com.buildingsmart.tech.ifc.IfcConstructionMgmtDomain.IfcLaborResourceTypeEnum predefinedType) {
		this.predefinedType = predefinedType;
	}


}
