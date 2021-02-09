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

import buildingsmart.io.Entity;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * An abstract entity type for various kinds of postal and telecom addresses.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcAddress extends Entity {
    private final IfcAddressTypeEnum purpose;
    private final IfcText description;
    private final IfcLabel userDefinedPurpose;

    /**
     * @param purpose            Identifies the logical location of the
     *                           address.
     * @param description        Text that relates the nature of the address.
     * @param userDefinedPurpose Allows for specification of user specific
     *                           purpose of the address beyond the enumeration
     *                           values provided by Purpose attribute of type
     *                           IfcAddressTypeEnum. When a value is provided
     *                           for attribute UserDefinedPurpose, in parallel
     *                           the attribute Purpose shall have enumeration
     *                           value USERDEFINED.
     * @throws IllegalArgumentException If there's an invalid combination of
     *                                  purpose and userDefinedPurpose.
     */
    public IfcAddress(IfcAddressTypeEnum purpose,
                      IfcText description,
                      IfcLabel userDefinedPurpose) {
        if (purpose == IfcAddressTypeEnum.USERDEFINED &&
                userDefinedPurpose == null) {
            throw new IllegalArgumentException(
                    "if purpose is set to " + "USERDEFINED, then " +
                            "userDefinedPurpose " + "cannot be null");
        }
        if (purpose != IfcAddressTypeEnum.USERDEFINED &&
                userDefinedPurpose != null) {
            throw new IllegalArgumentException(
                    "userDefinedPurpose should be " + "set only when purpose" +
                            " is set to " + "USERDEFINED");
        }
        this.purpose = purpose;
        this.description = description;
        this.userDefinedPurpose = userDefinedPurpose;
    }
}
