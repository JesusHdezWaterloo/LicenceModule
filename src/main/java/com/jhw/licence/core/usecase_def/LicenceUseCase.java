package com.jhw.licence.core.usecase_def;

import com.clean.core.app.usecase.AbstractUseCase;
import com.clean.core.app.usecase.ReadWriteUseCase;
import com.jhw.licence.core.domain.Licence;

/**
 * Interfaz del caso de uso de la licencia para definir las principales
 * funcionalidades de la licencia
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public interface LicenceUseCase extends ReadWriteUseCase<Licence> {

    /**
     * Chequea si la licencia es correcta, incluye integridad, y ubicacion en el
     * tiempo
     *
     * @return true si la licencia es correcta, false en cualquier otro caso
     */
    public boolean isLicenceCorrect();

    /**
     * Activa la licencia en dependencia de un codigo de activacion cifrado
     *
     * @param codeCypher codigo de activacion cifrado
     * @throws Exception si hay algun problema en la activacion
     */
    public void activateLicence(String codeCypher) throws Exception;

    public int daysUntilActivation();
}
