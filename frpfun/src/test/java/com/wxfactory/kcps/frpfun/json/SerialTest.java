package com.wxfactory.kcps.frpfun.json;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.SerializeUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wxfactory.kcps.frpfun.entity.FrpConfig;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.auths.TokenAuth;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.XtcpFcc;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xhvvvv
 * @time 2024/5/30
 */
public class SerialTest {
    
    
    /**
     * 
     */
    @Test
    public void xv_Tyou38D2(){
        FrpConfig shit = new TcpFcc("38.0.101.76", 7000);
        shit.setAuthentication(new TokenAuth("kldsfkal"));
        
        String jsonStr = JSON.toJSONString(shit);
        System.out.println(jsonStr);
        
        FrpConfig frpConfig = JSONObject.parseObject(jsonStr, TcpFcc.class);
        System.out.println(frpConfig);
    }
    
    /**
     * 
     */
    @Test
    public void xv_FGZjrkuO(){
        TcpFcc shit = new TcpFcc("38.0.101.76", 7000);
        shit.setAuthentication(new TokenAuth("kldsfkal"));
        
        byte[] date = SerializeUtil.serialize(shit);
        
        FileUtil.writeBytes(date,"D:\\Programmer\\EPOW_ALL\\Kotlin\\kcps\\frpfun\\build\\shit.javas");
        
    }
    
    /**
     * 
     */
    @Test
    public void xv_VphmoEfb(){
        byte[] date = FileUtil.readBytes("D:\\Programmer\\EPOW_ALL\\Kotlin\\kcps\\frpfun\\build\\shit.javas");
        
        TcpFcc shit =SerializeUtil.deserialize(date, TcpFcc.class);
    }
    /**
     * 
     */
    @Test
    public void xv_EsIZ8WJO(){
        TcpFcc shit = new TcpFcc("38.0.101.76", 7000);
        shit.setAuthentication(new TokenAuth("kldsfkal"));
        
        byte[] date = SerializeUtil.serialize(shit);
        TcpFcc shit2 =SerializeUtil.deserialize(date, TcpFcc.class);
    }
    /**
     * 
     */
    @Test
    public void xv_I9b7eRM3(){
        TcpFcc shit = new TcpFcc("38.0.101.76", 7000);
        shit.setAuthentication(new TokenAuth("kldsfkal"));
        // 序列化为字节数组
        byte[] serializedPerson = null;
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
            out.writeObject(shit);
            serializedPerson = byteOut.toByteArray();
           
        } catch (IOException i) {
            i.printStackTrace();
        }
        
        // 反序列化为对象
        if (serializedPerson != null) {
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(serializedPerson);
                 ObjectInputStream in = new ObjectInputStream(byteIn)) {
                TcpFcc deserializedPerson = (TcpFcc) in.readObject();
                
                System.out.println("Deserialized Person: " + deserializedPerson);
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Person class not found");
                c.printStackTrace();
            }
        }
    }
    
    
    /**
     * 
     */
    @Test
    public void xv_ABgqtU6q(){
        List<FrpConfigC> list = new ArrayList<>();
        TcpFcc shit = new TcpFcc("38.0.101.76", 7000);
        shit.setAuthentication(new TokenAuth("kldsfkal"));
        list.add(shit);
        
        XtcpFcc xtcpFcc = new XtcpFcc("localhost",2333);
        list.add(xtcpFcc);
        
        // 序列化为字节数组
        byte[] serializedPerson = null;
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
            out.writeObject(list);
            serializedPerson = byteOut.toByteArray();
            
        } catch (IOException i) {
            i.printStackTrace();
        }
        
        // 反序列化为对象
        if (serializedPerson != null) {
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(serializedPerson);
                 ObjectInputStream in = new ObjectInputStream(byteIn)) {
                List deserializedPerson = (List) in.readObject();
                
                System.out.println("Deserialized Person: " + deserializedPerson);
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Person class not found");
                c.printStackTrace();
            }
        }
    }
}
