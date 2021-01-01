/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.control.licence.core.module;

import com.root101.clean.core.app.modules.AbstractModule;
import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.root101.module.control.licence.repo.module.LicenceRepoModule;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LicenceCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new LicenceCoreInjectionConfig());

    private static LicenceCoreModule INSTANCE;

    public static LicenceCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de licencia no se ha inicializado");
        }
        return INSTANCE;
    }

    public static LicenceCoreModule init() {
        if (INSTANCE == null) {
            INSTANCE = new LicenceCoreModule();
            INSTANCE.registerModule(LicenceRepoModule.init());
        }
        return getInstance();
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
        return "Licence Core Module Server";
    }

}
