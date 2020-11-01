package ru.job4j.statistics;

import java.util.*;

public class Analize {
    public static Info diff(List<User> previous, List<User> current) {
        int deleted = 0;
        int added = 0;
        int changed = 0;

        Map<Integer, User> difference = new HashMap<>();
        for (User i : previous) {
            difference.put(i.getId(), i);
        }

        for (User curUser : current) {
            if (difference.containsKey(curUser.getId())
                    && difference.containsValue(curUser)) {
                difference.remove(curUser.getId());
            } else if (difference.containsKey(curUser.getId())
                    && !difference.containsValue(curUser)) {
                changed++;
                difference.remove(curUser.getId());
            } else {
                difference.put(curUser.getId(), curUser);
            }
        }

        for (Integer id : difference.keySet()) {
            User checked = difference.get(id);
            if (previous.contains(checked)) {
                deleted++;
            }
            if (current.contains(checked)) {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
