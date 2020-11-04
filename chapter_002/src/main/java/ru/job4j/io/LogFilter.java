package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader (new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                if (line.contains("404")) {
                    result.add(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                if (line.contains("404")) {
                    out.write(line + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        LogFilter.save(log, "res.txt");
    }
}
