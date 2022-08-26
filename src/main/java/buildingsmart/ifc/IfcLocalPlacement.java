/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2022 Antea S.r.l.
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
import buildingsmart.util.Functions;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * The <i>IfcLocalPlacement</i> defines the relative placement of a product in relation to the placement of another
 * product or the absolute placement of a product within the geometric representation context of the project. </p>
 * <p>The <i>IfcLocalPlacement</i> allows that an <i>IfcProduct</i>
 * can be placed by this <i>IfcLocalPlacement</i> (through the attribute<i>ObjectPlacement</i>) within the local
 * coordinate system of the object placement of another <i>IfcProduct</i>, which is referenced by the
 * <i>PlacementRelTo</i>. Rules to prevent cyclic relative placements have to
 * be introduced on the application level.</p>
 * <p>If the <i>PlacementRelTo</i> is not given, then
 * the <i>IfcProduct</i> is placed absolutely within the world coordinate system.</p>
 */
@Slf4j
@ToString
public class IfcLocalPlacement extends IfcObjectPlacement {

    private static final IfcAxis2Placement3D DEFAULT_A2P3D = new IfcAxis2Placement3D(0, 0, 0);
    private static final IfcAxis2Placement2D DEFAULT_A2P2D = new IfcAxis2Placement2D(0, 0);

    @Getter
    @Attribute(0)
    private final IfcObjectPlacement placementRelTo;
    @Getter
    @Attribute(1)
    private final IfcAxis2Placement relativePlacement;
    /**
     * This field is needed to avoid recursion in {@link #equals(Object)}, as comparing hashCodes of {@code
     * placementRelTo} could lead to wrong results because of collisions.
     */
    private final byte[] md5;

    /**
     * @param placementRelTo    Reference to Object that provides the relative placement by its local coordinate system.
     *                          If it is omitted, then the local placement is given to the WCS, established by the
     *                          project's geometric representation context.
     * @param relativePlacement Geometric placement that defines the transformation from the related coordinate system
     *                          into the relating. The placement can be either 2D or 3D, depending on the dimension
     *                          count of the coordinate system.
     * @throws NullPointerException     If relativePlacement is null.
     * @throws IllegalArgumentException If relativePlacement is 3D and placementRelTo is not and is not null; if
     *                                  placementRelTo is of type IfcGridPlacement.
     */
    public IfcLocalPlacement(IfcObjectPlacement placementRelTo, @NonNull IfcAxis2Placement relativePlacement) {
        if (!Boolean.TRUE.equals(Functions.ifcCorrectLocalPlacement(relativePlacement, placementRelTo))) {
            throw new IllegalArgumentException("if relativePlacement is 3D, so must be placementRelTo; " +
                                                       "placementRelTo cannot be an IfcGridPlacement");
        }
        this.placementRelTo = placementRelTo;
        this.relativePlacement = relativePlacement;
        if (placementRelTo != null &&
                (relativePlacement.equals(DEFAULT_A2P3D) || relativePlacement.equals(DEFAULT_A2P2D))) {
            this.md5 = placementRelTo.getMd5();
        } else {
            this.md5 = computeMD5(placementRelTo, relativePlacement);
        }
    }

    private static byte[] computeMD5(IfcObjectPlacement placementRelTo, IfcAxis2Placement relativePlacement) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            MessageDigest md = MessageDigest.getInstance("MD5");

            // adds placementRelTo
            if (placementRelTo != null) {
                md.update(placementRelTo.getMd5());
            }

            // skips adding relativePlacement if it's the default, as in that case the placement wouldn't be different
            // from that of placementRelTo
            if (!relativePlacement.equals(DEFAULT_A2P3D) && !relativePlacement.equals(DEFAULT_A2P2D)) {
                oos.writeObject(relativePlacement.getLocation());
                oos.writeObject(relativePlacement.getP());
                md.update(baos.toByteArray());
            }

            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            // MD5 must be implemented by the Java Security API so the NoSuchAlgorithmException should not be possible,
            // see https://docs.oracle.com/javase/1.5.0/docs/guide/security/CryptoSpec.html#AppA
            log.error("", e);

            // using hashCode instead of md5, not ideal but at least the program won't crash because of an
            // IOException. This could lead to wrong comparisons in equals() when comparing two identical instances
            // of this class if in one of them md5 was initialized in the try block. The only inconvenience resulting
            // from it should be that the same object will be serialized multiple times, but the generated IFC files
            // should be correct.
            int hashCode = Objects.hash(placementRelTo, relativePlacement);
            return new byte[]{(byte) (hashCode >>> 24),
                              (byte) (hashCode >>> 16),
                              (byte) (hashCode >>> 8),
                              (byte) hashCode};
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        IfcLocalPlacement that = (IfcLocalPlacement) o;

        return Arrays.equals(md5, that.md5) && relativePlacement.equals(that.relativePlacement) ||
                // if a "child" placement is in the same place as its parent, they're considered equal
                that.placementRelTo != null && Arrays.equals(md5, that.placementRelTo.getMd5()) &&
                        (that.relativePlacement.equals(DEFAULT_A2P3D) || that.relativePlacement.equals(DEFAULT_A2P2D)) ||
                placementRelTo != null && Arrays.equals(placementRelTo.getMd5(), that.md5) &&
                        (relativePlacement.equals(DEFAULT_A2P3D) || relativePlacement.equals(DEFAULT_A2P2D));
    }

    /**
     * @return The hashCode generated by using the last 4 bytes of the md5.
     */
    @Override
    public int hashCode() {
        byte b0 = md5[md5.length - 4];
        byte b1 = md5[md5.length - 3];
        byte b2 = md5[md5.length - 2];
        byte b3 = md5[md5.length - 1];

        int result = ((b0 & 0xFF) << 24) | ((b1 & 0xFF) << 16) | ((b2 & 0xFF) << 8) | (b3 & 0xFF);
        return result + 31 * super.hashCode();
    }

    /**
     * @return The MD5 digest of the serialization of this class.
     */
    @Override
    protected byte[] getMd5() {
        return md5;
    }
}
