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
            Class cls = Class.forName("Config");
            ClassLoader classLoader = cls.getClassLoader();

            //ClassLoader classLoader = this.getClass().getClassLoader();

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

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}