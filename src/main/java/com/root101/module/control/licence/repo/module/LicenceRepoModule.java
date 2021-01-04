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
package com.root101.module.control.licence.repo.module;

import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.exceptions.AlreadyInitModule;
import com.root101.clean.core.exceptions.NotInitModule;
import com.root101.module.control.licence.repo.utils.ResourcesLicence;
import com.root101.module.control.licence.services.ResourceKeys;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LicenceRepoModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new LicenceRepoInjectionConfig());

    private static LicenceRepoModule INSTANCE;

    static {
        ResourcesLicence.initEMF();
    }

    private LicenceRepoModule() {
    }

    public static LicenceRepoModule getInstance() {
        if (INSTANCE == null) {
            throw new NotInitModule(ResourceHandler.getString(ResourceKeys.KEY_MODULE_NAME_LICENCE));
        }
        return INSTANCE;
    }

    public static LicenceRepoModule init() {
        if (INSTANCE != null) {
            throw new AlreadyInitModule(ResourceHandler.getString(ResourceKeys.KEY_MODULE_NAME_LICENCE));
        }
        INSTANCE = new LicenceRepoModule();
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Licence Repo Module Server";
    }

}
