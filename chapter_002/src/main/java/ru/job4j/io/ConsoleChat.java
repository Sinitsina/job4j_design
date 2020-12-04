package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    public static final Charset CHARSET = Charset.forName("WINDOWS-1251");
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
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
        List<String> answers = ConsoleChat.readAnswers(botAnswers);
        List<String> dialog = new ArrayList<>();
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
                }  else if (s.equals(OUT)) {
                    System.out.println("Чат окончен");
                    break;
                } else {
                    String res = ConsoleChat.botAnswer(answers);
                    System.out.println(res);
                }
                s = reader.readLine();
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }

        ConsoleChat.writing(dialog, path);
    }

    private static String botAnswer(List<String> answers) {
        Random rand = new Random();
        return answers.get(rand.nextInt(answers.size()));
    }

    private static List<String> readAnswers(String botAnswers) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, CHARSET))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                answers.add(line);
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
        return answers;
    }

    private static void writing(List<String> dialog, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            for (String line: dialog) {
                writer.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("/Users/olga/IdeaProjects/job4j_design/log.txt",
                "/Users/olga/IdeaProjects/botAnswers.txt");
        cc.run();
    }
}

