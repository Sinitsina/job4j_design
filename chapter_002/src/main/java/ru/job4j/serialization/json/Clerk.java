package ru.job4j.serialization.json;

public class Clerk {
    private final String position;

    public Clerk(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Clerk{"
                + "position='" + position + '\''
                + '}';
    }
}
