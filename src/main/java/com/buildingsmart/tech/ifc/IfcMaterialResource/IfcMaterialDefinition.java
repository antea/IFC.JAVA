// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcMaterialResource;

import java.util.HashSet;
import java.util.Set;

import com.buildingsmart.tech.annotations.Description;
import com.buildingsmart.tech.annotations.Guid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Guid("b31f0891-e60f-4cc1-9b3b-9bbae1fde79c")
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class IfcMaterialDefinition implements IfcMaterialSelect, com.buildingsmart.tech.ifc.IfcPropertyResource.IfcObjectReferenceSelect, com.buildingsmart.tech.ifc.IfcExternalReferenceResource.IfcResourceObjectSelect
{
	@Description("Use of the <em>IfcMaterialDefinition</em> subtypes within the material association of an element occurrence or element type. The association is established by the <em>IfcRelAssociatesMaterial</em> relationship.  <blockquote class=\"change-ifc2x4\">IFC4 CHANGE&nbsp; The inverse attribute has been added.</blockquote>")
	@Guid("b1ec3680-78e2-4c73-915a-89a294a334a7")
	private Set<com.buildingsmart.tech.ifc.IfcProductExtension.IfcRelAssociatesMaterial> associatedTo = new HashSet<com.buildingsmart.tech.ifc.IfcProductExtension.IfcRelAssociatesMaterial>();

	@Description("Reference to external references, e.g. library, classification, or document information, that are associated to the material.  <blockquote class=\"change-ifc2x4\">IFC4 CHANGE&nbsp; The inverse attribute has been added.</blockquote>")
	@Guid("d28dbc0d-d8f5-445d-8f01-e84587f1ef49")
	private Set<com.buildingsmart.tech.ifc.IfcExternalReferenceResource.IfcExternalReferenceRelationship> hasExternalReferences = new HashSet<com.buildingsmart.tech.ifc.IfcExternalReferenceResource.IfcExternalReferenceRelationship>();

	@JacksonXmlProperty(isAttribute=false, localName = "HasProperties")
	@Description("Material properties assigned to instances of subtypes of <em>IfcMaterialDefinition</em>.  <blockquote class=\"change-ifc2x4\">IFC4 CHANGE&nbsp; The inverse attribute has been added.</blockquote>")
	@Guid("d60a2049-8e83-46d1-9e06-029c5163fab4")
	private Set<com.buildingsmart.tech.ifc.IfcMaterialResource.IfcMaterialProperties> hasProperties = new HashSet<com.buildingsmart.tech.ifc.IfcMaterialResource.IfcMaterialProperties>();


	public IfcMaterialDefinition()
	{
	}

	public Set<com.buildingsmart.tech.ifc.IfcProductExtension.IfcRelAssociatesMaterial> getAssociatedTo() {
		return this.associatedTo;
	}

	public Set<com.buildingsmart.tech.ifc.IfcExternalReferenceResource.IfcExternalReferenceRelationship> getHasExternalReferences() {
		return this.hasExternalReferences;
	}

	public Set<com.buildingsmart.tech.ifc.IfcMaterialResource.IfcMaterialProperties> getHasProperties() {
		return this.hasProperties;
	}


}
