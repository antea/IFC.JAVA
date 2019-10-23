// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcKernel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.buildingsmart.tech.annotations.Description;
import com.buildingsmart.tech.annotations.Guid;
import com.buildingsmart.tech.annotations.MinLength;
import com.buildingsmart.tech.annotations.Required;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Guid("50e72608-2b70-4951-afa7-68d8cf130d15")
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class IfcRelAssociates extends IfcRelationship
{
	@Description("Set of object or property definitions to which the external references or information is associated. It includes object and type objects, property set templates, property templates and property sets and contexts.  <blockquote class=\"change-ifc2x4\">IFC4 CHANGE&nbsp; The attribute datatype has been changed from <em>IfcRoot</em> to <em>IfcDefinitionSelect</em>.</blockquote>")
	@Required()
	@MinLength(1)
	@Guid("22a3ccef-98eb-4699-b8ff-4edd0ca9ab83")
	private Set<com.buildingsmart.tech.ifc.IfcKernel.IfcDefinitionSelect> relatedObjects = new HashSet<com.buildingsmart.tech.ifc.IfcKernel.IfcDefinitionSelect>();


	public IfcRelAssociates()
	{
	}

	public IfcRelAssociates(String globalId, com.buildingsmart.tech.ifc.IfcKernel.IfcDefinitionSelect[] relatedObjects)
	{
		super(globalId);
		this.relatedObjects = new HashSet<>(Arrays.asList(relatedObjects));
	}

	public Set<com.buildingsmart.tech.ifc.IfcKernel.IfcDefinitionSelect> getRelatedObjects() {
		return this.relatedObjects;
	}


}
