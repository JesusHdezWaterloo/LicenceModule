package com.jhw.licence.repo.app;

import com.jhw.licence.core.app.LicenceRepo;
import com.jhw.licence.core.domain.Licence;
import com.jhw.licence.core.main.CONFIG;
import com.jhw.utils.jackson.JACKSON;
import com.jhw.utils.security.AES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.inject.Inject;

/**
 * Implementacion de la Interfaz {@code LicenceRepo} para manejar el
 * comportamiento de las definiciones de su interfaz
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LicenceRepoImpl implements LicenceRepo {

    /**
     * Fichero donde se va a almacenar la licencia.
     */
    private File file = new File("licence.lic");

    /**
     * Constructor por defecto, usado par injectar.
     */
    @Inject
    public LicenceRepoImpl() {
    }

    /**
     * Lee la licencia del fichero.<\br>
     * Lee el arreglo de bytes almacenados, lo decifra con AES, el resultado lo
     * convierte String y lo parsea con JACKSON a un objeto Licencia.
     *
     * @return la licencia leida
     * @throws Exception si hay algun problema leyendo la licencias
     */
    @Override
    public Licence read() throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        byte[] arr = (byte[]) ois.readObject();
        fis.close();
        ois.close();
        return JACKSON.read(new String(AES.decipher(CONFIG.HARDCORE_PASSWORD, arr)), Licence.class);
    }

    /**
     * Guarda la licencia en el fichero.<\br>
     * Escribe el arreglo de bytes del resultado de cifrar con AES el toString
     * del JACKSON al objeto Licencia
     *
     * @param lic Licencia a guardar
     * @throws Exception si hay algun problema guardando la licencias
     */
    @Override
    public void write(Licence lic) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(AES.cipher(CONFIG.HARDCORE_PASSWORD, JACKSON.toString(lic).getBytes()));
        oos.close();
        fos.close();
    }

    /**
     * Getter del File donde se va a almacenar la licencia.
     *
     * @return File donde se va a almacenar la licencia.
     */
    public File getFile() {
        return file;
    }

    /**
     * Setter del File donde se va a almacenar la licencia.
     *
     * @param file donde se va a almacenar la licencia.
     */
    public void setFile(File file) {
        this.file = file;
    }

}
