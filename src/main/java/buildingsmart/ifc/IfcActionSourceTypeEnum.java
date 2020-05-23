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

public enum IfcActionSourceTypeEnum {
    DEAD_LOAD_G, COMPLETION_G1, LIVE_LOAD_Q, SNOW_S, WIND_W, PRESTRESSING_P,
    SETTLEMENT_U, TEMPERATURE_T, EARTHQUAKE_E, FIRE, IMPULSE, IMPACT, TRANSPORT,
    ERECTION, PROPPING, SYSTEM_IMPERFECTION, SHRINKAGE, CREEP, LACK_OF_FIT,
    BUOYANCY, ICE, CURRENT, WAVE, RAIN, BRAKES, USERDEFINED, NOTDEFINED,
}
