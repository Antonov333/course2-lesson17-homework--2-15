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
        System.arraycopy(storage, 0, storageDoubled, 0, lengthOfExistingArray);
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

    public boolean containsOld(Integer item) {
        for (Integer i : storage) {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageTmp = Arrays.copyOf(storage, count);
        MyIntegerList listTmp = new MyIntegerList(storageTmp);
        listTmp.sort();
        return (listTmp.binarySearch(item) >= 0);
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
        checkIndex(index);
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
    }

    public void sortBySelectionVector() {
        ValueAndIndex valueAndIndex = new ValueAndIndex();
        for (int i = 0; i < count; i++) {
            valueAndIndex = getMin(i);
            if (valueAndIndex.getValue() < storage[i]) {
                swapItems(i, valueAndIndex.getIndex());
            }
        }
    }

    public void sortBySelection() {
        int indexOfMin;
        for (int i = 0; i < count; i++) {
            indexOfMin = getIndexOfMin(i);
            if (storage[i] > storage[indexOfMin]) {
                swapItems(i, indexOfMin);
            }
        }
    }

    public void sort() {
        sortBySelection();
    }

    private int getIndexOfMin(int startIndex) {
        checkIndex(startIndex);
        int result = startIndex;
        for (int i = startIndex; i < count; i++) {
            if (storage[result] > storage[i]) {
                result = i;
            }
        }
        return result;
    }

    private ValueAndIndex getMin(int startIndex) {
        checkIndex(startIndex);
        ValueAndIndex result = new ValueAndIndex(storage[startIndex], startIndex);
        for (int i = startIndex; i < count; i++) {
            if (result.getValue() > storage[i]) {
                result.setIndex(i);
                result.setValue(storage[i]);
            }
        }
        return result;
    }

    class ValueAndIndex {
        private Integer value;
        private int index;

        public ValueAndIndex() {
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ValueAndIndex{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public ValueAndIndex(Integer value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public void bubbleSort() {
        int length = storage.length;
        if (length < 2) {
            return;
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (storage[j] > storage[j + 1]) {
                    swapItems(j, j + 1);
                }
            }
        }
    }

    private void swapItems(int indexA, int indexB) {
        Integer tmp = storage[indexA];
        storage[indexA] = storage[indexB];
        storage[indexB] = tmp;
    }

    private void putArray(Integer[] array) {
        storage = Arrays.copyOf(array, array.length);
    }

    public Integer binarySearch(Integer item) {
        return Arrays.binarySearch(toArray(), item);
    }
}
