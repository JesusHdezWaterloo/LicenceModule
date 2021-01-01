package com.jhw.module.util.licence.repo.module;

import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jhw.module.util.licence.repo.utils.ResourcesLicence;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceRepoModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new LicenceRepoInjectionConfig());

    private static LicenceRepoModule INSTANCE;

    static {
        ResourcesLicence.initEMF();
    }

    private LicenceRepoModule() {
    }

    public static LicenceRepoModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de Licencia no se ha inicializado");
        }
        return INSTANCE;
    }

    public static LicenceRepoModule init() {
        if (INSTANCE == null) {
            INSTANCE = new LicenceRepoModule();
        }
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Licence Repo Module Server";
    }

}
