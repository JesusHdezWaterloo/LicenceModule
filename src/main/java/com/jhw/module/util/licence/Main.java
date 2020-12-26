/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence;

import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.generator.GENERATOR;
import com.jhw.module.util.licence.repo.module.LicenceRepoModule;
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
        /*
        LicenceCoreModule.init(LicenceRepoModule.init());

        System.out.println("123");
        Date inicio = new Date();
        Date fin = new Date(150, 7, 21);
        long token = GENERATOR.generateToken(inicio, fin, CONFIG.DIFICULTY);
        LicenceDomain lic = new LicenceDomain(token, inicio, fin);

        String TO_SEND = GENERATOR.generateActivationCode(lic, CONFIG.HARDCORE_PASSWORD);
        System.out.println(TO_SEND);
        LicenceUseCase useCase = LicenceCoreModule.getInstance().getImplementation(LicenceUseCase.class);
        useCase.activateLicence(TO_SEND);
        System.out.println(useCase.isLicenceCorrect());

        //LicenceUseCase useCase = new LicenceUseCaseImpl(new File("licence.lic"));
        //System.out.println(useCase.isLicenceCorrect());
        */
    }

}
