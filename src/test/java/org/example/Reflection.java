package org.example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Reflection {

    @Test
    void breakSingletonTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Singleton singleton1 = Singleton.getInstance();
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton singleton2 = constructor.newInstance();

        assertNotSame(singleton1, singleton2);
    }

}

class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}