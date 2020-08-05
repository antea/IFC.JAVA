/*
 * Copyright 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Antea S.r.l.
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

import buildingsmart.io.DefinedType;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

/**
 * Holds an identifier that is unique throughout the software world. This is
 * also known as a Globally Unique Identifier (GUID) or Universal Unique
 * Identifier (UUID) by the Open Group. The identifier is generated using an
 * algorithm published by the Object Management Group. The algorithm is
 * explained at the open group
 * <a href="http://www.opengroup.org/dce/info/draft-leach-uuids-guids-01.txt">
 * website</a>. The Microsoft Foundation Class (MFC) function "CoCreateGuid",
 * which is an implementation of the above algorithm, has been used by many IFC
 * implementers to create an identifier.</p>
 * <p>An identifier is a unique 128-bit number. Since this identifier is
 * required for all IFC object instances, it is desirable to compress the
 * identifier size to reduce overhead. Beginning in IFC R1.5.1, IFC implementers
 * agreed to compress the identifier down to 20 characters using an algorithm
 * developed by IAI Implementers. This algorithm maps the identifier bits onto a
 * base 85 digit encoded from the following character set:</p>
 * <p>0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!#$%&amp;
 * ^|*+,-./:;&lt;=&gt;?~`@_</p>
 * <p>An index (0-84) into this character set string determines the "value"
 * for each character (e.g. "A" has a value of 10, "@" has a value of 83, etc.).
 * Note that all characters are case-sensitive. In order to prevent possible
 * problems with some parsers, implementers have agreed not to use the "/*" and
 * "*&#x002F;" character combinations in the identifier string and instead to
 * use "\*" and "*\".</p>
 * <p>The implementation of the compression algorithm has changed in IFC
 * R2x in order to prevent conflicts with special characters in the ISO 10303-21
 * exchange file and the W3C XML file (such as '&lt;*', '*&gt;', '&lt;' and
 * '&gt;'). Using 64 characters for the base rather than 85, the resulting
 * compressed string now needs 22 characters. The encoding of the base 64
 * character set is shown below:</p>
 * <blockquote>
 * <p><font FACE="Courier New" size="-1">
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 * &nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5&nbsp;
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6
 * <br>&nbsp;0123456789012345678901234567890123456789012345678901234567890123
 * <br>
 * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_$";
 * </font></p>
 * </blockquote>
 * <p>The resulting string is a fixed 22 character length string to be
 * exchanged within the IFC exchange file structure.
 */
@EqualsAndHashCode
@ToString
public class IfcGloballyUniqueId implements DefinedType {
    protected static final int LENGTH = 22;
    private static final String ALLOWED_CHARS_REGEX = "^[0-9A-Za-z_$]*$";
    private static final char[] CONVERSION_TABLE = new char[]{'0',
                                                              '1',
                                                              '2',
                                                              '3',
                                                              '4',
                                                              '5',
                                                              '6',
                                                              '7',
                                                              '8',
                                                              '9',
                                                              'A',
                                                              'B',
                                                              'C',
                                                              'D',
                                                              'E',
                                                              'F',
                                                              'G',
                                                              'H',
                                                              'I',
                                                              'J',
                                                              'K',
                                                              'L',
                                                              'M',
                                                              'N',
                                                              'O',
                                                              'P',
                                                              'Q',
                                                              'R',
                                                              'S',
                                                              'T',
                                                              'U',
                                                              'V',
                                                              'W',
                                                              'X',
                                                              'Y',
                                                              'Z',
                                                              'a',
                                                              'b',
                                                              'c',
                                                              'd',
                                                              'e',
                                                              'f',
                                                              'g',
                                                              'h',
                                                              'i',
                                                              'j',
                                                              'k',
                                                              'l',
                                                              'm',
                                                              'n',
                                                              'o',
                                                              'p',
                                                              'q',
                                                              'r',
                                                              's',
                                                              't',
                                                              'u',
                                                              'v',
                                                              'w',
                                                              'x',
                                                              'y',
                                                              'z',
                                                              '_',
                                                              '$'};
    private final String value;

    /**
     * @param value The String representation of the GUID, obtained by mapping
     *              the 128-bit identifier to a 22-characters String.
     * @throws NullPointerException     If value is null.
     * @throws IllegalArgumentException If value is not 22 characters long or
     *                                  contains characters not included in the
     *                                  following string: {@code
     *                                  "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_$"}
     */
    public IfcGloballyUniqueId(@NonNull String value) {
        if (value.length() != LENGTH) {
            throw new IllegalArgumentException(
                    "value must be 22 characters long");
        }
        if (!value.matches(ALLOWED_CHARS_REGEX)) {
            throw new IllegalArgumentException(
                    "value contains illegal characters");
        }
        this.value = value;
    }

    /**
     * Generates a pseudo random IfcGloballyUniqueId.
     */
    public IfcGloballyUniqueId() {
        this.value =
                getCompressedStringFromGuid(getGuidFromUUID(UUID.randomUUID()));
    }

    /**
     * Converts an UIID (also called GUID) into a Guid data structure.
     *
     * @param uuid the UUID to convert.
     * @return a Guid object.
     */
    private static Guid getGuidFromUUID(UUID uuid) {
        String uncompressedUuidString = uuid.toString();
        String[] parts = uncompressedUuidString.split("-");
        Guid guid = new Guid();
        guid.data1 = Long.parseLong(parts[0], 16);
        guid.data2 = Integer.parseInt(parts[1], 16);
        guid.data3 = Integer.parseInt(parts[2], 16);

        String temp;

        temp = parts[3];
        guid.data4[0] = (char) Integer.parseInt(temp.substring(0, 2), 16);
        guid.data4[1] = (char) Integer.parseInt(temp.substring(2, 4), 16);

        temp = parts[4];
        guid.data4[2] = (char) Integer.parseInt(temp.substring(0, 2), 16);
        guid.data4[3] = (char) Integer.parseInt(temp.substring(2, 4), 16);
        guid.data4[4] = (char) Integer.parseInt(temp.substring(4, 6), 16);
        guid.data4[5] = (char) Integer.parseInt(temp.substring(6, 8), 16);
        guid.data4[6] = (char) Integer.parseInt(temp.substring(8, 10), 16);
        guid.data4[7] = (char) Integer.parseInt(temp.substring(10, 12), 16);

        return guid;
    }

    /**
     * Converts a Guid object into its compressed String representation
     * according to the IFC specification.
     *
     * @param guid The Guid object to compress.
     * @return The 22 characters long String representation of the Guid.
     */
    private static String getCompressedStringFromGuid(Guid guid) {
        long[] num = new long[6];
        char[][] str = new char[6][5];
        int i, j, n;
        StringBuilder result = new StringBuilder();

        // Creation of six 32 bit integers from the components of the Guid
        // structure
        num[0] = guid.data1 / 16777216;
        // 16. byte  (guid.data1 / 16777216) is the same as (guid.data1 >> 24)
        num[1] = guid.data1 % 16777216;
        // 15-13. bytes (guid.data1 % 16777216) is the same as (guid.data1 &
        // 0xFFFFFF)
        num[2] = guid.data2 * 256 + guid.data3 / 256;
        // 12-10. bytes
        num[3] = (guid.data3 % 256) * 65536 + guid.data4[0] * 256 +
                guid.data4[1];
        // 09-07. bytes
        num[4] = guid.data4[2] * 65536 + guid.data4[3] * 256 + guid.data4[4];
        // 06-04. bytes
        num[5] = guid.data4[5] * 65536 + guid.data4[6] * 256 + guid.data4[7];
        // 03-01. bytes

        // Conversion of the numbers into a system using a base of 64
        n = 3;
        for (i = 0; i < 6; i++) {
            if (!convertToBase64(num[i], str[i], n)) {
                return null;
            }
            for (j = 0; j < str[i].length; j++) {
                if (str[i][j] != '\0') {
                    result.append(str[i][j]);
                }
            }
            n = 5;
        }
        return result.toString();
    }

    /**
     * Conversion of an integer into a number with base 64 using the table
     * CONVERSION_TABLE.
     *
     * @param number The integer to convert.
     * @param code   The char array where to put the converted integer.
     * @param len
     * @return true if no error occurred
     */
    private static boolean convertToBase64(long number, char[] code, int len) {
        long act;
        int iDigit, nDigits;
        char[] result = new char[5];

        if (len > 5) {
            return false;
        }

        act = number;
        nDigits = len - 1;

        for (iDigit = 0; iDigit < nDigits; iDigit++) {
            result[nDigits - iDigit - 1] = CONVERSION_TABLE[(int) (act % 64)];
            act /= 64;
        }
        result[len - 1] = '\0';

        if (act != 0) {
            return false;
        }

        System.arraycopy(result, 0, code, 0, result.length);

        return true;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "'" + value + "'";
    }

    /**
     * Data structure used for the conversion from standard String
     * representation of a UUID (or GUID) to its compressed String
     * representation (according to the IFC specification).
     */
    private static class Guid {
        long data1;
        int data2;
        int data3;
        char[] data4 = new char[8];
    }
}
