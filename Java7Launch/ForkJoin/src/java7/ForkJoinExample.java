package java7;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class ForkJoinExample {
    
    private static String vegas = ".*Las Vegas.*";
    private static String me = ".*sanderma@infosupport.com.*";

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, Throwable {
        String[] input = readLinesFromFile("sony.txt");
        String query = vegas;
        
        // Fork join run
        long start = System.currentTimeMillis();
        LogScanner scanner = new LogScanner(input);
        Set<String> matches = scanner.scan(query);
        long end = System.currentTimeMillis();
        
        for(String match: matches)
            System.out.println(match);
        
        out.println("Number of matches  : " + matches.size());
        out.println("Elapsed (ForkJoin) : " + (end - start));
        
        // Naive sequential run
        start = System.currentTimeMillis();
        matches = naive(input, query);
        end = System.currentTimeMillis();
        out.println("\nNumber of matches  : " + matches.size());
        out.println("Elapsed (naive)    : " + (end - start));
        
        
        
    }
    
    private static Set<String> naive(String[] input, String query) {
        Pattern pattern = Pattern.compile(query);
        
        Set<String> matches = new HashSet<>();
        for(int i = 0; i < input.length; i++) {
            if (pattern.matcher(input[i]).matches()) {
                matches.add(input[i]);
            }
        }

        return matches;
    }

    private static String[] readLinesFromFile(String string) throws IOException {
        List<String> lines = 
                Files.readAllLines(Paths.get("sony.txt"), Charset.forName("UTF-8"));
        return lines.toArray(new String[lines.size()]);
    }
    

}
