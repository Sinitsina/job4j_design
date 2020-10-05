package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] data;
    private int ind = 0;

    public SimpleArray(T[] data) {
        this.data = data;
    }

    public SimpleArray() {
        this.data = (T[]) new Object[10];
    }

    public void add(T model) {
        Objects.checkIndex(ind, data.length);
        data[ind++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, ind);
        data[index] = model;

    }

    public void remove(int index) {
        Objects.checkIndex(index, ind);
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        data[ind--] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, ind);
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < ind;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }
        };
        return iterator;
    }
}
