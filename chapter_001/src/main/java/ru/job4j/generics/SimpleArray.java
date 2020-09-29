package ru.job4j.generics;

import java.util.Arrays;
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
        Objects.checkIndex(index, ind);
        data[index] = model;

    }

    public SimpleArray<T> remove(int index) {
        Objects.checkIndex(index, ind);
        T[] result = (T[]) new Object[data.length - 1];
        System.arraycopy(data, 0, result, 0, index);
        System.arraycopy(data, index + 1, result, index, data.length - 1 - index);
        return new SimpleArray<>(result);
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
                return currentIndex < ind && data[currentIndex] != null;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }
        };
        return iterator;
    }
}
