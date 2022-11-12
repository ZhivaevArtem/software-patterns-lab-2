package com.zhivaevartemsaveg.geometry.strategy;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.Line;
import java.util.List;

public class GetLengthStrategy implements  IReduceSegmentsStrategy<Double> {
    private final double maxT;

    public GetLengthStrategy(double maxT) {
        this.maxT = maxT;
    }

    @Override
    public Double getInitialAccumulator() {
        return 0.0;
    }

    @Override
    public boolean reduce(Ref<Double> accRef, double t, List<Line> segments) {
        if (t > maxT) return false;
        accRef.value = segments.stream().map(Line::length).reduce(Double::sum).get();
        return t < maxT;
    }
}
