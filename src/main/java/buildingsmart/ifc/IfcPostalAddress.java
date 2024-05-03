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

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * The address for delivery of paper based mail.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcPostalAddress extends IfcAddress {
    private final IfcLabel internalLocation;
    private final List<IfcLabel> addressLines;
    private final IfcLabel postalBox;
    private final IfcLabel town;
    private final IfcLabel region;
    private final IfcLabel postalCode;
    private final IfcLabel country;

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
     * @param internalLocation   An organization defined address for internal
     *                           mail delivery.
     * @param addressLines       The postal address.<br/>
     *                           <BLOCKQUOTE><FONT SIZE="-1">NOTE: A postal
     *                           address may occupy several lines (or elements)
     *                           when recorded. It is expected that normal usage
     *                           will incorporate relevant elements of the
     *                           following address concepts: A location within a
     *                           building (e.g. 3rd Floor) Building name (e.g.
     *                           Interoperability House) Street number (e.g.
     *                           6400) Street name (e.g. Alliance Boulevard).
     *                           Typical content of address lines may vary in
     *                           different countries.
     *                           </FONT></BLOCKQUOTE>
     * @param postalBox          An address that is implied by an identifiable
     *                           mail drop.
     * @param town               The name of a town.
     * @param region             The name of a region.<br/>
     *                           <BLOCKQUOTE><FONT SIZE="-1">NOTE: The counties
     *                           of the United Kingdom and the states of North
     *                           America are examples of regions.
     *                           </FONT></BLOCKQUOTE>
     * @param postalCode         The code that is used by the country's postal
     *                           service.
     * @param country            The name of a country.
     * @throws IllegalArgumentException If there's an invalid combination of
     *                                  purpose and userDefinedPurpose; if
     *                                  internalLocation, addressLines, town,
     *                                  region and country are all null; if
     *                                  addressLines has size lower than 1.
     */
    public IfcPostalAddress(IfcAddressTypeEnum purpose,
                            IfcText description,
                            IfcLabel userDefinedPurpose,
                            IfcLabel internalLocation,
                            List<IfcLabel> addressLines,
                            IfcLabel postalBox,
                            IfcLabel town,
                            IfcLabel region,
                            IfcLabel postalCode,
                            IfcLabel country) {
        super(purpose, description, userDefinedPurpose);
        if (internalLocation == null && addressLines == null &&
                postalBox == null && town == null && region == null &&
                postalCode == null && country == null) {
            throw new IllegalArgumentException(
                    "at least one of internalLocation, addressLines, town, " +
                            "region and country must not be null");
        }
        if (addressLines != null && addressLines.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of addressLines must be at least 1");
        }
        this.internalLocation = internalLocation;
        this.addressLines = addressLines;
        this.postalBox = postalBox;
        this.town = town;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }
}
