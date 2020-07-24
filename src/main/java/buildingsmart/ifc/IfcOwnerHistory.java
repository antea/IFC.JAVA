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
import lombok.NonNull;

import java.util.Objects;

/**
 * IfcOwnerHistory defines all history and identification related information .
 * In order to provide fast access it is directly attached to all independent
 * objects, relationships and properties.
 */
public class IfcOwnerHistory extends IfcEntity {
    @Attribute(order = 0)
    private final IfcPersonAndOrganization owningUser;
    @Attribute(order = 1)
    private final IfcApplication owningApplication;
    @Attribute(order = 2)
    private final IfcStateEnum state;
    @Attribute(order = 3)
    private final IfcChangeActionEnum changeAction;
    @Attribute(order = 4)
    private final IfcTimeStamp lastModifiedDate;
    @Attribute(order = 5)
    private final IfcPersonAndOrganization lastModifyingUser;
    @Attribute(order = 6)
    private final IfcApplication lastModifyingApplication;
    @Attribute(order = 7)
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
     * @throws IllegalArgumentException If owningUser, owningApplication,
     *                                  changeAction or creationDate are null.
     */
    public IfcOwnerHistory(@NonNull IfcPersonAndOrganization owningUser,
                           @NonNull IfcApplication owningApplication,
                           IfcStateEnum state,
                           @NonNull IfcChangeActionEnum changeAction,
                           IfcTimeStamp lastModifiedDate,
                           IfcPersonAndOrganization lastModifyingUser,
                           IfcApplication lastModifyingApplication,
                           @NonNull IfcTimeStamp creationDate) {
        if (owningUser == null) {
            throw new IllegalArgumentException("owningUser cannot be null");
        }
        if (owningApplication == null) {
            throw new IllegalArgumentException(
                    "owningApplication cannot be null");
        }
        if (changeAction == null) {
            throw new IllegalArgumentException("changeAction cannot be null");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("creationDate cannot be null");
        }
        this.owningUser = owningUser;
        this.owningApplication = owningApplication;
        this.state = state;
        this.changeAction = changeAction;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifyingUser = lastModifyingUser;
        this.lastModifyingApplication = lastModifyingApplication;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcOwnerHistory that = (IfcOwnerHistory) o;
        return owningUser.equals(that.owningUser) &&
                owningApplication.equals(that.owningApplication) &&
                state == that.state && changeAction == that.changeAction &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
                Objects.equals(lastModifyingUser, that.lastModifyingUser) &&
                Objects.equals(lastModifyingApplication,
                        that.lastModifyingApplication) &&
                creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owningUser, owningApplication, state, changeAction,
                lastModifiedDate, lastModifyingUser, lastModifyingApplication,
                creationDate);
    }

    public static final class Builder {
        private IfcPersonAndOrganization owningUser;
        private IfcApplication owningApplication;
        private IfcStateEnum state;
        private IfcChangeActionEnum changeAction;
        private IfcTimeStamp lastModifiedDate;
        private IfcPersonAndOrganization lastModifyingUser;
        private IfcApplication lastModifyingApplication;
        private IfcTimeStamp creationDate;

        private Builder() {
        }

        public static Builder anIfcOwnerHistory() {
            return new Builder();
        }

        public Builder owningUser(IfcPersonAndOrganization owningUser) {
            this.owningUser = owningUser;
            return this;
        }

        public Builder owningApplication(IfcApplication owningApplication) {
            this.owningApplication = owningApplication;
            return this;
        }

        public Builder state(IfcStateEnum state) {
            this.state = state;
            return this;
        }

        public Builder changeAction(IfcChangeActionEnum changeAction) {
            this.changeAction = changeAction;
            return this;
        }

        public Builder lastModifiedDate(IfcTimeStamp lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Builder lastModifyingUser(
                IfcPersonAndOrganization lastModifyingUser) {
            this.lastModifyingUser = lastModifyingUser;
            return this;
        }

        public Builder lastModifyingApplication(
                IfcApplication lastModifyingApplication) {
            this.lastModifyingApplication = lastModifyingApplication;
            return this;
        }

        public Builder creationDate(IfcTimeStamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public IfcOwnerHistory build() {
            return new IfcOwnerHistory(owningUser, owningApplication, state,
                    changeAction, lastModifiedDate, lastModifyingUser,
                    lastModifyingApplication, creationDate);
        }
    }
}
