package ru.job4j.collection;

import org.junit.Assert;
import org.junit.Test;

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
        array.insert(150, "first");
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

    @Test
    public void whenAddTwoEqualElements() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        boolean rsl = array.insert(1, "first");
        assertThat(rsl, is(false));
    }

    @Test
    public void whenAddElementWithNullKey() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        boolean rsl = array.insert(0, "first");
        assertThat(rsl, is(true));
    }

    @Test
    public void whenAddElementWithNullKeyThenGet() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(0, "first");
        array.insert(0, "five");
        String rsl = array.get(0);
        assertThat(rsl, is("five"));
    }

    @Test
    public void whenAddNullKeyThenGet() {
        SimpleHashMap<String, String> array = new SimpleHashMap<>();
        array.insert(null, "first");
        String rsl = array.get(null);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenRemoveAndCheckSize() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(150, "first");
        array.insert(5, "five");
        array.insert(3, "three");
        array.delete(3);
        int rsl = array.size();
        assertThat(rsl, is(2));
    }

    @Test
    public void whenAddThenIt() {
        SimpleHashMap<Integer, String> array = new SimpleHashMap<>();
        array.insert(1, "first");
        var rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }
}