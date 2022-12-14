package day2;

import shared.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    public static void main(String[] args) {
        var day2 = new Day2();

        System.out.println("First problem: " + day2.getScore());
        System.out.println("Second problem: " + day2.getScore2());
    }

    private int getScore() {
        var elfEncryption = getElfEncryption();
        var myEncryption = getMyEncryption();
        var content = InputReader.getFileContentsAsList(2);
        return content.stream()
            .mapToInt(s -> {
                var split = s.split(" ");
                var elfChoice = elfEncryption.get(split[0]);
                var myChoice = myEncryption.get(split[1]);
                return myChoice.calculateResult(elfChoice);
            }).sum();
    }

    private int getScore2() {
        var elfEncryption = getElfEncryption();
        var elfResultEncryption = getElfResultEncryption();
        var content = InputReader.getFileContentsAsList(2);
        return content.stream()
            .mapToInt(s -> {
                var split = s.split(" ");
                var elfChoice = elfEncryption.get(split[0]);
                var elfResult = elfResultEncryption.get(split[1]);
                return elfChoice.calculateFromResult(elfResult);
            }).sum();
    }

    private Map<String, GameOptions> getElfEncryption() {
        var elfEncryption = new HashMap<String, GameOptions>();
        elfEncryption.put("A", GameOptions.ROCK);
        elfEncryption.put("B", GameOptions.PAPER);
        elfEncryption.put("C", GameOptions.SCISSORS);

        return elfEncryption;
    }

    private Map<String, GameOptions> getMyEncryption() {
        var myEncryption = new HashMap<String, GameOptions>();
        myEncryption.put("X", GameOptions.ROCK);
        myEncryption.put("Y", GameOptions.PAPER);
        myEncryption.put("Z", GameOptions.SCISSORS);

        return myEncryption;
    }

    private Map<String, GameResult> getElfResultEncryption() {
        var myEncryption = new HashMap<String, GameResult>();
        myEncryption.put("X", GameResult.LOSS);
        myEncryption.put("Y", GameResult.DRAW);
        myEncryption.put("Z", GameResult.WIN);

        return myEncryption;
    }

    enum GameOptions {
        ROCK(1), PAPER(2), SCISSORS(3);

        final int value;

        GameOptions(int value) {
            this.value = value;
        }

        private boolean beat(GameOptions other) {
            return (this == ROCK && other == SCISSORS) ||
                (this == PAPER && other == ROCK) ||
                (this == SCISSORS && other == PAPER);
        }

        int calculateResult(GameOptions other) {
            return this.value + calculateOutcome(other).value;
        }

        int calculateFromResult(GameResult result) {
            return result.value + result.fromResult(this).value;
        }

        GameResult calculateOutcome(GameOptions other) {
            if (this == other) {
                return GameResult.DRAW;
            }
            if (this.beat(other)) {
                return GameResult.WIN;
            }
            return GameResult.LOSS;
        }
    }

    enum GameResult {
        LOSS(0), DRAW(3), WIN(6);

        final int value;

        GameResult(int value) {
            this.value = value;
        }

        GameOptions fromResult(GameOptions input) {
            switch (this) {
                case WIN -> {
                    return Arrays.stream(GameOptions.values())
                        .filter(g -> g.beat(input))
                        .findFirst().get();
                }
                case LOSS -> {
                    return Arrays.stream(GameOptions.values())
                        .filter(input::beat)
                        .findFirst().get();
                }
                default -> {
                    return input;
                }
            }
        }
    }
}
