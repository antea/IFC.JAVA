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

import org.junit.Assert;
import org.junit.Test;

public class HeaderTest {

    @Test
    public void serialize() {
        String expectedSerialization = "HEADER;\n" +
                "FILE_DESCRIPTION(('ViewDefinition [CoordinationView]'),'" +
                Header.IMPLEMENTATION_LEVEL + "');\n" +
                "FILE_NAME('freecad-cylinder.ifc','2020-04-14T22:16:25',('')," +
                "(''),'" + Header.PREPROCESSOR_VERSION + "','" + "','');\n" +
                "FILE_SCHEMA(('" + Header.FILE_SCHEMA + "'));\n" + "ENDSEC;\n";

        Header header =
                new Header().setDescription("ViewDefinition [CoordinationView]")
                        .setFileName("freecad-cylinder.ifc")
                        .setTimeStamp("2020-04-14T22:16:25");
        String serialization = header.serialize();

        Assert.assertEquals(expectedSerialization, serialization);
    }

    @Test
    public void serialize_emptyHeader_hasTimeStamp() {
        Header header = new Header();
        String serialization = header.serialize();

        String timeStampSubstring = serialization
                .substring(49 + Header.IMPLEMENTATION_LEVEL.length(), 71);
        // the only String of variable length serialized before the timestamp
        // is IMPLEMENTATION_LEVEL
        Assert.assertTrue(timeStampSubstring.matches(
                "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]" +
                        ":[0-5][0-9]:[0-5][0-9]$"));
        // checks that the String representation of the timestamp is actually
        // a date with time
    }
}