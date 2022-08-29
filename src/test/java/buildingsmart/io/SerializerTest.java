/*
 * Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2022 Antea S.r.l.
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

package buildingsmart.io;

import buildingsmart.ifc.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SerializerTest {
    private static final String FILE_PATH = "./ifc-out/freecad-cylinder.ifc";
    private static final IfcProject validIfcProject;

    /*
     * Initializes field validIfcProject to an {@link IfcProject} object
     * containing the representation of a cylinder, ready to be serialized in an
     * IFC STEP file.
     */
    static {
        IfcPerson person =
                IfcPerson.builder().givenName(new IfcLabel("")).build();
        IfcOrganization organization =
                IfcOrganization.builder().name(new IfcLabel("")).build();
        IfcPersonAndOrganization personAndOrganization =
                new IfcPersonAndOrganization(person, organization, null);
        IfcApplication application = new IfcApplication(organization,
                                                        new IfcLabel(
                                                                "0.18 build 4" +
                                                                        " (GitTag)"),
                                                        new IfcLabel("FreeCAD"),
                                                        new IfcIdentifier(
                                                                "118df2cf_ed21_438e_a41"));
        IfcOwnerHistory ownerHistory =
                new IfcOwnerHistory(personAndOrganization,
                                    application,
                                    null,
                                    IfcChangeActionEnum.ADDED,
                                    null,
                                    personAndOrganization,
                                    application,
                                    new IfcTimeStamp(1586902585));

        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                                        new IfcDirection(0, 0, 1),
                                        new IfcDirection(1, 0, 0));
        IfcGeometricRepresentationContext geometricRepresentationContext =
                new IfcGeometricRepresentationContext(new IfcLabel("Plan"),
                                                      new IfcLabel("Model"),
                                                      new IfcDimensionCount(3),
                                                      new IfcReal(1.E-05),
                                                      axis2Placement3D,
                                                      new IfcDirection(0,
                                                                       1,
                                                                       0));

        IfcDimensionalExponents angleExponents =
                new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
        IfcSIUnit metre = new IfcSIUnit(IfcUnitEnum.LENGTHUNIT,
                                        null,
                                        IfcSIUnitName.METRE);
        IfcSIUnit squareMetre = new IfcSIUnit(IfcUnitEnum.AREAUNIT,
                                              null,
                                              IfcSIUnitName.SQUARE_METRE);
        IfcSIUnit cubicMetre = new IfcSIUnit(IfcUnitEnum.VOLUMEUNIT,
                                             null,
                                             IfcSIUnitName.CUBIC_METRE);
        IfcSIUnit radian = new IfcSIUnit(IfcUnitEnum.PLANEANGLEUNIT,
                                         null,
                                         IfcSIUnitName.RADIAN);
        IfcMeasureWithUnit conversionFactor =
                new IfcMeasureWithUnit(new IfcPlaneAngleMeasure(
                        0.017453292519943295), radian);
        IfcConversionBasedUnit degree = new IfcConversionBasedUnit(
                angleExponents,
                IfcUnitEnum.PLANEANGLEUNIT,
                new IfcLabel("DEGREE"),
                conversionFactor);
        IfcUnitAssignment unitAssignment =
                new IfcUnitAssignment(metre, squareMetre, cubicMetre, degree);

        IfcProject ifcProject = IfcProject.builder()
                .globalId(new IfcGloballyUniqueId("51f413ef_7964_4d38_b19"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Unnamed"))
                .representationContext(geometricRepresentationContext)
                .unitsInContext(unitAssignment).build();

        IfcAxis2Placement2D axis2Placement2D =
                new IfcAxis2Placement2D(new IfcCartesianPoint(0, 0),
                                        new IfcDirection(1, 0));
        IfcCircleProfileDef circle =
                new IfcCircleProfileDef(IfcProfileTypeEnum.AREA,
                                        null,
                                        axis2Placement2D,
                                        new IfcPositiveLengthMeasure(0.1));
        IfcAxis2Placement3D cylinderPlacement =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                                        new IfcDirection(0, 0, 1),
                                        new IfcDirection(1, 0, 0));
        IfcExtrudedAreaSolid cylinder = new IfcExtrudedAreaSolid(circle,
                                                                 cylinderPlacement,
                                                                 new IfcDirection(
                                                                         0,
                                                                         0,
                                                                         1),
                                                                 new IfcPositiveLengthMeasure(
                                                                         0.1));

        IfcColourRgb colour = new IfcColourRgb(null, 1, 1, 1);
        IfcSurfaceStyleRendering surfaceStyleRendering =
                IfcSurfaceStyleRendering.builder().surfaceColour(colour)
                        .reflectanceMethod(IfcReflectanceMethodEnum.FLAT)
                        .build();
        IfcSurfaceStyle surfaceStyle = new IfcSurfaceStyle(null,
                                                           IfcSurfaceSide.BOTH,
                                                           surfaceStyleRendering);
        IfcPresentationStyleAssignment presentationStyleAssignment =
                new IfcPresentationStyleAssignment(surfaceStyle);
        IfcStyledItem styledItem =
                new IfcStyledItem(cylinder, presentationStyleAssignment, null);

        IfcLocalPlacement wallPlacement =
                new IfcLocalPlacement(null, axis2Placement3D);
        IfcShapeRepresentation shapeRepresentation = new IfcShapeRepresentation(
                geometricRepresentationContext,
                new IfcLabel("Body"),
                new IfcLabel("SweptSolid"),
                cylinder);
        IfcProductDefinitionShape productDefinitionShape =
                new IfcProductDefinitionShape(null, null, shapeRepresentation);
        IfcWall wall = IfcWall.builder()
                .globalId(new IfcGloballyUniqueId("2KcxKeVfqHwhb6N5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Wall"))
                .description(new IfcText("")).objectPlacement(wallPlacement)
                .representation(productDefinitionShape).build();

        //creating a trimmed curve to test serialization of Sets with an
        // interface as type parameter (trim1 and trim2)
        IfcAxis2Placement3D circumferencePlacement =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                                        new IfcDirection(1, 0, 0),
                                        new IfcDirection(0, 1, 0));
        IfcCircle circumference = new IfcCircle(circumferencePlacement,
                                                new IfcPositiveLengthMeasure(0.5));
        Set<IfcTrimmingSelect> trim1 =
                Collections.singleton(new IfcParameterValue(0));
        Set<IfcTrimmingSelect> trim2 =
                Collections.singleton(new IfcParameterValue(Math.PI / 2));
        IfcTrimmedCurve trimmedCurve = new IfcTrimmedCurve(circumference,
                                                           trim1,
                                                           trim2,
                                                           IfcBoolean.T,
                                                           IfcTrimmingPreference.PARAMETER);
        IfcLocalPlacement curvePlacement =
                new IfcLocalPlacement(null, axis2Placement3D);
        IfcShapeRepresentation curveShapeRepresentation =
                new IfcShapeRepresentation(geometricRepresentationContext,
                                           new IfcLabel("Body"),
                                           new IfcLabel("GeometricCurveSet"),
                                           trimmedCurve);
        IfcProductDefinitionShape curveProductDefinitionShape =
                new IfcProductDefinitionShape(null,
                                              null,
                                              curveShapeRepresentation);
        IfcProxy curveProxy = IfcProxy.builder()
                .globalId(new IfcGloballyUniqueId("2KcxKeVfqHwhb6N5zd1234"))
                .ownerHistory(ownerHistory).name(new IfcLabel("TrimmedCurve"))
                .description(new IfcText("")).objectPlacement(curvePlacement)
                .representation(curveProductDefinitionShape)
                .proxyType(IfcObjectTypeEnum.PRODUCT).build();

        IfcSite site = IfcSite.builder()
                .globalId(new IfcGloballyUniqueId("2KdG88VfqHwfDCN5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Default Site"))
                .description(new IfcText(""))
                .compositionType(IfcElementCompositionEnum.ELEMENT).build();
        IfcRelAggregates projectLink = IfcRelAggregates.builder()
                .globalId(new IfcGloballyUniqueId("2KdG89VfqHweGDN5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("ProjectLink"))
                .description(new IfcText("")).relatingObject(ifcProject)
                .relatedObject(site).build();
        IfcBuilding building = IfcBuilding.builder()
                .globalId(new IfcGloballyUniqueId("2KdHMSVfqHwfiJN5zdz5Bw"))
                .ownerHistory(ownerHistory)
                .name(new IfcLabel("Default Building"))
                .description(new IfcText(""))
                .compositionType(IfcElementCompositionEnum.ELEMENT).build();
        IfcRelAggregates siteLink = IfcRelAggregates.builder()
                .globalId(new IfcGloballyUniqueId("2KdHMTVfqHwePlN5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("SiteLink"))
                .description(new IfcText("")).relatingObject(site)
                .relatedObject(building).build();
        IfcBuildingStorey buildingStorey = IfcBuildingStorey.builder()
                .globalId(new IfcGloballyUniqueId("2KdHMUVfqHwg4XN5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Default Storey"))
                .description(new IfcText(""))
                .compositionType(IfcElementCompositionEnum.ELEMENT).build();
        IfcRelAggregates defaultStoreyLink = IfcRelAggregates.builder()
                .globalId(new IfcGloballyUniqueId("2KdHMVVfqHwhFMN5zdz5Bw"))
                .ownerHistory(ownerHistory)
                .name(new IfcLabel("DefaultStoreyLink"))
                .description(new IfcText("")).relatingObject(building)
                .relatedObject(buildingStorey).build();
        IfcRelContainedInSpatialStructure unassignedObjectsLink =
                IfcRelContainedInSpatialStructure.
                        builder().globalId(new IfcGloballyUniqueId(
                        "2KdIamVfqHwf$aN5zdz5Bw")).ownerHistory(ownerHistory)
                        .name(new IfcLabel("UnassignedObjectsLink"))
                        .description(new IfcText(""))
                        .relatingStructure(buildingStorey).relatedElement(wall)
                        .relatedElement(curveProxy).build();
        validIfcProject = ifcProject;
    }

    /**
     * @param filePath The path to the IFC file of which to retrieve the DATA
     *                 section.
     * @return The DATA section of the IFC file.
     *
     * @throws IOException       If an I/O error occurs reading from the file or
     *                           a malformed or unmappable byte sequence is
     *                           read.
     * @throws SecurityException In the case of the default provider, and a
     *                           security manager is installed, the {@link
     *                           SecurityManager#checkRead(String) checkRead}
     *                           method is invoked to check read access to the
     *                           file.
     */
    private static String getDataSection(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                                                StandardCharsets.US_ASCII);
        StringBuilder dataSection = new StringBuilder();
        for (int i = 6; i < lines.size() - 1; i++) {
            // DATA section starts at line 6 (if the first one is line 0) and
            // ends at the penultimate line
            dataSection.append(lines.get(i)).append("\n");
        }
        return dataSection.toString();
    }

    @Test
    public void serialize_correctHeader_correctProject_correctPath()
            throws IOException {
        final String expectedDataSection = "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                "#2=IFCORGANIZATION($,'',$,$,$);\n" + "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)','FreeCAD','118df2cf_ed21_438e_a41');\n" +
                "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4,1586902585);\n" + "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                "#7=IFCDIRECTION((0.0,0.0,1.0));\n" + "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" + "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan','Model',3,1.0E-5,#9,#10);\n" +
                "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" + "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" + "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0.017453292519943295),#16);\n" +
                "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.,'DEGREE',#17);\n" +
                "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5,'Unnamed',$,$,$,$,(#11),#19);\n" +
                "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default Site','',$,$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5,'ProjectLink','',#20,(#21));\n" +
                "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5,'SiteLink','',#21,(#23));\n" +
                "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5,'Default Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5,'DefaultStoreyLink','',#23,(#25));\n" +
                "#27=IFCLOCALPLACEMENT($,#9);\n" + "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                "#29=IFCDIRECTION((1.0,0.0));\n" + "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" + "#32=IFCEXTRUDEDAREASOLID(#31,#9,#7,0.1);\n" +
                "#33=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid',(#32));\n" +
                "#34=IFCPRODUCTDEFINITIONSHAPE($,$,(#33));\n" +
                "#35=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$,#27,#34,$);\n" + "#36=IFCLOCALPLACEMENT($,#9);\n" +
                "#37=IFCAXIS2PLACEMENT3D(#6,#8,#10);\n" + "#38=IFCCIRCLE(#37,0.5);\n" +
                "#39=IFCTRIMMEDCURVE(#38,(IFCPARAMETERVALUE(0.0)),(IFCPARAMETERVALUE(1.5707963267948966)),.T.," +
                ".PARAMETER.);\n" +
                "#40=IFCSHAPEREPRESENTATION(#11,'Body','GeometricCurveSet',(#39));\n" +
                "#41=IFCPRODUCTDEFINITIONSHAPE($,$,(#40));\n" +
                "#42=IFCPROXY('2KcxKeVfqHwhb6N5zd1234',#5,'TrimmedCurve','',$,#36,#41,.PRODUCT.,$);\n" +
                "#43=IFCRELCONTAINEDINSPATIALSTRUCTURE('2KdIamVfqHwf$aN5zdz5Bw',#5,'UnassignedObjectsLink','',(#35," +
                "#42),#25);\n" +
                "#44=IFCCOLOURRGB($,1.0,1.0,1.0);\n" + "#45=IFCSURFACESTYLERENDERING(#44,$,$,$,$,$,$,$,.FLAT.);\n" +
                "#46=IFCSURFACESTYLE($,.BOTH.,(#45));\n" + "#47=IFCPRESENTATIONSTYLEASSIGNMENT((#46));\n" +
                "#48=IFCSTYLEDITEM(#32,(#47),$);\n" + "ENDSEC;\n";
        Serializer serializer = new Serializer();

        serializer.serialize(new Header().setDescription(
                "ViewDefinition[CoordinationView]"),
                             validIfcProject,
                             FILE_PATH);

        String writtenDataSection = getDataSection(FILE_PATH);
        Assert.assertEquals(expectedDataSection, writtenDataSection);
    }

    @Test(expected = NullPointerException.class)
    public void serialize_nullHeader() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(null, validIfcProject, FILE_PATH);
    }

    @Test
    public void serialize_nullProject() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(new Header(), null, FILE_PATH);
        String writtenDataSection = getDataSection(FILE_PATH);
        Assert.assertEquals("DATA;\nENDSEC;\n", writtenDataSection);
    }

    @Test(expected = NullPointerException.class)
    public void serialize_nullPath() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(new Header(), validIfcProject, (String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void serialize_emptyPath() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(new Header(), validIfcProject, "");
    }

    @Test(expected = FileNotFoundException.class)
    public void serialize_rootPath() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(new Header(), validIfcProject, "/");
    }

    @Test(expected = FileNotFoundException.class)
    public void serialize_pointPath() throws IOException {
        Serializer serializer = new Serializer();
        serializer.serialize(new Header(), validIfcProject, ".");
    }
}