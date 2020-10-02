/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.services;

import com.google.inject.Guice;
import com.jhw.module.util.licence.core.module.InjectionConfigLicenceCore;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceHandler {

    private static LicenceUseCase licenceUC = Guice.createInjector(new InjectionConfigLicenceCore()).getInstance(LicenceUseCase.class);

    private LicenceHandler() {
    }

    public static void registerLicenceService(LicenceUseCase newService) {
        licenceUC = newService;
    }

    public static boolean isLicenceCorrect() {
        if (licenceUC != null) {
            return licenceUC.isLicenceCorrect();
        }
        return false;
    }

    public static void activateLicence(String codeCypher) throws Exception {
        if (licenceUC != null) {
            licenceUC.activateLicence(codeCypher);
        }
    }

    public static int daysUntilActivation() {
        if (licenceUC != null) {
            return licenceUC.daysUntilActivation();
        }
        return 0;
    }

}
