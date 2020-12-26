package com.jhw.module.util.licence.repo.repo_impl;

import com.jhw.module.util.licence.core.repo_def.LicenceRepo;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.repo.entity.Licence;
import com.jhw.module.util.licence.repo.utils.ResourcesLicence;
import com.jhw.utils.jpa.JPACleanCRUDRepo;

public class LicenceRepoImpl extends JPACleanCRUDRepo<LicenceDomain, Licence> implements LicenceRepo {

    /**
     * Constructor por defecto, usado par injectar.
     */
    public LicenceRepoImpl() {
        super(ResourcesLicence.EMF, LicenceDomain.class, Licence.class);
    }
    /*
    @Override
    public LicenceDomain read() throws Exception {
        FileInputStream fis = new FileInputStream(getFile());
        ObjectInputStream ois = new ObjectInputStream(fis);
        byte[] arr = (byte[]) ois.readObject();
        fis.close();
        ois.close();
        return JACKSON.read(new String(AES.decipher(CONFIG.HARDCORE_PASSWORD, arr)), LicenceDomain.class);
    }

    @Override
    public void write(LicenceDomain lic) throws Exception {
        FileOutputStream fos = new FileOutputStream(getFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(AES.cipher(CONFIG.HARDCORE_PASSWORD, JACKSON.toString(lic).getBytes()));
        oos.close();
        fos.close();
    }

    @Override
    protected void onReadError(Exception e) {
    }
     */
}
