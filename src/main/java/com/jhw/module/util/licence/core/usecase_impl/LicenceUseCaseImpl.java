package com.jhw.module.util.licence.core.usecase_impl;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.jhw.module.util.licence.core.repo_def.LicenceRepo;
import com.jhw.module.util.licence.core.usecase_def.LicenceUseCase;
import com.jhw.module.util.licence.core.domain.LicenceDomain;
import com.jhw.module.util.licence.core.domain.LicenceDomainSimpleConverter;
import com.jhw.module.util.licence.core.module.LicenceCoreModule;
import com.jhw.module.util.licence.core.exception.BadLicenceException;
import java.time.LocalDate;

/**
 * Implementacion de la Interfaz {@code LicenceUseCase} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceUseCaseImpl extends DefaultCRUDUseCase<LicenceDomain> implements LicenceUseCase {

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
    public boolean isActive() {
        try {
            LicenceDomain licence = null;
            try {
                licence = read();
            } catch (Exception e) {
            }

            //NO EXISTE
            if (licence == null) {
                throw new BadLicenceException(ResourceHandler.getString("NO LICENCE"));
            }

            //NO es integra: INVALIDA
            if (!licence.checkIntegrity()) {
                throw new BadLicenceException(ResourceHandler.getString("INVALID"));
            }

            LocalDate now = LocalDate.now();

            //si hoy es antes de la ultima fecha o el inicio: CORRUPTA
            if (now.isBefore(licence.getFechaUltimoRevisado())
                    || now.isBefore(licence.getFechaInicio())) {
                throw new BadLicenceException(ResourceHandler.getString("CORRUPT"));
            }

            //si hoy es pasada la fecha de fin: EXPIRADA
            if (now.isAfter(licence.getFechaFin())) {
                throw new BadLicenceException(ResourceHandler.getString("EXPIRED"));
            }

            //si la ultima fecha es despues de hoy actualizo
            if (licence.getFechaUltimoRevisado().isAfter(now)) {
                licence.setFechaUltimoRevisado(now);
                System.out.println("Actualizando la licencia");
                write(licence);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error comprobando la licencia " + e.getMessage());
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
     * @param actvationCode codigo de activacion cifrado
     * @throws Exception si hay algun problema en la activacion
     */
    @Override
    public void activate(String actvationCode) throws Exception {
        LicenceDomain domain = LicenceDomainSimpleConverter.getInstance().from(actvationCode);
        if (!domain.checkIntegrity()) {
            throw new NullPointerException("Error activando el programa.");
        }
        write(domain);
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
