/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.control.licence.rest;

import com.root101.spring.server.RESTServiceTemplate;
import static com.root101.module.control.licence.rest.ModuleLicenceRESTConstants.*;
import com.root101.module.control.licence.core.domain.LicenceDomain;
import com.root101.module.control.licence.core.usecase_def.LicenceUseCase;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@RestController
@RequestMapping(value = LICENCE_LICENCE_GENERAL_PATH)
public class LicenceRESTService extends RESTServiceTemplate<LicenceDomain> implements LicenceUseCase {

    private final LicenceUseCase licenceUC = A_ModuleUtilLicenceRESTConfig.licenceUC;

    public LicenceRESTService() {
        setUseCase(licenceUC);
    }

    @Override
    @GetMapping(LICENCE_IS_ACTIVE_PATH)
    public boolean isActive() {
        return licenceUC.isActive();
    }

    @Override
    @PostMapping(LICENCE_ACTIVE_PATH)
    public void activate(String codeCypher) throws Exception {
        licenceUC.activate(codeCypher);
    }

    @Override
    @GetMapping(LICENCE_DAYS_LEFT_PATH)
    public int daysUntilActivation() {
        return licenceUC.daysUntilActivation();
    }

    @Override
    public LicenceDomain read() throws Exception {
        return licenceUC.read();
    }

    @Override
    public void write(LicenceDomain licence) throws Exception {
        licenceUC.write(licence);
    }

}
