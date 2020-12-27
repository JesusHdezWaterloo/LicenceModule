package com.jhw.module.util.licence.core.injection;

import com.clean.core.utils.Licenced;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public abstract class LicenceInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(Licenced.class),
                LicenceInterceptor.from()
        );
    }

}
