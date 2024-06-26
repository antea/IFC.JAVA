/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

/**
 * The wall represents a vertical construction that bounds or subdivides spaces.
 * Wall are usually vertical, or nearly vertical, planar elements, often
 * designed to bear structural loads. A wall is however&nbsp;not required to be
 * load bearing. The IFC specification provides two entities for wall
 * occurrences:</p>
 * <ul> <li><i>IfcWallStandardCase</i>
 * &nbsp;used for
 * all occurrences of
 * walls, that have a non-changing thickness along the wall path and where
 * the thickness parameter can be fully described by a material layer set.
 * These walls are always represented geometrically by a SweptSolid
 * geometry, if a 3D geometric representation is assigned.</li>
 * <li><i>IfcWall</i>
 * &nbsp;used for all other
 * occurrences of wall,
 * particularly for walls with changing thickness along the wall path
 * (e.g. polygonal walls), or walls with a non-rectangular cross sections
 * (e.g. L-shaped retaining walls), and walls having an extrusion axis
 * that is unequal to the global Z axis of the project (i.e. non-vertical
 * walls). </li>
 * </ul>
 * <p><u><b>Type Use Definition</b></u></p>
 * <p><i>IfcWall</i> (or the subtype <i>IfcWallStandardCase</i>)
 * defines the occuurence of any wall, common information
 * about&nbsp;wall types (or styles) is handled by <i>IfcWallType</i>.
 * The <i>IfcWallType</i>
 * (if present) may establish the common&nbsp;type name, usage (or
 * predefined) type, common material layer set, common set of properties
 * and common shape
 * representations (using <i>IfcRepresentationMap</i>). The <i>IfcWallType</i>
 * is attached using the <i>IfcRelDefinedByType.RelatingType</i>
 * objectified relationship and is accessible by the inverse <i>IsDefinedBy</i>
 * attribute.</p>
 * </ul><p><u><b>Quantity Use Definition</b></u>:</p>
 * <p>The quantities relating to the <i>IfcWall</i> are
 * defined by the <i>IfcElementQuantity</i> and attached by
 * the <i>IfcRelDefinesByProperties</i>. The following
 * quantities are foreseen, but will be subjected to the local standard of
 * measurement:</p>
 * <table border="1" cellpadding="2" cellspacing="2"><tbody>
 * <tr valign="top"> <td align="left" valign="top"><font
 *  size="-1"><i><b>Name</b></i></font></td>
 * <td align="left" valign="top" width="60%"><font
 *  size="-1"><i><b>Description</b></i></font></td>
 * <td align="left" valign="top"><font size="-1"><i><b>Value
 * Type</b></i></font></td> </tr> <tr>
 * <td align="left" valign="top"><font size="-1">NominalLength</font></td>
 * <td align="left" valign="top" width="60%"><font
 *  size="-1">Total nominal (or average) length of the wall
 * along the wall path. The exact definition and calculation rules depend
 * on the method of measurement used.</font></td> <td
 *  align="left" valign="top"><font
 *  size="-1"><i>IfcQuantityLength</i></font></td>
 * </tr> <tr> <td valign="top"><font
 *  size="-1">NominalWidth</font></td> <td
 *  valign="top"><font size="-1">Total
 * nominal (or average) width (or thickness) of the wall perpendicular to
 * the wall path. The exact definition and calculation rules depend on the
 * method of measurement used.</font></td> <td valign="top"><font
 *  size="-1"><i>IfcQuantityLength</i></font></td>
 * </tr> <tr> <td valign="top"><font
 *  size="-1">NominalHeight</font></td> <td
 *  valign="top"><font size="-1">Total
 * nominal (or average) height of the wall along the wall path. The exact
 * definition and calculation rules depend on the method of measurement
 * used.</font></td> <td valign="top"><font
 *  size="-1"><i>IfcQuantityLength</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">GrossFootprintArea</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by a ground floor view,
 * not taking any wall modifications (like recesses) into account. It is
 * also referred to as the foot print of the wall. The exact definition
 * and calculation rules depend on the method of measurement used.</font></td>
 * <td align="left" valign="top"><font
 * size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">NetFootprintArea</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by a ground floor view,
 * taking all wall modifications (like recesses) into account. It is also
 * referred to as the foot print of the wall. The exact definition and
 * calculation rules depend on the method of measurement used.</font></td>
 * <td align="left" valign="top"><font
 * size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">GrossSideArea</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the middle plane of the wall.&nbsp; It does
 * not take into account any wall modifications (such as openings). The
 * exact definition and calculation rules depend on the method of
 * measurement used.</font></td> <td align="left"
 *  valign="top"><font size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">NetSideArea</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the middle plane. It does
 * take into account all wall modifications (such as openings). The exact
 * definition and calculation rules depend on the method of measurement
 * used.</font></td> <td align="left" valign="top"><font
 *  size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr><tr> <td align="left" valign="top"><font
 *  size="-1">GrossSideAreaLeft</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the left side (when viewed along the wall path orientation). It does
 * not take into account any wall modifications (such as openings). The
 * exact definition and calculation rules depend on the method of
 * measurement used.</font></td> <td align="left"
 *  valign="top"><font size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">NetSideAreaLeft</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the left side (when viewed along the wall path orientation). It does
 * take into account all wall modifications (such as openings). The exact
 * definition and calculation rules depend on the method of measurement
 * used.</font></td> <td align="left" valign="top"><font
 *  size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr><tr> <td align="left" valign="top"><font
 *  size="-1">GrossSideAreaRight</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the right side (when viewed along the wall path orientation). It does
 * not take into account any wall modifications (such as openings). The
 * exact definition and calculation rules depend on the method of
 * measurement used.</font></td> <td align="left"
 *  valign="top"><font size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">NetSideAreaRight</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Area of the wall as viewed by an elevation view of
 * the right side (when viewed along the wall path orientation). It does
 * take into account all wall modifications (such as openings). The exact
 * definition and calculation rules depend on the method of measurement
 * used.</font></td> <td align="left" valign="top"><font
 *  size="-1"><i>IfcQuantityArea</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">GrossVolume</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Volume of the wall, without taking into account
 * the openings and the connection geometry. The exact definition and
 * calculation rules depend on the method of measurement used.</font></td>
 * <td align="left" valign="top"><font
 * size="-1"><i>IfcQuantityVolume</i></font></td>
 * </tr> <tr> <td align="left" valign="top"><font
 *  size="-1">NetVolume</font></td> <td
 *  align="left" valign="top" width="60%"><font
 *  size="-1">Volume of the wall, after subtracting the openings
 * and after considering the connection geometry. The exact definition and
 * calculation rules depend on the method of measurement used.</font></td>
 * <td align="left" valign="top"><font
 * size="-1"><i>IfcQuantityVolume</i></font></td>
 * </tr> </tbody></table><p><u><b>Containment
 * Use Definition</b></u></p>
 * <p>The <i>IfcWall</i> (and the subtype <i>IfcWallStandardCase</i>)
 * as any subtype of <i>IfcBuildingElement</i>,
 * may participate in two different containment relationships. The first
 * (and in most implementation scenarios mandatory) relationship is the
 * hierarchical spatial containment, the second (optional) relationship is
 * the aggregation within an&nbsp;element assembly.</p>
 * <ul> <li>The <i>IfcWall </i>is
 * places within the
 * project spatial hierarchy using the objectified relationship
 * <i>IfcRelContainedInSpatialStructure</i>,
 * referring to it by its inverse attribute <i>SELF\IfcElement
 * .ContainedInStructure</i>.
 * Subtypes of&nbsp;<i>IfcSpatialStructureElement</i> are
 * valid spatial containers, with <i>IfcBuildingStorey</i>
 * being the default container.</li> <li>The&nbsp;<i>IfcWall</i><i>
 * </i>may
 * be aggregated into an
 * element assembly using the objectified relationship <i>IfcRelAggregates</i>,
 * referring to it by its inverse attribute <i>SELF\IfcObjectDefinition
 * .Decomposes</i>.
 * Any subtype of <i>IfcElement</i> can be an element
 * assembly, with <i>IfcElementAssembly</i> as a special
 * focus subtype. <br>
 * In this case the wall should not be additionally contained in the
 * project
 * spatial hierarchy, i.e.&nbsp;<i>SELF\IfcElement.ContainedInStructure
 * </i>should be <i>NIL.</i></li>
 * </ul><p>The&nbsp;<i>IfcWall&nbsp;</i>may
 * also be
 * an aggregate i.e. being composed by other
 * elements and acting as an assembly using the objectified relationship
 * <i>IfcRelAggregates</i>,
 * referring to it by its inverse attribute <i>SELF\IfcObjectDefinition
 * .IsDecomposedBy</i>.
 * Components of a wall are described by instances of
 * <i>IfcBuildingElementPart</i>
 * that are aggregated to form a complex wall. <br>
 * In this case, the contained&nbsp;<i>IfcBuildingElementPart</i>'s
 * should not be additionally contained in the project
 * spatial hierarchy, i.e. the inverse attribute <i>SELF\IfcElement
 * .ContainedInStructure
 * </i>of <i>IfcBuildingElementPart </i>should be <i>NIL.</i></p>
 * <u><b>Geometry Use Definitions</b></u>:
 * <p>The geometric representation of <i>IfcWall</i> is
 * given by the <i>IfcProductDefinitionShape</i>, allowing
 * multiple geometric representations. Included are: </p>
 * <p><b>Local Placement</b></p>
 * <p>The local placement for <i>IfcWall</i> is defined
 * in its supertype <i>IfcProduct</i>. It is defined by the
 * <i>IfcLocalPlacement</i>,
 * which defines the local coordinate system that is referenced by all
 * geometric representations. </p>
 * <ul> <li>The <i>PlacementRelTo</i>
 * relationship of <i>IfcLocalPlacement</i>
 * shall point (if given) to the local placement of the same
 * <i>IfcSpatialStructureElement</i>
 * that is used in the <i>ContainedInStructure</i> inverse
 * attribute or to a referenced spatial structure element at a higher
 * level.</li> <li>If the relative placement is not used, the
 * absolute
 * placement is defined within the world coordinate system. </li>
 * </ul><p><b><i>Geometric Representations</i></b></p>
 * <p>Currently, the use of 'SweptSolid', 'Clipping', and 'Brep'
 * representations is supported. In addition the general representation
 * types 'SurfaceModel' and 'BoundingBox' are allowed. The geometry use
 * definition for 'BoundingBox', 'SurfaceModel' and 'Brep' is explained at
 * <i>IfcBuildingElement</i>. A more restricted geometry
 * definition is given at the level of the subtype <i>IfcWallStandardCase</i>
 * .</p>
 * <p><b>SweptSolid representation</b></p>
 * <p>The standard geometric representation (for body) of <i>IfcWall</i>
 * is defined using the 'SweptSolid' representation. <font
 *  color="#0000ff">If
 * the wall body can be described by a
 * vertical extrusion of a polygonal footprint of the wall body (where
 * vertical = into the direction of the global Z axis), the subtype
 * <i>IfcWallStandardCase
 * </i>should be used</font>. If the extrusion is not equal to
 * global Z, then the <i>IfcWall</i> with 'SweptSolid
 * representation should be used. The <i>IfcShapeRepresentation</i>
 * shall have the following values:</p>
 * <ul> <li><i>RepresentationIdentifier</i> :
 * 'Body'</li> <li><i>RepresentationType</i> :
 * 'SweptSolid'</li>
 * </ul><p>The following additional constraints apply to the
 * swept solid
 * representation:</p>
 * <ul> <li><u>Solid</u>: <i>IfcExtrudedAreaSolid</i>
 * is required,</li> <li><u>Profile</u>: <i>IfcArbitraryClosedProfileDef</i>
 * shall be supported. </li> <li><u>Extrusion</u>:&nbsp;The
 * extrusion axis shall be perpendicular to the swept
 * profile, i.e. pointing into the direction of the z-axis of the Position
 * of the <i>IfcExtrudedAreaSolid</i>.</li>
 * </ul><p><b><u>Connection Geometry</u></b></p>
 * <p>The connection between two walls is represented by the
 * <i>IfcRelConnectsPathElements</i>.
 * The use of the parameter of that relationship object is defined at the
 * level of the subtypes of <i>IfcWall</i> and at the
 * <i>IfcRelConnectsPathElements</i>.</p>
 */
@ToString(callSuper = true)
public class IfcWall extends IfcBuildingElement {
    /**
     * Creates a new IfcWall, using the provided globalId.
     *
     * @param globalId        Assignment of a globally unique identifier within
     *                        the entire software world.
     * @param ownerHistory    Assignment of the information about the current
     *                        ownership of that object, including owning actor,
     *                        application, local identification and information
     *                        captured about the recent changes of the object,
     *                        NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating
     *                        software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be
     *                        required . This would be enforced by a where
     *                        rule.
     * @param description     Optional description, provided for exchanging
     *                        informative comments.
     * @param objectType      The type denotes a particular type that indicates
     *                        the object further. The use has to be established
     *                        at the level of instantiable subtypes. In
     *                        particular it holds the user defined type, if the
     *                        enumeration of the attribute PredefinedType is set
     *                        to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement
     *                        can either be absolute (relative to the world
     *                        coordinate system), relative (relative to the
     *                        object placement of another product), or
     *                        constraint (e.g. relative to grid axes). It is
     *                        determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis
     *                        placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product,
     *                        being either a representation
     *                        (IfcProductRepresentation)
     *                        or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product
     *                        definition shape provides for multiple geometric
     *                        representations of the shape property of the
     *                        object within the same object coordinate system,
     *                        defined by the object placement.
     * @param tag             The tag (or label) identifier at the particular
     *                        instance of a product, e.g. the serial number, or
     *                        the position number. It is the identifier at the
     *                        occurrence level.
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class; if representation is not
     *                                  null and objectPlacement is, while
     *                                  representation is an instance of
     *                                  IfcProductDefinitionShape.
     */
    @Builder
    public IfcWall(@NonNull IfcGloballyUniqueId globalId,
                   @NonNull IfcOwnerHistory ownerHistory,
                   IfcLabel name,
                   IfcText description,
                   IfcLabel objectType,
                   IfcObjectPlacement objectPlacement,
                   IfcProductRepresentation representation,
                   IfcIdentifier tag) {
        super(globalId,
              ownerHistory,
              name,
              description,
              objectType,
              objectPlacement,
              representation,
              tag);
    }

    /**
     * Creates a new IfcWall and generates a pseudo random globalId.
     *
     * @param ownerHistory    Assignment of the information about the current
     *                        ownership of that object, including owning actor,
     *                        application, local identification and information
     *                        captured about the recent changes of the object,
     *                        NOTE: only the last modification in stored.
     * @param name            Optional name for use by the participating
     *                        software systems or users. For some subtypes of
     *                        IfcRoot the insertion of the Name attribute may be
     *                        required . This would be enforced by a where
     *                        rule.
     * @param description     Optional description, provided for exchanging
     *                        informative comments.
     * @param objectType      The type denotes a particular type that indicates
     *                        the object further. The use has to be established
     *                        at the level of instantiable subtypes. In
     *                        particular it holds the user defined type, if the
     *                        enumeration of the attribute PredefinedType is set
     *                        to USERDEFINED.
     * @param objectPlacement Placement of the product in space, the placement
     *                        can either be absolute (relative to the world
     *                        coordinate system), relative (relative to the
     *                        object placement of another product), or
     *                        constraint (e.g. relative to grid axes). It is
     *                        determined by the various subtypes of
     *                        IfcObjectPlacement, which includes the axis
     *                        placement information to determine the
     *                        transformation for the object coordinate system.
     * @param representation  Reference to the representations of the product,
     *                        being either a representation
     *                        (IfcProductRepresentation)
     *                        or as a special case a shape representations
     *                        (IfcProductDefinitionShape). The product
     *                        definition shape provides for multiple geometric
     *                        representations of the shape property of the
     *                        object within the same object coordinate system,
     *                        defined by the object placement.
     * @param tag             The tag (or label) identifier at the particular
     *                        instance of a product, e.g. the serial number, or
     *                        the position number. It is the identifier at the
     *                        occurrence level.
     * @throws NullPointerException     If ownerHistory is null.
     * @throws IllegalArgumentException If representation is not null and
     *                                  objectPlacement is, while representation
     *                                  is an instance of
     *                                  IfcProductDefinitionShape.
     */
    public IfcWall(@NonNull IfcOwnerHistory ownerHistory,
                   IfcLabel name,
                   IfcText description,
                   IfcLabel objectType,
                   IfcObjectPlacement objectPlacement,
                   IfcProductRepresentation representation,
                   IfcIdentifier tag) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             objectType,
             objectPlacement,
             representation,
             tag);
    }

    /**
     * @param relationship The relationship to add to the Set hasAssociations.
     * @throws IllegalArgumentException If this object is not contained in the
     *                                  relationship's relatedObjects, if
     *                                  relationship is of type
     *                                  IfcRelAssociatesMaterial
     *                                  and hasAssociations already contains an
     *                                  instance of that class.
     * @throws NullPointerException     If relationship is null.
     */
    @Override
    protected void addToHasAssociations(@NonNull IfcRelAssociates relationship) {
        if (relationship instanceof IfcRelAssociatesMaterial) {
            for (IfcRelAssociates rel : this.getHasAssociations()) {
                if (rel instanceof IfcRelAssociatesMaterial) {
                    throw new IllegalArgumentException(
                            "hasAssociations must contain a maximum of 1 " +
                                    "IfcRelAssociatesMaterial, and it already" +
                                    " contains one");
                }
            }
        }
        super.addToHasAssociations(relationship);
    }
}
