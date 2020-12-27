/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.services;

import com.clean.core.app.services.LicenceHandler;
import com.clean.core.app.services.LicenceService;
import com.jhw.module.util.licence.rest.A_ModuleUtilLicence;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceServiceImpl implements LicenceService {

    public static LicenceServiceImpl init() {
        LicenceServiceImpl res = new LicenceServiceImpl();
        LicenceHandler.registerLicenceService(res);
        return res;
    }

    private LicenceServiceImpl() {
    }

    @Override
    public boolean isActive() {
        return A_ModuleUtilLicence.licenceUC.isActive();
    }

}
