/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.licence.services;

import com.jhw.licence.core.utils.BadLicenceException;
import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.ExceptionHandlerServiceFunctional;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceExceptionHandler extends ExceptionHandlerServiceFunctional {

    public static final String EXCEPTION_BAD_LICENCE = ExceptionHandlerServiceFunctional.getExceptionType(BadLicenceException.class);

    public static LicenceExceptionHandler init() {
        LicenceExceptionHandler excep = new LicenceExceptionHandler();
        ExceptionHandler.registerExceptionHandlerService(excep);
        return excep;
    }

    public LicenceExceptionHandler() {
        addAll();
    }

    @Override
    protected void addAll() {
        addHandler(EXCEPTION_BAD_LICENCE, (Exception e) -> {
            Notification.showConfirmDialog(LicenceNotificationService.CONFIRM_BAD_LICENCE, e.getMessage());
        });
    }

}
