package PEngine;

import java.util.Arrays;

public class PLog {
    public static void i(Object ... params) {
        System.out.print("INFO:: ");
        Arrays.stream(params).map(x -> x + " ").forEach(System.out::print);
        System.out.println();
    }
}
