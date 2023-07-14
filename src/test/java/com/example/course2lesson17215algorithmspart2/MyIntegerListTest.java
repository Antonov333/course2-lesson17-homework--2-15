package com.example.course2lesson17215algorithmspart2;

import com.example.course2lesson17215algorithmspart2.implementations.MyIntegerList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MyIntegerListTest {

    private Random random() {
        return new Random() ;
    }

    private MyIntegerList getMyIntegerList() {
        return new MyIntegerList() ;
    }

    private MyIntegerList getMyIntegerList(int capacity) {
        return new MyIntegerList(capacity) ;
    }

    @Test
    public void addTest(){
        MyIntegerList myIntegerList = getMyIntegerList(2);
        myIntegerList.add(1);
        myIntegerList.add(1);
        myIntegerList.add(1);

        Integer testItem = random().nextInt(0,1000000);
        assertNotEquals(testItem, myIntegerList.get(myIntegerList.size()-1));
        myIntegerList.add(testItem) ;
        assertEquals(testItem, myIntegerList.get(myIntegerList.size()-1));
    }

    @Test
    public void addByIndex(){
        MyIntegerList testIntegerList = randomlyFilledIntegerList(5) ;
        int testIndex = testIntegerList.size()-1;
        Integer testItem = random().nextInt(1000000,2000000) ;
        testIntegerList.add(testIndex,testItem);
        assertEquals(testItem, testIntegerList.add(testIndex, testItem));
        assertEquals(testItem, testIntegerList.get(testIndex));

    }

    @Test
    public void removeByItemTest() {

        MyIntegerList testIntegerList = new MyIntegerList(5) ;

        for (int i = 0; i < random().nextInt(10,20); i++) {
            testIntegerList.add(random().nextInt(10,20));}

            Integer testItem = random().nextInt(50,60) ;
            int index = random().nextInt(0,testIntegerList.size() -1);

            testIntegerList.set(index, testItem) ;
            assertEquals(testItem, testIntegerList.get(index));

            assertEquals(testItem, testIntegerList.remove(testItem));
            assertEquals(null,testIntegerList.remove(testItem));
        }

        @Test
        public void removeByIndexTest() {

        MyIntegerList testIntegerList = randomlyFilledIntegerList(5) ;

            System.out.println("testIntegerList.size() = " + testIntegerList.size());
            int testIndex = random().nextInt(0, testIntegerList.size());
        Integer testItem = random().nextInt(1000000, 2000000) ;
        while (testIntegerList.contains(testItem)) {
            testItem = random().nextInt(1000000, 2000000) ;
        }
        testIntegerList.set(testIndex, testItem) ;
        assertEquals(testItem, testIntegerList.remove(testIndex));
        assertNotEquals(testItem, testIntegerList.get(testIndex));
        }

        @Test
        public void sortByInsertionTest() {
        MyIntegerList testIntegerList = randomlyFilledIntegerList(100000) ;
            long start = System.currentTimeMillis();
            testIntegerList.sortByInsertion();
            System.out.println(System.currentTimeMillis() - start);
        } //13750ms, 12933ms, 12856ms

        @Test
        public void sortBySelectionTest() {
        MyIntegerList myIntegerList = randomlyFilledIntegerList(100000) ;
            long start = System.currentTimeMillis();
            myIntegerList.sortByInsertion();
            System.out.println(System.currentTimeMillis() - start);
            // 13635 ms, 13223ms, 13274ms
        }

        @Test
        public void bubbleSortTest() {
            MyIntegerList myIntegerList = randomlyFilledIntegerList(100000) ;
            long start = System.currentTimeMillis();
            myIntegerList.bubbleSort();
            System.out.println(System.currentTimeMillis() - start);
            // 31578ms, 31688ms, 31529ms
        }

    private MyIntegerList randomlyFilledIntegerList(int size) {
        MyIntegerList integerList = new MyIntegerList(size);

        for (int i = 0; i < size; i++) {
            integerList.add(random().nextInt(0, 10000)) ;
        }
        return integerList ;
    }


}
