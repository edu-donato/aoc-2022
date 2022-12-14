package shared;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {
    public static String getFileContentsAsString(int day) {
        try {
            return Files.readString(getResourcePath(day));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List<String> getFileContentsAsList(int day) {
        try {
            return Files.readAllLines(getResourcePath(day));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Path getResourcePath(int day) {
        return new File(String.format("src/main/resources/input%d.txt", day)).toPath();
    }
}
