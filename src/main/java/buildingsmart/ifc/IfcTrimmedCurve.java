/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
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

package buildingsmart.ifc;

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * A trimmed curve is a bounded curve which is created by taking a selected
 * portion, between two identified points, of the associated basis curve. The
 * basis curve itself is unaltered and more than one trimmed curve may reference
 * the same basis curve. Trimming points for the curve may be identified
 * by:</p>
 * <ul> <li> parametric value </li> <li>geometric
 * position </li> <li>both of the above </li>
 * </ul><p>At least one of these shall be specified at each
 * end of the
 * curve. The <i>SenseAgreement</i> makes it possible to
 * unambiguously define any segment of a closed curve such as a circle.
 * The combinations of sense and ordered end points make it possible to
 * define four distinct directed segments connecting two different points
 * on a circle or other closed curve. For this purpose cyclic properties
 * of the parameter range are assumed; for example, 370 degrees is
 * equivalent to 10 degrees. </p>
 * <p>The <i>IfcTrimmedCurve</i> has a parameterization
 * which is inherited from the particular basis curve reference. More
 * precisely the parameter s of the trimmed curve is derived from the
 * parameter of the basis curve as follows: </p>
 * <ul> <li>if <i>SenseAgreement</i> is TRUE: <i>s
 * = t - t<sub>1</sub></i></li> <li> if <i>SenseAgreement</i>
 * is FALSE: <i>s
 * = t<sub>2</sub> - t</i> </li>
 * </ul><p>In the above equations t<sub>1</sub> is
 * the value
 * given by <i>Trim1</i> or the parameter value corresponding
 * to point 1 and t<sub>2</sub> is the value given by <i>Trim2</i>
 * or the parameter value corresponding to point 2. The resultant
 * <i>IfcTrimmedCurve</i>
 * has a parameter ranging from 0 at the first trimming point to |t<sub>2</sub>
 * - t<sub>1</sub>| at the second trimming point. </p>
 * <blockquote> <p><font size="-1">NOTE In case
 * of a closed curve,
 * it may be necessary to increment t1 or t2 by the parametric length for
 * consistency with the sense flag.</font></p>
 * </blockquote><blockquote> <p><font color="#0000ff"
 *  size="-1">NOTE
 * Corresponding STEP entity: trimmed_curve; As a further IFC restriction,
 * an <i>IfcTrimmedCurve</i> should only trim a <i>IfcLine</i>
 * or <i>IfcConic</i>. Please refer to ISO/IS 10303-42:1994,
 * p. 54 for the final definition of the formal standard. </font></p>
 * </blockquote><p> <u>Informal Propositions</u>:</p>
 * <ol> <li>Where both the parameter value and the Cartesian
 * point
 * exist for <i>Trim1</i> and <i>Trim2</i> they
 * shall be consistent. (i.e., the <i>BasisCurve</i>
 * evaluated at the parameter value shall coincide with the specified
 * point).</li> <li>When a Cartesian point is specified by <i>Trim1</i>
 * or by <i>Trim2</i> it shall lie on the <i>BasisCurve</i>.</li>
 * <li>Except the case of a closed <i>BasisCurve</i>
 * where both parameter 1 and parameter 2 exist, they shall be consistent
 * with the sense flag, i.e., (sense = parameter 1 &lt; parameter 2). <font
 *  color="#0000ff">Or, for every open curve where both
 * parameter 1 and parameter 2 exist, they shall be consistent with the
 * <i>SenseAgreement</i>,
 * i.e., <i>SenseAgreement</i> = (parameter 1 &lt;
 * parameter 2).</font></li> <li>If both parameter 1
 * and parameter 2 exist, then parameter 1
 * &lt;&gt; parameter 2. <font color="#0000ff">For a
 * closed base curve, e.g. <i>IfcCircle</i> or <i>IfcEllipse</i>,
 * this also applies to the cyclic properties, as 360' is equal to 0',
 * parameter 1 = 360' and parameter 2 = 0' are treated as being equal and
 * therefore violating this proposition.</font></li> <li>When
 * a parameter value is specified by <i>Trim1</i>
 * or <i>Trim2</i> it shall lie within the parametric range
 * of the <i>BasisCurve</i>.</li>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcTrimmedCurve extends IfcBoundedCurve {
    @Attribute(0)
    private final IfcCurve basisCurve;
    @Attribute(1)
    private final Set<IfcTrimmingSelect> trim1;
    @Attribute(2)
    private final Set<IfcTrimmingSelect> trim2;
    @Attribute(3)
    private final IfcBoolean senseAgreement;
    @Attribute(4)
    private final IfcTrimmingPreference masterRepresentation;

    /**
     * @param basisCurve           The curve to be trimmed. For curves with
     *                             multiple representations any parameter values
     *                             given as Trim1 or Trim2 refer to the master
     *                             representation of the BasisCurve only.
     * @param trim1                The first trimming point which may be
     *                             specified as a Cartesian point, as a real
     *                             parameter or both.
     * @param trim2                The second trimming point which may be
     *                             specified as a Cartesian point, as a real
     *                             parameter or both.
     * @param senseAgreement       Flag to indicate whether the direction of the
     *                             trimmed curve agrees with or is opposed to
     *                             the direction of the basis curve.
     * @param masterRepresentation Where both parameter and point are present at
     *                             either end of the curve this indicates the
     *                             preferred form.
     * @throws NullPointerException     If any of the parameters are null.
     * @throws IllegalArgumentException If the size of trim1 or trim2 is not 1
     *                                  nor 2; if two values are specified for
     *                                  trim1 (or trim2), but they are of the
     *                                  same type; if basisCurve is of type
     *                                  IfcBoundedCurve.
     */
    public IfcTrimmedCurve(@NonNull IfcCurve basisCurve,
                           @NonNull Set<IfcTrimmingSelect> trim1,
                           @NonNull Set<IfcTrimmingSelect> trim2,
                           @NonNull IfcBoolean senseAgreement,
                           @NonNull IfcTrimmingPreference masterRepresentation) {
        if ((trim1.size() != 1 && trim1.size() != 2) ||
                (trim2.size() != 1 && trim2.size() != 2)) {
            throw new IllegalArgumentException(
                    "size of trim1 and trim2 must be either 1 or 2");
        }
        if (trim1.size() != 1) {
            Iterator<IfcTrimmingSelect> iterator = trim1.iterator();
            Class<?> type1 = iterator.next().getClass();
            Class<?> type2 = iterator.next().getClass();
            if (type1 == type2) {
                throw new IllegalArgumentException(
                        "either a single value is specified for trim1, or the" +
                                " two trimming values must be of different " +
                                "type (point and parameter)");
            }
        }
        if (trim2.size() != 1) {
            Iterator<IfcTrimmingSelect> iterator = trim2.iterator();
            Class<?> type1 = iterator.next().getClass();
            Class<?> type2 = iterator.next().getClass();
            if (type1 == type2) {
                throw new IllegalArgumentException(
                        "either a single value is specified for trim2, or the" +
                                " two trimming values must be of different " +
                                "type (point and parameter)");
            }
        }
        if (basisCurve instanceof IfcBoundedCurve) {
            throw new IllegalArgumentException(
                    "basisCurve cannot be of type IfcBoundedCurve, only line " +
                            "and conic curves can be trimmed");
        }
        this.basisCurve = basisCurve;
        this.trim1 = Collections.unmodifiableSet(trim1);
        this.trim2 = Collections.unmodifiableSet(trim2);
        this.senseAgreement = senseAgreement;
        this.masterRepresentation = masterRepresentation;
    }

    /**
     * @return The space dimensionality of this IfcCurve.
     */
    @Override
    public IfcDimensionCount getDim() {
        return basisCurve.getDim();
    }
}
