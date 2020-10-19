package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(Arrays.asList(1, 3, 2), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveIfPredicateMoreFour() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> filter = item -> item > 4;
        ListUtils.removeIf(input, filter);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenReplaceIfPredicateMoreFour() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> filter = item -> item > 4;
        Integer value = 99;
        ListUtils.replaceIf(input, filter, value);
        assertThat(Arrays.asList(1, 2, 3, 4, 99), Is.is(input));
    }

    @Test
    public void whenRemoveAllEqualElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 5));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(4), Is.is(input));
    }

    @Test
    public void whenRemoveAllEqualElements2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 5));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(3, 4), Is.is(input));
    }
}