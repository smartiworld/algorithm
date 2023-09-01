package com.iworld.algorithm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/3/17 19:24
 */
public class CustomerClassloader extends ClassLoader {
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
//            URL resource = getClass().getResource(name);
//            InputStream inputStream = resource.openStream();
            FileInputStream fis = new FileInputStream("D:\\m\\gitw\\algorithm\\src\\com\\iworld\\algorithm\\CustomerClass.java");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel wbc = Channels.newChannel(baos);
            ByteBuffer by = ByteBuffer.allocate(1024);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
