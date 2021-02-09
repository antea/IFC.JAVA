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

package buildingsmart.ifc;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class IfcSurfaceStyleTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_tooManyStyles() {
        new IfcSurfaceStyle(null, IfcSurfaceSide.BOTH,
                mock(IfcSurfaceStyleShading.class),
                mock(IfcSurfaceStyleLighting.class),
                mock(IfcSurfaceStyleRefraction.class),
                mock(IfcSurfaceStyleWithTextures.class),
                mock(IfcExternallyDefinedSurfaceStyle.class),
                mock(IfcExternallyDefinedSurfaceStyle.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_duplicateStyles() {
        new IfcSurfaceStyle(null, IfcSurfaceSide.BOTH,
                mock(IfcSurfaceStyleShading.class),
                mock(IfcSurfaceStyleLighting.class),
                mock(IfcSurfaceStyleLighting.class),
                mock(IfcSurfaceStyleWithTextures.class));
    }

    @Test
    public void constructor_successful() {
        new IfcSurfaceStyle(null, IfcSurfaceSide.BOTH,
                mock(IfcSurfaceStyleShading.class),
                mock(IfcSurfaceStyleLighting.class),
                mock(IfcSurfaceStyleWithTextures.class));
    }
}