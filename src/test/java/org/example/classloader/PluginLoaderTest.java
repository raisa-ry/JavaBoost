package org.example.classloader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.net.URL;
import java.nio.file.Path;

import static org.example.classloader.Util.createJarWithResource;
import static org.example.classloader.Util.createTestJar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PluginLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Test Parent-Last delegation logic for system classes")
    void testDelegationLogic() throws Exception {
        try (PluginLoader loader = new PluginLoader(new URL[0], ClassLoader.getSystemClassLoader())) {
            Class<?> stringClass = loader.loadClass("java.lang.String");
            assertNotEquals(loader, stringClass.getClassLoader(),
                    "System classes should be loaded by the parent classloader, not PluginLoader");
        }
    }

    @Test
    @DisplayName("Test loading a class from a JAR and ensuring it's loaded by PluginLoader")
    void testLoadClassFromJar() throws Exception {
        String className = "com.example.GeneratedClass";
        Path tempJar = createTestJar(className, tempDir);

        try (PluginLoader loader = new PluginLoader(new URL[]{tempJar.toUri().toURL()},
                ClassLoader.getSystemClassLoader())) {

            Class<?> clazz = loader.loadClass(className);

            assertNotNull(clazz);
            assertEquals(loader, clazz.getClassLoader(), "Class should be loaded by PluginLoader");
            assertEquals(className, clazz.getName());

            Object instance = clazz.getDeclaredConstructor().newInstance();
            Object result = clazz.getMethod("test").invoke(instance);
            assertEquals("test", result);
        }
    }

    @Test
    @DisplayName("Test resource loading priority (JAR vs parent classloader)")
    void testResourcePriority() throws Exception {
        String resourceName = "plugin.properties";
        Path tempJar = createJarWithResource(resourceName, "version=1.0", tempDir);

        try (PluginLoader loader = new PluginLoader(new URL[]{tempJar.toUri().toURL()},
                ClassLoader.getSystemClassLoader())) {

            URL resource = loader.findResource(resourceName);

            assertNotNull(resource, "Resource should be found in the JAR");
            assertTrue(resource.toString().contains(tempJar.getFileName().toString()),
                    "URL should point to the JAR file, indicating it was found there");
        }
    }

}