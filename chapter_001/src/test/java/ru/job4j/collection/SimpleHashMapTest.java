package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whenAddThenGet() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        String rsl = array.get(1);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        SimpleHashMap.Entry rsl = array.iterator().next();
        assertThat(rsl.getValue(), is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.iterator().next();
    }

    @Test
    public void whenAddTwoElementsThenGet() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        array.insert(5, "five");
        String rsl = array.get(5);
        assertThat(rsl, is("five"));
    }

    @Test
    public void whenAddThenRemove() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        array.insert(5, "five");
        array.insert(3, "three");
        boolean rsl = array.delete(3);
        assertThat(rsl, is(true));
    }

    @Test
    public void whenAddThenRemoveThenGet() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        array.insert(5, "five");
        array.insert(3, "three");
        array.delete(3);
        String rsl = array.get(3);
        Assert.assertNull(rsl);
    }

}