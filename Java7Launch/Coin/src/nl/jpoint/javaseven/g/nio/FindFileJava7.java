package nl.jpoint.javaseven.g.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FindFileJava7 {

    public static void main(String[] args) throws Exception {
        FindFileJava7 ffj7 = new FindFileJava7();
        ffj7.find();
    }

    private void find() throws Exception {
        final Path root = Paths.get("/Users/sbmak/Documents");
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.key");

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(path.getFileName())) {
                    System.out.println(path.toString());
                }
                return super.visitFile(path, attrs);
            }
        });
    }
}
