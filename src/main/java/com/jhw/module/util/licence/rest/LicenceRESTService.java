/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.rest;

import static com.jhw.module.util.licence.core.ModuleLicenceConstants.*;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
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

    /*@Override
    @GetMapping(GASTO_REPORTE_POR_TIPO_PATH)
    public HashMap<TipoGastoDomain, BigDecimal> reporteGastadoPorGasto() throws Exception {
        return gastoUC.reporteGastadoPorGasto();
    }*/
    @Override
    public boolean isLicenceCorrect() {
        return licenceUC.isLicenceCorrect();
    }

    @Override
    public void activateLicence(String codeCypher) throws Exception {
        licenceUC.activateLicence(codeCypher);
    }

    @Override
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
