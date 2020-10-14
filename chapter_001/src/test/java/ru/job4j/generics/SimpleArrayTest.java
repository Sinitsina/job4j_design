package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.collection.SimpleArray;

public class SimpleArrayTest {
    @Test
    public void whenAddThenGet() {
        SimpleArray<String> strings = new SimpleArray<>();
        strings.add("first");
        String rsl = strings.get(0);
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> strings = new SimpleArray<>();
        strings.get(5);
    }

    @Test
    public void whenSetThenGet() {
        SimpleArray<String> strings = new SimpleArray<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.set(1, "cat");
        String rsl = strings.get(1);
                assertThat(rsl, is("cat"));
    }

    @Test
    public void whenRemoveThenGet() {
        SimpleArray<String> strings = new SimpleArray<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.remove(0);
        assertThat(strings.get(0), is("second"));
        assertThat(strings.get(1), is("third"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexForSetOutOfBounds() {
        SimpleArray<String> strings = new SimpleArray<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.set(3, "cat");
    }
}