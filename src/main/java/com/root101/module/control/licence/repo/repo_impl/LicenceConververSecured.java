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
package com.root101.module.control.licence.repo.repo_impl;

import com.root101.clean.core.app.repo.Converter;
import com.root101.module.control.licence.core.domain.LicenceDomain;
import com.root101.module.control.licence.repo.entity.Licence;
import com.root101.json.jackson.JACKSON;
import com.root101.security.AES;
import java.util.Base64;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LicenceConververSecured implements Converter<LicenceDomain, Licence> {

    public static final byte[] HARDCORE_PASS = new byte[0];

    @Override
    public LicenceDomain from(Licence entity) throws Exception {
        //el string de la BD cifrado y en base 64
        String string = entity.getLicence();
        string = decipher(string);

        LicenceDomain domain = JACKSON.read(string, LicenceDomain.class);//directo xq se esta leyendo de un String y por el converter se parte

        if (entity.getIdLicence() != null) {
            domain.setIdLicence(entity.getIdLicence());
        }
        if (entity.getClientCode() != null) {
            domain.setClientCode(entity.getClientCode());
        }

        return domain;
    }

    @Override
    public Licence to(LicenceDomain domain) throws Exception {
        //el objeto limpio, cifrarlo y encodearlo e 64
        String string = JACKSON.toString(domain);//ConverterService.convert(domain, String.class);
        string = cipher(string);

        Licence entity = new Licence("cod_cliente_UNKNOWN", string);
        
        if (domain.getIdLicence() != null) {
            entity.setIdLicence(domain.getIdLicence());
        }
        
        if (domain.getClientCode() != null) {
            entity.setClientCode(domain.getClientCode());
        }

        return entity;
    }

    private String cipher(String text) throws Exception {
        return new String(
                Base64.getEncoder().encode(
                        AES.cipher(
                                HARDCORE_PASS,
                                text.getBytes()
                        )
                )
        );
    }

    private String decipher(String text) throws Exception {
        return new String(
                AES.decipher(
                        HARDCORE_PASS,
                        Base64.getDecoder().decode(
                                text
                        )
                )
        );
    }
}
