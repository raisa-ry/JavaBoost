package org.example.classloader;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ClassloaderTest {

    @Test
    void mapClassesToLoadersTest() {
        ModuleLayer.boot().modules().stream()
                .collect(Collectors.groupingBy(
                        m -> Optional.ofNullable(m.getClassLoader())
                                .map(ClassLoader::getName).orElse("bootstrap"),
                        Collectors.mapping(Module::getName,
                                Collectors.toCollection(TreeSet::new))))
                .entrySet().stream()
                .sorted(Comparator.comparingInt(
                        e -> List.of("bootstrap", "platform", "app").indexOf(e.getKey())))
                .map(e -> e.getKey() + "\n\t" + String.join("\n\t", e.getValue()))
                .forEach(System.out::println);
    }

    @Test
    void twoIdenticalClasses() throws IOException, ClassNotFoundException {
        URL url = new File("target/test-classes").toURI().toURL();

        Class<?> c1;
        Class<?> c2;

        try (URLClassLoader loader1 = new URLClassLoader(new URL[]{url}, null);
             URLClassLoader loader2 = new URLClassLoader(new URL[]{url}, null)) {

            c1 = loader1.loadClass("org.example.classloader.Dummy");
            c2 = loader2.loadClass("org.example.classloader.Dummy");
        }

        assertNotSame(c1, c2);
    }

    @Test
    void delegationTest() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.sun.source.doctree.AttributeTree", false, ClassLoader.getPlatformClassLoader());
        System.out.println(c.getClassLoader().getName());
        assertNotSame(c.getClassLoader(), ClassLoader.getPlatformClassLoader());
    }

}