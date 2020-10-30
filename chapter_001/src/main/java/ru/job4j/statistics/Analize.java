package ru.job4j.statistics;

import java.util.*;

public class Analize {
    public static Info diff(List<User> previous, List<User> current) {
        int deleted = 0;
        int added = 0;
        int changed = 0;
        boolean res = false;

        Set<User> difference = new HashSet<>(previous);

        for (User curUser : current) {
            if (difference.contains(curUser)) {
                difference.remove(curUser);
            } else {
                if (difference.isEmpty()) {
                    difference.add(curUser);
                } else {
                    Set<User> copy = new HashSet<>(difference);
                    for (User differUser : copy) {
                        if (differUser.getId() == curUser.getId()) {
                            changed++;
                            difference.remove(differUser);
                            res = true;
                        }
                    }
                    if (!res) {
                        difference.add(curUser);
                    }
                }
            }
        }

        for (User difUser : difference) {
            if (previous.contains(difUser)) {
                deleted++;
            } else {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
