package com.jhw.module.util.licence.core.usecase_impl;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.usecase.DefaultCRUDUseCase;
import com.clean.core.domain.services.Resource;
import com.jhw.module.util.licence.core.repo_def.LicenceRepo;
import com.google.common.io.BaseEncoding;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.core.exception.BadLicenceException;
import com.jhw.utils.security.AES;
import java.time.LocalDate;

/**
 * Implementacion de la Interfaz {@code LicenceUseCase} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceUseCaseImpl extends DefaultCRUDUseCase<LicenceDomain> implements LicenceUseCase {

    public static final byte[] HARDCORE_PASSWORD = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//SerialNumber.getUID();

    public static final String MSG_NO_FILE = "msg.licence.no_file";
    public static final String MSG_INVALID = "msg.licence.invalid";
    public static final String MSG_CORRUPT = "msg.licence.corrupt";
    public static final String MSG_EXPIRED = "msg.licence.expired";

    /**
     * Instancia del repo para almacenar las cosas en memoria
     */
    private final LicenceRepo repo = LicenceCoreModule.getInstance().getImplementation(LicenceRepo.class);

    /**
     * Constructor por defecto, usado par injectar.
     */
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
            LicenceDomain licence = null;
            try {
                licence = read();
            } catch (Exception e) {
            }
            if (licence == null) {
                throw new BadLicenceException(Resource.getString(MSG_NO_FILE));
            }
            if (!licence.checkIntegrity()) {
                throw new BadLicenceException(Resource.getString(MSG_INVALID));
            }
            LocalDate now = LocalDate.now();
            if (now.isBefore(licence.getFechaUltimoRevisado())
                    || now.isBefore(licence.getFechaInicio())) {
                throw new BadLicenceException(Resource.getString(MSG_CORRUPT));
            }
            licence.setFechaUltimoRevisado(now);
            write(licence);
            if (licence.getFechaUltimoRevisado().isAfter(licence.getFechaFin())) {
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
            return read().daysUntilActivation();
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
        repo.activate(codeCypher);
    }

    @Override
    public LicenceDomain read() throws Exception {
        return repo.read();
    }

    @Override
    public void write(LicenceDomain licence) throws Exception {
        repo.write(licence);
    }
}
