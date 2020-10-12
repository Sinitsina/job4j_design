package ru.job4j.generics.ru.job4j.generics;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedTest {
    @Test
    public void whenAddThenGet() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    /*@Test
    public void whenAddMoreThanTenElementsThenGetLast() {
        SimpleLinked<String> array = new SimpleLinked<>();
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
    }*/

}