package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Day1 {
    public static void main(String[] args) {
        var day1 = new Day1();
        var elves = day1.getElvesList();
        elves.sort(Comparator.comparingInt(Elf::getCalories).reversed());

        System.out.println("First problem: " + elves.subList(0, 1).stream().mapToInt(Elf::getCalories).reduce(0, Integer::sum));
        System.out.println("Second problem: " + elves.subList(0, 3).stream().mapToInt(Elf::getCalories).reduce(0, Integer::sum));
    }

    private List<Elf> getElvesList() {
        var result = new ArrayList<Elf>();
        var fileName = "../input1.txt";
        var line = "";
        var currentCalories = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource(fileName).getPath()))) {
            while ((line = reader.readLine()) != null) {
                currentCalories += Integer.parseInt(line);
                while((line = reader.readLine()) != null && !line.isEmpty()) {
                    currentCalories += Integer.parseInt(line);
                }

                result.add(new Elf(currentCalories));
                currentCalories = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static class Elf {
        int calories;

        public Elf(int calories) {
            this.calories = calories;
        }

        public void addCalories(int calories) {
            this.calories += calories;
        }

        public int getCalories() {
            return calories;
        }
    }
}
