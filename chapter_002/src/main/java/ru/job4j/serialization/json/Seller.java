package ru.job4j.serialization.json;

public class Seller {
    private final String position;
    private final String name;

    public Seller(String position, String name) {
        this.position = position;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seller{"
                + "position='" + position + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
