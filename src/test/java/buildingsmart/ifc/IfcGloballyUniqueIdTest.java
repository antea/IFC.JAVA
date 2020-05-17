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