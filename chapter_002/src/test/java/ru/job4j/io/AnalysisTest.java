package ru.job4j.io;

import org.junit.Test;

public class AnalysisTest {

    @Test
    public void whenPairWithComment() {
        String source = "./unavailable.csv";
        String target = "result.txt";
        Analysis test = new Analysis();
        test.unavailable(source, target);
    }

}