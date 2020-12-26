package com.jhw.module.util.licence.repo.module;

import com.jhw.module.util.licence.repo.repo_impl.*;
import com.jhw.module.util.licence.core.repo_def.*;
import com.google.inject.AbstractModule;

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
