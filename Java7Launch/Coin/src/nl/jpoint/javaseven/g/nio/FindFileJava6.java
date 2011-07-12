package nl.jpoint.javaseven.g.nio;

import java.io.File;

/**
 * @author Roy van Rijn, JPoint 2011
 */
public class FindFileJava6 {

    public static void main(String[] args) throws Exception {
        FindFileJava6 ffj6 = new FindFileJava6();
        ffj6.find();
    }

    private void find() throws Exception {

        File root = new File("/Users/sbmak/Documents");
        walkDisk(root, ".key");

    }

    private void walkDisk(File fromHere, String patternToFind) throws Exception {
        for (File child : fromHere.listFiles()) {
            if (child.isDirectory()) {
                walkDisk(child, patternToFind);
            }
            if (child.isFile() && child.getName().endsWith(patternToFind)) {
                System.out.println(child.getPath());
            }
        }
    }
}
