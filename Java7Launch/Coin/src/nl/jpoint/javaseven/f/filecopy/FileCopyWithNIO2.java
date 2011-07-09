package nl.jpoint.javaseven.f.filecopy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyWithNIO2 {

    private static final Path INPUT = Paths.get("input.txt");
    private static final Path OUTPUT = Paths.get("output.txt");

    public static void main(String[] args) throws Exception {
        Files.copy(INPUT, OUTPUT, StandardCopyOption.REPLACE_EXISTING);
    }
}
