package nl.jpoint.javaseven.c.switchit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SwitchOnStrings {

    public static void main(String[] args) throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        while (true) {
            String string = in.readLine();

            switch (string) {
                case "hello":
                    System.out.println("hello world!");
                    return;
                case "cafe":
                    System.out.println("cafebabe!");
                    return;
                case "open":
                    System.out.println("open sesame!");
                    return;
                case "n":
                    System.out.println("You go north\nIn front of you is a giant Whumpus!\nYou die.");
                    return;
                default:
                    System.out.println("wrong...");
            }
        }
    }
}