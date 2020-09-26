package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int point = 0;
    private int next = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean numberIsEven(int point) {
        return numbers[point] % 2 == 0;
    }

    public boolean checkIfArrayHasEvenNumbers(int point) {
        for (int i = point; i <= numbers.length - 1; i++) {
            if (numberIsEven(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < numbers.length-1; i++) {
            if (checkIfArrayHasEvenNumbers(point)) {
                if (!numberIsEven(point)) {
                    while (!numberIsEven(point) && point < numbers.length-1) {
                        point++;
                    }
                }
                next = numbers[point];
                return true;
            }
        }
        return false;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point++;
        return next;
    }
}
