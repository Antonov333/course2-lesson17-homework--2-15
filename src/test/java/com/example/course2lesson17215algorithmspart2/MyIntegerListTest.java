package com.example.course2lesson17215algorithmspart2;

import com.example.course2lesson17215algorithmspart2.implementations.MyIntegerList;
import org.junit.jupiter.api.Test;

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

    }
