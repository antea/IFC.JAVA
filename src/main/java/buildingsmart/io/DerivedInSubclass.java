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
 * Indicates a field annotated with {@link Attribute} that will have to be
 * serialized as an asterisk in the specified subclass, because it's a derived
 * attribute in that subclass.
 * </p>
 * One example of an attribute becoming a derived attribute in a subclass can be
 * found in {@link buildingsmart.ifc.IfcNamedUnit}: in its subclass {@link
 * buildingsmart.ifc.IfcSIUnit}, dimensions is a derived attribute.
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface DerivedInSubclass {
    Class<?> value();
}
