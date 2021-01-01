/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.control.licence.rest;

import static com.root101.module.control.licence.core.ModuleLicenceConstants.*;
import com.root101.module.control.licence.core.domain.LicenceDomain;
import com.root101.module.control.licence.core.usecase_def.LicenceUseCase;
import com.jhw.utils.spring.server.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@RestController
@RequestMapping(value = LICENCE_LICENCE_GENERAL_PATH)
public class LicenceRESTService extends RESTServiceTemplate<LicenceDomain> implements LicenceUseCase {

    private final LicenceUseCase licenceUC = A_ModuleUtilLicence.licenceUC;

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
