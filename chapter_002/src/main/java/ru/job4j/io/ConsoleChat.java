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
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, CHARSET))) {
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
                ConsoleChat.whiteDialog(s, path);
                if (s.equals(STOP)) {
                    s = reader.readLine();
                    while (!s.equals(CONTINUE)) {
                        ConsoleChat.whiteDialog(s, path);
                        s = reader.readLine();
                    }
                    ConsoleChat.whiteDialog(s, path);
                }  else if (s.equals(OUT)) {
                    System.out.println("Чат окончен");
                    break;
                } else {
                    String res = ConsoleChat.botAnswer(answers);
                    System.out.println(res);
                    ConsoleChat.whiteDialog(res, path);
                }
                s = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String botAnswer(List<String> answers) {
        Random rand = new Random();
        return answers.get(rand.nextInt(answers.size()));
    }

    private static void whiteDialog(String line, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt",
                Charset.forName("WINDOWS-1251"), true))) {
            writer.append(line).append(System.lineSeparator());
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

