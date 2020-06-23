package com.jhw.licence.repo.main;

import com.jhw.licence.repo.app.LicenceRepoImpl;
import com.jhw.licence.core.app.LicenceRepo;
import com.google.inject.AbstractModule;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class InjectionRepoConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LicenceRepo.class).to(LicenceRepoImpl.class);
    }

}
