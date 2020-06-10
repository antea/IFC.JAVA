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
 * One of the entities defined in the IFC specification. This interface is not
 * part of the IFC specification, its only purpose is to distinguish IFC
 * entities from IFC defined types and enumerations.
 * </p>
 * Subclasses of this class must have fields of type {@link IfcEntity} (or
 * Collections thereof) annotated with either {@link Attribute} or {@link
 * InverseAttribute}, in the first case they must also be annotated with {@link
 * Order}. Fields of type {@link IfcDefinedType} must be annotated with {@link
 * Attribute} and {@link Order}.
 */
public abstract class IfcEntity {

    /**
     * Must not include fields annotated with {@link InverseAttribute} in the
     * comparison, or you might get infinite recursion when comparing objects.
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Must not include fields annotated with {@link InverseAttribute} in the
     * computation of the hashCode, or you might get infinite recursion when
     * computing hashCodes.
     */
    @Override
    public abstract int hashCode();
}
