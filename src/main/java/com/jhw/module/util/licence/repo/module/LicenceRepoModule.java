package com.jhw.module.util.licence.repo.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceRepoModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new InjectionConfigLicenceRepo());

    private static LicenceRepoModule INSTANCE;

    private LicenceRepoModule() {
    }

    public static LicenceRepoModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de Person no se ha inicializado");
        }
        return INSTANCE;
    }

    public static LicenceRepoModule init() {
        INSTANCE = new LicenceRepoModule();
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Licence Repo Module";
    }

}
