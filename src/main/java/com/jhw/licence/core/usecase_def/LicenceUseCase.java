package com.jhw.licence.core.usecase_def;

import com.clean.core.app.usecase.AbstractUseCase;

/**
 * Interfaz del caso de uso de la licencia para definir las principales
 * funcionalidades de la licencia
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public interface LicenceUseCase extends AbstractUseCase {

    /**
     * Chequea si la licencia es correcta, incluye integridad, y ubicacion en el
     * tiempo
     *
     * @return true si la licencia es correcta, false en cualquier otro caso
     * @throws Exception si hay algun problema en la comprobacion
     */
    public boolean isLicenceCorrect() throws Exception;

    /**
     * Activa la licencia en dependencia de un codigo de activacion cifrado
     *
     * @param codeCypher codigo de activacion cifrado
     * @throws Exception si hay algun problema en la activacion
     */
    public void activateLicence(String codeCypher) throws Exception;

    public int daysUntilActivation() throws Exception;
}
