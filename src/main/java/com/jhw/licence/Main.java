/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.licence;

import com.jhw.licence.core.usecase_def.LicenceUseCase;
import com.jhw.licence.core.domain.Licence;
import com.jhw.licence.core.module.CONFIG;
import com.jhw.licence.core.module.LicenceModule;
import com.jhw.licence.generator.GENERATOR;
import com.jhw.licence.repo.module.LicenceRepoModule;
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
        LicenceModule.init(new LicenceRepoModule());

        System.out.println("123");
        Date inicio = new Date();
        Date fin = new Date(120, 6, 21);
        long token = GENERATOR.generateToken(inicio, fin, CONFIG.DIFICULTY);
        Licence lic = new Licence(token, inicio, fin);

        String TO_SEND = GENERATOR.generateActivationCode(lic, CONFIG.HARDCORE_PASSWORD);
        System.out.println(TO_SEND);
        LicenceUseCase useCase = LicenceModule.getInstance().getImplementation(LicenceUseCase.class);
        useCase.activateLicence(TO_SEND);
        System.out.println(useCase.isLicenceCorrect());

        //LicenceUseCase useCase = new LicenceUseCaseImpl(new File("licence.lic"));
        //System.out.println(useCase.isLicenceCorrect());
    }

}
