package com.jhw.licence.repo.main;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.clean.core.app.modules.RepoModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceRepoModule extends DefaultAbstractModule implements RepoModule {

    private final Injector inj = Guice.createInjector(new InjectionRepoConfig());

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Licence Repo Module";
    }

}
