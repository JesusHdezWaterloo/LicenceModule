/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.util.licence.core.injection;

import com.clean.core.app.services.LicenceHandler;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.licence.core.exception.BadLicenceException;
import com.jhw.module.util.licence.services.ResourceKeys;
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
            throw new BadLicenceException(Resource.getString(ResourceKeys.MSG_BAD_LICENCE));
        }
        return invocation.proceed();
    }
}
