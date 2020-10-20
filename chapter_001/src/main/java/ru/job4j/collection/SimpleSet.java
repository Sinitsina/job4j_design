package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> list = new SimpleArray<>();

    public void add(T model) {
        if (contains(model)) {
            list.add(model);
        }
    }

    public boolean contains(T model) {
        boolean res = true;
        for (T elem : list) {
            if (elem.equals(model)) {
                res = false;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = list.iterator();
        return iterator;
    }

}
