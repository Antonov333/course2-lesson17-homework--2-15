package com.example.course2lesson17215algorithmspart2.implementations;

import com.example.course2lesson17215algorithmspart2.exceptions.IntegerListException;
import com.example.course2lesson17215algorithmspart2.interfaces.IntegerList;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyIntegerList implements IntegerList {

    private Integer[] storage;
    private int count ; // Will make it auto expanding

    public MyIntegerList() {
        this.storage = new Integer[100] ;
        count = 0;
    }

    public MyIntegerList(int capacity) {
        if (capacity < 1) {
            throw new IntegerListException("capacity invalid");
        }
        this.storage = new Integer[capacity] ;
        count = 0;
    }

    public int getCapacity() {
        return storage.length ;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {throw new IntegerListException("null item detected") ; }
        int l = storage.length ;
        if (count>=l) {this.doubleStorage();}
        storage[count] = item ;
        count++ ;
        return item;
    }

    private void doubleStorage() {
        int lengthOfExistingArray = storage.length ;
        Integer[] storageDoubled = new Integer[lengthOfExistingArray * 2] ;
        for (int i = 0; i < lengthOfExistingArray; i++) {
            storageDoubled[i] = storage[i] ;
        }
        storage = storageDoubled ;
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IntegerListException("item is null");
        }
    }

    private void checkIndexAndItem(int index, Integer item) {
        checkIndex(index);
        checkItem(item);

    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IntegerListException("index invalid");
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        Integer toReturn = item;
        checkIndexAndItem(index + 1, item);
        if(count>=storage.length) {this.doubleStorage();}
        IntStream.iterate(count - 1, i -> i >= index, i -> i - 1).forEach(i -> storage[i + 1] = storage[i]);
        count++;
        storage[index] = item;
        return toReturn;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndexAndItem(index, item);
        storage[index] = item ;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        Integer toReturn = null;

        for (int i = 0; i < count; i++) {
            if (storage[i] == item) {
                toReturn = item ;
                for (int j = i; j < count; j++) {
                    storage[j] = storage [j+1];
                }
                storage[count-1] = null ;
                count-- ;
                break;
            }
        }

        return toReturn;
    }

    @Override
    public Integer remove(int index) {
        return null;
    }

    @Override
    public boolean contains(Integer item) {
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        return 0;
    }

    @Override
    public int lastIndexOf(Integer item) {
        return 0;
    }

    @Override
    public Integer get(int index) {
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Integer[] toArray() {
        return new Integer[0];
    }
}
