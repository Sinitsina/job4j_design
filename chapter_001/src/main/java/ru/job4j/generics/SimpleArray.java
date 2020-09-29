package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] data;
    private int ind = 0;

    public SimpleArray(T[] data) {
        this.data = data;
    }

    public void add(T model) {
        Objects.checkIndex(ind, data.length);
        data[ind++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, data.length);
        data[index] = model;

    }

    public void remove(int index) {
        Objects.checkIndex(index, data.length);
        for (int i = index; i < data.length; i++) {
            data[i - 1] = data[i];
            data[i] = null;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, data.length);
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < data.length && data[currentIndex] != null;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }
        };
        return iterator;
    }
}
