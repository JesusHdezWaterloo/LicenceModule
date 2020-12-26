package com.jhw.module.util.licence.repo.repo_impl;

import com.jhw.module.util.licence.core.repo_def.*;
import com.jhw.module.util.licence.core.domain.*;
import com.jhw.module.util.licence.repo.entity.*;
import com.jhw.module.util.licence.repo.utils.ResourcesLicence;
import com.jhw.utils.jpa.JPACleanCRUDRepo;

public class LicenceRepoImpl extends JPACleanCRUDRepo<LicenceDomain, Licence> implements LicenceRepo {

    public LicenceRepoImpl() {
        super(ResourcesLicence.EMF, LicenceDomain.class, Licence.class);
        this.setConverter(new LicenceConververSecured());
    }

    // El String de licence viene cifrado y en Base64
    @Override
    public LicenceDomain activate(String licence) throws Exception {
        Licence entity = new Licence("no_code", licence);
        LicenceDomain domain = converter.from(entity);
        if (!domain.checkIntegrity()) {
            throw new NullPointerException("Error activando el programa.");
        }
        return domain;
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
            edit(licence);
        }
    }

}
