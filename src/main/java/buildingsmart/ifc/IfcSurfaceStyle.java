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

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An assignment of one or many surface style elements to a surface, defined by
 * subtypes of
 * <I>IfcSurface</I>,
 * <I>IfcFaceBasedSurfaceModel</I>,
 * <I>IfcShellBasedSurfaceModel</I>, or by
 * subtypes of <I>IfcSolidModel</I>. The positive direction of the surface
 * normal relates to the positive side. In case of solids the outside of the
 * solid is to be taken as positive side.</P>
 * <BLOCKQUOTE><FONT SIZE="-1">NOTE: The surface style is often referred
 * to as material definition in rendering applications .</FONT></BLOCKQUOTE>
 * <BLOCKQUOTE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcSurfaceStyle extends IfcPresentationStyle
        implements IfcPresentationStyleSelect {
    @Attribute(1)
    private final IfcSurfaceSide side;
    @Attribute(2)
    private final Set<IfcSurfaceStyleElementSelect> styles;

    /**
     * @param name   Name of the presentation style.
     * @param side   An indication of which side of the surface to apply the
     *               style.
     * @param styles A collection of different surface styles.
     * @throws NullPointerException     If side or syles is null.
     * @throws IllegalArgumentException If the size of styles is lower than 1 or
     *                                  bigger than 5; if styles contains more
     *                                  than one instance of each type that
     *                                  implements IfcSurfaceStyleElementSelect.
     */
    public IfcSurfaceStyle(IfcLabel name,
                           @NonNull IfcSurfaceSide side,
                           @NonNull Set<IfcSurfaceStyleElementSelect> styles) {
        super(name);
        if (styles.isEmpty() || styles.size() > 5) {
            throw new IllegalArgumentException(
                    "size of styles cannot be lower than 1 nor bigger than 5");
        }

        int ifcSurfaceStyleShadingCounter = 0;
        int ifcSurfaceStyleLightingCounter = 0;
        int ifcSurfaceStyleRefractionCounter = 0;
        int ifcSurfaceStyleWithTexturesCounter = 0;
        int ifcExternallyDefinedSurfaceStyleCounter = 0;
        for (IfcSurfaceStyleElementSelect style : styles) {
            if (style instanceof IfcSurfaceStyleShading) {
                ifcSurfaceStyleShadingCounter++;
            } else if (style instanceof IfcSurfaceStyleLighting) {
                ifcSurfaceStyleLightingCounter++;
            } else if (style instanceof IfcSurfaceStyleRefraction) {
                ifcSurfaceStyleRefractionCounter++;
            } else if (style instanceof IfcSurfaceStyleWithTextures) {
                ifcSurfaceStyleWithTexturesCounter++;
            } else if (style instanceof IfcExternallyDefinedSurfaceStyle) {
                ifcExternallyDefinedSurfaceStyleCounter++;
            }
        }
        if (ifcSurfaceStyleShadingCounter > 1 ||
                ifcSurfaceStyleLightingCounter > 1 ||
                ifcSurfaceStyleRefractionCounter > 1 ||
                ifcSurfaceStyleWithTexturesCounter > 1 ||
                ifcExternallyDefinedSurfaceStyleCounter > 1) {
            throw new IllegalArgumentException(
                    "each type that implements IfcSurfaceStyleElementSelect " +
                            "shall only be used maximum once within styles");
        }

        this.side = side;
        this.styles = styles;
    }

    /**
     * @param name   Name of the presentation style.
     * @param side   An indication of which side of the surface to apply the
     *               style.
     * @param styles A collection of different surface styles.
     * @throws NullPointerException     If side or syles is null.
     * @throws IllegalArgumentException If the size of styles is lower than 1 or
     *                                  bigger than 5; if styles contains more
     *                                  than one instance of each type that
     *                                  implements IfcSurfaceStyleElementSelect.
     */
    public IfcSurfaceStyle(IfcLabel name,
                           @NonNull IfcSurfaceSide side,
                           @NonNull IfcSurfaceStyleElementSelect... styles) {
        this(name, side, new HashSet<>(Arrays.asList(styles)));
    }
}
