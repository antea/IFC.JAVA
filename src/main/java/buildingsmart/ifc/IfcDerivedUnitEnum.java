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

import buildingsmart.io.DefinedType;

/**
 * An enumeration type for allowed types of derived units.
 */
public enum IfcDerivedUnitEnum implements DefinedType {
    ANGULARVELOCITYUNIT,
    COMPOUNDPLANEANGLEUNIT,
    DYNAMICVISCOSITYUNIT,
    HEATFLUXDENSITYUNIT,
    INTEGERCOUNTRATEUNIT,
    ISOTHERMALMOISTURECAPACITYUNIT,
    KINEMATICVISCOSITYUNIT,
    LINEARVELOCITYUNIT,
    MASSDENSITYUNIT,
    MASSFLOWRATEUNIT,
    MOISTUREDIFFUSIVITYUNIT,
    MOLECULARWEIGHTUNIT,
    SPECIFICHEATCAPACITYUNIT,
    THERMALADMITTANCEUNIT,
    THERMALCONDUCTANCEUNIT,
    THERMALRESISTANCEUNIT,
    THERMALTRANSMITTANCEUNIT,
    VAPORPERMEABILITYUNIT,
    VOLUMETRICFLOWRATEUNIT,
    ROTATIONALFREQUENCYUNIT,
    TORQUEUNIT,
    MOMENTOFINERTIAUNIT,
    LINEARMOMENTUNIT,
    LINEARFORCEUNIT,
    PLANARFORCEUNIT,
    MODULUSOFELASTICITYUNIT,
    SHEARMODULUSUNIT,
    LINEARSTIFFNESSUNIT,
    ROTATIONALSTIFFNESSUNIT,
    MODULUSOFSUBGRADEREACTIONUNIT,
    ACCELERATIONUNIT,
    CURVATUREUNIT,
    HEATINGVALUEUNIT,
    IONCONCENTRATIONUNIT,
    LUMINOUSINTENSITYDISTRIBUTIONUNIT,
    MASSPERLENGTHUNIT,
    MODULUSOFLINEARSUBGRADEREACTIONUNIT,
    MODULUSOFROTATIONALSUBGRADEREACTIONUNIT,
    PHUNIT,
    ROTATIONALMASSUNIT,
    SECTIONAREAINTEGRALUNIT,
    SECTIONMODULUSUNIT,
    SOUNDPOWERUNIT,
    SOUNDPRESSUREUNIT,
    TEMPERATUREGRADIENTUNIT,
    THERMALEXPANSIONCOEFFICIENTUNIT,
    WARPINGCONSTANTUNIT,
    WARPINGMOMENTUNIT,
    USERDEFINED;

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "." + name() + ".";
    }
}
