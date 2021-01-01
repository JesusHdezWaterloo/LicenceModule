/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.control.licence.core.injection;

import com.root101.clean.core.app.services.LicenceHandler;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.module.control.licence.core.exception.BadLicenceException;
import com.root101.module.control.licence.services.ResourceKeys;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceInterceptor implements MethodInterceptor {

    public static LicenceInterceptor from() {
        return new LicenceInterceptor();
    }

    private LicenceInterceptor() {
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!LicenceHandler.isActive()) {
            throw new BadLicenceException(ResourceHandler.getString(ResourceKeys.MSG_BAD_LICENCE));
        }
        return invocation.proceed();
    }
}
