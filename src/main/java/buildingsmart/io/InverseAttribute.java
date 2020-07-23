/*
 * Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.io;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a field that represent an inverse relationship (that should be
 * serialized after the entity) of an IFC entity. All fields having this
 * annotation cannot be annotated with {@link Attribute}.
 * </p>
 * Fields annotated with this Annotation are serialized after the entity they're
 * in, because inverse relationships are references to entities which contain a
 * reference to this entity in their regular attributes, so they can be
 * serialized only after this entity has been; and also because they're not
 * referenced directly in the tree of {@link Attribute}s starting from
 * IfcProject.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InverseAttribute {
}
