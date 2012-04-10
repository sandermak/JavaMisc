package java7.worldcities;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;


/**
 * @author Sander Mak, Info Support 2011
 */
public class CityScanner {

    private String[] input;
    private ForkJoinPool pool;

    public CityScanner(String[] input) {
        this.input = input;
        pool = new ForkJoinPool();
    }

    public Set<String> scan(String query) {
        Pattern queryPattern = Pattern.compile(query);
        ScanTask initialTask =
          new ScanTask(input, 0, input.length, queryPattern);
   
        return pool.invoke(initialTask);
    }
}
class ScanTask extends RecursiveTask<Set<String>> {

    private String[] input;
    private int start;
    private int end;
    private Pattern query;

    public ScanTask(String[] input, int start, int end, Pattern queryPattern) {
        this.input = input;
        this.start = start;
        this.end = end;
        this.query = queryPattern;
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
             new ScanTask(input, start, midpoint, query),
             new ScanTask(input, midpoint, end, query)
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
            if (query.matcher(input[i]).matches()) {
                matches.add(input[i]);
            }
        }

        return matches;
    }
}
