package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPairWithComment() {
        String source = "./unavailable.csv";
        String target = "result.txt";
        Analysis.unavailable(source, target);
        assertThat(Files.exists(
                Path.of("/Users/olga/IdeaProjects/job4j_design/chapter_002/result.txt")),
                is(true));
    }

    @Test
    public void drop() throws IOException {
        File target = folder.newFile("target.txt");
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "200 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
        }
        Analysis.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01 ; 10:59:0111:01:02 ; 11:02:02"));
    }

}