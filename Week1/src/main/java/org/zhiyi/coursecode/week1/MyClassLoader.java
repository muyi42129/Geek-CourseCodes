package org.zhiyi.coursecode.week1;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{

    private byte[] origin; // 原始字节码
    private static final int READ_SIZE = 1024; // 每次读取字节大小

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 这里获取resources/Hello.xlass 输入流，不可以使用File的形式获取
        ClassPathResource resource = new ClassPathResource("Hello.xlass");
        try(InputStream inputStream = resource.getInputStream()) {
            byte[] bytes = new byte[READ_SIZE];
            int read = inputStream.read(bytes);
            while (read > 0) {
                // 取反获取字节码
                decode(bytes, read);
                read = inputStream.read(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (origin == null) {
            return null;
        }
        return defineClass(resource.getFilename().split("\\.")[0], origin, 0, origin.length);
    }

    /**
     * 按字节取反
     * origin 数组扩容
     * @param bytes
     * @param read
     */
    private void decode(byte[] bytes, int read) {
        int offset = 0;
        if (origin == null) {
            origin = new byte[read];
        } else {
            // 设置偏移量并扩容
            offset = origin.length;
            byte[] newBytes = new byte[origin.length + read];
            System.arraycopy(origin, 0, newBytes, 0, origin.length);
            origin = newBytes;
        }
        for (int i=0; i < read; i++, offset++) {
            origin[offset] = (byte) ~bytes[i];
        }
    }
}
