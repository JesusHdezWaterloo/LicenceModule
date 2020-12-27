/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.rest;

import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.services.LicenceResourceService;
import com.jhw.module.util.licence.services.LicenceResourceServiceServerImplementation;
import com.jhw.module.util.licence.services.LicenceServiceImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Component
public class A_ModuleUtilLicence {

    public static final String BASE_PACKAGE = "com.jhw.module.util.licence";

    public final static LicenceUseCase licenceUC;

    static {
        LicenceServiceImpl.init();
        LicenceResourceServiceServerImplementation.init();
        LicenceResourceService.init();

        LicenceCoreModule.init();
        licenceUC = LicenceCoreModule.getInstance().getImplementation(LicenceUseCase.class);
    }
}
