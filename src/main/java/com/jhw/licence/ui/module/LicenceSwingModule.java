package com.jhw.licence.ui.module;

import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.AbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.licence.core.module.LicenceModule;
import com.jhw.licence.core.usecase_def.LicenceUseCase;
import com.jhw.licence.repo.module.LicenceRepoModule;
import com.jhw.licence.services.Licence;
import com.jhw.swing.material.standars.MaterialIcons;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class LicenceSwingModule implements AbstractSwingMainModule {

    private final LicenceModuleNavigator navigator = new LicenceModuleNavigator();

    public static LicenceUseCase licenceUC;

    public LicenceSwingModule() {
        init();
    }

    private void init() {
        LicenceModule core = LicenceModule.init(LicenceRepoModule.init());
        licenceUC = core.getImplementation(LicenceUseCase.class);

        Licence.registerLicenceService(licenceUC);
    }

    @Override
    public void register(AbstractSwingApplication app) {
        System.out.println("Creando 'Licencia'");
        registerLicence(app);
    }

    private void registerLicence(AbstractSwingApplication app) {
        DashBoardSimple dash = app.rootView().dashboard();

        dash.putKeyValue(DashboardConstants.DOWN_LICENCE, new AbstractAction(Licence.daysUntilActivation() + " Días restantes", MaterialIcons.SECURITY.deriveIcon(16)) {
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
