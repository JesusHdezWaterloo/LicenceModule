package com.jhw.licence.core.app;

import com.clean.core.app.repo.AbstractRepository;
import com.jhw.licence.core.domain.Licence;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public interface LicenceRepo extends AbstractRepository {

    public Licence read() throws Exception;

    public void write(Licence lic) throws Exception;
}
