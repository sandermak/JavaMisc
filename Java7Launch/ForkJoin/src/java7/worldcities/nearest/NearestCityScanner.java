package java7.worldcities.nearest;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/**
 * @author Sander Mak, Info Support 2011
 */
public class NearestCityScanner {

    private String[] input;
    private ForkJoinPool pool;

    public NearestCityScanner(String[] input) {
        this.input = input;
        pool = new ForkJoinPool();
    }

    public Set<String> scan(double lat, double lng) {
        ScanTask initialTask =
          new ScanTask(input, 0, input.length, lat, lng);
   
        return pool.invoke(initialTask);
    }
}
class ScanTask extends RecursiveTask<Set<String>> {

    private String[] input;
    private int start;
    private int end;

    // Search parameters    
    private double lat, lng;
    private double searchBound = 0.5;

    public ScanTask(String[] input, int start, int end, double lat, double lng) {
        this.input = input;
        this.start = start;
        this.end = end;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected Set<String> compute() {
        int numLines = end - start;
        int midpoint = start + numLines / 2;
        if (end - start < 15000) {
            return scanSequentially();
        }

        // Fork step
        Collection<ScanTask> completedForks = invokeAll(
          Arrays.asList(
             new ScanTask(input, start, midpoint, lat, lng),
             new ScanTask(input, midpoint, end, lat, lng)
          )
        );

        // Join step
        Set<String> joinedResults = new HashSet<>();
        for (ScanTask task : completedForks) {
            if (task.getException() != null) {
                throw new RuntimeException("Recursive task failed", task.getException());
            }

            joinedResults.addAll(task.getRawResult());
        }

        return joinedResults;
    }

    private Set<String> scanSequentially() {
        Set<String> matches = new HashSet<>();
        for(int i = start; i < end; i++) {
            String[] fields = input[i].split(",");
            double fieldLat = Double.valueOf(fields[5]);
            double fieldLng = Double.valueOf(fields[6]);
            Integer population = !"".equals(fields[4]) ? Integer.valueOf(fields[4]) : null;
            if(Math.abs(lat - fieldLat) <= searchBound &&
                    Math.abs(lng - fieldLng) <= searchBound && population != null) {
                matches.add(fields[2] + " (" + fields[0] + ") -> " + input[i]);
            }
            
        }

        return matches;
    }
}
