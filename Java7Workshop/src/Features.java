
import java.util.HashMap;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sbmak
 */
public class Features {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String, String> diamondMap = new HashMap<>();
        diamondMap.put("Test", "tested");
        for(String s: diamondMap.keySet()) {
            System.out.println(s);
        }
    }
}
