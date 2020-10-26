package ru.job4j.hash;

import java.util.*;
import java.util.Map;

public class Store {
    public static void main(String[] args) {
        User u1 = new User("Ivan", 5, new GregorianCalendar(1990, 01, 01));
        User u2 = new User("Ivan", 5, new GregorianCalendar(1990, 01, 01));
        Map<User, Object> map = new HashMap<>();
        map.put(u1, new Object());
        map.put(u2, new Object());

        map.entrySet().stream().forEach(e -> System.out.println(e));
    }

}
