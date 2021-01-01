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

import com.root101.module.control.licence.core.module.LicenceCoreModule;
import com.root101.module.control.licence.core.usecase_def.LicenceUseCase;
import com.root101.module.control.licence.services.LicenceResourceService;
import com.root101.module.control.licence.services.LicenceResourceServiceServerImplementation;
import com.root101.module.control.licence.services.LicenceServiceImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Component
public class A_ModuleUtilLicence {

    public static final String BASE_PACKAGE = "com.root101.module.control.licence";

    public final static LicenceUseCase licenceUC;

    static {
        LicenceServiceImpl.init();
        LicenceResourceServiceServerImplementation.init();
        LicenceResourceService.init();

        LicenceCoreModule.init();
        licenceUC = LicenceCoreModule.getInstance().getImplementation(LicenceUseCase.class);
    }
}
