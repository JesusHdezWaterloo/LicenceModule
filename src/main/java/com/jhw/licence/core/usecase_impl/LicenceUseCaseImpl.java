package com.jhw.licence.core.usecase_impl;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.usecase.DefaultReadWriteUseCase;
import com.clean.core.domain.services.Resource;
import com.jhw.licence.core.repo_def.LicenceRepo;
import com.google.common.io.BaseEncoding;
import com.jhw.licence.core.usecase_def.LicenceUseCase;
import com.jhw.licence.core.domain.Licence;
import com.jhw.licence.core.module.CONFIG;
import com.jhw.licence.core.module.LicenceModule;
import com.jhw.licence.core.utils.BadLicenceException;
import com.jhw.utils.security.AES;
import java.util.Date;
import javax.inject.Inject;

/**
 * Implementacion de la Interfaz {@code LicenceUseCase} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceUseCaseImpl extends DefaultReadWriteUseCase<Licence> implements LicenceUseCase {

    public static final String MSG_NO_FILE = "msg.licence.no_file";
    public static final String MSG_INVALID = "msg.licence.invalid";
    public static final String MSG_CORRUPT = "msg.licence.corrupt";
    public static final String MSG_EXPIRED = "msg.licence.expired";

    /**
     * Instancia del repo para almacenar las cosas en memoria
     */
    private final LicenceRepo repo = LicenceModule.getInstance().getImplementation(LicenceRepo.class);

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public LicenceUseCaseImpl() {
        super.setRepo(repo);
    }

    /**
     * Chequea si la licencia es correcta, incluye integridad, y ubicacion en el
     * tiempo. Actualiza la ultima fecha de chequeo y la guarda en memoria
     * actualizada.
     *
     * @return true si la licencia es correcta, false en cualquier otro caso
     */
    @Override
    public boolean isLicenceCorrect() {
        try {
            Licence licence = null;
            try {
                licence = repo.read();
            } catch (Exception e) {
            }
            if (licence == null) {
                throw new BadLicenceException(Resource.getString(MSG_NO_FILE));
            }
            if (!licence.isLicenceValid()) {
                throw new BadLicenceException(Resource.getString(MSG_INVALID));
            }
            Date now = new Date();
            if (now.before(licence.getFechaUltimoRevisado())
                    || now.before(licence.getFechaInicio())) {
                throw new BadLicenceException(Resource.getString(MSG_CORRUPT));
            }
            licence.setFechaUltimoRevisado(now);
            repo.write(licence);
            if (licence.getFechaUltimoRevisado().after(licence.getFechaFin())) {
                throw new BadLicenceException(Resource.getString(MSG_EXPIRED));
            }
            return true;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            return false;
        }
    }

    @Override
    public int daysUntilActivation() {
        try {
            return repo.read().daysUntilActivation();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Activa la licencia. Si el codigo de cifrado esta bien, la activa y guarda
     * en memoria, sino lanza excepcion.
     *
     * @param codeCypher codigo de activacion cifrado
     * @throws Exception si hay algun problema en la activacion
     */
    @Override
    public void activateLicence(String codeCypher) throws Exception {
        Licence act = activate(codeCypher);
        if (act.isLicenceValid()) {
            repo.write(act);
        } else {
            throw new NullPointerException("Error activando el programa.");
        }
    }

    /**
     * Lleva a cabo el proceso de activar la licencia en dependencia de un
     * codigo de activacion cifrado.
     *
     * @param codeCypher codigo de activacion cifrado
     * @return la Licencia activada
     * @throws Exception si hay algun problema en la activacion
     */
    private Licence activate(String codeCypher) throws Exception {
        byte base[] = BaseEncoding.base64().decode(codeCypher);
        byte des[] = AES.decipher(CONFIG.HARDCORE_PASSWORD, base);
        return new Licence(new String(des));
    }
}
