/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
