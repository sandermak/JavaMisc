package java7.worldcities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * @author Sander Mak, Info Support 2012
 */
public class CityScannerMain {
    
    private static String veenendaal = ".*Bruxelles,.*";

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, Throwable {
        String[] input = readLinesFromFile("worldcitiespop.txt");
        
        String query = veenendaal;
        
        // Fork join run
        long start = System.currentTimeMillis();
        CityScanner scanner = new CityScanner(input);
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

    private static String[] readLinesFromFile(String filename) throws IOException {
        out.println("Reading " + filename);
        List<String> lines = 
                Files.readAllLines(Paths.get(filename), Charset.forName("ISO-8859-1"));
        out.println(lines.size() + " lines read.");
        return lines.toArray(new String[lines.size()]);
    }
    

}
