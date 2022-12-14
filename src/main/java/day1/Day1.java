package day1;

import shared.InputReader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) {
        var day1 = new Day1();
        var elves = day1.getElvesList();
        elves.sort(Comparator.comparingInt(Elf::getCalories).reversed());

        System.out.println("First problem: " + elves.get(0).getCalories());
        System.out.println("Second problem: " + elves.subList(0, 3).stream().mapToInt(Elf::getCalories).sum());
    }

    private List<Elf> getElvesList() {
        var content = InputReader.getFileContentsAsString(1);
        return Arrays.stream(content.split("\n\n"))
            .map(s -> Arrays.stream(s.split("\n"))
                .mapToInt(Integer::parseInt).sum())
            .map(Elf::new)
            .collect(Collectors.toList());
    }

    static class Elf {
        int calories;

        public Elf(int calories) {
            this.calories = calories;
        }

        public int getCalories() {
            return calories;
        }
    }
}
