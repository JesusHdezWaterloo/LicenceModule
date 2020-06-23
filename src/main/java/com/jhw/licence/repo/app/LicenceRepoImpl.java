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

public class LicenceRepoImpl implements LicenceRepo {

    private File file = new File("licence.lic");

    @Inject
    public LicenceRepoImpl() {
    }

    @Override
    public Licence read() throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        byte[] arr = (byte[]) ois.readObject();
        fis.close();
        ois.close();
        return JACKSON.read(new String(AES.decipher(CONFIG.HARDCORE_PASSWORD, arr)), Licence.class);
    }

    @Override
    public void write(Licence lic) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(AES.cipher(CONFIG.HARDCORE_PASSWORD, JACKSON.toString(lic).getBytes()));
        oos.close();
        fos.close();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
