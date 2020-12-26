package com.jhw.licence.services;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationServiceFunctional;
import com.jhw.swing.material.standars.MaterialIcons;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceNotificationService extends NotificationServiceFunctional {

    public static final String CONFIRM_BAD_LICENCE = "confirm.licence.bad_licence";

    public static LicenceNotificationService init() {
        LicenceNotificationService notif = new LicenceNotificationService();
        Notification.registerNotificationService(notif);
        return notif;
    }

    private LicenceNotificationService() {
        addAll();
    }

    @Override
    protected void addNotifications() {
    }

    @Override
    protected void addConfirmDialog() {
        super.addConfirmDialog(CONFIRM_BAD_LICENCE, (Object t)
                -> JOptionPane.showConfirmDialog(null,
                        t,
                        "Error de Licencia",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        MaterialIcons.SECURITY.deriveIcon(36f)) == 0
        );
    }

    @Override
    protected void addInputDialog() {
    }
}
