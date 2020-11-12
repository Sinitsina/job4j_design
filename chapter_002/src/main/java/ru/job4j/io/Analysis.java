package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public static void unavailable(String source, String target) {
        String start = null;
        String finish = null;
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] words = line.split(" ");
                if ((line.contains("400") || line.contains("500")) && start == null) {
                    start = words[1];
                    continue;
                }

                if (!(line.contains("400") || line.contains("500")) && start != null) {
                    finish = words[1];
                }

                if (start != null && finish != null) {
                    result.add(start);
                    result.add(finish);
                    start = null;
                    finish = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (int i = 0; i < result.size(); i++) {
                out.write(result.get(i) + " ; " + result.get(i + 1) + System.lineSeparator());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis test = new Analysis();
        String source = "./unavailable.csv";
        String target = "target.txt";
        test.unavailable(source, target);

    }
}
