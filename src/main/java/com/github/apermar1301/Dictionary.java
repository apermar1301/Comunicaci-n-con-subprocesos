package com.github.apermar1301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Dictionary {
    private final List<ProcessBuilder> processBuilders = new ArrayList<>();
    private final List<String> words = new ArrayList<>();

    public static void main(String[] args) {
        new Dictionary().init();

    }

    public void init() {

        IntStream.range(1, 11).forEach(i -> {
            addProcessToList(i);
        });

        launchProcesses();
        showWords();
    }

    private void showWords() {
        words.stream().distinct().forEach(System.out::println);
    }

    public void addProcessToList(int i) {
        processBuilders.add(new ProcessBuilder("java", "-jar", "language.jar", String.valueOf(i)));
    }

    public void launchProcesses() {
        processBuilders.forEach(processBuilder -> {

            try {
                Process process = processBuilder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String word;
                    while ((word = reader.readLine()) != null) {
                        System.out.println(word);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        });
    }


}
