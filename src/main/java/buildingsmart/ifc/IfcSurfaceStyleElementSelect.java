/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

/**
 * The select type only includes the
 * <I>IfcSurfaceStyleRendering</I> (which is the equivalent to
 * surface_style_rendering) from the select type surface_style_element_select.
 * In addition it has the <I>IfcSurfaceStyleLighting</I>, which holds the exact
 * physically based lighting properties for lighting based calculation
 * algorithms (as the opposite to the rendering based calculation), the
 * <I>IfcSurfaceStyleRefraction</I> (for more advanced refraction
 * indices) and
 * <I>IfcSurfaceStyleWithTextures</I> (to allow for image
 * textures applied to surfaces). In addition an
 * <I>IfcExternallyDefinedSurfaceStyle</I> can be
 * selected that points into an external material library.</P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE The
 * <I>IfcSurfaceLightingProperties</I> are
 * needed for exact lighting calculation, because physically based lighting
 * calculation algorithms need exact physically based parameters. The factors
 * in
 * <I>IfcSurfaceStyleRendering</I> lack the physical base, they
 * are intended for rendering calculations, but a lighting calculation based
 * software cannot use these values.</FONT></P></BLOCKQUOTE>
 * <BLOCKQUOTE>
 */
public interface IfcSurfaceStyleElementSelect {
}
