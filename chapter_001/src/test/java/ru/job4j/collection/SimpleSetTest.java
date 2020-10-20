package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddThenGet() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        boolean rsl = strings.contains("first");
        assertThat(rsl, is(false));
    }

    @Test
    public void whenAddTwoEqualThenGet() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        strings.add("first");
        boolean rsl = strings.contains("first");
        assertThat(rsl, is(false));
    }
}