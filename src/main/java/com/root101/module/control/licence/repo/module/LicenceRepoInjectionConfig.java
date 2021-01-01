package com.root101.module.control.licence.repo.module;

import com.google.inject.AbstractModule;
import com.root101.module.control.licence.core.repo_def.LicenceRepo;
import com.root101.module.control.licence.repo.repo_impl.LicenceRepoImpl;

/**
 * Configuracion del injection del modulo de licencia-repo.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceRepoInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LicenceRepo.class).to(LicenceRepoImpl.class);
    }

}
