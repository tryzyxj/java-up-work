package com.yxjajxy.week1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class helloClass = myClassLoader.findClass("Hello.xlass");

        Object hello = helloClass.newInstance();

        Method method = helloClass.getMethod("hello");

        method.invoke(hello);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (!name.endsWith(".xlass")) {
            return super.findClass(name);
        } else {
            try (InputStream in = this.getClass().getResourceAsStream("/week1/" + name);
                 ByteArrayOutputStream data = new ByteArrayOutputStream()) {

                byte[] tmp = new byte[1024];
                int count = 0;
                while ((count = in.read(tmp)) > -1) {
                    data.write(tmp, 0, count);
                }

                byte[] clazz = data.toByteArray();
                for (int i = 0; i < clazz.length; i ++) {
                    clazz[i] = (byte) (255 - clazz[i]);
                }
                return defineClass(name.substring(0, name.indexOf(".")), clazz, 0, clazz.length);

            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new ClassNotFoundException();
        }
    }

}
