package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream i = classLoader.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(i));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.isEmpty() || !line.contains("=")) {
                    continue;
                }
                String[] words = line.split("=");
                values.put(words[0], words[1]);
            }
            i.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        this.values.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(
                "./chapter_002/src/main/resources/" + this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        final Config x = new Config("app.properties");
        x.load();
        x.print();
    }
}