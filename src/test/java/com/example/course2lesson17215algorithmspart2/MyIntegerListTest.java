package com.example.course2lesson17215algorithmspart2;

import com.example.course2lesson17215algorithmspart2.exceptions.IntegerListException;
import com.example.course2lesson17215algorithmspart2.implementations.MyIntegerList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class MyIntegerListTest {

    private Random random() {
        return new Random();
    }

    private MyIntegerList getMyIntegerList() {
        return new MyIntegerList();
    }

    private MyIntegerList getMyIntegerList(int capacity) {
        return new MyIntegerList(capacity);
    }

    @Test
    public void addTest() {
        MyIntegerList myIntegerList = getMyIntegerList(2);
        myIntegerList.add(1);
        myIntegerList.add(1);
        myIntegerList.add(1);

        Integer testItem = random().nextInt(0, 1000000);
        assertNotEquals(testItem, myIntegerList.get(myIntegerList.size() - 1));
        myIntegerList.add(testItem);
        assertEquals(testItem, myIntegerList.get(myIntegerList.size() - 1));
    }

    @Test
    public void addByIndex() {
        MyIntegerList testIntegerList = randomlyFilledIntegerList(5);
        int testIndex = testIntegerList.size() - 1;
        Integer testItem = random().nextInt(1000000, 2000000);
        testIntegerList.add(testIndex, testItem);
        assertEquals(testItem, testIntegerList.add(testIndex, testItem));
        assertEquals(testItem, testIntegerList.get(testIndex));

    }

    @Test
    public void removeByItemTest() {

        MyIntegerList testIntegerList = new MyIntegerList(5);

        for (int i = 0; i < random().nextInt(10, 20); i++) {
            testIntegerList.add(random().nextInt(10, 20));
        }

        Integer testItem = random().nextInt(50, 60);
        int index = random().nextInt(0, testIntegerList.size() - 1);

        testIntegerList.set(index, testItem);
        assertEquals(testItem, testIntegerList.get(index));

        assertEquals(testItem, testIntegerList.remove(testItem));
        assertNull(testIntegerList.remove(testItem));
    }

    @Test
    public void removeByIndexTest() {

        MyIntegerList testIntegerList = randomlyFilledIntegerList(random().nextInt(5, 1000));

        int testIndex = random().nextInt(0, testIntegerList.size());
        Integer testItem = random().nextInt(1000000, 2000000);
        while (testIntegerList.contains(testItem)) {
            testItem = random().nextInt(1000000, 2000000);
        }
        testIntegerList.set(testIndex, testItem);
        assertEquals(testItem, testIntegerList.remove(testIndex));
        assertNotEquals(testItem, testIntegerList.get(testIndex));
    }

    @Test
    public void compareSortSpeed() {
        int testListSize = 100000;
        MyIntegerList testSample = randomlyFilledIntegerList(testListSize);

        System.out.println("\nSORT DURATION TIME COMPARING\n");

        MyIntegerList testSampleCopyForSelectionSort =
                new MyIntegerList(Arrays.copyOf(testSample.toArray(), testListSize));
        MyIntegerList testSampleCopyForInsertionSort =
                new MyIntegerList(Arrays.copyOf(testSample.toArray(), testListSize));
        MyIntegerList testSampleCopyForBubbleSort =
                new MyIntegerList(Arrays.copyOf(testSample.toArray(), testListSize));

        System.out.print("Selection sort duration (ms): ");
        long start = System.currentTimeMillis();
        testSampleCopyForSelectionSort.sortBySelection();
        System.out.println(System.currentTimeMillis() - start);

        System.out.print("\nInsertion sort duration (ms): ");
        start = System.currentTimeMillis();
        testSampleCopyForInsertionSort.sortByInsertion();
        System.out.println(System.currentTimeMillis() - start);

        System.out.print("\nBubble sort duration (ms): ");
        start = System.currentTimeMillis();
        testSampleCopyForBubbleSort.bubbleSort();
        System.out.println(System.currentTimeMillis() - start);

    }

    @Test
    public void sortTest() {
        MyIntegerList testList = randomlyFilledIntegerList(100000);
        long start = System.currentTimeMillis();
        testList.sort();
        System.out.println("Sort duration: " + (System.currentTimeMillis() - start));
    }


    private MyIntegerList randomlyFilledIntegerList(int size) {
        MyIntegerList integerList = new MyIntegerList(size);
        for (int i = 0; i < size; i++) {
            integerList.add(random().nextInt(0, 10000));
        }
        return integerList;
    }

    @Test
    public void indexOfTest() {
        MyIntegerList testIntegerList = randomlyFilledIntegerList(50);
        Integer testItem;

        do {
            testItem = random().nextInt(0, 10000);
        }
        while (testIntegerList.contains(testItem));

        int indexCount = 3;
        int[] indexes = new int[indexCount + 1];
        indexes[0] = -1;
        for (int i = 1; i <= indexCount; i++) {
            indexes[i] = random().nextInt(indexes[i - 1] + 1, testIntegerList.size() - 1);
            testIntegerList.set(indexes[i], testItem);
        }
        assertEquals(indexes[1], testIntegerList.indexOf(testItem));
    }

    @Test
    public void lastIndexOfTest() {
        MyIntegerList testInegerList = randomlyFilledIntegerList(15);
        int firstTestPatternPosition = random().nextInt(0, 5);
        int middleTestPatterPosition = random().nextInt(6, 10);
        int lastTestPatternPosition = random().nextInt(11, 15);

        Integer testPattern = random().nextInt(0, 10000);

        while (testInegerList.contains(testPattern)) {
            testPattern = random().nextInt(0, 1000000);
        }

        testInegerList.set(firstTestPatternPosition, testPattern);
        testInegerList.set(middleTestPatterPosition, testPattern);
        testInegerList.set(lastTestPatternPosition, testPattern);

        assertEquals(lastTestPatternPosition, testInegerList.lastIndexOf(testPattern));

        Integer missingPattern = random().nextInt(1000000);
        while (testInegerList.contains(missingPattern)) {
            missingPattern = random().nextInt(1000000);
        }

        assertEquals(testInegerList.lastIndexOf(missingPattern), -1);

    }

    @Test
    public void equalsTest() {
        int testListSize = random().nextInt(10, 1000);
        MyIntegerList testIntegerList = randomlyFilledIntegerList(testListSize);
        MyIntegerList sameIntegerList = new MyIntegerList(Arrays.copyOf(testIntegerList.toArray(), testListSize));
        MyIntegerList differentInegerList = randomlyFilledIntegerList(testListSize + 1);
        assertTrue(testIntegerList.equals(sameIntegerList));
        assertFalse(testIntegerList.equals(differentInegerList));
    }

    @Test
    public void getTest() {
        MyIntegerList testIntegerList = randomlyFilledIntegerList(random().nextInt(5, 50));
        Integer[] array = testIntegerList.toArray();
        int index = random().nextInt(0, testIntegerList.size() - 1);
        assertEquals(array[index], testIntegerList.get(index));

        int capacity = random().nextInt(10, 100);
        testIntegerList = randomlyFilledIntegerList(capacity);
        MyIntegerList finalTIL = testIntegerList;
        assertThrows(IntegerListException.class, () -> finalTIL.get(-1));
        assertThrows(IntegerListException.class, () -> finalTIL.get(finalTIL.size()));
    }

    @Test
    public void setTest() {
        MyIntegerList testIntegerList = randomlyFilledIntegerList(random().nextInt(5, 50000));
        Integer testItem = random().nextInt(1000000);
        while (testIntegerList.contains(testItem)) {
            testItem = random().nextInt(1000000);
        }
        int index = random().nextInt(0, testIntegerList.size() - 1);
        Integer returnedItem = testIntegerList.set(index, testItem);
        assertEquals(testItem, returnedItem);
        assertEquals(testItem, testIntegerList.get(index));

        Integer finalTestItem = testItem;
        assertThrows(IntegerListException.class, () -> testIntegerList.set(-1, finalTestItem));
        assertThrows(IntegerListException.class, () -> testIntegerList.set(testIntegerList.size() + 1, finalTestItem));
    }

    @Test
    public void isEmptyTest() {
        MyIntegerList emptyList = new MyIntegerList();
        assertTrue(emptyList.isEmpty());
        MyIntegerList listWithElements = randomlyFilledIntegerList(5);
        assertFalse(listWithElements.isEmpty());
    }

    @Test
    public void sizeTest() {
        int testSize = random().nextInt(1, 100000);
        MyIntegerList testList = randomlyFilledIntegerList(testSize);
        assertEquals(testSize, testList.size());
    }

    @Test
    public void clearTest() {
        int testSize = random().nextInt(5, 5000);
        MyIntegerList testList = randomlyFilledIntegerList(testSize);
        assertNotNull(testList.get(random().nextInt(0, testSize)));
        testList.clear();
        assertEquals(0, testList.size());
        assertThrows(IntegerListException.class, () -> testList.get(0));
    }

    @Test
    public void toArrayTest() {
        int testArraySize = random().nextInt(1, 100000);
        Integer[] sampleArray = new Integer[testArraySize];
        for (Integer s : sampleArray
        ) {
            s = random().nextInt(100000);
        }
        MyIntegerList testIntegerList = new MyIntegerList(sampleArray);
        Arrays.equals(sampleArray, testIntegerList.toArray());
    }

    @Test
    public void binarySearchTest() {

        MyIntegerList testIntegerList = randomlyFilledIntegerList(random().nextInt(100, 1000));
        testIntegerList.sort();
        Integer testItem = testIntegerList.get(random().nextInt(0, testIntegerList.size()));
        assertEquals(testItem, testIntegerList.get(testIntegerList.binarySearch(testItem)));
        Integer missingItem = random().nextInt(100000, 5000000);
        while (testIntegerList.contains(missingItem)) {
            missingItem = random().nextInt(100000, 5000000);
        }
        assertTrue(testIntegerList.binarySearch(missingItem) < 0);
    }

    @Test
    public void containsTest() {
        MyIntegerList testList = randomlyFilledIntegerList(random().nextInt(1, 10000));
        Integer testItem = testList.get(random().nextInt(0, testList.size() - 1));
        assertTrue(testList.contains(testItem));
        Integer missingItem = 100 + random().nextInt(10, 100);
        for (int i = 0; i < testList.size(); i++) {
            testList.set(i, testList.get(i) % 100);
        }
        assertFalse(testList.contains(testItem));
    }

}
