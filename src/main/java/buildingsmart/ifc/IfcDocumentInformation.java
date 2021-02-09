/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

public class IfcDocumentInformation {
    private String DocumentId;
    private String Name;
    private String Description;
    private IfcDocumentReference[] DocumentReferences;
    private String Purpose;
    private String IntendedUse;
    private String Scope;
    private String Revision;
    private IfcActorSelect DocumentOwner;
    private IfcActorSelect[] Editors;
    private IfcDateAndTime CreationTime;
    private IfcDateAndTime LastRevisionTime;
    private IfcDocumentElectronicFormat ElectronicFormat;
    private IfcCalendarDate ValidFrom;
    private IfcCalendarDate ValidUntil;
    private IfcDocumentConfidentialityEnum Confidentiality;
    private IfcDocumentStatusEnum Status;
    private IfcDocumentInformationRelationship[] IsPointedTo;
    private IfcDocumentInformationRelationship[] IsPointer;
}
