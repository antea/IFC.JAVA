/*
 * Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.io;

import buildingsmart.ifc.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(Parameterized.class)
public class SerializerTest {
    private static final String FILE_PATH = "./ifc-out/freecad-cylinder.ifc";
    private static IfcProject validIfcProject;
    @Parameter
    public Class<?> serializerClass;
    private Serializer testedSerializer;

    @Parameters(name = "{index}: Impl Class: {0}")
    public static Collection<Class<?>[]> serializersToTest() {
        List<Class<?>[]> serializers = new ArrayList<>(2);
        serializers.add(new Class<?>[]{Serializer.class});
        serializers.add(new Class<?>[]{MultiThreadedSerializer.class});
        return serializers;
    }

    /**
     * @param filePath The path to the IFC file of which to retrieve the DATA
     *                 section.
     * @return The DATA section of the IFC file.
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
        List<String> lines =
                Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        StringBuilder dataSection = new StringBuilder();
        for (int i = 6; i < lines.size() - 1; i++) {
            // DATA section starts at line 6 (if the first one is line 0) and
            // ends at the penultimate line
            dataSection.append(lines.get(i)).append("\n");
        }
        return dataSection.toString();
    }

    /**
     * Initializes field validIfcProject to an {@link IfcProject} object
     * containing the representation of a cylinder, ready to be serialized in an
     * IFC STEP file.
     */
    @BeforeClass
    public static void setUpClass() {
        IfcPerson person =
                IfcPerson.Builder.anIfcPerson().givenName(new IfcLabel(""))
                        .build();
        IfcOrganization organization =
                IfcOrganization.Builder.anIfcOrganization()
                        .name(new IfcLabel("")).build();
        IfcPersonAndOrganization personAndOrganization =
                new IfcPersonAndOrganization(person, organization, null);
        IfcApplication application = new IfcApplication(organization,
                new IfcLabel("0.18 build 4 (GitTag)"), new IfcLabel("FreeCAD"),
                new IfcIdentifier("118df2cf_ed21_438e_a41"));
        IfcOwnerHistory ownerHistory =
                new IfcOwnerHistory(personAndOrganization, application, null,
                        IfcChangeActionEnum.ADDED, null, personAndOrganization,
                        application, new IfcTimeStamp(1586902585));

        IfcAxis2Placement3D axis2Placement3D =
                new IfcAxis2Placement3D(new IfcCartesianPoint(0, 0, 0),
                        new IfcDirection(0, 0, 1), new IfcDirection(1, 0, 0));
        IfcGeometricRepresentationContext geometricRepresentationContext =
                new IfcGeometricRepresentationContext(new IfcLabel("Plan"),
                        new IfcLabel("Model"), new IfcDimensionCount(3),
                        new IfcReal(1.E-05), axis2Placement3D,
                        new IfcDirection(0, 1, 0));

        IfcDimensionalExponents angleExponents =
                new IfcDimensionalExponents(0, 0, 0, 0, 0, 0, 0);
        IfcSIUnit metre = new IfcSIUnit(IfcUnitEnum.LENGTHUNIT, null,
                IfcSIUnitName.METRE);
        IfcSIUnit squareMetre = new IfcSIUnit(IfcUnitEnum.AREAUNIT, null,
                IfcSIUnitName.SQUARE_METRE);
        IfcSIUnit cubicMetre = new IfcSIUnit(IfcUnitEnum.VOLUMEUNIT, null,
                IfcSIUnitName.CUBIC_METRE);
        IfcSIUnit radian = new IfcSIUnit(IfcUnitEnum.PLANEANGLEUNIT, null,
                IfcSIUnitName.RADIAN);
        IfcMeasureWithUnit conversionFactor = new IfcMeasureWithUnit(
                new IfcPlaneAngleMeasure(0.017453292519943295), radian);
        IfcConversionBasedUnit degree =
                new IfcConversionBasedUnit(angleExponents,
                        IfcUnitEnum.PLANEANGLEUNIT, new IfcLabel("DEGREE"),
                        conversionFactor);
        IfcUnitAssignment unitAssignment =
                new IfcUnitAssignment(metre, squareMetre, cubicMetre, degree);

        IfcProject ifcProject = IfcProject.Builder.anIfcProject()
                .globalId(new IfcGloballyUniqueId("51f413ef_7964_4d38_b19"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Unnamed"))
                .representationContexts(
                        Collections.singleton(geometricRepresentationContext))
                .unitsInContext(unitAssignment).build();

        IfcAxis2Placement2D axis2Placement2D =
                new IfcAxis2Placement2D(new IfcCartesianPoint(0, 0),
                        new IfcDirection(1, 0));
        IfcCircleProfileDef circle =
                new IfcCircleProfileDef(IfcProfileTypeEnum.AREA, null,
                        axis2Placement2D, new IfcPositiveLengthMeasure(0.1));
        IfcAxis2Placement3D cylinderPlacement = new IfcAxis2Placement3D(
                new IfcCartesianPoint(-1.4210854715202E-17,
                        -2.73641172593403E-18, 0), new IfcDirection(0, 0, 1),
                new IfcDirection(1, 0, 0));
        IfcExtrudedAreaSolid cylinder =
                new IfcExtrudedAreaSolid(circle, cylinderPlacement,
                        new IfcDirection(0, 0, 1), new IfcLengthMeasure(0.1));

        IfcColourRgb colour = new IfcColourRgb(null, 1, 1, 1);
        IfcSurfaceStyleRendering surfaceStyleRendering =
                IfcSurfaceStyleRendering.Builder.anIfcSurfaceStyleRendering()
                        .surfaceColour(colour)
                        .reflectanceMethod(IfcReflectanceMethodEnum.FLAT)
                        .build();
        IfcSurfaceStyle surfaceStyle =
                new IfcSurfaceStyle(null, IfcSurfaceSide.BOTH,
                        surfaceStyleRendering);
        IfcPresentationStyleAssignment presentationStyleAssignment =
                new IfcPresentationStyleAssignment(surfaceStyle);
        IfcStyledItem styledItem =
                new IfcStyledItem(cylinder, presentationStyleAssignment, null);

        IfcLocalPlacement wallPlacement =
                new IfcLocalPlacement(null, axis2Placement3D);
        IfcShapeRepresentation shapeRepresentation =
                new IfcShapeRepresentation(geometricRepresentationContext,
                        new IfcLabel("Body"), new IfcLabel("SweptSolid"),
                        cylinder);
        IfcProductDefinitionShape productDefinitionShape =
                new IfcProductDefinitionShape(null, null, shapeRepresentation);
        IfcWall wall = IfcWall.Builder.anIfcWall()
                .globalId(new IfcGloballyUniqueId("2KcxKeVfqHwhb6N5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Wall"))
                .description(new IfcText("")).objectPlacement(wallPlacement)
                .representation(productDefinitionShape).build();

        IfcSite site = IfcSite.Builder.anIfcSite()
                .globalId(new IfcGloballyUniqueId("2KdG88VfqHwfDCN5zdz5Bw"))
                .ownerHistory(ownerHistory).name(new IfcLabel("Default Site"))
                .description(new IfcText(""))
                .compositionType(IfcElementCompositionEnum.ELEMENT).build();
        IfcRelAggregates projectLink =
                IfcRelAggregates.Builder.anIfcRelAggregates().globalId(
                        new IfcGloballyUniqueId("2KdG89VfqHweGDN5zdz5Bw"))
                        .ownerHistory(ownerHistory)
                        .name(new IfcLabel("ProjectLink"))
                        .description(new IfcText("")).relatingObject(ifcProject)
                        .relatedObjects(site).build();
        IfcBuilding building = IfcBuilding.Builder.anIfcBuilding()
                .globalId(new IfcGloballyUniqueId("2KdHMSVfqHwfiJN5zdz5Bw"))
                .ownerHistory(ownerHistory)
                .name(new IfcLabel("Default Building"))
                .description(new IfcText(""))
                .compositionType(IfcElementCompositionEnum.ELEMENT).build();
        IfcRelAggregates siteLink =
                IfcRelAggregates.Builder.anIfcRelAggregates().globalId(
                        new IfcGloballyUniqueId("2KdHMTVfqHwePlN5zdz5Bw"))
                        .ownerHistory(ownerHistory)
                        .name(new IfcLabel("SiteLink"))
                        .description(new IfcText("")).relatingObject(site)
                        .relatedObjects(building).build();
        IfcBuildingStorey buildingStorey =
                IfcBuildingStorey.Builder.anIfcBuildingStorey().globalId(
                        new IfcGloballyUniqueId("2KdHMUVfqHwg4XN5zdz5Bw"))
                        .ownerHistory(ownerHistory)
                        .name(new IfcLabel("Default Storey"))
                        .description(new IfcText(""))
                        .compositionType(IfcElementCompositionEnum.ELEMENT)
                        .build();
        IfcRelAggregates defaultStoreyLink =
                IfcRelAggregates.Builder.anIfcRelAggregates().globalId(
                        new IfcGloballyUniqueId("2KdHMVVfqHwhFMN5zdz5Bw"))
                        .ownerHistory(ownerHistory)
                        .name(new IfcLabel("DefaultStoreyLink"))
                        .description(new IfcText("")).relatingObject(building)
                        .relatedObjects(buildingStorey).build();
        IfcRelContainedInSpatialStructure unassignedObjectsLink =
                IfcRelContainedInSpatialStructure.Builder
                        .anIfcRelContainedInSpatialStructure().globalId(
                        new IfcGloballyUniqueId("2KdIamVfqHwf$aN5zdz5Bw"))
                        .ownerHistory(ownerHistory)
                        .name(new IfcLabel("UnassignedObjectsLink"))
                        .description(new IfcText(""))
                        .relatingStructure(buildingStorey).relatedElements(wall)
                        .build();
        validIfcProject = ifcProject;
    }

    @Before
    public void setUp() throws IllegalAccessException, InstantiationException {
        testedSerializer = (Serializer) serializerClass.newInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void serialize_nullHeader()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(null, validIfcProject, FILE_PATH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void serialize_nullProject()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(new Header(), null, FILE_PATH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void serialize_nullPath()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(new Header(), validIfcProject, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void serialize_emptyPath()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(new Header(), validIfcProject, "");
    }

    @Test(expected = FileNotFoundException.class)
    public void serialize_rootPath()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(new Header(), validIfcProject, "/");
    }

    @Test(expected = FileNotFoundException.class)
    public void serialize_pointPath()
            throws IOException, ExecutionException, InterruptedException {
        testedSerializer.serialize(new Header(), validIfcProject, ".");
    }

    @Test
    public void serialize_correctHeader_correctProject_correctPath()
            throws IOException, ExecutionException, InterruptedException {
        String expectedDataSection =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD'," + "'118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);" + "\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1" + ".0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE'," + "#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$," + "$,$," + "$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site'," + "'',$," + "$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " " + "Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#23=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default " + "Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#24=IFCLOCALPLACEMENT($,#9);\n" +
                        "#25=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#26=IFCDIRECTION((1.0,0.0));\n" +
                        "#27=IFCAXIS2PLACEMENT2D(#25,#26);\n" +
                        "#28=IFCCIRCLEPROFILEDEF(.AREA.,$,#27,0.1);\n" +
                        "#29=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT3D(#29,#7,#8);\n" +
                        "#31=IFCEXTRUDEDAREASOLID(#28,#30,#7,0.1);\n" +
                        "#32=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#33=IFCSURFACESTYLERENDERING(#32,$,$,$,$,$,$,$,.FLAT" +
                        ".);" + "\n" +
                        "#34=IFCSURFACESTYLE($,.BOTH.,(#33));\n" +
                        "#35=IFCPRESENTATIONSTYLEASSIGNMENT((#34));\n" +
                        "#36=IFCSTYLEDITEM(#31,(#35),$);\n" +
                        "#37=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#31)" + ");\n" +
                        "#38=IFCPRODUCTDEFINITIONSHAPE($,$,(#37));\n" +
                        "#39=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#24," + "#38," + "$);\n" +
                        "#40=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink',''," + "(#39),#23);\n" +
                        "#41=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#22,(#23));\n" +
                        "#42=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink'," + "'',#21,(#22));\n" +
                        "#43=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" + "ENDSEC;\n";
        String expectedDataSectionMultiThreading0 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD','118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1.0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE',#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$,$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$,$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink','',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36,$);\n" +
                        "#38=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink','',(#37),#25);\n" +
                        "#39=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#40=IFCSURFACESTYLERENDERING(#39,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" + "#41=IFCSURFACESTYLE($,.BOTH.,(#40));\n" +
                        "#42=IFCPRESENTATIONSTYLEASSIGNMENT((#41));\n" +
                        "#43=IFCSTYLEDITEM(#34,(#42),$);\n" + "ENDSEC;\n";
        String expetedDataSectionMultiThreading1 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD'," + "'118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1" + ".0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE'," + "#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$," + "$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$," + "$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " " + "Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink'," + "'',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default " + "Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#38=IFCSURFACESTYLERENDERING(#37,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" +
                        "#39=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36," + "$);\n" +
                        "#40=IFCSURFACESTYLE($,.BOTH.,(#38));\n" +
                        "#41=IFCPRESENTATIONSTYLEASSIGNMENT((#40));\n" +
                        "#42=IFCSTYLEDITEM(#34,(#41),$);\n" +
                        "#43=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink',''," + "(#39),#25);\n" +
                        "ENDSEC;\n";
        String expectedDataSectionMultiThreading2 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD','118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1.0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE',#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$,$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$,$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink','',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#38=IFCSURFACESTYLERENDERING(#37,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" +
                        "#39=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36,$);\n" +
                        "#40=IFCSURFACESTYLE($,.BOTH.,(#38));\n" +
                        "#41=IFCPRESENTATIONSTYLEASSIGNMENT((#40));\n" +
                        "#42=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink','',(#39),#25);\n" +
                        "#43=IFCSTYLEDITEM(#34,(#41),$);\n" + "ENDSEC;\n";
        String expectedDataSectionMultiThreading3 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD','118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1.0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE',#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$,$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$,$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink','',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36,$);\n" + "#38=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#39=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink','',(#37),#25);\n" +
                        "#40=IFCSURFACESTYLERENDERING(#38,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" + "#41=IFCSURFACESTYLE($,.BOTH.,(#40));\n" +
                        "#42=IFCPRESENTATIONSTYLEASSIGNMENT((#41));\n" +
                        "#43=IFCSTYLEDITEM(#34,(#42),$);\n" + "ENDSEC;\n";
        String expectedDataSectionMultiThreading4 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD'," + "'118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1" + ".0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE'," + "#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$," + "$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$," + "$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " " + "Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink'," + "'',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default " + "Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36," + "$);\n" +
                        "#38=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#39=IFCSURFACESTYLERENDERING(#38,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" + "#40=IFCSURFACESTYLE($,.BOTH.,(#39));\n" +
                        "#41=IFCPRESENTATIONSTYLEASSIGNMENT((#40));\n" +
                        "#42=IFCSTYLEDITEM(#34,(#41),$);\n" +
                        "#43=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink',''," + "(#37),#25);\n" +
                        "ENDSEC;\n";
        String expectedDataSectionMultiThreading5 =
                "DATA;\n" + "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                        "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                        "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                        "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)'," +
                        "'FreeCAD'," + "'118df2cf_ed21_438e_a41');\n" +
                        "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4," +
                        "1586902585);\n" +
                        "#6=IFCCARTESIANPOINT((0.0,0.0,0.0));\n" +
                        "#7=IFCDIRECTION((0.0,0.0,1.0));\n" +
                        "#8=IFCDIRECTION((1.0,0.0,0.0));\n" +
                        "#9=IFCAXIS2PLACEMENT3D(#6,#7,#8);\n" +
                        "#10=IFCDIRECTION((0.0,1.0,0.0));\n" +
                        "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan'," +
                        "'Model',3,1" + ".0E-5,#9,#10);\n" +
                        "#12=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                        "#13=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                        "#14=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                        "#15=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                        "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                        "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                        ".017453292519943295),#16);\n" +
                        "#18=IFCCONVERSIONBASEDUNIT(#15,.PLANEANGLEUNIT.," +
                        "'DEGREE'," + "#17);\n" +
                        "#19=IFCUNITASSIGNMENT((#12,#13,#14,#18));\n" +
                        "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5," +
                        "'Unnamed',$,$,$," + "$,(#11),#19);\n" +
                        "#21=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default " +
                        "Site','',$," + "$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                        "#22=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                        "'ProjectLink','',#20,(#21));\n" +
                        "#23=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default" +
                        " " + "Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                        "#24=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5," +
                        "'SiteLink'," + "'',#21,(#23));\n" +
                        "#25=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5," +
                        "'Default " + "Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                        "#26=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                        "'DefaultStoreyLink','',#23,(#25));\n" +
                        "#27=IFCLOCALPLACEMENT($,#9);\n" +
                        "#28=IFCCARTESIANPOINT((0.0,0.0));\n" +
                        "#29=IFCDIRECTION((1.0,0.0));\n" +
                        "#30=IFCAXIS2PLACEMENT2D(#28,#29);\n" +
                        "#31=IFCCIRCLEPROFILEDEF(.AREA.,$,#30,0.1);\n" +
                        "#32=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                        ".73641172593403E-18,0.0));\n" +
                        "#33=IFCAXIS2PLACEMENT3D(#32,#7,#8);\n" +
                        "#34=IFCEXTRUDEDAREASOLID(#31,#33,#7,0.1);\n" +
                        "#35=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid'," +
                        "(#34));\n" +
                        "#36=IFCPRODUCTDEFINITIONSHAPE($,$,(#35));\n" +
                        "#37=IFCCOLOURRGB($,1.0,1.0,1.0);\n" +
                        "#38=IFCSURFACESTYLERENDERING(#37,$,$,$,$,$,$,$,.FLAT" +
                        ".);\n" + "#39=IFCSURFACESTYLE($,.BOTH.,(#38));\n" +
                        "#40=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$," +
                        "#27,#36," + "$);\n" +
                        "#41=IFCPRESENTATIONSTYLEASSIGNMENT((#39));\n" +
                        "#42=IFCSTYLEDITEM(#34,(#41),$);\n" +
                        "#43=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                        "('2KdIamVfqHwf$aN5zdz5Bw',#5," +
                        "'UnassignedObjectsLink',''," + "(#40),#25);\n" +
                        "ENDSEC;\n";

        testedSerializer.serialize(new Header(), validIfcProject, FILE_PATH);

        String writtenDataSection = getDataSection(FILE_PATH);

        //TODO: write a parser and compare the models in memory instead of
        // having this mess that doesn't always work, or remove multithreading
        Assert.assertTrue(expectedDataSection.equals(writtenDataSection) ||
                expectedDataSectionMultiThreading0.equals(writtenDataSection) ||
                expetedDataSectionMultiThreading1.equals(writtenDataSection) ||
                expectedDataSectionMultiThreading2.equals(writtenDataSection) ||
                expectedDataSectionMultiThreading3.equals(writtenDataSection) ||
                expectedDataSectionMultiThreading4.equals(writtenDataSection) ||
                expectedDataSectionMultiThreading5.equals(writtenDataSection));
    }
}