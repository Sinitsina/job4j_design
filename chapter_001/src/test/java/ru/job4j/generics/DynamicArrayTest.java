package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayTest {
    @Test
    public void whenAddThenGet() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        DynamicArray<String> array = new DynamicArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        DynamicArray<String> array = new DynamicArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void whenAddMoreThanTenElementsThenGetLast() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("one");
        array.add("two");
        array.add("three");
        array.add("four");
        array.add("five");
        array.add("six");
        array.add("seven");
        array.add("eight");
        array.add("nine");
        array.add("ten");
        array.add("eleven");
        String rsl = array.get(10);
        assertThat(rsl, is("eleven"));
    }

}