package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddThenGet() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        String rsl = strings.get(0);
        assertThat(rsl, is("first"));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenAddTwoEqualThenGet() {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("first");
        strings.add("second");
        strings.add("first");
        String rsl = strings.get(2);
    }
}