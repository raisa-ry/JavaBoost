package org.example.collections;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ArrayListBehaviourTest {

    @Test
    void capacityTest() throws NoSuchFieldException, IllegalAccessException {
        Integer[] testData = new Integer[100];

        List<Integer> forLoopTest = new ArrayList<>();
        printCapacity(forLoopTest, "before For loop");
        for (Integer i : testData) {
            forLoopTest.add(i);
        }
        printCapacity(forLoopTest, "after for loop");
        printSeparatorLine();

        List<Integer> constructorTest = new ArrayList<>(Arrays.asList(testData));
        printCapacity(constructorTest, "pass in Constructor on creation");
        constructorTest.addLast(1);
        printCapacity(constructorTest, "when add one more");
        printSeparatorLine();

        List<Integer> addAllTest = new ArrayList<>();
        printCapacity(addAllTest, "before addAll()");
        addAllTest.addAll(Arrays.asList(testData));
        printCapacity(addAllTest, "after addAll()");
        addAllTest.addLast(1);
        printCapacity(addAllTest, "when add one more");
        printSeparatorLine();

        List<Integer> collectionsAddAllTest = new ArrayList<>();
        printCapacity(collectionsAddAllTest, "before Collections.addAll()");
        Collections.addAll(collectionsAddAllTest, testData);
        printCapacity(collectionsAddAllTest, "after Collections.addAll()");
    }

    // --add-opens java.base/java.util=ALL-UNNAMED
    private void printCapacity(List<?> list, String way) throws IllegalAccessException, NoSuchFieldException {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);
        System.out.println("Capacity " + way + ": " + elementData.length);
        System.out.println("Size: " + list.size());
    }

    private void printSeparatorLine() {
        System.out.println("\n===============================\n");
    }
}