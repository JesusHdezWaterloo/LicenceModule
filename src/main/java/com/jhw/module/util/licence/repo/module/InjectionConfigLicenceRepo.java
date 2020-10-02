package com.jhw.module.util.licence.repo.module;

import com.jhw.module.util.licence.repo.repo_impl.LicenceRepoImpl;
import com.jhw.module.util.licence.core.repo_def.LicenceRepo;
import com.google.inject.AbstractModule;

/**
 * Configuracion del injection del modulo de licencia-repo.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class InjectionConfigLicenceRepo extends AbstractModule {

    @Override
    protected void configure() {
        bind(LicenceRepo.class).to(LicenceRepoImpl.class);
    }

}
