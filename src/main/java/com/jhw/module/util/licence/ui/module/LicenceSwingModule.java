package com.jhw.module.util.licence.ui.module;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.domain.services.Resource;
import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.repo.module.LicenceRepoModule;
import com.jhw.module.util.licence.services.LicenceHandler;
import com.jhw.module.util.licence.services.LicenceExceptionHandler;
import com.jhw.module.util.licence.services.LicenceNotificationService;
import com.jhw.module.util.licence.services.LicenceResourceService;
import com.jhw.swing.material.standards.MaterialIcons;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import javax.swing.AbstractAction;

public class LicenceSwingModule extends DefaultAbstractSwingMainModule {

    public static final String MSG_DAYS_TO_ACTIVATE = "msg.licence.days_to_activate";

    private final LicenceModuleNavigator navigator = new LicenceModuleNavigator();

    public static LicenceUseCase licenceUC;

    static {
        LicenceCoreModule.init(LicenceRepoModule.init());

        licenceUC = LicenceCoreModule.getInstance().getImplementation(LicenceUseCase.class);
    }

    private LicenceSwingModule() {
    }

    public static LicenceSwingModule init() {
        System.out.println("Creando 'Licencia'");
        LicenceNotificationService.init();
        LicenceExceptionHandler.init();
        try {
            LicenceResourceService.init();
        } catch (MalformedURLException ex) {
            ExceptionHandler.handleException(ex);
        }

        LicenceHandler.registerLicenceService(licenceUC);
        return new LicenceSwingModule();
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