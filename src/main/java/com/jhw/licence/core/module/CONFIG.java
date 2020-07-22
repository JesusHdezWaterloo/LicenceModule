package com.jhw.licence.core.module;

import com.jhw.utils.security.SerialNumber;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CONFIG {

    public static final int DIFICULTY = 4; 
    public static final byte[] HARDCORE_PASSWORD = SerialNumber.getUID();
}
