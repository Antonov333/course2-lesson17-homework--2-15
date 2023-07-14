package com.example.course2lesson17215algorithmspart2.implementations;

import com.example.course2lesson17215algorithmspart2.exceptions.IntegerListException;
import com.example.course2lesson17215algorithmspart2.interfaces.IntegerList;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyIntegerList implements IntegerList {

    private Integer[] storage;
    private int count; // Will make it auto expanding

    public MyIntegerList() {
        this.storage = new Integer[100];
        count = 0;
    }

    public MyIntegerList(int capacity) {
        if (capacity < 1) {
            throw new IntegerListException("capacity invalid");
        }
        this.storage = new Integer[capacity];
        count = 0;
    }

    public MyIntegerList(Integer[] array) {
        storage = array;
        count = array.length;
    }

    public int getCapacity() {
        return storage.length;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new IntegerListException("null item detected");
        }
        int l = storage.length;
        if (count >= l) {
            this.doubleStorage();
        }
        storage[count] = item;
        count++;
        return item;
    }

    private void doubleStorage() {
        int lengthOfExistingArray = storage.length;
        Integer[] storageDoubled = new Integer[lengthOfExistingArray * 2];
        for (int i = 0; i < lengthOfExistingArray; i++) {
            storageDoubled[i] = storage[i];
        }
        storage = storageDoubled;
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

        checkIndexAndItem(index, item);

        Integer toReturn = item;
        if (count + 1 >= storage.length) {
            this.doubleStorage();
        }
        if (index == count - 1) {
            storage[count] = storage[index];
        } else {
            IntStream.iterate(count - 1, i -> i >= index, i -> i - 1).forEach(i -> storage[i + 1] = storage[i]);
        }
        storage[index] = item;
        count++;

        return toReturn;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndexAndItem(index, item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        Integer toReturn = null;
        for (int i = 0; i < count; i++) {
            if (storage[i] == item) {
                toReturn = item;
                for (int j = i; j < count - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[count - 1] = null;
                count--;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = storage[index];
        for (int j = index; j < count - 1; j++) {
            storage[j] = storage[j + 1];
        }
        storage[count - 1] = null;
        count--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        for (Integer i : storage) {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private void insert(int index, Integer item) {

    }

    @Override
    public int indexOf(Integer item) {
        int index = 0;
        for (index = 0; index < count; index++) {
            if (item.equals(storage[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        int index = count - 1;
        for (; index >= 0; index--) {
            if (item.equals(storage[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        boolean result = count == otherList.size();
        if (result) {
            for (int i = 0; i < count - 1; i++) {
                if (!storage[i].equals(otherList.get(i))) {
                    return false;
                }
            }
        }
        return result;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        Integer[] clearStorage = new Integer[count];
        storage = clearStorage;
        count = 0;
    }

    @Override
    public Integer[] toArray() {
        final Integer[] storage1 = storage;
        return storage1;
    }

    public void sortByInsertion() {
        if (count <= 1) {
            return;
        }

        Integer key;
        for (int i = 1; i < count; i++) {
            key = storage[i];
            for (int j = i; j > 0; j--) {
                if (storage[j - 1] > key) {
                    swapItems(j, j - 1);
                } else {
                    break;
                }
            }
        }
        return;
    }

    public void sortBySelection() {
        Integer element = storage[0];

        for (int i = 0; i < count - 2; i++) {
            element = storage[i];
            int iom = getIndexOfMin(storage, i + 1);
            if (element > storage[iom]) {
                swapItems(i, iom);
            }
        }
    }

    public void bubbleSort() {
        int length = storage.length ;
        if (length < 2) { return ; }
        for (int i = 0; i < length - 1 ; i++) {
            for (int j = 0 ; j < length-1-i ; j++) {
                if (storage[j] > storage[j+1]) {
                    swapItems(j,j+1);
                }
            }
        }
    }

    private int getIndexOfMin(Integer[] array, int startIndex) {
        int length = array.length;
        if (startIndex < 0 || startIndex >= length) { throw new IntegerListException("startIndex invalid"); }
        if (array == null || length == 0) { throw new IntegerListException("array is null or empty"); }

        if (length == 1) return 0;

        Integer minimum = array[0];
        int indexOfMin = 0;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] < minimum) {
                minimum = array[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    private void swapItems(int indexA, int indexB) {
        Integer tmp = storage[indexA];
        storage[indexA] = storage[indexB];
        storage[indexB] = tmp;
    }

    public void putArray(Integer[] array) {
        storage = Arrays.copyOf(array, array.length);
    }

}
