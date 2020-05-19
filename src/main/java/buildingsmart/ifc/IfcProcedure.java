package buildingsmart.ifc;

public class IfcProcedure extends IfcProcess {
    private final String ProcedureID;
    private IfcProcedureTypeEnum ProcedureType;
    private String UserDefinedProcedureType;

    public IfcProcedure(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, IfcLabel objectType,
                        IfcRelAssignsToProcess[] operatesOn,
                        String procedureID) {

        super(globalId, ownerHistory, name, description, objectType,
                operatesOn);
        ProcedureID = procedureID;
    }
}
