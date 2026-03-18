package org.example.classloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Use try-with-resources to ensure the URLClassLoader is closed. This is essential for releasing file handles and unlocking the JAR file.
 * Nullify all references to plugin instances and classes once they are no longer needed. This allows the Garbage Collector to reclaim heap space and clear the associated metadata from Metaspace.
 */
public class PluginManager {

    public void runAndUnloadPlugin(String jarPath) throws Exception {

        isJarExists(jarPath);
        URL jarUrl = new File(jarPath).toURI().toURL();

        try (PluginLoader loader = new PluginLoader(new URL[]{jarUrl},
                ClassLoader.getSystemClassLoader())) {

            // Isolate execution so local variables (clazz, plugin)
            // disappear from the stack as soon as this block ends.
            execute(loader);

            System.out.println("Plugin executed. Stack is clear.");
        }
    }

    private void isJarExists(String jarPath) throws FileNotFoundException {
        if (Files.notExists(Path.of(jarPath))) {
            throw new FileNotFoundException("Plugin JAR not found: " + jarPath);
        }
    }

    // Method returns -> clazz and plugin references are popped from the stack
    private void execute(PluginLoader loader) throws Exception {
        Class<?> clazz = loader.loadClass("com.example.MyPlugin");
        Object plugin = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod("execute");
        method.invoke(plugin);
    }

}