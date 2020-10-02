package com.jhw.module.util.licence.core.module;

import com.clean.core.app.modules.AbstractModule;
import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new InjectionConfigLicenceCore());

    public static final int DIF = 4;

    private static LicenceCoreModule INSTANCE;

    public static LicenceCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de licencia no se ha inicializado");
        }
        return INSTANCE;
    }

    public static LicenceCoreModule init(AbstractModule repoModule) {
        INSTANCE = new LicenceCoreModule();
        INSTANCE.registerModule(repoModule);
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Licence Module";
    }

}