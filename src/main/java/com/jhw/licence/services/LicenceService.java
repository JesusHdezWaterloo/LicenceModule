/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.licence.services;

import com.google.inject.Guice;
import com.jhw.licence.core.module.InjectionConfig;
import com.jhw.licence.core.usecase_def.LicenceUseCase;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceService {

    private static LicenceUseCase licenceUC = Guice.createInjector(new InjectionConfig()).getInstance(LicenceUseCase.class);

    private LicenceService() {
    }

    public static void registerLicenceService(LicenceUseCase newService) {
        licenceUC = newService;
    }

    public static boolean isLicenceCorrect() throws Exception {
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
}
