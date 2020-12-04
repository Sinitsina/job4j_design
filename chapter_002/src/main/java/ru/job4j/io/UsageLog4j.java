package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte number1 = 8;
        short number2 = 30000;
        int number3 = 500000;
        long number4 = 2L;
        float value1 = 2.35f;
        double value2 = 185457.90;
        char character = 'a';
        boolean result = true;

        LOG.debug("Numbers info: "
                        + "byte = {}, "
                        + "short = {}, "
                        + "int = {}, "
                        + "long = {}",
                number1, number2, number3, number4);
        LOG.debug("Information: "
                        + "float = {}, "
                        + "double = {}, "
                        + "char : {}, "
                        + "enabled : {}",
                value1, value2, character, result);

    }
}
