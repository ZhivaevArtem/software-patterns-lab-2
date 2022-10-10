package com.zhivaevartemsaveg.utils;

public class Algebra {
    public static double linear(double a, double b, double t) {
        return (1 - t) * a + t * b;
    }

    public static double bezier(double a, double b, double c, double d, double t) {
        return Math.pow(1 - t, 3) * a + 3 * t * Math.pow(1 - t, 2) * c + 3 * Math.pow(t, 2) * (1 - t) * d + Math.pow(t, 3) * b;
    }

    public static double round(double a, int signs) {
        double m = Math.pow(10, signs);
        return Math.round(a * m) / m;
    }
}
