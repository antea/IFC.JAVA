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

package buildingsmart.ifc;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static buildingsmart.ifc.IfcGloballyUniqueId.LENGTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IfcGloballyUniqueIdTest {

    /**
     * Generates a number of IfcGloballyUniqueId equal to numTests, checks if
     * they contain only allowed characters and that none of the generated GUIDs
     * is a duplicate of one generated before. The only guarantee this last
     * check provides is that in a typical IFC file there shouldn't be any GUID
     * collisions, it doesn't really prove that the algorithm we used to
     * compress the GUID into a 22 char String didn't lose any of the UUID data
     * somewhere along the way.
     */
    @Test
    public void randomConstructorTest() {
        int numTests = 10000;
        Set<String> guidStrings = new HashSet<>(numTests);
        for (int i = 0; i < numTests; i++) {
            IfcGloballyUniqueId guid = new IfcGloballyUniqueId();
            String guidString = guid.serialize();
            assertTrue(guidString.matches("^'[0-9A-Za-z_$]*'$"));
            assertEquals(LENGTH + 2, guidString.length());
            guidStrings.add(guidString);
        }
        assertEquals(numTests, guidStrings.size());
    }
}