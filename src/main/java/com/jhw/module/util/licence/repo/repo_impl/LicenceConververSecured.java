/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.repo.repo_impl;

import com.clean.core.app.repo.Converter;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.domain.LicenceDomainSimpleConverter;
import com.jhw.module.util.licence.repo.entity.Licence;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceConververSecured implements Converter<LicenceDomain, Licence> {

    @Override
    public LicenceDomain from(Licence entity) throws Exception {
        //el string de la BD cifrado y en base 64
        String string = entity.getLicence();
        //decifrado
        return LicenceDomainSimpleConverter.getInstance().from(string);
    }

    @Override
    public Licence to(LicenceDomain object) throws Exception {
        //el objeto limpio, cifrarlo y encodearlo e 64
        String string = LicenceDomainSimpleConverter.getInstance().to(object);
        //cifrado
        return new Licence("cod_cliente_UNKNOWN", string);
    }

}
