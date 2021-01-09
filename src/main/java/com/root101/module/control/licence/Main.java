/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.control.licence;

import com.root101.module.control.licence.core.domain.LicenceDomain;
import com.root101.module.control.licence.core.domain.LicenceDomainSimpleConverter;
import com.root101.module.control.licence.generator.GENERATOR;
import com.root101.module.control.licence.rest.A_ModuleUtilLicenceRESTConfig;
import com.root101.utils.services.ConverterService;
import java.time.LocalDate;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        create();
        //read();
        //convert();
    }

    private static void create() throws Exception {
        LocalDate inicio = LocalDate.now();
        LocalDate fin = inicio.plusDays(5000);
        long token = GENERATOR.generateToken(inicio, fin, DIFICULTY.VALUE);

        LicenceDomain lic = new LicenceDomain(token, inicio, fin);

        //String TO_SEND = GENERATOR.generateActivationCode(lic, new byte[0]);
        String TO_SEND = LicenceDomainSimpleConverter.getInstance().to(lic);
        A_ModuleUtilLicenceRESTConfig.licenceUC.activate(TO_SEND);
    }

    private static void read() throws Exception {
        LicenceDomain lic = A_ModuleUtilLicenceRESTConfig.licenceUC.read();
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
