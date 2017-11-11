package com.example.babyv20.atha.Model;

import android.util.Log;

import com.example.babyv20.atha.Model.Patients.Patient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Created by BABY v2.0 on 3/13/2017.
 */

public class DataCompression {
    private static final String TAG = DataCompression.class.getSimpleName();

    public static byte[] compressObject (Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            bos.close();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            //TODO: Handle the exception
            Log.e(TAG, ex.toString());
        }
       return bytes;
    }

    public static Object decompressObject(byte bytes[]){
        ByteArrayInputStream inputStream =null;
        ObjectInputStream obj = null;

        try {
            inputStream = new ByteArrayInputStream(bytes);
            obj = new ObjectInputStream(inputStream);

            return obj.readObject();
        }catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }catch(ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
