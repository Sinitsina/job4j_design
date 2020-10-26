package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry> {
    class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private final static int DEFAULT_CAPACITY = 16;
    private int size;
    private Entry<K, V>[] values = new Entry[DEFAULT_CAPACITY];

    public boolean insert(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i].setValue(value);
                insert = false;
            }
        }
        if (insert) {
            if (size == values.length) {
                int newSize = values.length * 2;
                values = Arrays.copyOf(values, newSize);
            }
            values[size++] = new Entry<K, V>(key, value);
        }
        return insert;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey().equals(key)) {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public boolean delete(K key) {
        boolean res = false;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey().equals(key)) {
                values[i] = null;
                size--;
                for (int j = i; j < size; j++) {
                    values[j] = values[j + 1];
                }
                res = true;
            }
        }
        return res;
    }

    @Override
    public Iterator<SimpleHashMap.Entry> iterator() {

        Iterator<SimpleHashMap.Entry> iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Entry next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return values[currentIndex++];
            }
        };
        return iterator;
    }
}

