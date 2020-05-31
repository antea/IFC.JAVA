/*
 * Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.io;

import buildingsmart.util.Functions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the HEADER section of an IFC STEP file.
 */
public class Header {
    protected static final String IMPLEMENTATION_LEVEL =
            Functions.formatForStepFile("2;1");
    protected static final String PROGRAM_NAME_AND_VERSION =
            Functions.formatForStepFile("IFC.JAVA 0.1");
    protected static final String FILE_SCHEMA =
            Functions.formatForStepFile("IFC2X3");
    private List<String> description;
    private String fileName;
    private String timeStamp;
    private List<String> author;
    private List<String> organization;
    private String authorization;

    public Header() {
    }

    /**
     * Checks whether the given String is longer than 256 characters.
     *
     * @param str The string to check.
     * @throws IllegalArgumentException If {@code str} is longer than 256
     *                                  characters.
     */
    private static void checkMaxLength(String str) {
        if (str.length() > 256) {
            throw new IllegalArgumentException(
                    "maximum length of the string is 256 characters");
        }
    }

    /**
     * Checks whether any String in the given List is longer than 256
     * characters, and formats all Strings in the list for serialization in a
     * STEP file.
     *
     * @param strings The list of Strings to check and format.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters.
     */
    private static void checkMaxLengthAndFormat(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            checkMaxLength(strings.get(i));
            String formatted = Functions.formatForStepFile(strings.get(i));
            strings.set(i, formatted);
        }
    }

    /**
     * @param list The List of Strings to serialize.
     * @return The serialization of the given List of Strings in a STEP file.
     */
    private static String serializeList(List<String> list) {
        StringBuilder result = new StringBuilder();
        result.append("(");
        if (list != null && list.size() >= 1) {
            for (String str : list) {
                result.append("'").append(str).append("'").append(",");
            }
            result.deleteCharAt(result.length() - 1);
        } else {
            result.append("''");
        }
        result.append(")");
        return result.toString();
    }

    /**
     * @param description Information about the exchanged content.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters.
     */
    public Header setDescription(List<String> description) {
        checkMaxLengthAndFormat(description);
        this.description = description;
        return this;
    }

    /**
     * @param description Information about the exchanged content.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters.
     */
    public Header setDescription(String... description) {
        return this.setDescription(Arrays.asList(description));
    }

    /**
     * @param fileName The name of the file in which this Header will be
     *                 serialized.
     * @throws IllegalArgumentException If {@code str} is longer than 256
     *                                  characters.
     */
    public Header setFileName(String fileName) {
        checkMaxLength(fileName);
        Functions.formatForStepFile(fileName);
        this.fileName = fileName;
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
     *                                  longer than 256 characters.
     */
    public Header setAuthor(List<String> author) {
        checkMaxLengthAndFormat(author);
        this.author = author;
        return this;
    }

    /**
     * @param author Name and email address of the author of the file.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters.
     */
    public Header setAuthor(String... author) {
        return this.setAuthor(Arrays.asList(author));
    }

    /**
     * @param organization Name of the organization to which the author belongs
     *                     to.
     * @throws IllegalArgumentException If any of the Strings in the list is
     *                                  longer than 256 characters.
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
     *                                  longer than 256 characters.
     */
    public Header setOrganization(String... organization) {
        return this.setOrganization(Arrays.asList(organization));
    }

    /**
     * @param authorization Name and email address of the person who authorized
     *                      the creation of this file.
     * @throws IllegalArgumentException If {@code str} is longer than 256
     *                                  characters.
     */
    public Header setAuthorization(String authorization) {
        checkMaxLength(authorization);
        Functions.formatForStepFile(authorization);
        this.authorization = authorization;
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

        return "HEADER;\n" + "FILE_DESCRIPTION(" + serializeList(description) +
                ",'" + IMPLEMENTATION_LEVEL + "');\n" + "FILE_NAME('" +
                fileName + "','" + timeStamp + "'," + serializeList(author) +
                "," + serializeList(organization) + ",'" +
                PROGRAM_NAME_AND_VERSION + "','" +
                Functions.formatForStepFile(System.getProperty("os.name")) +
                " " +
                Functions.formatForStepFile(System.getProperty("os.version")) +
                " " +
                Functions.formatForStepFile(System.getProperty("os.arch")) +
                "','" + authorization + "');\n" + "FILE_SCHEMA(('" +
                FILE_SCHEMA + "'));\n" + "ENDSEC;\n";
    }
}
