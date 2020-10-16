package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        int index = 0;
        while (index < size) {
            out.push(in.pop());
            index++;
        }
        size--;

        T res = out.pop();
        index = 0;

        while (index < size) {
            in.push(out.pop());
            index++;
        }

        return res;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
