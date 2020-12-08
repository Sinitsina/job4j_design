package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Supermarket {
    private final boolean open;
    private final int quantityOfClerks;
    private final Clerk clerk;
    private final String[] departments;

    public Supermarket(boolean open, int quantityOfClerks, Clerk clerk, String... departments) {
        this.open = open;
        this.quantityOfClerks = quantityOfClerks;
        this.clerk = clerk;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Supermarket{"
                + "open = " + open
                + ", quantity of clerks = " + quantityOfClerks
                + ", Clerk = " + clerk
                + ", departments = " + Arrays.toString(departments)
                + '}';
    }

    public static void main(String[] args) {
        final Supermarket supermarket = new Supermarket(
                true,
                10,
                new Clerk("Seller"),
                "Grocery", "Meat", "Bakery", "Seafood");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(supermarket));

        /* Модифицируем json-строку */
        final String supermarketJson =
                "{"
                        + "\"open\":true,"
                        + "\"quantity of clerks\":10,"
                        + "\"Clerk\":"
                        + "{"
                        + "\"position\":\"Seller\""
                        + "},"
                        + "\"departments\":"
                        + "[\"Grocery\",\"Meat\",\"Bakery\",\"Seafood\"]"
                        + "}";
        final Supermarket supermarketMod = gson.fromJson(supermarketJson, Supermarket.class);
        System.out.println(supermarketMod);
    }
}
