package nl.jpoint.javaseven.g.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Roy van Rijn, JPoint 2011
 */
public class AsyncFileReader {

    public static void main(String[] args) throws Exception {
        AsyncFileReader asyncFileReader = new AsyncFileReader();
        asyncFileReader.readFile();
    }

    private void readFile() throws Exception {
        AsynchronousFileChannel input = AsynchronousFileChannel.open(Paths.get("input.txt"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        input.read(buffer, 0, null, new CompletionHandler<Integer, Void>() {

            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("Done reading the file.");
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.err.println("An error occured:" + exc.getMessage());
            }
        });
        System.out.println("This thread keeps on running");
        Thread.sleep(100); // Without this sometimes the println of callback misses...
    }
}
