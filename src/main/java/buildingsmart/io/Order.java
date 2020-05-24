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
 * Indicates the order in which a field with annotation Attribute or
 * InverseAttribute should be serialized in an IFC file. When applying this
 * annotation on fields in a class, you should continue counting from the
 * highest value in the class' superclass. Attributes and InverseAttributes use
 * two different orders, so both the first Attribute and the first
 * InverseAttribute in a class will have value == 0.
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    int value();
}

