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
package com.root101.module.control.licence.core.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.module.control.licence.core.repo_def.LicenceRepo;
import com.root101.module.control.licence.core.usecase_def.LicenceUseCase;
import com.root101.module.control.licence.core.domain.LicenceDomain;
import com.root101.module.control.licence.core.domain.LicenceDomainSimpleConverter;
import com.root101.module.control.licence.core.module.LicenceCoreModule;
import com.root101.module.control.licence.core.exception.BadLicenceException;
import java.time.LocalDate;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
        isActive();
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

            //si la ultima fecha es antes de hoy actualizo y ajusto
            if (licence.getFechaUltimoRevisado().isBefore(now)) {
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
