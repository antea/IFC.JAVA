/*
 * Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.io;

import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the HEADER section of an IFC STEP file.
 */
@EqualsAndHashCode
@ToString
public class Header {
    protected static final String IMPLEMENTATION_LEVEL = checkMaxLengthAndFormat("2;1");
    protected static final String PREPROCESSOR_VERSION = checkMaxLengthAndFormat("ifc-java 0.3.4");
    protected static final String FILE_SCHEMA = Functions.formatForStepFile("IFC2X3");
    private List<String> description;
    private String fileName;
    private String timeStamp;
    private List<String> author;
    private List<String> organization;
    private String originatingSystem;
    private String authorization;

    public Header() {}

    /**
     * @param str The String to check.
     * @return The String formatted for serialization in an IFC STEP file.
     *
     * @throws IllegalArgumentException If the formatted {@code str} is longer
     *                                  than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    private static String checkMaxLengthAndFormat(String str) {
        str = Functions.formatForStepFile(str);
        if (str.length() > 256) {
            throw new IllegalArgumentException(
                    "maximum length of the string is 256 characters");
        }
        return str;
    }

    /**
     * Formats all Strings in the list for serialization in a STEP file and
     * checks whether any String in the given List is longer than 256 characters
     * (after formatting).
     *
     * @param strings The list of Strings to check and format.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    private static void checkMaxLengthAndFormat(List<String> strings) {
        strings.replaceAll(Header::checkMaxLengthAndFormat);
    }

    /**
     * @param list The List of Strings to serialize.
     * @return The serialization of the given List of Strings in a STEP file.
     */
    private static String serializeList(List<String> list) {
        return list == null ? "('')" :
                list.stream().collect(Collectors.joining("','", "('", "')"));
    }

    /**
     * @param description Information about the exchanged content.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setDescription(List<String> description) {
        checkMaxLengthAndFormat(description);
        this.description = description;
        return this;
    }

    /**
     * @param description Information about the exchanged content.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setDescription(String... description) {
        return this.setDescription(Arrays.asList(description));
    }

    /**
     * @param fileName Local path and name of the file in which this Header will
     *                 be serialized.
     * @throws IllegalArgumentException If {@code fileName} is longer than 256
     *                                  characters after being formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setFileName(String fileName) {
        this.fileName = checkMaxLengthAndFormat(fileName);
        return this;
    }

    /**
     * @param timeStamp The date and time of the creation of the file, in the
     *                  ISO 8601 local time format.
     */
    public Header setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    /**
     * @param author Name and email address of the author of the file.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setAuthor(List<String> author) {
        checkMaxLengthAndFormat(author);
        this.author = author;
        return this;
    }

    /**
     * @param author Name and email address of the author of the file.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setAuthor(String... author) {
        return this.setAuthor(Arrays.asList(author));
    }

    /**
     * @param organization Name of the organization to which the author belongs
     *                     to.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setOrganization(List<String> organization) {
        checkMaxLengthAndFormat(organization);
        this.organization = organization;
        return this;
    }

    /**
     * @param organization Name of the organization to which the author belongs
     *                     to.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setOrganization(String... organization) {
        return this.setOrganization(Arrays.asList(organization));
    }

    /**
     * @param originatingSystem Name of the application using this library.
     * @throws IllegalArgumentException If {@code originatingSystem} is longer
     *                                  than 256 characters after being
     *                                  formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setOriginatingSystem(String originatingSystem) {
        this.originatingSystem = checkMaxLengthAndFormat(originatingSystem);
        return this;
    }

    /**
     * @param authorization Name and email address of the person who authorized
     *                      the creation of this file.
     * @throws IllegalArgumentException If {@code authorization} is longer than
     *                                  256 characters after being formatted.
     * @see Functions#formatForStepFile(String)
     */
    public Header setAuthorization(String authorization) {
        this.authorization = checkMaxLengthAndFormat(authorization);
        return this;
    }

    /**
     * @return The HEADER section of an IFC STEP file, containing the data
     * passed to this instance of Header.
     */
    public String serialize() {
        if (fileName == null) {
            fileName = "";
        }
        if (timeStamp == null) {
            timeStamp = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        if (authorization == null) {
            authorization = "";
        }
        if (originatingSystem == null) {
            originatingSystem = "";
        }

        return "HEADER;\n" + "FILE_DESCRIPTION(" + serializeList(description) +
                ",'" + IMPLEMENTATION_LEVEL + "');\n" + "FILE_NAME('" +
                fileName + "','" + timeStamp + "'," + serializeList(author) +
                "," + serializeList(organization) + ",'" +
                PREPROCESSOR_VERSION + "','" + originatingSystem + "','" +
                authorization + "');\n" + "FILE_SCHEMA(('" + FILE_SCHEMA +
                "'));\n" + "ENDSEC;\n";
    }
}
