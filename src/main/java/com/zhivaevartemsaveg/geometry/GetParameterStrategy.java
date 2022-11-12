package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.Ref;

import java.util.List;

public class GetParameterStrategy implements IIterateOverSegmentStrategy {
    private double maxLength;

    public GetParameterStrategy(double maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean reduceSegment(Ref<Double> acc, List<Line> segments, double t) {
        acc.value = t;
        return segments.stream().map(Line::length).reduce(Double::sum).get() < maxLength;
    }
}
