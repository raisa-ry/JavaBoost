package org.example.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/*
 * Use the Parent-Last pattern if you need library isolation.
 * Always delegate system class loading to the parent class loader. If isolation is not required, override findClass instead of loadClass.
 */
public class PluginLoader extends URLClassLoader {

    static {
        ClassLoader.registerAsParallelCapable();
    }

    public PluginLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        synchronized (getClassLoadingLock(name)) {

            Class<?> c = findLoadedClass(name);

            if (c == null) {

                if (name.startsWith("java.") || name.startsWith("jdk.")) {
                    return super.loadClass(name, resolve);
                }

                if (isClassExist(name)) {
                    c = findClass(name);
                } else {
                    c = getParent().loadClass(name);
                }
            }

            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private boolean isClassExist(String name) {
        String resourcePath = name.replace('.', '/').concat(".class");
        return findResource(resourcePath) != null;
    }

}


