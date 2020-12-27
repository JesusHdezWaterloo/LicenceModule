/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.repo.repo_impl;

import com.clean.core.app.repo.Converter;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.repo.entity.Licence;
import com.jhw.utils.jackson.JACKSON;
import com.jhw.utils.security.AES;
import com.jhw.utils.services.ConverterService;
import java.util.Base64;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceConververSecured implements Converter<LicenceDomain, Licence> {

    public static final byte[] HARDCORE_PASS = new byte[0];

    @Override
    public LicenceDomain from(Licence entity) throws Exception {
        //el string de la BD cifrado y en base 64
        String string = entity.getLicence();
        string = decipher(string);

        LicenceDomain domain = JACKSON.read(string, LicenceDomain.class);//directo xq se esta leyendo de un String y por el converter se parte

        domain.setIdLicence(entity.getIdLicence());
        domain.setClientCode(entity.getClientCode());

        return domain;
    }

    @Override
    public Licence to(LicenceDomain domain) throws Exception {
        //el objeto limpio, cifrarlo y encodearlo e 64
        String string = ConverterService.convert(domain, String.class);
        string = cipher(string);

        Licence entity = new Licence("cod_cliente_UNKNOWN", string);

        entity.setIdLicence(domain.getIdLicence());
        entity.setClientCode(domain.getClientCode());

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