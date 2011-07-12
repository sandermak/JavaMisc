package java7;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.System.out;

/**
 * Note: needs the following flags in some JDK7 builds:
 * -XX:+UnlockExperimentalVMOptions -XX:+EnableMethodHandles
 * 
 * @author Sander Mak, Info Support 2011
 */
public class MethodHandlesExample {

    public static void main(String[] args) throws Throwable {
        MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "substring", 
                MethodType.methodType(String.class, int.class)); 
        
        String java = (String) mh.invokeExact("jjava", 1);
        out.println(java);
    }
}
