package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Store {
    private final boolean open;
    private final int quantityOfClerks;
    private final Seller seller;
    private final String[] departments;

    public Store(boolean open, int quantityOfClerks, Seller seller, String... departments) {
        this.open = open;
        this.quantityOfClerks = quantityOfClerks;
        this.seller = seller;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Supermarket{"
                + "open = " + open
                + ", quantity of clerks = " + quantityOfClerks
                + ", Seller = " + seller
                + ", departments = " + Arrays.toString(departments)
                + '}';
    }

    public static void main(String[] args) {
        final Store store = new Store(
                true,
                10,
                new Seller("Seller","John"),
                "Grocery", "Meat", "Bakery", "Seafood");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(store));

        final String storeJson =
                "{"
                        + "\"open\":true,"
                        + "\"quantity of clerks\":10,"
                        + "\"Seller\":"
                        + "{"
                        + "\"position\":\"Seller\","
                        + "\"name\":\"John\""
                        + "},"
                        + "\"departments\":"
                        + "[\"Grocery\",\"Meat\",\"Bakery\",\"Seafood\"]"
                        + "}";
        final Store storeMod = gson.fromJson(storeJson, Store.class);
        System.out.println(storeMod);
    }
}
