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

/**
 * One of the Defined Types or Enumerations defined in the IFC specification.
 * This interface is not part of the IFC specification, its only purpose is to
 * distinguish IFC Defined Types and Enumerations from Entities.
 */
public interface DefinedType {
    /**
     * @return The representation of the Defined Type in an IFC STEP file.
     */
    String serialize();
}
