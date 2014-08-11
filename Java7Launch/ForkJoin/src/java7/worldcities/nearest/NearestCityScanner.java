package java7.worldcities.nearest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.lang.Math.abs;

/**
 * @author Sander Mak, Info Support 2012
 */
public class NearestCityScanner {

    private String[] input;
    private ForkJoinPool pool;

    public NearestCityScanner(String[] input) {
        this.input = input;
        pool = new ForkJoinPool();
    }

    public Set<City> scan(double lat, double lng) {
        ScanTask initialTask =
          new ScanTask(input, 0, input.length, lat, lng);
   
        return pool.invoke(initialTask);
    }
}

class ScanTask extends RecursiveTask<Set<City>> {

    private String[] input;
    private int start;
    private int end;

    // Search parameters    
    private double lat, lng;
    private double searchBoundSquared = 0.4 * 0.4;

    public ScanTask(String[] input, int start, int end, double lat, double lng) {
        this.input = input;
        this.start = start;
        this.end = end;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected Set<City> compute() {
        int numLines = end - start;
        int midpoint = start + numLines / 2;
        if (end - start < 15000) {
            return findTopNearestCities();
        }

        // Fork step
        ScanTask left = new ScanTask(input, start, midpoint, lat, lng);
        ScanTask right = new ScanTask(input, midpoint, end, lat, lng);
        left.fork();
        Set<City> rightResult = right.compute();
        Set<City> leftResult = left.join();
                
        // Combine results
        Set<City> joinedResults = new HashSet<>();
        joinedResults.addAll(leftResult);
        joinedResults.addAll(rightResult);
        
        return joinedResults;
    }

    private Set<City> findTopNearestCities() {
        Set<City> matches = new HashSet<>();
        
        for(int i = start; i < end; i++) {
            String[] fields = input[i].split(",");
            Integer population = !"".equals(fields[4]) ? Integer.valueOf(fields[4]) : null;
            double fieldLat = Double.valueOf(fields[5]);
            double fieldLng = Double.valueOf(fields[6]);
            
            if(Math.pow((lat - fieldLat), 2) + Math.pow((lng - fieldLng), 2) <= searchBoundSquared && population != null) {
                matches.add(new City(fields[2] + " (" + fields[0] + ")", population));
            }
            
        }

        return matches;
    }

}

class City implements Comparable<City> {
    public final String name;
    public final Integer population;
    public City(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public int compareTo(City other) {
        return population.compareTo(other.population);
    }
         
    
}
