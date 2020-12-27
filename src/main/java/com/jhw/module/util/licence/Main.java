/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence;

import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.domain.LicenceDomainSimpleConverter;
import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.generator.GENERATOR;
import com.jhw.module.util.licence.repo.entity.Licence;
import com.jhw.module.util.licence.repo.module.LicenceRepoModule;
import com.jhw.module.util.licence.rest.A_ModuleUtilLicence;
import com.jhw.module.util.mysql.services.MySQLHandler;
import com.jhw.utils.services.ConverterService;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //create();
        read();
        //convert();
    }

    private static void create() throws Exception {
        LocalDate inicio = LocalDate.now();
        LocalDate fin = inicio.plusDays(5);
        long token = GENERATOR.generateToken(inicio, fin, DIFICULTY.VALUE);

        LicenceDomain lic = new LicenceDomain(token, inicio, fin);

        //String TO_SEND = GENERATOR.generateActivationCode(lic, new byte[0]);
        String TO_SEND = LicenceDomainSimpleConverter.getInstance().to(lic);
        A_ModuleUtilLicence.licenceUC.activate(TO_SEND);
    }

    private static void read() throws Exception {
        LicenceDomain lic = A_ModuleUtilLicence.licenceUC.read();
        System.out.println(lic);
    }

    private static void convert() throws Exception {
        LicenceDomain l = new LicenceDomain(7489, LocalDate.now(), LocalDate.now().plusDays(50));
        String toString = ConverterService.convert(l, String.class);
        System.out.println(toString);

        LicenceDomain l2 = ConverterService.convert(toString, LicenceDomain.class);
        System.out.println(l2);
    }
}
