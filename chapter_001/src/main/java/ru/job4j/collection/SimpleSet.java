package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> list = new SimpleArray<>();
    private int size;
    private int ind;

    public void add(T model) {
        if (size == 0) {
            list.add(model);
            size++;
        } else {
            int i = 0;
            while (i <= size) {
                if (list.get(i).equals(model)) {
                    list.set(i, model);
                } else {
                    list.add(model);
                    size++;
                }
                break;
            }
        }
        ind++;
    }

    public T get(int index) {
        Objects.checkIndex(index, ind);
        return list.get(index);
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
                return list.get(currentIndex++);
            }
        };
        return iterator;
    }
}
