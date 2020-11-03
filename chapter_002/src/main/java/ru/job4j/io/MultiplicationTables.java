package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTables {
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                table[j - 1][i - 1] = i * j;
            }
        }
        return table;
    }

    public static void main(String[] args) {
        int[][] res = multiple(9);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    String s = res[i][j] + " ";
                    out.write(s.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
