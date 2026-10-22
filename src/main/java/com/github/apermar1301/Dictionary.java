package com.github.apermar1301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private final List<ProcessBuilder> processBuilders = new ArrayList<>();
    private final List<Process> activeProcesses = new ArrayList<>();
    private final List<StringBuilder> outputList = new ArrayList<>();

    public static void main(String[] args) {
        new Dictionary().init();
    }

    private void init() {
        configureProcess();
        createSubprocess();
        redirectProcess();
        waitExitCodeAndVerify();

        showOutput();
    }


    private void showOutput(){
        outputList.stream().distinct().forEach(System.out::println);
    }


    private void configureProcess() {
        for (int i = 1; i <= 10; i++) {
            processBuilders.add(new ProcessBuilder("java", "-jar", "language.jar", String.valueOf(i)));
        }
    }

    private void createSubprocess() {
        for (ProcessBuilder processBuilder : processBuilders){
            try {
                processBuilder.inheritIO();
                activeProcesses.add(processBuilder.start());
            } catch (IOException e) {
                System.err.printf("I/O EXCEPTION: %s%n", e.getMessage());
                System.exit(1);
            }
        }
    }

    private void redirectProcess() {
        for (Process process : activeProcesses) {
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

            } catch (IOException e) {
                System.err.printf("I/O EXCEPTION: %s%n", e.getMessage());
                System.exit(1);
            }
            outputList.add(output);
        }
    }

    private void waitExitCodeAndVerify() {

        

    }
}
