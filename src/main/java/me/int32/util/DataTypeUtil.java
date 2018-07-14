package me.int32.util;

import org.apache.commons.lang3.math.NumberUtils;

public class DataTypeUtil {
    public static Double intToDoublePrice(int value) {
        return value * 1.0 / 100;
    }

    public static Integer doubleToIntPrice(double value) {
        return (int) (value * 100);
    }

    public static void main(String[] args) {
        int a = 123456;
        System.out.println(intToDoublePrice(a));

        double b = 123.123;
        System.out.println(doubleToIntPrice(b));
    }
}
