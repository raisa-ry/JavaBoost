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

            Class<?> clazz = findLoadedClass(name);

            if (clazz == null) {

                if (name.startsWith("java.") || name.startsWith("jdk.")) {
                    return super.loadClass(name, resolve);
                } else if (isClassExists(name)) {
                    clazz = findClass(name);
                } else {
                    clazz = getParent().loadClass(name);
                }
            }

            if (resolve) {
                resolveClass(clazz);
            }
            return clazz;
        }
    }

    private boolean isClassExists(String name) {
        String resourcePath = name.replace('.', '/').concat(".class");
        return findResource(resourcePath) != null;
    }

}