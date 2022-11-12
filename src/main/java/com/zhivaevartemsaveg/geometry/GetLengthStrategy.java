package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.Ref;

import java.util.List;

public class GetLengthStrategy implements IIterateOverSegmentStrategy {
    private double maxT;

    public GetLengthStrategy(double maxT) {
        this.maxT = maxT;
    }

    @Override
    public boolean reduceSegment(Ref<Double> acc, List<Line> segments, double t) {
        acc.value = segments.stream().map(Line::length).reduce(Double::sum).get();
        return t < maxT;
    }
}
