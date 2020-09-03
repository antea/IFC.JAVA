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

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * A clipping result is defined as a special subtype of the general Boolean
 * result (<I>IfcBooleanResult</I>). It constrains the operands and the operator
 * of the Boolean result.</P>
 * <P>A clipping result is the Boolean difference between a solid (restricted
 * to swept area solid) and a half space solid, whereas more than one difference
 * operation can be applied to the Boolean result.</P>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcBooleanClippingResult extends IfcBooleanResult {
    /**
     * @param operator      The Boolean operator used in the operation to create
     *                      the result.
     * @param firstOperand  The first operand to be operated upon by the Boolean
     *                      operation.
     * @param secondOperand The second operand specified for the operation.
     * @throws NullPointerException     If any of the arguments is null.
     * @throws IllegalArgumentException If the dimensionality of {@code
     *                                  firstOperand} is not the same as {@code
     *                                  secondOperand}; if {@code firstOperand}
     *                                  is not an instance of IfcSweptAreaSolid
     *                                  or IfcBooleanResult; if the {@code
     *                                  secondOperand} is not an instance of
     *                                  IfcHalfSpaceSolid; if {@code operator}
     *                                  is not DIFFERENCE.
     */
    public IfcBooleanClippingResult(@NonNull IfcBooleanOperator operator,
                                    @NonNull IfcBooleanOperand firstOperand,
                                    @NonNull IfcBooleanOperand secondOperand) {
        super(operator, firstOperand, secondOperand);
        if (!(firstOperand instanceof IfcSweptAreaSolid ||
                firstOperand instanceof IfcBooleanResult)) {
            throw new IllegalArgumentException(
                    "The first operand of the Boolean clipping operation " +
                            "shall be either an IfcSweptAreaSolid or (in case" +
                            " of more than one clipping) an IfcBooleanResult.");
        }
        if (!(secondOperand instanceof IfcHalfSpaceSolid)) {
            throw new IllegalArgumentException(
                    "The second operand of the Boolean clipping operation " +
                            "shall be an IfcHalfSpaceSolid.");
        }
        if (operator != IfcBooleanOperator.DIFFERENCE) {
            throw new IllegalArgumentException(
                    "The Boolean operator for clipping is always " +
                            "\"Difference\".");
        }

    }
}
