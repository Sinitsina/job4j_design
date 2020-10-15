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
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
    //Метод poll() - должен возвращать первое значение и удалять его из коллекции.
    //Метод push(T value) - помещает значение в конец.
    //
    //Алгоритм.
    //
    //Данные очереди нужно хранить в ru.job4j.collection.SimpleStack. Для этого задания нужны два стека.
    //Представьте, что у вас стопка с тарелками. Вам нужно достать нижнюю тарелку. Для этого вы перекладываете все тарелки в другую стопку.
    //Стопка - это стек. Для операции извлечения первой тарелки нужны две стопки.
}
