package com.root101.module.control.licence.repo.repo_impl;

import com.root101.module.control.licence.core.domain.*;
import com.root101.module.control.licence.core.repo_def.LicenceRepo;
import com.root101.module.control.licence.repo.entity.Licence;
import com.root101.module.control.licence.repo.utils.ResourcesLicence;
import com.root101.repo.jpa.JPACleanCRUDRepo;

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
