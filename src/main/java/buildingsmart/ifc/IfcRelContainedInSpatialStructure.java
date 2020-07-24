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
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This objectified relationship,
 * <i>IfcRelContainedInSpatialStructure</i>, is used to assign
 * elements to a certain level of the spatial project certain level of the
 * spatial structure. The question, which level is relevant for which type of
 * element, can only be answered within the context of a particular project and
 * might vary within the various regions.
 * </p>
 * <blockquote>
 * <p>
 * <small>EXAMPLE A multi-storey space is contained (or
 * belongs to) the building storey at which its ground level is, but it is
 * referenced by all the other building storeys, in which it spans. A lift shaft
 * might be contained by the basement, but referenced by all storeys, through
 * which it spans.</small>
 * </p>
 * </blockquote>
 * <p>
 * The containment relationship of an element within a spatial structure has to
 * be a hierarchical relationship, an element can only be contained within a
 * single spatial structure element. The reference relationship between an
 * element and the spatial structure may not be hierarchical, i.e. an element
 * can reference many spatial structure elements.
 * </p>
 * <blockquote>
 * <p>
 * <small>NOTE The reference relationship is expressed by
 * <i>IfcRelReferencedInSpatialStructure</i>.</small>
 * </p>
 * </blockquote>
 * <p>
 * Predefined spatial structure elements to which elements can be assigned are
 * </p>
 * <ul>
 *   <li>site as <i>IfcSite</i>
 *   </li>
 *   <li>building as <i>IfcBuilding</i>
 *   </li>
 *   <li>storey as <i>IfcBuildingStorey</i>
 *   </li>
 *   <li>space as <i>IfcSpace</i>
 *   </li>
 * </ul>
 * <p>
 *   Occurrences of the same element type can be assigned to
 *   different spatial structure elements depending on the
 *   context of the occurrence.
 * </p>
 * <blockquote>
 *   <p>
 *     <font size="-1">EXAMPLE A wall might be normally assigned
 *     to a storey, however the curtain wall might be assigned
 *     to the building and the retaining wall in the terrain
 *     might be assigned to the site.</font>
 *   </p>
 * </blockquote>
 */
public class IfcRelContainedInSpatialStructure extends IfcRelConnects {
    @Attribute(4)
    private final Set<IfcProduct> relatedElements;
    @Attribute(5)
    private final IfcSpatialStructureElement relatingStructure;

    /**
     * Creates a new IfcRelContainedInSpatialStructure, using the provided
     * globalId.
     *
     * @param globalId          Assignment of a globally unique identifier
     *                          within the entire software world.
     * @param ownerHistory      Assignment of the information about the current
     *                          ownership of that object, including owning
     *                          actor, application, local identification and
     *                          information captured about the recent changes of
     *                          the object, NOTE: only the last modification in
     *                          stored.
     * @param name              Optional name for use by the participating
     *                          software systems or users. For some subtypes of
     *                          IfcRoot the insertion of the Name attribute may
     *                          be required. This would be enforced by a where
     *                          rule.
     * @param description       Optional description, provided for exchanging
     *                          informative comments.
     * @param relatedElements   Set of products, which are contained within this
     *                          level of the spatial structure hierarchy.
     * @param relatingStructure Spatial structure element, within which the
     *                          element is contained. Any element can only be
     *                          contained within one element of the project
     *                          spatial structure.
     * @throws IllegalArgumentException If globalId, ownerHistory,
     * relatedElements
     *                                  or relatingStructure are null; if
     *                                  globalId was used in another instance of
     *                                  this class or its superclass; if the
     *                                  size of relatedElements is lower than 1;
     *                                  if relatedElements contains objects of
     *                                  type IfcSpatialStructureElement.
     */
    public IfcRelContainedInSpatialStructure(
            @NonNull IfcGloballyUniqueId globalId,
            @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
            IfcText description, @NonNull Set<IfcProduct> relatedElements,
            @NonNull IfcSpatialStructureElement relatingStructure) {
        super(globalId, ownerHistory, name, description);
        if (relatedElements == null) {
            throw new IllegalArgumentException(
                    "relatedElements cannot be null");
        }
        if (relatingStructure == null) {
            throw new IllegalArgumentException(
                    "relatingStructure cannot be null");
        }
        if (relatedElements.size() < 1) {
            throw new IllegalArgumentException(
                    "size of relatedElements must be at least 1");
        }
        for (IfcProduct prod : relatedElements) {
            if (prod instanceof IfcSpatialStructureElement) {
                throw new IllegalArgumentException(
                        "relatedElements cannot contain objects of type " +
                                "IfcSpatialStructureElement, use " +
                                "IfcRelAggregates for that");
            }
        }
        this.relatedElements = relatedElements;
        this.relatingStructure = relatingStructure;
        relatingStructure.addToContainsElements(this);
    }

    /**
     * Creates a new IfcRelContainedInSpatialStructure and generates a pseudo
     * random globalId.
     *
     * @param ownerHistory      Assignment of the information about the current
     *                          ownership of that object, including owning
     *                          actor, application, local identification and
     *                          information captured about the recent changes of
     *                          the object, NOTE: only the last modification in
     *                          stored.
     * @param name              Optional name for use by the participating
     *                          software systems or users. For some subtypes of
     *                          IfcRoot the insertion of the Name attribute may
     *                          be required. This would be enforced by a where
     *                          rule.
     * @param description       Optional description, provided for exchanging
     *                          informative comments.
     * @param relatedElements   Set of products, which are contained within this
     *                          level of the spatial structure hierarchy.
     * @param relatingStructure Spatial structure element, within which the
     *                          element is contained. Any element can only be
     *                          contained within one element of the project
     *                          spatial structure.
     * @throws IllegalArgumentException If ownerHistory, relatedElements or
     *                                  relatingStructure is null; if the size
     *                                  of relatedElements is lower than 1; if
     *                                  relatedElements contains objects of type
     *                                  IfcSpatialStructureElement.
     */
    public IfcRelContainedInSpatialStructure(
            @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
            IfcText description, @NonNull Set<IfcProduct> relatedElements,
            @NonNull IfcSpatialStructureElement relatingStructure) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                relatedElements, relatingStructure);
    }

    /**
     * Creates a new IfcRelContainedInSpatialStructure, using the provided
     * globalId.
     *
     * @param globalId          Assignment of a globally unique identifier
     *                          within the entire software world.
     * @param ownerHistory      Assignment of the information about the current
     *                          ownership of that object, including owning
     *                          actor, application, local identification and
     *                          information captured about the recent changes of
     *                          the object, NOTE: only the last modification in
     *                          stored.
     * @param name              Optional name for use by the participating
     *                          software systems or users. For some subtypes of
     *                          IfcRoot the insertion of the Name attribute may
     *                          be required. This would be enforced by a where
     *                          rule.
     * @param description       Optional description, provided for exchanging
     *                          informative comments.
     * @param relatingStructure Spatial structure element, within which the
     *                          element is contained. Any element can only be
     *                          contained within one element of the project
     *                          spatial structure.
     * @param relatedElements   Set of products, which are contained within this
     *                          level of the spatial structure hierarchy.
     * @throws IllegalArgumentException If globalId, ownerHistory,
     * relatedElements
     *                                  or relatingStructure are null; if
     *                                  globalId was used in another instance of
     *                                  this class or its superclass; if the
     *                                  size of relatedElements is lower than 1;
     *                                  if relatedElements contains objects of
     *                                  type IfcSpatialStructureElement.
     */
    public IfcRelContainedInSpatialStructure(
            @NonNull IfcGloballyUniqueId globalId,
            @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
            IfcText description,
            @NonNull IfcSpatialStructureElement relatingStructure,
            @NonNull IfcProduct... relatedElements) {
        this(globalId, ownerHistory, name, description,
                new HashSet<>(Arrays.asList(relatedElements)),
                relatingStructure);
    }

    /**
     * Creates a new IfcRelContainedInSpatialStructure and generates a pseudo
     * random globalId.
     *
     * @param ownerHistory      Assignment of the information about the current
     *                          ownership of that object, including owning
     *                          actor, application, local identification and
     *                          information captured about the recent changes of
     *                          the object, NOTE: only the last modification in
     *                          stored.
     * @param name              Optional name for use by the participating
     *                          software systems or users. For some subtypes of
     *                          IfcRoot the insertion of the Name attribute may
     *                          be required. This would be enforced by a where
     *                          rule.
     * @param description       Optional description, provided for exchanging
     *                          informative comments.
     * @param relatingStructure Spatial structure element, within which the
     *                          element is contained. Any element can only be
     *                          contained within one element of the project
     *                          spatial structure.
     * @param relatedElements   Set of products, which are contained within this
     *                          level of the spatial structure hierarchy.
     * @throws IllegalArgumentException If ownerHistory, relatedElements or
     *                                  relatingStructure is null; if the size
     *                                  of relatedElements is lower than 1; if
     *                                  relatedElements contains objects of type
     *                                  IfcSpatialStructureElement.
     */
    public IfcRelContainedInSpatialStructure(
            @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
            IfcText description,
            @NonNull IfcSpatialStructureElement relatingStructure,
            @NonNull IfcProduct... relatedElements) {
        this(ownerHistory, name, description,
                new HashSet<>(Arrays.asList(relatedElements)),
                relatingStructure);
    }

    /**
     * @return A copy of relatedElements. Operations performed on this Set don't
     * have any effect on relatedElements. This is done to prevent its size from
     * becoming zero, and to prevent the addition of illegal IfcProducts to
     * relatedElements.
     */
    public Set<IfcProduct> getRelatedElements() {
        return new HashSet<>(relatedElements);
    }

    protected IfcSpatialStructureElement getRelatingStructure() {
        return relatingStructure;
    }

    public static final class Builder {
        private Set<IfcProduct> relatedElements;
        private IfcSpatialStructureElement relatingStructure;
        private IfcGloballyUniqueId globalId;
        private IfcOwnerHistory ownerHistory;
        private IfcLabel name;
        private IfcText description;

        private Builder() {
        }

        public static Builder anIfcRelContainedInSpatialStructure() {
            return new Builder();
        }

        public Builder relatedElements(Set<IfcProduct> relatedElements) {
            this.relatedElements = relatedElements;
            return this;
        }

        public Builder relatedElements(IfcProduct... relatedElements) {
            this.relatedElements =
                    new HashSet<>(Arrays.asList(relatedElements));
            return this;
        }

        public Builder relatingStructure(
                IfcSpatialStructureElement relatingStructure) {
            this.relatingStructure = relatingStructure;
            return this;
        }

        public Builder globalId(IfcGloballyUniqueId globalId) {
            this.globalId = globalId;
            return this;
        }

        public Builder ownerHistory(IfcOwnerHistory ownerHistory) {
            this.ownerHistory = ownerHistory;
            return this;
        }

        public Builder name(IfcLabel name) {
            this.name = name;
            return this;
        }

        public Builder description(IfcText description) {
            this.description = description;
            return this;
        }

        public IfcRelContainedInSpatialStructure build() {
            return new IfcRelContainedInSpatialStructure(globalId, ownerHistory,
                    name, description, relatedElements, relatingStructure);
        }
    }
}
