package nl.jpoint.javaseven.d.literals;

public class JavaSevenLiterals {

    public static void main(String[] args) {

        int binary = 0b1001_0000_1111_0010;
        System.out.println(binary);
        System.out.println(Integer.toBinaryString(binary));

        int readableMillion = 1_000_000; //instead of 1000000
        System.out.println(readableMillion);

        int readableBillion = 1_000_000_000; //instead of 1000000000
        System.out.println(readableBillion);

    }
}
