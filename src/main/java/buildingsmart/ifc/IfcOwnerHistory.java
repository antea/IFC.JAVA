/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

import buildingsmart.io.Attribute;
import buildingsmart.io.IfcEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * IfcOwnerHistory defines all history and identification related information .
 * In order to provide fast access it is directly attached to all independent
 * objects, relationships and properties.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcOwnerHistory extends IfcEntity {
    @Attribute(0)
    private final IfcPersonAndOrganization owningUser;
    @Attribute(1)
    private final IfcApplication owningApplication;
    @Attribute(2)
    private final IfcStateEnum state;
    @Attribute(3)
    private final IfcChangeActionEnum changeAction;
    @Attribute(4)
    private final IfcTimeStamp lastModifiedDate;
    @Attribute(5)
    private final IfcPersonAndOrganization lastModifyingUser;
    @Attribute(6)
    private final IfcApplication lastModifyingApplication;
    @Attribute(7)
    private final IfcTimeStamp creationDate;

    /**
     * @param owningUser               Direct reference to the end user who
     *                                 currently "owns" this object. Note that
     *                                 IFC includes the concept of ownership
     *                                 transfer from one user to another and
     *                                 therefore distinguishes between the
     *                                 Owning User and Creating User.
     * @param owningApplication        Direct reference to the application which
     *                                 currently "Owns" this object on behalf of
     *                                 the owning user, who uses this
     *                                 application. Note that IFC includes the
     *                                 concept of ownership transfer from one
     *                                 app to another and therefore
     *                                 distinguishes between the Owning
     *                                 Application and Creating Application.
     * @param state                    Enumeration that defines the current
     *                                 access state of the object.
     * @param changeAction             Enumeration that defines the actions
     *                                 associated with changes made to the
     *                                 object.
     * @param lastModifiedDate         Date and Time at which the last
     *                                 modification occurred.
     * @param lastModifyingUser        User who carried out the last
     *                                 modification.
     * @param lastModifyingApplication Application used to carry out the last
     *                                 modification.
     * @param creationDate             Time and date of creation.
     * @throws NullPointerException If owningUser, owningApplication,
     *                              changeAction or creationDate are null.
     */
    @Builder
    public IfcOwnerHistory(@NonNull IfcPersonAndOrganization owningUser,
                           @NonNull IfcApplication owningApplication,
                           IfcStateEnum state,
                           @NonNull IfcChangeActionEnum changeAction,
                           IfcTimeStamp lastModifiedDate,
                           IfcPersonAndOrganization lastModifyingUser,
                           IfcApplication lastModifyingApplication,
                           @NonNull IfcTimeStamp creationDate) {
        this.owningUser = owningUser;
        this.owningApplication = owningApplication;
        this.state = state;
        this.changeAction = changeAction;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifyingUser = lastModifyingUser;
        this.lastModifyingApplication = lastModifyingApplication;
        this.creationDate = creationDate;
    }
}
