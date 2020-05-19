package buildingsmart.ifc;

public class IfcScheduleTimeControl extends IfcControl {
    private final IfcDateTimeSelect ActualStart;
    private IfcDateTimeSelect EarlyStart;
    private IfcDateTimeSelect LateStart;
    private IfcDateTimeSelect ScheduleStart;
    private IfcDateTimeSelect ActualFinish;
    private IfcDateTimeSelect EarlyFinish;
    private IfcDateTimeSelect LateFinish;
    private IfcDateTimeSelect ScheduleFinish;
    private double ScheduleDuration;
    private double ActualDuration;
    private double RemainingTime;
    private double FreeFloat;
    private double TotalFloat;
    private boolean IsCritical;
    private IfcDateTimeSelect StatusTime;
    private double StartFloat;
    private double FinishFloat;
    private IfcRatioMeasure Completion;
    private IfcRelAssignsTasks ScheduleTimeControlAssigned;

    /**
     * Creates a new IfcObjectDefinition, using the provided globalId.
     *
     * @param globalId     Assignment of a globally unique identifier within the
     *                     entire software world.
     * @param ownerHistory Assignment of the information about the current
     *                     ownership of that object, including owning actor,
     *                     application, local identification and information
     *                     captured about the recent changes of the object,
     *                     NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software
     *                     systems or users. For some subtypes of IfcRoot the
     *                     insertion of the Name attribute may be required. This
     *                     would be enforced by a where rule.
     * @param description  Optional description, provided for exchanging
     *                     informative comments.
     * @param objectType   The type denotes a particular type that indicates the
     *                     object further. The use has to be established at the
     *                     level of instantiable subtypes. In particular it
     *                     holds the user defined type, if the enumeration of
     *                     the attribute PredefinedType is set to USERDEFINED.
     * @throws IllegalArgumentException If globalId or ownerHistory are null, or
     *                                  if globalId was used in another instance
     *                                  of this class.
     */
    public IfcScheduleTimeControl(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcLabel objectType,
                                  IfcDateTimeSelect actualStart) {
        super(globalId, ownerHistory, name, description, objectType);
        ActualStart = actualStart;
    }
}
