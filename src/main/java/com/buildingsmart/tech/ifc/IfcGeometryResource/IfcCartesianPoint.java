// This file was automatically generated from IFCDOC at https://technical.buildingsmart.org/.
// Very slight modifications were made to made content align with ifcXML reference examples.
// Use this class library to create IFC-compliant (web) applications with XML and JSON data.
// Author: Pieter Pauwels, Eindhoven University of Technology

package com.buildingsmart.tech.ifc.IfcGeometryResource;

import com.buildingsmart.tech.annotations.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Guid("2ddee6ae-615d-41ff-baa2-ba51d50e78c2")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfcCartesianPoint extends IfcPoint implements IfcTrimmingSelect
{
	@Description("The first, second, and third coordinate of the point location. If placed in a two or three dimensional rectangular Cartesian coordinate system, Coordinates[1] is the X coordinate, Coordinates[2] is the Y coordinate, and Coordinates[3] is the Z coordinate.")
	@Required()
	@Guid("4a8b21fa-0aa6-4d2a-b0ce-55d5777e3b0f")
	@MinLength(1)
	@MaxLength(3)
	@JacksonXmlProperty(isAttribute = false, localName = "Double")
	@JacksonXmlElementWrapper(useWrapping = true, localName = "Coordinates")
	private List<Double> coordinates;


	public IfcCartesianPoint()
	{
	}

	public IfcCartesianPoint(Double[] coordinates)
	{
		if (coordinates.length < 1 || coordinates.length > 3)
			throw new IllegalArgumentException("number of coordinates must be between 1 and 3");
		this.coordinates = new ArrayList<>(Arrays.asList(coordinates));
	}

	public List<Double> getCoordinates() {
		return this.coordinates;
	}

	public int getDim() {
		return 0;
	}


}

