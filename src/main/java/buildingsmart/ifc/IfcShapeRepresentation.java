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

import buildingsmart.util.Functions;
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The <i>IfcShapeRepresentation</i> represents the concept of a particular
 * geometric representation of a product or a product component within a
 * specific geometric representation context. The inherited attribute
 * <i>RepresentationType</i> is used to define the geometric model used for the
 * shape representation, the inherited attribute <i>RepresentationIdentifier</i>
 * is used to denote the part of the representation captured by the
 * <i>IfcShapeRepresentation</i>
 * (e.g. Axis, Body, etc.). <br>
 * </p><p>Several representation types for shape
 * representation are included as predefined types: </p>
 * <table cellpadding="2" cellspacing="2"> <tbody><tr>
 * <td colspan="2" align="left" valign="top"
 *  width="20"><b>Curve2D</b></td> <td
 *  align="left" valign="top">2
 * dimensional curves </td> </tr> <tr> <td
 *  colspan="2" align="left" valign="top" width="20"><b>GeometricSet
 * </b></td> <td align="left" valign="top">points,
 * curves, surfaces (2 or 3 dimensional)</td> </tr> <tr>
 * <td align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="20"><b>GeometricCurveSet</b></td>
 * <td align="left" valign="top">points,
 * curves (2 or 3 dimensional)</td> </tr> <tr> <td></td>
 * <td><b>Annotation2D</b></td> <td>points,
 * curves (2 or 3 dimensional), hatches and text (2 dimensional) </td>
 * </tr> <tr> <td colspan="2" align="left"
 *  valign="top" width="20"><b>SurfaceModel </b></td>
 * <td align="left" valign="top">face
 * based and shell based surface model</td> </tr> <tr>
 * <td colspan="2" align="left" valign="top"
 *  width="20"><b>SolidModel </b></td> <td
 *  align="left" valign="top">including
 * swept solid, Boolean results and Brep bodies<br>
 * more specific types are:</td> </tr> <tr> <td
 *  align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>SweptSolid</b></td>
 * <td align="left" valign="top">swept
 * area solids, by extrusion and revolution</td> </tr> <tr>
 * <td align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>Brep</b></td>
 * <td align="left" valign="top">faceted
 * Brep's with and without voids</td> </tr> <tr> <td
 *  align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>CSG</b></td>
 * <td align="left" valign="top">Boolean
 * results of operations between solid models, half spaces and Boolean
 * results</td> </tr> <tr> <td align="left"
 *  valign="top" width="20">&nbsp;</td> <td
 *  align="left" valign="top" width="130"><b>Clipping</b></td>
 * <td align="left" valign="top">Boolean
 * differences between swept area solids, half spaces and Boolean results</td>
 * </tr> <tr> <td align="left" valign="top"
 *  width="20">&nbsp;</td> <td align="left"
 *  valign="top" width="130"><b>AdvancedSweptSolid</b></td>
 * <td align="left" valign="top">swept
 * area solids created by sweeping a profile along a directrix</td> </tr>
 * <tr> <td colspan="2" align="left" valign="top"
 *  width="20"><i>additional
 * types</i> </td> <td align="left" valign="top">some
 * additional representation types are given:</td> </tr> <tr>
 * <td align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>BoundingBox</b></td>
 * <td align="left" valign="top">simplistic
 * 3D representation by a bounding box</td> </tr> <tr>
 * <td align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>SectionedSpine</b></td>
 * <td align="left" valign="top">cross
 * section based representation of a spine curve and planar cross
 * sections. It can represent a surface or a solid and the interpolations
 * of the between the cross sections is not defined</td> </tr>
 * <tr> <td align="left" valign="top" width="20">&nbsp;</td>
 * <td align="left" valign="top" width="130"><b>MappedRepresentation</b></td>
 * <td align="left" valign="top">representation
 * based on mapped item(s), referring to a representation map. Note: it
 * can be seen as an inserted block reference. The shape representation of the
 * mapped item has a representation type declaring the type of its
 * representation items.</td> </tr> </tbody>
 */
public class IfcShapeRepresentation extends IfcShapeModel {
    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws IllegalArgumentException If contextOfItems, representationType or
     *                                  items are null; if the size of items is
     *                                  lower than 1; if contextOfItems is not
     *                                  of type
     *                                  IfcGeometricRepresentationContext;
     *                                  if items contains objects of type
     *                                  IfcTopologicalRepresentationItem (except
     *                                  for IfcVertexPoint, IfcEdgeCurve,
     *                                  IfcFaceSurface); if representationType;
     *                                  if representationType is unknown (not
     *                                  present in the specification of this
     *                                  class); if items contains objects of the
     *                                  wrong type depending on the given
     *                                  representationType (see the
     *                                  specification of this class).
     */
    public IfcShapeRepresentation(
            @NonNull IfcRepresentationContext contextOfItems,
            IfcLabel representationIdentifier,
            @NonNull IfcLabel representationType,
            @NonNull Set<IfcRepresentationItem> items) {
        super(contextOfItems, representationIdentifier, representationType,
                items);
        if (!(contextOfItems instanceof IfcGeometricRepresentationContext)) {
            throw new IllegalArgumentException(
                    "contextOfItems must be of type " +
                            "IfcGeometricRepresentationContext");
        }
        for (IfcRepresentationItem item : items) {
            if (item instanceof IfcTopologicalRepresentationItem &&
                    !(item instanceof IfcVertexPoint ||
                            item instanceof IfcEdgeCurve ||
                            item instanceof IfcFaceSurface)) {
                throw new IllegalArgumentException(
                        "items cannot contain objects of type " +
                                "IfcTopologicalRepresentationItem, with the " +
                                "exception of IfcVertexPoint, IfcEdgeCurve, " +
                                "IfcFaceSurface.");
            }
        }
        if (representationType == null) {
            throw new IllegalArgumentException(
                    "representationType cannot be " + "null");
        }
        if (!Objects.equals(Functions
                        .ifcShapeRepresentationTypes(representationType, items),
                true)) {
            throw new IllegalArgumentException(
                    "either items contains objects of the" +
                            " wrong type (according to the given " +
                            "representationType) " +
                            "or representationType is unknown");
        }
    }

    /**
     * @param contextOfItems           Definition of the representation context
     *                                 for which the different subtypes of
     *                                 representation are valid.
     * @param representationIdentifier The optional identifier of the
     *                                 representation as used within a project.
     * @param representationType       The description of the type of a
     *                                 representation context. The
     *                                 representation
     *                                 type defines the type of geometry or
     *                                 topology used for representing the
     *                                 product representation. More information
     *                                 is given at the subtypes
     *                                 IfcShapeRepresentation
     *                                 and IfcTopologyRepresentation. The
     *                                 supported values for context type are to
     *                                 be specified by implementers agreements.
     * @param items                    Set of geometric representation items
     *                                 that are defined for this representation.
     * @throws IllegalArgumentException If contextOfItems, representationType or
     *                                  items are null; if the size of items is
     *                                  lower than 1; if contextOfItems is not
     *                                  of type
     *                                  IfcGeometricRepresentationContext;
     *                                  if items contains objects of type
     *                                  IfcTopologicalRepresentationItem (except
     *                                  for IfcVertexPoint, IfcEdgeCurve,
     *                                  IfcFaceSurface); if representationType;
     *                                  if representationType is unknown (not
     *                                  present in the specification of this
     *                                  class); if items contains objects of the
     *                                  wrong type depending on the given
     *                                  representationType (see the
     *                                  specification of this class).
     */
    public IfcShapeRepresentation(
            @NonNull IfcRepresentationContext contextOfItems,
            IfcLabel representationIdentifier,
            @NonNull IfcLabel representationType,
            @NonNull IfcRepresentationItem... items) {
        this(contextOfItems, representationIdentifier, representationType,
                new HashSet<>(Arrays.asList(items)));
    }
}
