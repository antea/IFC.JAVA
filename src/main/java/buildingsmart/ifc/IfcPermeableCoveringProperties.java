/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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

import lombok.NonNull;

public class IfcPermeableCoveringProperties extends IfcPropertySetDefinition {
    private IfcPermeableCoveringOperationEnum OperationType;
    private IfcWindowPanelPositionEnum PanelPosition;
    private IfcLengthMeasure FrameDepth;
    private IfcLengthMeasure FrameThickness;
    private IfcShapeAspect ShapeAspectStyle;

    /**
     * @param globalId     Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory Assignment of the information about the current ownership of that object, including owning
     *                     actor, application, local identification and information captured about the recent changes of
     *                     the object, NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software systems or users. For some subtypes of
     *                     IfcRoot the insertion of the Name attribute may be required. This would be enforced by a
     *                     where rule.
     * @param description  Optional description, provided for exchanging informative comments.
     * @throws NullPointerException     If {@code globalId} or {@code ownerHistory} is null.
     * @throws IllegalArgumentException If {@code globalId} was used in another instance of this class.
     */
    public IfcPermeableCoveringProperties(@NonNull IfcGloballyUniqueId globalId,
                                          @NonNull IfcOwnerHistory ownerHistory,
                                          IfcLabel name,
                                          IfcText description) {
        super(globalId, ownerHistory, name, description);
    }
}
