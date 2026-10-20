package com.github.apermar1301;

import java.util.Random;
import java.util.stream.IntStream;

public class Language {

    // English alphabet. This is used later to create the made up words
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        new Language().init(args);
    }


    private void init(String[] args) {
        // Here we list the made up words by passing the number of made up words we want to create. Before we parse this number and validate it.
        listMadeUpWords(parseAndValidateArgs(args));
    }


    private void listMadeUpWords(int count) {
        Random random = new Random();
        IntStream.range(0, count).forEach(i -> {
            System.out.println(madeUpWords(random));
        });
    }

    private String madeUpWords(Random random) {
        StringBuilder word = new StringBuilder();
        int wordLength = random.nextInt(2, 9);
        for (int i = 0; i < wordLength; i++) {
            word.append(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
        return word.toString();
    }


    private int parseAndValidateArgs(String[] args) {

        try {
            if (args.length == 0) throw new IllegalArgumentException("You need to specify how many invent words you want.");

            int count = stringToInt(args);
            if(count <= 0) throw new IllegalArgumentException("The word count must be greater than 0.");

            return count;
        } catch (IllegalArgumentException e) {
            System.err.printf("You do not use a correct use of args: %s%n", e.getMessage());
            System.exit(1);
            return -1;
        }
    }


    private int stringToInt(String[] madeUpWords) {
        try {
            return Integer.parseInt(madeUpWords[0]);
        } catch (NumberFormatException e) {
            System.err.println("Number format exception: " + e.getMessage());
            System.exit(1);
            return -1;
        }

    }
}