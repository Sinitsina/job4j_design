package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte number_1 = 8;
        short number_2 = 30000;
        int number_3 = 500000;
        long number_4 = 2L;
        float value_1 = 2.35f;
        double value_2 = 185457.90;
        char character = 'a';
        boolean result = true;

        LOG.debug("Numbers info: byte = {}, short = {}, int = {}, long = {}", number_1, number_2, number_3, number_4);
        LOG.debug("Information: float = {}, double = {}, char : {}, enabled : {}", value_1, value_2, character, result);

    }
}
