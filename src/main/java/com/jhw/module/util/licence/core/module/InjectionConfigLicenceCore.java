package com.jhw.module.util.licence.core.module;

import com.google.inject.AbstractModule;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.core.usecase_impl.LicenceUseCaseImpl;

/**
 * Configuracion del injection del modulo de licencia-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class InjectionConfigLicenceCore extends AbstractModule {

    @Override
    protected void configure() {
        bind(LicenceUseCase.class).to(LicenceUseCaseImpl.class)/*.in(Singleton.class)*/;
    }

}
