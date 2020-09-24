package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.length != 0) {
            if (data[row].length == 0 && data.length < 2) {
                return false;
            }
            return column <= data[row].length;
            }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int res = 0;
        if (row < data.length && column < data[row].length) {
            res = data[row][column];
            column++;
            while (row < data.length && column >= data[row].length) {
                column = 0;
                row++;
            }
        } else if (row < data.length && column == 0) {
                row++;
                column = 0;
                res = data[row][column];
        }
        return res;
    }
}
