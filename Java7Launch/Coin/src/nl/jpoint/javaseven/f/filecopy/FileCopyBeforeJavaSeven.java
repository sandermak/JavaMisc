package nl.jpoint.javaseven.f.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Roy van Rijn, JPoint 2011
 */
public class FileCopyBeforeJavaSeven {

    private static final File INPUT = new File("input.txt");
    private static final File OUTPUT = new File("output.txt");

    public static void main(String[] args) throws IOException {
        final FileChannel inChannel = new FileInputStream(INPUT).getChannel();
        try {
            final FileChannel outChannel = new FileOutputStream(OUTPUT).getChannel();
            try {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            } finally {
                outChannel.close();
            }
        } finally {
            inChannel.close();
        }
    }
}
