package nl.jpoint.javaseven.f.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopyWithJavaSeven {

    private static final File INPUT = new File("input.txt");
    private static final File OUTPUT = new File("output.txt");

    public static void main(String[] args) throws IOException {

        try (final FileChannel inChannel = new FileInputStream(INPUT).getChannel();
                final FileChannel outChannel = new FileOutputStream(OUTPUT).getChannel()) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }
}
