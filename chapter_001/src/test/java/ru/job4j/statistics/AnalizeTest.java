package ru.job4j.statistics;

import org.junit.Test;

import java.util.*;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void whenAddChangeDeleteUsers() {
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel"),
                new User(3, "Max")));
        List<User> current = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(4, "Petr"),
                new User(3, "Anton")));
        Info rsl = Analize.diff(previous, current);
        assertThat(rsl.toString(), is("Info{added=1, changed=1, deleted=1}"));
    }

    @Test
    public void whenAddUsers() {
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel")));
        List<User> current = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel"),
                new User(4, "Petr"),
                new User(3, "Anton")));
        Info rsl = Analize.diff(previous, current);
        assertThat(rsl.toString(), is("Info{added=2, changed=0, deleted=0}"));
    }

    @Test
    public void whenDeleteUsers() {
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel"),
                new User(4, "Petr"),
                new User(3, "Anton")));
        List<User> current = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel")));
        Info rsl = Analize.diff(previous, current);
        assertThat(rsl.toString(), is("Info{added=0, changed=0, deleted=2}"));
    }

    @Test
    public void whenChangeUsers() {
        List<User> previous = new ArrayList<>(Arrays.asList(
                new User(1, "Ivan"),
                new User(2, "Pavel"),
                new User(4, "Petr"),
                new User(3, "Anton")));
        List<User> current = new ArrayList<>(Arrays.asList(
                new User(1, "Anna"),
                new User(2, "Maria"),
                new User(4, "Darya"),
                new User(3, "Svetlana")));
        Info rsl = Analize.diff(previous, current);
        assertThat(rsl.toString(), is("Info{added=0, changed=4, deleted=0}"));
    }
}