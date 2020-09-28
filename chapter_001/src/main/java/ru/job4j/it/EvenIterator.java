package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int point = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean numberIsEven(int point) {
        return numbers[point] % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        while (point < numbers.length) {
            if (numbers[point] % 2 == 0) {
                return true;
            }
            point++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}
