package com.jhw.licence.ui.module;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;
import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.AbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.licence.core.module.LicenceModule;
import com.jhw.licence.core.usecase_def.LicenceUseCase;
import com.jhw.licence.repo.module.LicenceRepoModule;
import com.jhw.licence.services.LicenceHandler;
import com.jhw.licence.services.LicenceExceptionHandler;
import com.jhw.licence.services.LicenceNotificationService;
import com.jhw.licence.services.LicenceResourceService;
import com.jhw.swing.material.components.container.panel._PanelGradient;
import com.jhw.swing.material.standars.MaterialIcons;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import javax.swing.AbstractAction;

public class LicenceSwingModule implements AbstractSwingMainModule {

    public static final String MSG_DAYS_TO_ACTIVATE = "msg.licence.days_to_activate";

    private final LicenceModuleNavigator navigator = new LicenceModuleNavigator();

    public static LicenceUseCase licenceUC;

    public LicenceSwingModule() {
        init();
    }

    private void init() {
        System.out.println("Creando 'Licencia'");
        LicenceNotificationService.init();
        LicenceExceptionHandler.init();
        try {
            LicenceResourceService.init();
        } catch (MalformedURLException ex) {
            ExceptionHandler.handleException(ex);
        }

        LicenceModule core = LicenceModule.init(LicenceRepoModule.init());
        licenceUC = core.getImplementation(LicenceUseCase.class);

        LicenceHandler.registerLicenceService(licenceUC);
    }

    @Override
    public void register(AbstractSwingApplication app) {
        registerLicence(app);
    }

    private void registerLicence(AbstractSwingApplication app) {
        DashBoardSimple dash = app.rootView().dashboard();

        dash.putKeyValue(DashboardConstants.DOWN_LICENCE, new AbstractAction(LicenceHandler.daysUntilActivation() + " " + Resource.getString(MSG_DAYS_TO_ACTIVATE), MaterialIcons.SECURITY.deriveIcon(16)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notification.showConfirmDialog(NotificationsGeneralType.CONFIRM_ERROR, "ACTIVAR LICENCIA");
            }
        });
    }

    @Override
    public void navigateTo(String string, Object... o) {
        navigator.navigateTo(string, o);
    }

}
