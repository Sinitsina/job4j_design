package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SimpleArrayTest {
    @Test
    public void whenAddThenGet() {
        String[] array = new String[10];
        SimpleArray<String> strings = new SimpleArray<>(array);
        strings.add("first");
        String rsl = strings.get(0);
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        String[] array = new String[3];
        SimpleArray<String> strings = new SimpleArray<>(array);
        strings.get(5);
    }

    @Test
    public void whenSetThenGet() {
        String[] array = new String[3];
        SimpleArray<String> strings = new SimpleArray<>(array);
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.set(1, "cat");
        String rsl = strings.get(1);
                assertThat(rsl, is("cat"));
    }

    @Test
    public void whenRemoveThenGet() {
        String[] array = new String[3];
        SimpleArray<String> strings = new SimpleArray<>(array);
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.remove(1);
        String rsl = strings.get(1);
        assertThat(rsl, is("third"));
    }
}