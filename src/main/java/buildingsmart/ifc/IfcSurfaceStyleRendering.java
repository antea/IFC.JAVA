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

import buildingsmart.io.Attribute;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * <I>IfcSurfaceStyleRendering</I> holds the
 * properties for visualization related to a particular surface side style.
 * </P>
 * <P>It allows rendering properties to be defined by:</P>
 * <UL>
 * <LI>a transparency component (<I>Transparency</I> attribute)
 * </LI>
 * <LI>a colour component (<I>SurfaceColour</I> attribute
 * inherited from
 * <I>IfcSurfaceStyleShading</I>)</LI>
 * <LI>a reflectance component, given either by
 * <UL>
 * <LI>applying reflectance factors to the surface
 * colour:
 * <UL>
 * <LI>diffuse component (<I>SurfaceColour *
 * DiffuseFactor</I>)</LI>
 * <LI>transmission component
 * (<I>SurfaceColour * TransmissionFactor</I> )</LI>
 * <LI>diffuse transmission component
 * (<I>SurfaceColour * DiffuseTransmissionFactor</I>)</LI>
 * <LI>reflection component (<I>SurfaceColour *
 * ReflectionFactor</I>)</LI>
 * <LI>specular component (<I>SurfaceColour *
 * SpecularFactor</I> attribute together with
 * <I>SpecularHighlight</I>)</LI>
 * </UL> </LI>
 * <LI>explicitly defining such factors as colours
 * (<I>DiffuseColour</I>,
 * <I>TransmissionColour</I>,
 * <I>DiffuseTransmissionColour</I>,
 * <I>ReflectionColour</I> and
 * <I>SpecularColour</I>)</LI>
 * </UL> </LI>
 * <LI>a displacement component, currently only given by a
 * texture map with the TextureType = bump</LI>
 * <LI>a coverage component, currently only given by the alpha
 * component of the texture map (2 or 4 component colour texture)</LI>
 * </UL>
 * <BLOCKQUOTE><FONT SIZE="-1">NOTE: The inherited attribute
 * <I>SurfaceColour</I> is treated as the ambient colour and
 * specifies how much ambient light from light sources this surface shall
 * reflect. Ambient light is omnidirectional and depends only on the number of
 * light sources, not their positions with respect to the
 * surface.</FONT></BLOCKQUOTE>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1">NOTE: If the reflectance method, as given
 * by the
 * <I>IfcReflectanceMethodEnum</I> is "GLASS", the transmission
 * factor controls the level of transparency in the glass, In this case the
 * transparency factor is interpreted as transmission factor
 * .</FONT></P></BLOCKQUOTE>
 * <BLOCKQUOTE><FONT SIZE="-1">NOTE: Both <I>Transparency</I> and
 * <I>TransmissionColour</I> (or factor) are included, the
 * following definitions apply</FONT>
 * <UL>
 * <LI><FONT SIZE="-1">Transparency is the ratio of the
 * transmitted flux in a solid angle of 2 * PI sr (one hemisphere). It is a
 * simple colour filtration that does not account for refraction.
 * </FONT></LI>
 * <LI><FONT SIZE="-1">Transmission factor of a material is the
 * ratio of transmitted flux in a given solid angle to the transmitted flux of a
 * completely diffuse material with 100% transmission in the same solid angle.
 * It is the portion of light that goes through the material and may be
 * refracted.</FONT></LI>
 * </UL></BLOCKQUOTE>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcSurfaceStyleRendering extends IfcSurfaceStyleShading {
    @Attribute(1)
    private final IfcNormalisedRatioMeasure transparency;
    @Attribute(2)
    private final IfcColourOrFactor diffuseColour;
    @Attribute(3)
    private final IfcColourOrFactor transmissionColour;
    @Attribute(4)
    private final IfcColourOrFactor diffuseTransmissionColour;
    @Attribute(5)
    private final IfcColourOrFactor reflectionColour;
    @Attribute(6)
    private final IfcColourOrFactor specularColour;
    @Attribute(7)
    private final IfcSpecularHighlightSelect specularHighlight;
    @Attribute(8)
    private final IfcReflectanceMethodEnum reflectanceMethod;

    /**
     * @param surfaceColour             The colour used to render the surface.
     *                                  The surface colour for visualisation is
     *                                  defined by specifying the intensity of
     *                                  red, green and blue.
     * @param transparency              Definition from ISO/CD 10303-46: The
     *                                  degree of transparency is indicated by
     *                                  the percentage of light traversing the
     *                                  surface.
     *                                  </p>
     *                                  Definition from VRML97 - ISO/IEC
     *                                  14772-1:1997: The transparency field
     *                                  specifies how "clear" an object is, with
     *                                  1.0 being completely transparent, and
     *                                  0.0 completely opaque. If not given, the
     *                                  value 0.0 (opaque) is assumed.
     * @param diffuseColour             The diffuse part of the reflectance
     *                                  equation can be given as either a colour
     *                                  or a scalar factor. The diffuse colour
     *                                  field reflects all light sources
     *                                  depending on the angle of the surface
     *                                  with respect to the light source. The
     *                                  more directly the surface faces the
     *                                  light, the more diffuse light reflects.
     *                                  The diffuse factor field specifies how
     *                                  much diffuse light from light sources
     *                                  this surface shall reflect. Diffuse
     *                                  light depends on the angle of the
     *                                  surface with respect to the light
     *                                  source. The more directly the surface
     *                                  faces the light, the more diffuse light
     *                                  reflects. The diffuse colour is then
     *                                  defined by surface colour * diffuse
     *                                  factor.
     * @param transmissionColour        The transmissive part of the reflectance
     *                                  equation can be given as either a colour
     *                                  or a scalar factor. It only applies to
     *                                  materials which Transparency field is
     *                                  greater than zero. The transmissive
     *                                  colour field specifies the colour that
     *                                  passes through a transparant material
     *                                  (like the colour that shines through a
     *                                  glass). The transmissive factor defines
     *                                  the transmissive part, the transmissive
     *                                  colour is then defined by surface colour
     *                                  * transmissive factor.
     * @param diffuseTransmissionColour The diffuse transmission part of the
     *                                  reflectance equation can be given as
     *                                  either a colour or a scalar factor. It
     *                                  only applies to materials whose
     *                                  Transparency field is greater than zero.
     *                                  The diffuse transmission colour
     *                                  specifies how much diffuse light is
     *                                  reflected at the opposite side of the
     *                                  material surface. The diffuse
     *                                  transmission factor field specifies how
     *                                  much diffuse light from light sources
     *                                  this surface shall reflect on the
     *                                  opposite side of the material surface.
     *                                  The diffuse transmissive colour is then
     *                                  defined by surface colour * diffuse
     *                                  transmissive factor.
     * @param reflectionColour          The reflection (or mirror) part of the
     *                                  reflectance equation can be given as
     *                                  either a colour or a scalar factor.
     *                                  Applies to "glass" and "mirror"
     *                                  reflection models. The reflection colour
     *                                  specifies the contribution made by light
     *                                  from the mirror direction, i.e. light
     *                                  being reflected from the surface. The
     *                                  reflection factor specifies the amount
     *                                  of contribution made by light from the
     *                                  mirror direction. The reflection colour
     *                                  is then defined by surface colour *
     *                                  reflection factor.
     * @param specularColour            The specular part of the reflectance
     *                                  equation can be given as either a colour
     *                                  or a scalar factor. The specular colour
     *                                  determine the specular highlights (e.g.,
     *                                  the shiny spots on an apple). When the
     *                                  angle from the light to the surface is
     *                                  close to the angle from the surface to
     *                                  the viewer, the specular colour is added
     *                                  to the diffuse and ambient colour
     *                                  calculations. The specular factor
     *                                  defines the specular part, the specular
     *                                  colour is then defined by surface colour
     *                                  * specular factor.
     * @param specularHighlight         The exponent or roughness part of the
     *                                  specular reflectance.
     * @param reflectanceMethod         Identifies the predefined types of
     *                                  reflectance method from which the method
     *                                  required may be set.
     * @throws NullPointerException If surfaceColour or reflectanceMethod are
     *                              null.
     */
    @Builder
    public IfcSurfaceStyleRendering(@NonNull IfcColourRgb surfaceColour,
                                    IfcNormalisedRatioMeasure transparency,
                                    IfcColourOrFactor diffuseColour,
                                    IfcColourOrFactor transmissionColour,
                                    IfcColourOrFactor diffuseTransmissionColour,
                                    IfcColourOrFactor reflectionColour,
                                    IfcColourOrFactor specularColour,
                                    IfcSpecularHighlightSelect specularHighlight,
                                    @NonNull IfcReflectanceMethodEnum reflectanceMethod) {
        super(surfaceColour);
        this.transparency = transparency;
        this.diffuseColour = diffuseColour;
        this.transmissionColour = transmissionColour;
        this.diffuseTransmissionColour = diffuseTransmissionColour;
        this.reflectionColour = reflectionColour;
        this.specularColour = specularColour;
        this.specularHighlight = specularHighlight;
        this.reflectanceMethod = reflectanceMethod;
    }
}
