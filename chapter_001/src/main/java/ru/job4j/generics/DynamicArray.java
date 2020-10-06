package ru.job4j.generics;

import java.util.*;

public class DynamicArray<T> implements Iterable<T> {
    private Object[] container;
    private int ind = 0;
    private int modCount = 0;

    public DynamicArray(Object[] container) {
        this.container = container;
    }

    public DynamicArray() {
        this.container = new Object[10];
    }

    public T get(int index) {
        Objects.checkIndex(index, ind);
        return (T) container[index];
    }

    public void checkCapacity() {
        if (ind > container.length - 1) {
            int newCapacity = ind + (ind >> 1);
            container = Arrays.copyOf(container, newCapacity);
        }
    }

    public void add(T model) {
        checkCapacity();
        Objects.checkIndex(ind, container.length);
        container[ind++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        Iterator<T> iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < ind;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[currentIndex++];
            }
        };
        return iterator;
    }
}
