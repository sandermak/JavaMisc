package java7.worldcities.nearest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.out;
import java.util.*;

/**
 * @author Sander Mak, Info Support 2012
 */
public class NearestCityMain {
    // bruxelles 50.833333,4.333333
    // Leuven
    private static final double lat = 50.88;
    private static final double lng = 4.7;

    // Amsterdam
    //private static final double lat = 52.37;
    //private static final double lng = 4.89;

    // Veenendaal
    //private static final double lat = 52.03;
    //private static final double lng = 5.55;

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, Throwable {
        String[] input = readLinesFromFile("worldcitiespop.txt");
        
        // Fork join run
        long start = System.currentTimeMillis();
        NearestCityScanner scanner = new NearestCityScanner(input);
        Set<City> matches = scanner.scan(lat, lng);
        long end = System.currentTimeMillis();
        
        SortedSet<City> sortedCities = new TreeSet<>();
        sortedCities.addAll(matches);
        
        for (City match: sortedCities)
            out.println(String.format("%s (population: %d)", match.name, match.population));
        
        out.println("Number of cities   : " + matches.size());
        out.println("Elapsed (ForkJoin) : " + (end - start));
        
    }
    
    private static String[] readLinesFromFile(String filename) throws IOException {
        out.println("Reading " + filename);
        List<String> lines = 
                Files.readAllLines(Paths.get(filename), Charset.forName("ISO-8859-1"));
        out.println(lines.size() + " lines read.");
        return lines.toArray(new String[lines.size()]);
    }
    

}
