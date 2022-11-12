package com.zhivaevartemsaveg.utils;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import java.util.ArrayList;
import java.util.List;

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

    public static double angle(IPoint a, IPoint b) {
        double v = b.getY() - a.getY();
        double h = b.getX() - a.getX();
        double tan = (v) / (h);
        double res = Math.atan(tan) - Math.PI / 2;
        if (h < 0) res += Math.PI;
        return res;
    }

    public static double map(double v, double fromFrom, double fromTo, double toFrom, double toTo) {
        return (v - fromFrom) / (fromTo - fromFrom) * (toTo - toFrom) + toFrom;
    }

    public static void rotatePoints(List<IPoint> points, IPoint centroid, double angle) {
        List<IPoint> newPoints = new ArrayList<>(points);
        double x, y;
        for (int i = 0; i < points.size(); i++) {
            x = points.get(i).getX() - centroid.getX();
            y = points.get(i).getY() - centroid.getY();
            newPoints.set(i, new Point(
                    centroid.getX() + (int) Math.round(((x * Math.cos(angle)) - (y * Math.sin(angle)))),
                    centroid.getY() + (int) Math.round(((x * Math.sin(angle)) + (y * Math.cos(angle))))
            ));
        }

        for (int i = 0; i < newPoints.size(); i++) {
            points.set(i, newPoints.get(i));
        }
    }
}
