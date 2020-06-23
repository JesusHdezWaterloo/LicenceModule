package com.jhw.licence.core.domain;

import com.clean.core.domain.EntityDomainObject;
import com.clean.core.exceptions.ValidationException;
import com.clean.core.utils.validation.ValidationResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import static com.jhw.licence.core.main.CONFIG.DIFICULTY;
import com.jhw.utils.security.SHA;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
@Getter
@Setter
public class Licence extends EntityDomainObject implements Serializable {

    public static final char separator = '-';
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy");

    //@Po
    private long token;

    private Date fechaInicio;

    private Date fechaFin;

    private Date fechaUltimoRevisado;

    public Licence() {
    }

    public Licence(long token, Date fechaActivacion, Date fechaFin) {
        this.token = token;
        this.fechaInicio = fechaActivacion;
        this.fechaFin = fechaFin;
    }

    public Licence(String fromString) throws ParseException {
        String inicio = fromString.substring(0, 10);
        fechaInicio = sdf.parse(inicio);
        fechaUltimoRevisado = sdf.parse(inicio);

        String fin = fromString.substring(11, 21);
        fechaFin = sdf.parse(fin);

        token = Long.parseLong(fromString.substring(22, fromString.length()));
    }

    @Override
    public ValidationResult validate() throws ValidationException {
        ValidationResult v = new ValidationResult();
        //v.checkFromAnnotations(this);
        return v.throwException();
    }

    @Override
    public String toString() {
        return sdf.format(fechaInicio) + separator + sdf.format(fechaFin) + separator + token;
    }

    @JsonIgnore
    public boolean isLicenceValid() {
        return isStartWithNCeros(SHA.hash256(this.toString()));
    }

    @JsonIgnore
    private boolean isStartWithNCeros(String hash) {
        if (DIFICULTY >= hash.length()) {
            return false;
        }
        for (int i = 0; i < DIFICULTY; i++) {
            if (hash.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
