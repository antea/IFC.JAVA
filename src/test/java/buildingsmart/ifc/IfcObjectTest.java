package buildingsmart.ifc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class IfcObjectTest {

    @Test
    public void addToIsDefinedBy_IfcRelDefinesByType() {
        IfcObject object = new ConcreteIfcObject();
        IfcRelDefines relDef = new IfcRelDefinesByType(new IfcGloballyUniqueId(),
                                                       ConcreteIfcObject.ownerHistory,
                                                       null,
                                                       null,
                                                       Collections.singleton(object));
        Assert.assertTrue(object.getIsDefinedBy().contains(relDef));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToIsDefinedBy_multipleIfcRelDefinesByType() {
        IfcObject object = new ConcreteIfcObject();
        new IfcRelDefinesByType(new IfcGloballyUniqueId(),
                                ConcreteIfcObject.ownerHistory,
                                null,
                                null,
                                Collections.singleton(object));
        new IfcRelDefinesByType(new IfcGloballyUniqueId(),
                                ConcreteIfcObject.ownerHistory,
                                null,
                                null,
                                Collections.singleton(object));
    }

    private static class ConcreteIfcObject extends IfcObject {
        private static final IfcOwnerHistory ownerHistory;

        static {
            IfcApplication.clearUniqueConstraint();
            IfcPerson person = IfcPerson.builder().givenName(new IfcLabel("")).build();
            IfcOrganization organization = IfcOrganization.builder().name(new IfcLabel("")).build();
            IfcPersonAndOrganization personAndOrganization = new IfcPersonAndOrganization(person, organization, null);
            IfcApplication application =
                    new IfcApplication(organization, new IfcLabel(""), new IfcLabel(""), new IfcIdentifier(""));
            ownerHistory = new IfcOwnerHistory(personAndOrganization,
                                               application,
                                               null,
                                               IfcChangeActionEnum.ADDED,
                                               null,
                                               personAndOrganization,
                                               application,
                                               new IfcTimeStamp());
        }

        public ConcreteIfcObject() {
            super(new IfcGloballyUniqueId(), ConcreteIfcObject.ownerHistory, null, null, null);
        }
    }

}