package ru.job4j.io;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalysisTest {

    @Test
    public void whenPairWithComment() {
        String source = "./unavailable.csv";
        String target = "result.txt";
        Analysis test = new Analysis();
        test.unavailable(source, target);
        assertThat(Files.exists(Path.of("/Users/olga/IdeaProjects/job4j_design/chapter_002/result.txt")),
                is(true));
    }

}