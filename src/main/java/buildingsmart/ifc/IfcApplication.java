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
import buildingsmart.io.IfcEntity;
import buildingsmart.util.Pair;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * IfcApplication holds the information about an IFC compliant application
 * developed by an application developer who is a member of the IAI. The
 * IfcApplication utilizes a short identifying name as provided by the
 * application developer.
 */
public class IfcApplication extends IfcEntity {
    private static final Set<Pair<IfcLabel, IfcLabel>>
            uniqueAppFullNameAndVersions = new HashSet<>();
    private static final Set<IfcIdentifier> uniqueAppIdentifiers =
            new HashSet<>();

    @Attribute(0)
    private final IfcOrganization applicationDeveloper;
    @Attribute(1)
    private final IfcLabel version;
    @Attribute(2)
    private final IfcLabel applicationFullName;
    @Attribute(3)
    private final IfcIdentifier applicationIdentifier;

    /**
     * @param applicationDeveloper  Name of the application developer, being
     *                              requested to be member of the IAI. Cannot be
     *                              null.
     * @param version               The version number of this software as
     *                              specified by the developer of the
     *                              application. Cannot be null.
     * @param applicationFullName   The full name of the application as
     *                              specified by the application developer.
     *                              Cannot be null.
     * @param applicationIdentifier Short identifying name for the application.
     *                              Cannot be null.
     * @throws IllegalArgumentException If any of the parameters is null, or if
     *                                  any instances of this class already
     *                                  exist where applicationIdentifier is the
     *                                  same as the one passed as parameter, or
     *                                  where the combination of fields
     *                                  applicationFullName and version is the
     *                                  same as the one passed as parameter.
     */
    public IfcApplication(@NonNull IfcOrganization applicationDeveloper,
                          @NonNull IfcLabel version,
                          @NonNull IfcLabel applicationFullName,
                          @NonNull IfcIdentifier applicationIdentifier) {
        if (applicationDeveloper == null || version == null ||
                applicationFullName == null || applicationIdentifier == null) {
            throw new IllegalArgumentException(
                    "parameters of this constructor cannot be null");
        }
        if (uniqueAppIdentifiers.contains(applicationIdentifier)) {
            throw new IllegalArgumentException(
                    "applicationIdentifier must be unique, and this one was " +
                            "already used in another instance of this class");
        }
        Pair<IfcLabel, IfcLabel> appFullNameAndVersion =
                new Pair<>(applicationFullName, version);
        if (uniqueAppFullNameAndVersions.contains(appFullNameAndVersion)) {
            throw new IllegalArgumentException(
                    "the combination of applicationFullName and version must " +
                            "be unique, and this one was already used in " +
                            "another instance of this class");
        }
        this.applicationDeveloper = applicationDeveloper;
        uniqueAppFullNameAndVersions.add(appFullNameAndVersion);
        this.version = version;
        this.applicationFullName = applicationFullName;
        uniqueAppIdentifiers.add(applicationIdentifier);
        this.applicationIdentifier = applicationIdentifier;
    }

    /**
     * Clears the Sets used to keep track of attributes that must be unique in
     * different instances of this class, thus allowing the creation of new
     * instances of this class having attributes marked as UNIQUE with the same
     * values as ones belonging to instances of this class created before
     * calling this method. Use at your own risk.
     */
    public static void clearUniqueConstraint() {
        uniqueAppFullNameAndVersions.clear();
        uniqueAppIdentifiers.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcApplication that = (IfcApplication) o;
        return applicationDeveloper.equals(that.applicationDeveloper) &&
                version.equals(that.version) &&
                applicationFullName.equals(that.applicationFullName) &&
                applicationIdentifier.equals(that.applicationIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationDeveloper, version, applicationFullName,
                applicationIdentifier);
    }
}
