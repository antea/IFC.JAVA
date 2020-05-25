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
import org.junit.Test;

import java.util.Collections;

public class SerializerTest {

    @Test
    public void serialize() {
        String expectedDataSection = "#1=IFCPERSON($,$,'',$,$,$,$,$);\n" +
                "#2=IFCORGANIZATION($,'',$,$,$);\n" +
                "#3=IFCPERSONANDORGANIZATION(#1,#2,$);\n" +
                "#4=IFCAPPLICATION(#2,'0.18 build 4 (GitTag)','FreeCAD'," +
                "'118df2cf_ed21_438e_a41');\n" +
                "#5=IFCOWNERHISTORY(#3,#4,$,.ADDED.,$,#3,#4,1586902585);\n" +
                "#6=IFCDIRECTION((1.,0.,0.));\n" +
                "#7=IFCDIRECTION((0.,0.,1.));\n" +
                "#8=IFCCARTESIANPOINT((0.,0.,0.));\n" +
                "#9=IFCAXIS2PLACEMENT3D(#8,#7,#6);\n" +
                "#10=IFCDIRECTION((0.,1.,0.));\n" +
                "#11=IFCGEOMETRICREPRESENTATIONCONTEXT('Plan','Model',3,1" +
                ".E-05,#9,#10);\n" +
                "#12=IFCDIMENSIONALEXPONENTS(0,0,0,0,0,0,0);\n" +
                "#13=IFCSIUNIT(*,.LENGTHUNIT.,$,.METRE.);\n" +
                "#14=IFCSIUNIT(*,.AREAUNIT.,$,.SQUARE_METRE.);\n" +
                "#15=IFCSIUNIT(*,.VOLUMEUNIT.,$,.CUBIC_METRE.);\n" +
                "#16=IFCSIUNIT(*,.PLANEANGLEUNIT.,$,.RADIAN.);\n" +
                "#17=IFCMEASUREWITHUNIT(IFCPLANEANGLEMEASURE(0" +
                ".017453292519943295),#16);\n" +
                "#18=IFCCONVERSIONBASEDUNIT(#12,.PLANEANGLEUNIT.,'DEGREE'," +
                "#17);\n" + "#19=IFCUNITASSIGNMENT((#13,#14,#15,#18));\n" +
                "#20=IFCPROJECT('51f413ef_7964_4d38_b19',#5,'Unnamed',$,$,$," +
                "$,(#11),#19);\n" + "#21=IFCDIRECTION((1.,0.));\n" +
                "#22=IFCCARTESIANPOINT((0.,0.));\n" +
                "#23=IFCAXIS2PLACEMENT2D(#22,#21);\n" +
                "#24=IFCCIRCLEPROFILEDEF(.AREA.,$,#23,0.1);\n" +
                "#25=IFCCARTESIANPOINT((-1.4210854715202E-17,-2" +
                ".73641172593403E-18,0.));\n" +
                "#26=IFCAXIS2PLACEMENT3D(#25,#7,#6);\n" +
                "#27=IFCEXTRUDEDAREASOLID(#24,#26,#7,0.1);\n" +
                "#28=IFCCOLOURRGB($,1.,1.,1.);\n" +
                "#29=IFCSURFACESTYLERENDERING(#28,$,$,$,$,$,$,$,.FLAT.);\n" +
                "#30=IFCSURFACESTYLE($,.BOTH.,(#29));\n" +
                "#31=IFCPRESENTATIONSTYLEASSIGNMENT((#30));\n" +
                "#32=IFCSTYLEDITEM(#27,(#31),$);\n" +
                "#33=IFCLOCALPLACEMENT($,#9);\n" +
                "#34=IFCSHAPEREPRESENTATION(#11,'Body','SweptSolid',(#27));\n" +
                "#35=IFCPRODUCTDEFINITIONSHAPE($,$,(#34));\n" +
                "#36=IFCWALL('2KcxKeVfqHwhb6N5zdz5Bw',#5,'Wall','',$,#33,#35," +
                "$);\n" +
                "#37=IFCSITE('2KdG88VfqHwfDCN5zdz5Bw',#5,'Default Site','',$," +
                "$,$,$,.ELEMENT.,$,$,$,$,$);\n" +
                "#38=IFCRELAGGREGATES('2KdG89VfqHweGDN5zdz5Bw',#5," +
                "'ProjectLink','',#20,(#37));\n" +
                "#39=IFCBUILDING('2KdHMSVfqHwfiJN5zdz5Bw',#5,'Default " +
                "Building','',$,$,$,$,.ELEMENT.,$,$,$);\n" +
                "#40=IFCRELAGGREGATES('2KdHMTVfqHwePlN5zdz5Bw',#5,'SiteLink'," +
                "'',#37,(#39));\n" +
                "#41=IFCBUILDINGSTOREY('2KdHMUVfqHwg4XN5zdz5Bw',#5,'Default " +
                "Storey','',$,$,$,$,.ELEMENT.,$);\n" +
                "#42=IFCRELAGGREGATES('2KdHMVVfqHwhFMN5zdz5Bw',#5," +
                "'DefaultStoreyLink','',#39,(#41));\n" +
                "#43=IFCRELCONTAINEDINSPATIALSTRUCTURE" +
                "('2KdIamVfqHwf$aN5zdz5Bw',#5,'UnassignedObjectsLink',''," +
                "(#36),#41);\n";

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

        Serializer serializer = new Serializer();
        String result = serializer.serialize(ifcProject);
        Assert.assertEquals(expectedDataSection, result);

    }
}