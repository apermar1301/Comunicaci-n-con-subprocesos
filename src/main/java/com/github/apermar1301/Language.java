package com.github.apermar1301;

import java.util.Random;
import java.util.stream.IntStream;

public class Language {
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {

        new Language().run(args);

    }


    private void run(String[] args) {

        validateArgs(args); // it throws an exception if madeUpWords are not provided
        int count = stringToInt(args);
        listMadeUpWords(count);

    }

    private void listMadeUpWords(int count) {
        Random random = new Random();
        IntStream.range(0, count).forEach(i -> {
            System.out.println(madeUpWords(random));
        });
    }

    private String madeUpWords(Random random) {
        StringBuilder word = new StringBuilder();
        int iterator = random.nextInt(2, 9);
        for (int i = 0; i < iterator; i++) {
            word.append(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
        return word.toString();
    }

    private int stringToInt(String[] madeUpWords) {
        try {
            return Integer.parseInt(madeUpWords[0]);
        } catch (NumberFormatException e) {
            System.err.println("Number format exception: " + e.getMessage());
            System.exit(2);
            return -1;
        }

    }

    private void validateArgs(String[] args) throws IllegalArgumentException {
        if (!(args.length > 0)) {
            System.err.println("You need to specify how many invent words you want.");
            System.exit(1);
        }
    }
}