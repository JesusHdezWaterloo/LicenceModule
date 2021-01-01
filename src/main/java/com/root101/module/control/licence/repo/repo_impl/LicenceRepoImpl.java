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
package com.root101.module.control.licence.repo.repo_impl;

import com.root101.module.control.licence.core.domain.*;
import com.root101.module.control.licence.core.repo_def.LicenceRepo;
import com.root101.module.control.licence.repo.entity.Licence;
import com.root101.module.control.licence.repo.utils.ResourcesLicence;
import com.root101.repo.jpa.JPACleanCRUDRepo;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LicenceRepoImpl extends JPACleanCRUDRepo<LicenceDomain, Licence> implements LicenceRepo {

    public LicenceRepoImpl() {
        super(ResourcesLicence.EMF, LicenceDomain.class, Licence.class);
        this.setConverter(new LicenceConververSecured());
    }

    @Override
    public LicenceDomain read() throws Exception {
        return findAll().get(0);
    }

    @Override
    public void write(LicenceDomain licence) throws Exception {
        if (count() == 0) {
            create(licence);
        } else {
            LicenceDomain actual = read();
            licence.setIdLicence(actual.getIdLicence());
            licence.setClientCode(actual.getClientCode());
            edit(licence);
        }
    }

}
