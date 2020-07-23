package com.jhw.licence.core.usecase_impl;

import com.jhw.licence.core.repo_def.LicenceRepo;
import com.google.common.io.BaseEncoding;
import com.jhw.licence.core.usecase_def.LicenceUseCase;
import com.jhw.licence.core.domain.Licence;
import com.jhw.licence.core.module.CONFIG;
import com.jhw.licence.core.module.LicenceModule;
import com.jhw.utils.security.AES;
import java.util.Date;
import javax.inject.Inject;

/**
 * Implementacion de la Interfaz {@code LicenceUseCase} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceUseCaseImpl implements LicenceUseCase {

    /**
     * Instancia del repo para almacenar las cosas en memoria
     */
    private final LicenceRepo repo = LicenceModule.getInstance().getImplementation(LicenceRepo.class);

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public LicenceUseCaseImpl() {
        isLicenceCorrect();
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
        } catch (Exception e) {
        }
        return false;
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
