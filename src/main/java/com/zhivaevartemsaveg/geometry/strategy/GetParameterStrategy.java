package com.zhivaevartemsaveg.geometry.strategy;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.Line;
import java.util.List;

public class GetParameterStrategy implements IReduceSegmentsStrategy<Double> {
    private final double maxLength;

    public GetParameterStrategy(double maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean reduce(Ref<Double> accRef, double t, List<Line> segments) {
        double len = segments.stream().map(Line::length).reduce(Double::sum).get();
        if (len > maxLength) return false;
        accRef.value = t;
        return len < maxLength;
    }
}
