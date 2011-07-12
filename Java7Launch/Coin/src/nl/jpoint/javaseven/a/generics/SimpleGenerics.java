package nl.jpoint.javaseven.a.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roy van Rijn, JPoint 2011
 */
public class SimpleGenerics {

    public static void main(String[] args) {
        SimpleGenerics s = new SimpleGenerics();
        s.beforeJavaSeven();
        s.withJavaSeven();
    }

    public void beforeJavaSeven() {
        Map<String, Collection<String>> data =
                new HashMap<String, Collection<String>>();
        data.put("key", Arrays.asList("1", "2", "3"));
        System.out.println(data);
    }

    public void withJavaSeven() {
        Map<String, Collection<String>> data = new HashMap<>();
        data.put("key", Arrays.asList("1", "2", "3"));
        System.out.println(data);
    }
}
