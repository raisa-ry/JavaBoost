package org.example.classloader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.classloader.Util.createTestJar;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PluginManagerTest {

    @TempDir
    Path tempDir;

    private static final String PLUGIN_CLASS_NAME = "com.example.GeneratedPlugin";

    @Test
    @DisplayName("Test Parent-Last class loading isolation")
    void testParentLastIsolation() throws Exception {
        Path jarPath = createTestJar(PLUGIN_CLASS_NAME, tempDir);
        URL[] urls = {jarPath.toUri().toURL()};

        try (PluginLoader loader = new PluginLoader(urls, ClassLoader.getSystemClassLoader())) {
            Class<?> pluginClass = loader.loadClass(PLUGIN_CLASS_NAME);

            assertEquals(loader, pluginClass.getClassLoader(),
                    "Classes from the plugin JAR should be loaded by PluginLoader, not the parent");

            Class<?> stringClass = loader.loadClass("java.lang.String");
            assertNotEquals(loader, stringClass.getClassLoader(),
                    "System classes should be loaded by the parent classloader, not PluginLoader");
        }
    }

    @Test
    @DisplayName("Parallel loading test")
    void testParallelLoading() throws Exception {
        Path jarPath = createTestJar(PLUGIN_CLASS_NAME, tempDir);
        URL[] urls = {jarPath.toUri().toURL()};

        try (var loader = new PluginLoader(urls, ClassLoader.getSystemClassLoader())) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            try {
                int tasks = 20;
                CompletableFuture<?>[] futures = new CompletableFuture[tasks];
                CountDownLatch latch = new CountDownLatch(1);
                for (int i = 0; i < tasks; i++) {
                    futures[i] = CompletableFuture.runAsync(() -> {
                        try {
                            latch.await();
                            Class<?> clazz = loader.loadClass(PLUGIN_CLASS_NAME);
                            assertNotNull(clazz);
                        } catch (ClassNotFoundException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }, executor);
                }
                latch.countDown();
                assertDoesNotThrow(() -> CompletableFuture.allOf(futures).get(5, TimeUnit.SECONDS));
            } finally {
                executor.shutdownNow();
            }
        }
    }

}