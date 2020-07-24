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
import buildingsmart.io.IfcEntity;
import lombok.NonNull;

import java.util.Objects;

/**
 * The <i>IfcProfileDef</i> is the supertype of all definitions of standard and
 * arbitrary profiles within IFC. It is used to define a standard set of
 * commonly used profiles by their parameters or by their explicit curve
 * geometry. Those profile definitions are used within the geometry and
 * geometric model resource to create either swept surfaces, swept area solids,
 * or sectioned spines. <br>
 * </p>
 * <p>The purpose of the profile
 * definition within the swept surfaces or swept area solids is to define a
 * uniform cross section being swept:</p>
 * <ul>
 *   <li>along a line (extrusion)
 * using <i>IfcSurfaceOfLinearExtrusion</i>
 * or <i>IfcExtrudedAreaSolid</i></li>
 *   <li>along a circular arc
 * (revolution) using <i>IfcSurfaceOfRevolution</i>
 * or <i>IfcRevolvedAreaSolid</i></li>
 *   <li>along a directrix lying on a
 * reference surface using <i>IfcSurfaceCurveSweptAreaSolid</i></li>
 * </ul>
 * <p>The purpose fo the profile
 * definition within the sectioned spine is to define a varying cross
 * sections at several positions along a spine curve. The subtype
 * <i>IfcDerivedProfileDef</i>
 * is particularly suited to provide the consecutive profiles to be based
 * on transformations of the start profile and thus maintaining the
 * identity of vertices and edges. </p>
 * <blockquote> <font
 *  size="-1">NOTE: Subtypes of the <i>IfcProfileDef</i>
 * contain parameterized profiles (as subtypes of
 * <i>IfcParameterizedProfileDef</i>)
 * which establish their own 2D position coordinate system, profiles given
 * by explicit curve geometry (either open or closed profiles) and two
 * special types for composite profiles and derived profiles, based on a
 * 2D Cartesian transformation.</font>
 * </blockquote>
 * <ul>
 *   <li>Parameterized profiles are
 * 2D primitives, which are used within the industry to describe cross
 * sections by a description of its parameters. <br>
 *   </li>
 *   <li>Arbitrary profiles are cross
 * sections defined by an outer boundary as bounded curve, which may also
 * include holes, defined by inner boundaries. <br>
 *   </li>
 *   <li>Derived profiles, based on a
 * transformation of a parent profile, are also part of the profile
 * definitions available. <br>
 *   </li>
 *   <li>In addition composite
 * profiles can be defined, which include two or more profile definitions
 * to define the resulting profile.</li>
 * </ul>
 * <p>An <i>IfcProfileDef</i>
 * is treated as bounded area if it is used within swept area solids. In
 * this case, the inside of the profile is part of the profile. The
 * attribute <i>ProfileType</i>
 * is set to AREA. An <i>IfcProfileDef</i>
 * is treated as a curve if it is used within swept surfaces. In this
 * case, the inside of the profile (if the curve is closed) is not part of
 * the profile. The attribute <i>ProfileType</i>
 * is set to CURVE. The optional attribute <i>ProfileName</i>
 * can be used to designate a standard profile type as e.g. given in
 * profile tables for steel profiles.</p>
 */
public abstract class IfcProfileDef extends IfcEntity {
    @Attribute(order = 0)
    private final IfcProfileTypeEnum profileType;
    @Attribute(order = 1)
    private final IfcLabel profileName;

    /**
     * @param profileType Defines the type of geometry into which this profile
     *                    definition shall be resolved, either a curve or a
     *                    surface area. In case of curve the profile should be
     *                    referenced by a swept surface, in case of area the
     *                    profile should be referenced by a swept area solid.
     * @param profileName Name of the profile type according to some standard
     *                    profile table.
     * @throws IllegalArgumentException If profileType is null.
     */
    public IfcProfileDef(@NonNull IfcProfileTypeEnum profileType,
                         IfcLabel profileName) {
        if (profileType == null) {
            throw new IllegalArgumentException("profileType cannot be null");
        }
        this.profileType = profileType;
        this.profileName = profileName;
    }

    public IfcProfileTypeEnum getProfileType() {
        return profileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcProfileDef that = (IfcProfileDef) o;
        return profileType == that.profileType &&
                Objects.equals(profileName, that.profileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileType, profileName);
    }
}
