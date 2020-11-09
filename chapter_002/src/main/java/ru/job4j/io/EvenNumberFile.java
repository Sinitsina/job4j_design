package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder numbers = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                numbers.append((char) read);
            }
            String[] lines = numbers.toString().split(System.lineSeparator());
            for (String line : lines) {
                int i = Integer.parseInt(line);
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
