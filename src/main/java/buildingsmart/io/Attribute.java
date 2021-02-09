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

package buildingsmart.io;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a field that is an attribute of an IFC entity, meaning that it must
 * be included in the serialization of the entity. Fields having this annotation
 * cannot be annotated with {@link InverseRelationship}. {@code value()}
 * indicates the order in which the annotated fields should be serialized in an
 * IFC file (ascending). All fields annotated with {@link Attribute} must have a
 * different order between each other (including fields of superclasses). The
 * ordering must follow what is outlined in the IFC specification of each class
 * (look at the section "Inheritance graph" in the corresponding IFC entity).
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {
    int value();
}
