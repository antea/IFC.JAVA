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
 * One of the types defined in the IFC specification, which can be serialized
 * without referencing other objects because types are primitives that cannot
 * contain IFC entities. This interface is not part of the IFC specification,
 * its only purpose is to distinguish IFC defined types and enumerations from
 * IFC entities.
 */
public interface IfcDefinedType {
    /**
     * @return The representation of the type in an IFC STEP file.
     */
    String serialize();
}