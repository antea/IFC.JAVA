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

package buildingsmart.ifc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class IfcSpatialStructureElementTest {

    private static IfcOwnerHistory ownerHistory;

    @BeforeClass
    public static void setUp() {
        IfcApplication.clearUniqueConstraint();
        IfcPerson person =
                IfcPerson.builder().givenName(new IfcLabel("")).build();
        IfcOrganization organization =
                IfcOrganization.builder().name(new IfcLabel("")).build();
        IfcPersonAndOrganization personAndOrganization =
                new IfcPersonAndOrganization(person, organization, null);
        IfcApplication application =
                new IfcApplication(organization, new IfcLabel(""),
                        new IfcLabel(""), new IfcIdentifier(""));
        ownerHistory =
                new IfcOwnerHistory(personAndOrganization, application, null,
                        IfcChangeActionEnum.ADDED, null, personAndOrganization,
                        application, new IfcTimeStamp());
    }

    @Test(expected = NullPointerException.class)
    public void addToContainsElements_null() {
        ConcreteIfcSpatialStructureElement spatialStructureElement =
                new ConcreteIfcSpatialStructureElement(ownerHistory, null, null,
                        null, null, null, null,
                        IfcElementCompositionEnum.ELEMENT);
        //noinspection ConstantConditions
        spatialStructureElement.addToContainsElements(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToContainsElements_notTheRelatingStructure() {
        ConcreteIfcSpatialStructureElement spatialStructureElement =
                new ConcreteIfcSpatialStructureElement(ownerHistory, null, null,
                        null, null, null, null,
                        IfcElementCompositionEnum.ELEMENT);

        ConcreteIfcSpatialStructureElement relatingStructure =
                new ConcreteIfcSpatialStructureElement(ownerHistory, null, null,
                        null, null, null, null,
                        IfcElementCompositionEnum.ELEMENT);
        IfcRelContainedInSpatialStructure rel =
                new IfcRelContainedInSpatialStructure(ownerHistory, null, null,
                        relatingStructure, Mockito.mock(IfcProduct.class),
                        Mockito.mock(IfcProduct.class));

        spatialStructureElement.addToContainsElements(rel);
    }

    @Test
    public void addToContainsElements_successful() {
        ConcreteIfcSpatialStructureElement spatialStructureElement =
                new ConcreteIfcSpatialStructureElement(ownerHistory, null, null,
                        null, null, null, null,
                        IfcElementCompositionEnum.ELEMENT);
        IfcRelContainedInSpatialStructure rel =
                new IfcRelContainedInSpatialStructure(ownerHistory, null, null,
                        spatialStructureElement, Mockito.mock(IfcProduct.class),
                        Mockito.mock(IfcProduct.class));

        spatialStructureElement.addToContainsElements(rel);
        Set<IfcRelContainedInSpatialStructure> expectedContainsElements =
                new HashSet<>(2, 1);
        expectedContainsElements.add(rel);

        Assert.assertEquals(expectedContainsElements,
                spatialStructureElement.getContainsElements());
    }
}