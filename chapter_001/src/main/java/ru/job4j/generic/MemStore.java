package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    public int indexOfId(String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexOfId(id);
        boolean rsl = index != -1;
        if (rsl) {
            mem.set(index, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOfId(id);
        boolean rsl = index != -1;
        if (rsl) {
            mem.remove(index);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        return indexOfId(id) != -1 ? mem.get(indexOfId(id)) : null;
    }
}
