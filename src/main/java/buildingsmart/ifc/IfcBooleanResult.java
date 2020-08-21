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

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * A Boolean result is the result of a regularized operation on two solids to
 * create a new solid. Valid operations are regularized union, regularized
 * intersection, and regularized difference. For purpose of Boolean operations,
 * a solid is considered to be a regularized set of points. The final Boolean
 * result depends upon the operation and the two operands. In the case of the
 * difference operator the order of the operands is also significant. The
 * operator can be either union, intersection or difference. The effect of these
 * operators is described below: </P>
 * <UL>
 * <LI>Union on two solids is the new solid that is the regularization of
 * the set of all points that are in either the first operand or the second
 * operand or in both. </LI>
 * <LI>Intersection on two solids is the new solid that is the
 * regularization of the set of all points that are in both the first operand
 * and the second operand. </LI>
 * <LI>The result of the difference operation on two solids is the
 * regularization of the set of all points which are in the first operand, but
 * not in the second operand. </LI>
 * </UL>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcBooleanResult extends IfcGeometricRepresentationItem
        implements IfcCsgSelect, IfcBooleanOperand {
    @Attribute(0)
    private final IfcBooleanOperator operator;
    @Attribute(1)
    private final IfcBooleanOperand firstOperand;
    @Attribute(2)
    private final IfcBooleanOperand secondOperand;

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
    public IfcBooleanResult(@NonNull IfcBooleanOperator operator,
                            @NonNull IfcBooleanOperand firstOperand,
                            @NonNull IfcBooleanOperand secondOperand) {
        if (!firstOperand.getDim().equals(secondOperand.getDim())) {
            throw new IllegalArgumentException(
                    "dimensionality of the two operands must be the same");
        }
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public IfcDimensionCount getDim() {
        return firstOperand.getDim();
    }
}
