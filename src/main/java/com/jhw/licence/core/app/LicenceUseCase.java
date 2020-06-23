package com.jhw.licence.core.app;

import com.clean.core.app.usecase.AbstractUseCase;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public interface LicenceUseCase extends AbstractUseCase {

    public boolean isLicenceCorrect() throws Exception;

    public void activateLicence(String codeCypher) throws Exception;
}
