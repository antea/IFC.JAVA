// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcGeometryResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.buildingsmart.tech.annotations.Description;
import com.buildingsmart.tech.annotations.Guid;
import com.buildingsmart.tech.annotations.MaxLength;
import com.buildingsmart.tech.annotations.MinLength;
import com.buildingsmart.tech.annotations.Required;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Guid("ba9fc3b6-e728-4ad6-be84-9a7975944d33")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfcTrimmedCurve extends IfcBoundedCurve
{
	@JacksonXmlProperty(isAttribute=false, localName = "BasisCurve")
	@Description("The curve to be trimmed. For curves with multiple representations any parameter values given as Trim1 or Trim2 refer to the master representation of the BasisCurve only.")
	@Required()
	@Guid("e2455642-0894-4cbf-ba52-1acb4f6c4715")
	private com.buildingsmart.tech.ifc.IfcGeometryResource.IfcCurve basisCurve;

	@Description("The first trimming point which may be specified as a Cartesian point, as a real parameter or both.")
	@Required()
	@MinLength(1)
	@MaxLength(2)
	@Guid("58e41bff-104e-4220-bbdf-7b4a2098b9a3")
	private Set<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect> trim1 = new HashSet<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect>();

	@Description("The second trimming point which may be specified as a Cartesian point, as a real parameter or both.")
	@Required()
	@MinLength(1)
	@MaxLength(2)
	@Guid("86e14707-9230-456e-b901-560cadf72284")
	private Set<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect> trim2 = new HashSet<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect>();

	@JacksonXmlProperty(isAttribute=true, localName = "SenseAgreement")
	@Description("Flag to indicate whether the direction of the trimmed curve agrees with or is opposed to the direction of the basis curve.")
	@Required()
	@Guid("46ffc35f-be5c-4de9-8f14-2900fade3416")
	private Boolean senseAgreement;

	@JacksonXmlProperty(isAttribute=true, localName = "MasterRepresentation")
	@Description("Where both parameter and point are present at either end of the curve this indicates the preferred form.")
	@Required()
	@Guid("fc7412a9-6e7e-4f16-bf76-cb309218dbbb")
	private com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingPreference masterRepresentation;


	public IfcTrimmedCurve()
	{
	}

	public IfcTrimmedCurve(com.buildingsmart.tech.ifc.IfcGeometryResource.IfcCurve basisCurve, com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect[] trim1, com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect[] trim2, Boolean senseAgreement, com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingPreference masterRepresentation)
	{
		this.basisCurve = basisCurve;
		this.trim1 = new HashSet<>(Arrays.asList(trim1));
		this.trim2 = new HashSet<>(Arrays.asList(trim2));
		this.senseAgreement = senseAgreement;
		this.masterRepresentation = masterRepresentation;
	}

	public com.buildingsmart.tech.ifc.IfcGeometryResource.IfcCurve getBasisCurve() {
		return this.basisCurve;
	}

	public void setBasisCurve(com.buildingsmart.tech.ifc.IfcGeometryResource.IfcCurve basisCurve) {
		this.basisCurve = basisCurve;
	}

	public Set<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect> getTrim1() {
		return this.trim1;
	}

	public Set<com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingSelect> getTrim2() {
		return this.trim2;
	}

	public Boolean getSenseAgreement() {
		return this.senseAgreement;
	}

	public void setSenseAgreement(Boolean senseAgreement) {
		this.senseAgreement = senseAgreement;
	}

	public com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingPreference getMasterRepresentation() {
		return this.masterRepresentation;
	}

	public void setMasterRepresentation(com.buildingsmart.tech.ifc.IfcGeometryResource.IfcTrimmingPreference masterRepresentation) {
		this.masterRepresentation = masterRepresentation;
	}


}
