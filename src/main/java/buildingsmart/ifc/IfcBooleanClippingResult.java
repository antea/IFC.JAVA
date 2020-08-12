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

import lombok.NonNull;

public abstract class IfcBooleanClippingResult extends IfcBooleanResult {
    /**
     * @param operator      The Boolean operator used in the operation to create
     *                      the result.
     * @param firstOperand  The first operand to be operated upon by the Boolean
     *                      operation.
     * @param secondOperand The second operand specified for the operation.
     * @throws NullPointerException     If any of the arguments is null.
     * @throws IllegalArgumentException If the dimensionality of {@code
     *                                  firstOperand} is not the same as {@code
     *                                  secondOperand}.
     */
    public IfcBooleanClippingResult(@NonNull IfcBooleanOperator operator,
                                    @NonNull IfcBooleanOperand firstOperand,
                                    @NonNull IfcBooleanOperand secondOperand) {
        super(operator, firstOperand, secondOperand);
    }
}
