package com.wxfactory.kcps.frpfun.util;

import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xhvvvv
 * @time 2024/5/30
 */
public class SerialUtil {
    
    public static byte[] serialObject(Object o) throws IOException {
        try (
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteOut)
        ) {
            out.writeObject(o);
            return byteOut.toByteArray();
        }
    }
    
    public static <T> T deSerialObject(byte[] o) throws IOException, ClassNotFoundException {
        if (o != null) {
            try (
                    ByteArrayInputStream byteIn = new ByteArrayInputStream(o);
                    ObjectInputStream in = new ObjectInputStream(byteIn)
            ) {
                T t = (T) in.readObject();
                return t;
            }
        }
        return null;
    }
    
    public static void serialObject(Object o , File dest) throws IOException {
        try (
                FileOutputStream fileOut = new FileOutputStream(dest);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            out.writeObject(o);
        } 
    }
    
    public static <T> T deSerialObject(File orign) throws IOException, ClassNotFoundException {
        if (orign != null && orign.exists()) {
            // 反序列化
            try (
                    FileInputStream fileIn = new FileInputStream(orign);
                 ObjectInputStream in = new ObjectInputStream(fileIn)
            ) {
                T t = (T) in.readObject();
                return t;
            } 
        }
        
        return null;
    }
    
    
}
