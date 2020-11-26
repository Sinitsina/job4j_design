package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = new ArrayList<>();
        List<String> dialog = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                answers.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Введите вопрос:");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            while (s != null) {
                dialog.add(s);
                if (s.equals(STOP)) {
                    s = reader.readLine();
                    while (!s.equals(CONTINUE)) {
                        dialog.add(s);
                        s = reader.readLine();
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
                s = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            for (String line: dialog) {
                writer.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
           e.printStackTrace();
       }

    }

    private static String botAnswer(List<String> answers) {
        Random rand = new Random();
        return answers.get(rand.nextInt(answers.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("/Users/olga/IdeaProjects/job4j_design/log.txt",
                "/Users/olga/IdeaProjects/job4j_design/chapter_002/botAnswers.txt");
        cc.run();
    }
}