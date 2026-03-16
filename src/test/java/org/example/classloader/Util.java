package org.example.classloader;

import net.bytebuddy.ByteBuddy;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public final class Util {

    private Util() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Path createTestJar(String className, Path tempDir) throws Exception {
        Path jarPath = tempDir.resolve("plugin-" + System.nanoTime() + ".jar");
        new ByteBuddy()
                .subclass(Object.class)
                .name(className)
                .defineMethod("test", String.class, net.bytebuddy.description.modifier.Visibility.PUBLIC)
                .intercept(net.bytebuddy.implementation.FixedValue.value("test"))
                .make()
                .toJar(jarPath.toFile());
        return jarPath;
    }

    public static Path createJarWithResource(String name, String content, Path tempDir) throws Exception {
        Path jarPath = tempDir.resolve("resource-" + System.nanoTime() + ".jar");
        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarPath.toFile()))) {
            JarEntry entry = new JarEntry(name);
            jos.putNextEntry(entry);
            jos.write(content.getBytes());
            jos.closeEntry();
        }
        return jarPath;
    }

}
