package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable {
    class Entry<K, V> {
        private K key;
        private V value;
        private int hash;
        private Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key)
                    && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private final static int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] values = new Entry[DEFAULT_CAPACITY];
    private int size;
    private double loadFactor = 0.75;
    private int modCount = 0;

    public boolean insert(K key, V value) {
    boolean res = true;
        if (key == null) {
            putForNullKey(value);
            modCount++;
        } else {
            int hash = SimpleHashMap.hash(key);
            int index = SimpleHashMap.indexFor(hash, values.length);

            for (Entry<K, V> e = values[index]; e != null; e = e.next) {
                K obj;
                obj = e.key;
                if (e.hash == hash && (obj == key || key.equals(obj))) {
                    e.value = value;
                    res = false;
                }
            }
            addEntry(hash, key, value, index);
            modCount++;
        }
        return res;
    }

    private void putForNullKey(V value) {
        for (Entry<K, V> e = values[0]; e != null; e = e.next) {
            if (e.key == null) {
                e.value = value;
            }
        }
        addEntry(0, null, value, 0);
    }

    private static int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h ^ (h >>> 16));
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private void addEntry(int hash, K key, V value, int index) {
        Entry<K, V> e = values[index];
        values[index] = new Entry<>(hash, key, value, e);
        if (size++ >= maxCapacity()) {
            resize(2 * values.length);
        }

    }

    private double maxCapacity() {
        return values.length * 0.75;
    }

    private void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        values = newTable;
    }

    private void transfer(Entry[] newTable) {
        Entry[] src = values;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry<K, V> e = src[j];
            if (null != e) {
                src[j] = null;
                int i = indexFor(e.hash, newCapacity);
                newTable[i] = e;
            }
        }
    }

    public V get(K key) {
        int index;
        if (key == null) {
            index = indexFor(0, values.length);
        } else {
            index = indexFor(key.hashCode(), values.length);
        }
            if (values[index] != null) {

                if (values[index].getKey() == null) {
                    return values[index].getValue();
                }
                if (values[index].getKey().equals(key)) {
                    return  values[index].getValue();
                }
            }
            return null;
    }

    public boolean delete(K key) {
        boolean res = false;
        int index;
        if (key == null) {
            index = indexFor(0, values.length);
        } else {
            index = indexFor(key.hashCode(), values.length);
        }
        if (values[index] != null) {
            if (values[index].getKey().equals(key)) {
                values[index] = null;
                size--;
                res = true;
            }
        }
        modCount++;
        return res;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        int expectedModCount = modCount;
        Iterator iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                while (values[currentIndex] == null && currentIndex < values.length - 1) {
                    currentIndex++;
                }
                return values[currentIndex] != null;
            }

            @Override
            public V next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                V el = values[currentIndex].getValue();
                currentIndex++;
                return el;
            }
        };
        return iterator;
    }
}

