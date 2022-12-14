package day3;

import shared.InputReader;

import java.util.stream.IntStream;

public class Day3 {
    public static void main(String[] args) {
        var day3 = new Day3();

        System.out.println("First problem: " + day3.getPriorities());
        System.out.println("Second problem: " + day3.getGroupPriorities());
    }

    private int getPriorities() {
        var contents = InputReader.getFileContentsAsList(3);
        return contents.stream()
            .map(s -> new Rucksack(s.substring(0, s.length()/2), s.substring(s.length()/2)))
            .map(Rucksack::getCommonType)
            .mapToInt(this::calculatePriority)
            .sum();
    }

    private int getGroupPriorities() {
        var contents = InputReader.getFileContentsAsList(3);
        return IntStream.range(0, contents.size()/3)
            .map(i -> i*3)
            .mapToObj(i -> new RucksackGroup(contents.get(i), contents.get(i+1), contents.get(i+2)))
            .map(RucksackGroup::getCommonType)
            .mapToInt(this::calculatePriority)
            .sum();
    }

    private int calculatePriority(char common) {
        if (Character.isLowerCase(common)) {
            return common - (int)'a' + 1;
        }
        return common - (int)'A' + 27;
    }

    record Rucksack(String firstComp, String secondComp) {
        char getCommonType() {
            return firstComp.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> secondComp.indexOf(c) != -1)
                .findFirst().get();
        }
    }

    record RucksackGroup(String firstRuck, String secondRuck, String thirdRuck) {
        char getCommonType() {
            return firstRuck.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> secondRuck.indexOf(c) != -1 && thirdRuck.indexOf(c) != -1)
                .findFirst().get();
        }
    }
}
