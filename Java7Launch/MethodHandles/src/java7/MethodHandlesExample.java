package java7;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.System.out;

public class MethodHandlesExample {

    public static void main(String[] args) throws Throwable {
        MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "substring", 
                MethodType.methodType(String.class, int.class)); 
        
        String java = (String) mh.invokeExact("jjava", 1);
        out.println(java);
    }
}
