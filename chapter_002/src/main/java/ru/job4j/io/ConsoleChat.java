package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    public static final Charset CHARSET = Charset.forName("WINDOWS-1251");
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = new ArrayList<>();
        List<String> dialog = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, CHARSET))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                answers.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Введите вопрос:");

            String s = ConsoleChat.reading();
            while (s != null) {
                dialog.add(s);
                if (s.equals(STOP)) {
                    s = ConsoleChat.reading();
                    while (!s.equals(CONTINUE)) {
                        dialog.add(s);
                        s = ConsoleChat.reading();
                    }
                    dialog.add(s);
                } else if (s.equals(OUT)) {
                    System.out.println("Чат окончен");
                    break;
                } else {
                    String res = ConsoleChat.botAnswer(answers);
                    System.out.println(res);
                    dialog.add(res);
                }
                s = ConsoleChat.reading();
            }

        ConsoleChat.writing(dialog, path);
    }

    private static String botAnswer(List<String> answers) {
        Random rand = new Random();
        return answers.get(rand.nextInt(answers.size()));
    }

    private static String reading() {
        String s = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            s = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private static void writing(List<String> dialog, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,
                CHARSET, true))) {
            for (String line: dialog) {
                writer.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("/Users/olga/IdeaProjects/job4j_design/log.txt",
                "/Users/olga/IdeaProjects/job4j_design/chapter_002/botAnswers.txt");
        cc.run();
    }
}

