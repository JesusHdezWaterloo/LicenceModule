package com.jhw.licence.core.app;

import com.google.common.io.BaseEncoding;
import com.jhw.licence.core.domain.Licence;
import com.jhw.licence.core.main.CONFIG;
import com.jhw.licence.core.main.LicenceModule;
import com.jhw.utils.security.AES;
import java.util.Date;
import javax.inject.Inject;

public class LicenceUseCaseImpl implements LicenceUseCase {

    private final LicenceRepo repo = LicenceModule.getInstance().getImplementation(LicenceRepo.class);

    @Inject
    public LicenceUseCaseImpl() {
    }

    @Override
    public boolean isLicenceCorrect() throws Exception {
        Licence licence = repo.read();
        if (licence == null) {
            return false;
        }
        if (!licence.isLicenceValid()) {
            return false;
        }
        Date now = new Date();
        if (now.before(licence.getFechaUltimoRevisado())) {
            return false;
        }
        licence.setFechaUltimoRevisado(now);
        repo.write(licence);
        if (now.after(licence.getFechaInicio()) && now.before(licence.getFechaFin())) {
            return true;
        }
        return false;
    }

    @Override
    public void activateLicence(String codeCypher) throws Exception {
        Licence act = activate(codeCypher);
        if (act.isLicenceValid()) {
            repo.write(act);
        } else {
            throw new NullPointerException("Error activando el programa.");
        }
    }

    private Licence activate(String codeCypher) throws Exception {
        byte base[] = BaseEncoding.base64().decode(codeCypher);
        byte des[] = AES.decipher(CONFIG.HARDCORE_PASSWORD, base);
        return new Licence(new String(des));
    }
}
