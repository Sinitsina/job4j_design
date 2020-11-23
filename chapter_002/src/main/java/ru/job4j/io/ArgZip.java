package ru.job4j.io;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return args.length == 0;
    }

    public String directory() {
        if (args.length < 1) {
            System.out.println("Check the parameters");
        }
        return args[0];
    }

    public String exclude() {
        if (args.length < 2) {
            System.out.println("Check the parameters");
        }
        return args[1];
    }

    public String output() {
        if (args.length < 3) {
            System.out.println("Check the parameters");
        }
        return args[2];
    }
}
