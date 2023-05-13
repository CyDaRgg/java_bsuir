package dao;

import java.util.Optional;
import java.util.OptionalDouble;

public class StatisticsValuesResponse
{
    private Optional<Float> minMedian;

    private Optional<Float> maxMedian;

    private  OptionalDouble avgMedian;

    private Optional<Float> minAverage;

    private Optional<Float> maxAverage;

    private OptionalDouble avgAverage;


    private Optional<Float> minParam;

    private  Optional<Float> maxParam;

    private  OptionalDouble avgParam;

    public Optional<Float> getMinMedian() {
        return minMedian;
    }

    public void setMinMedian(Optional<Float> minMedian) {
        this.minMedian = minMedian;
    }

    public Optional<Float> getMaxMedian() {
        return maxMedian;
    }

    public void setMaxMedian(Optional<Float> maxMedian) {
        this.maxMedian = maxMedian;
    }

    public OptionalDouble getAvgMedian() {
        return avgMedian;
    }

    public void setAvgMedian(OptionalDouble avgMedian) {
        this.avgMedian = avgMedian;
    }

    public Optional<Float> getMinAverage() {
        return minAverage;
    }

    public void setMinAverage(Optional<Float> minAverage) {
        this.minAverage = minAverage;
    }

    public Optional<Float> getMaxAverage() {
        return maxAverage;
    }

    public void setMaxAverage(Optional<Float> maxAverage) {
        this.maxAverage = maxAverage;
    }

    public OptionalDouble getAvgAverage() {
        return avgAverage;
    }

    public void setAvgAverage(OptionalDouble avgAverage) {
        this.avgAverage = avgAverage;
    }

    public Optional<Float> getMinParam() {
        return minParam;
    }

    public void setMinParam(Optional<Float> minParam) {
        this.minParam = minParam;
    }

    public Optional<Float> getMaxParam() {
        return maxParam;
    }

    public void setMaxParam(Optional<Float> maxParam) {
        this.maxParam = maxParam;
    }

    public OptionalDouble getAvgParam() {
        return avgParam;
    }

    public void setAvgParam(OptionalDouble avgParam) {
        this.avgParam = avgParam;
    }
}
