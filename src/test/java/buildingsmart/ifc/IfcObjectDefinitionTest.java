/*
 * Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.ifc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class IfcObjectDefinitionTest {

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
    public void addToIsDecomposedBy_null() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        //noinspection ConstantConditions
        objDef.addToIsDecomposedBy(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToIsDecomposedBy_notTheRelatingObject() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);

        ConcreteIfcObjectDefinition relatingObject =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelDecomposes rel =
                new IfcRelDecomposes(ownerHistory, null, null, relatingObject,
                        relatedObject1, relatedObject2);

        objDef.addToIsDecomposedBy(rel);
    }

    @Test
    public void addToIsDecomposedBy_successful() {
        ConcreteIfcObjectDefinition relatingObject =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelDecomposes rel =
                new IfcRelDecomposes(ownerHistory, null, null, relatingObject,
                        relatedObject1, relatedObject2);

        relatingObject.addToIsDecomposedBy(rel);
        Set<IfcRelDecomposes> expectedIsDecomposedBy = new HashSet<>(2, 1);
        expectedIsDecomposedBy.add(rel);

        Assert.assertEquals(expectedIsDecomposedBy,
                relatingObject.getIsDecomposedBy());
    }

    @Test(expected = NullPointerException.class)
    public void setDecomposes_null() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        //noinspection ConstantConditions
        objDef.setDecomposes(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDecomposes_notInRelatedObjects() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);

        ConcreteIfcObjectDefinition relatingObject =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelDecomposes rel =
                new IfcRelDecomposes(ownerHistory, null, null, relatingObject,
                        relatedObject1, relatedObject2);

        objDef.setDecomposes(rel);
    }

    @Test
    public void setDecomposed_successful() {
        ConcreteIfcObjectDefinition relatingObject =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelDecomposes rel =
                new IfcRelDecomposes(ownerHistory, null, null, relatingObject,
                        relatedObject1, relatedObject2);

        relatedObject1.setDecomposes(rel);

        Assert.assertEquals(rel, relatedObject1.getDecomposes());
    }

    @Test(expected = NullPointerException.class)
    public void addToHasAssociations_null() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        //noinspection ConstantConditions
        objDef.addToHasAssociations(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToHasAssociations_notInRelatedObjects() {
        ConcreteIfcObjectDefinition objDef =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);

        ConcreteIfcObjectDefinition relatedObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject3 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelAssociates rel =
                new IfcRelAssociates(ownerHistory, null, null, relatedObject1,
                        relatedObject2, relatedObject3);

        objDef.addToHasAssociations(rel);
    }

    @Test
    public void addToHasAssociations_successful() {
        ConcreteIfcObjectDefinition relatingObject1 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject2 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        ConcreteIfcObjectDefinition relatedObject3 =
                new ConcreteIfcObjectDefinition(ownerHistory, null, null);
        IfcRelAssociates rel =
                new IfcRelAssociates(ownerHistory, null, null, relatingObject1,
                        relatedObject2, relatedObject3);

        relatingObject1.addToHasAssociations(rel);
        Set<IfcRelAssociates> expectedHasAssociations = new HashSet<>(2, 1);
        expectedHasAssociations.add(rel);

        Assert.assertEquals(expectedHasAssociations,
                relatingObject1.getHasAssociations());
    }
}