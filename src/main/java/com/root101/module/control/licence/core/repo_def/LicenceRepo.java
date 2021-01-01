package com.root101.module.control.licence.core.repo_def;

import com.root101.clean.core.app.repo.CRUDRepository;
import com.root101.module.control.licence.core.domain.*;

public interface LicenceRepo extends CRUDRepository<LicenceDomain> {

    public LicenceDomain read() throws Exception;

    public void write(LicenceDomain licence) throws Exception;

}
