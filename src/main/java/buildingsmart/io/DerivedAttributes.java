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

import java.lang.annotation.*;

/**
 * To be used on subclasses to have attributes from superclasses ignored in the
 * serialization. Indicates that certain {@link Attribute}s from a superclass
 * will have to be serialized as asterisks in the subclass, because they have
 * become derived attributes.
 * </p>
 * One example of an attribute becoming a derived attribute in a subclass can be
 * found in {@link buildingsmart.ifc.IfcNamedUnit}: in its subclass {@link
 * buildingsmart.ifc.IfcSIUnit}, {@code dimensions} is a derived attribute.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DerivedAttributes {
    String[] value();
}
