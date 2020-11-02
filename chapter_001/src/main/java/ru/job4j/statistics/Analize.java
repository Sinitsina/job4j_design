package ru.job4j.statistics;

import java.util.*;

public class Analize {
    public static Info diff(List<User> previous, List<User> current) {
        int deleted = 0;
        int added = 0;
        int changed = 0;

        Map<Integer, User> difference = new HashMap<>();
        for (User currUser : current) {
            difference.put(currUser.getId(), currUser);
        }

        for (User prevUser : previous) {
            if (!difference.containsKey(prevUser.getId())) {
                deleted++;
            } else if (!difference.get(prevUser.getId()).equals(prevUser)) {
                changed++;
            }
            difference.put(prevUser.getId(), prevUser);
        }
        added = difference.size() - previous.size();
        return new Info(added, changed, deleted);
    }
}
