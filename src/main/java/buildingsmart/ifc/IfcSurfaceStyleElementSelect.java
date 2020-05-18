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
