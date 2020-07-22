package com.jhw.licence.core.domain;

import com.clean.core.domain.EntityDomainObject;
import com.clean.core.exceptions.ValidationException;
import com.clean.core.utils.validation.ValidationResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import static com.jhw.licence.core.module.CONFIG.DIFICULTY;
import com.jhw.utils.clean.EntityDomainObjectValidated;
import com.jhw.utils.security.SHA;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Objeto dominio Licencia.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Licence extends EntityDomainObjectValidated {

    /**
     * Separador del toString
     */
    public static final char separator = '-';

    /**
     * Formato de la fecha
     */
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy");

    /**
     * Token de integridad
     */
    //@Po
    private long token;

    /**
     * Fecha de inicio de activacion
     */
    private Date fechaInicio;

    /**
     * Fecha de fin de licencia
     */
    private Date fechaFin;

    /**
     * Fecha del ultimo revisado para evitar el alteraciones en la fecha del
     * dispositivo
     */
    private Date fechaUltimoRevisado;

    /**
     * Constructor vacio para JACKSON
     */
    public Licence() {
    }

    /**
     * Constructor por defecto.
     *
     * @param token Token de integridad
     * @param fechaActivacion Fecha de inicio de activacion
     * @param fechaFin Fecha de fin de licencia
     */
    public Licence(long token, Date fechaActivacion, Date fechaFin) {
        this.token = token;
        this.fechaInicio = fechaActivacion;
        this.fechaFin = fechaFin;
    }

    /**
     * Constructor para la activacion de la licencia.
     *
     * @param fromString codigo de activacion descifrado, basicamente el
     * toString de el mismo
     * @throws ParseException Si hay error en el parseo del codigo de activacion
     * al convertirlo en instancia de la licencia
     */
    public Licence(String fromString) throws ParseException {
        String inicio = fromString.substring(0, 10);
        fechaInicio = sdf.parse(inicio);
        fechaUltimoRevisado = sdf.parse(inicio);

        String fin = fromString.substring(11, 21);
        fechaFin = sdf.parse(fin);

        token = Long.parseLong(fromString.substring(22, fromString.length()));
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaUltimoRevisado() {
        return fechaUltimoRevisado;
    }

    public void setFechaUltimoRevisado(Date fechaUltimoRevisado) {
        this.fechaUltimoRevisado = fechaUltimoRevisado;
    }

    @Override
    public String toString() {
        return sdf.format(fechaInicio) + separator + sdf.format(fechaFin) + separator + token;
    }

    /**
     * Chequea la integridad de la licencia.
     *
     * @return true si la licencia es valida(integra), false cualquier otro caso
     */
    @JsonIgnore
    public boolean isLicenceValid() {
        String hash = SHA.hash256(this.toString());
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
