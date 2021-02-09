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

public class IfcTelecomAddress extends IfcAddress {
    private String[] TelephoneNumbers;
    private String[] FacsimileNumbers;
    private String PagerNumber;
    private String[] ElectronicMailAddresses;
    private String WWWHomePageURL;

    /**
     * @param purpose            Identifies the logical location of the address.
     * @param description        Text that relates the nature of the address.
     * @param userDefinedPurpose Allows for specification of user specific
     *                           purpose of the address beyond the
     *                           enumeration values provided by Purpose
     *                           attribute of type IfcAddressTypeEnum. When a
     *                           value is provided for attribute
     *                           UserDefinedPurpose, in parallel the
     *                           attribute Purpose shall have enumeration
     *                           value USERDEFINED.
     * @throws IllegalArgumentException if there's an invalid combination of
     *                                  purpose and userDefinedPurpose.
     */
    public IfcTelecomAddress(IfcAddressTypeEnum purpose, IfcText description,
                             IfcLabel userDefinedPurpose) {
        super(purpose, description, userDefinedPurpose);
    }
}
