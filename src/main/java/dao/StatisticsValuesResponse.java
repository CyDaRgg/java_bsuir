package dao;





public class StatisticsValuesResponse
{

    private int statistics_id;

    private float minMedian;

    private float maxMedian;

    private  double avgMedian;


    private float minAverage;

    private float maxAverage;

    private double avgAverage;


    private float minParam;

    private  float maxParam;

    private  double avgParam;

    public int getStatistics_id() {
        return statistics_id;
    }

    public void setStatistics_id(int statistics_id) {
        this.statistics_id = statistics_id;
    }

    public float getMinMedian() {
        return minMedian;
    }

    public void setMinMedian(float minMedian) {
        this.minMedian = minMedian;
    }

    public float getMaxMedian() {
        return maxMedian;
    }

    public void setMaxMedian(float maxMedian) {
        this.maxMedian = maxMedian;
    }

    public double getAvgMedian() {
        return avgMedian;
    }

    public void setAvgMedian(double avgMedian) {
        this.avgMedian = avgMedian;
    }

    public float getMinAverage() {
        return minAverage;
    }

    public void setMinAverage(float minAverage) {
        this.minAverage = minAverage;
    }

    public float getMaxAverage() {
        return maxAverage;
    }

    public void setMaxAverage(float maxAverage) {
        this.maxAverage = maxAverage;
    }

    public double getAvgAverage() {
        return avgAverage;
    }

    public void setAvgAverage(double avgAverage) {
        this.avgAverage = avgAverage;
    }

    public float getMinParam() {
        return minParam;
    }

    public void setMinParam(float minParam) {
        this.minParam = minParam;
    }

    public float getMaxParam() {
        return maxParam;
    }

    public void setMaxParam(float maxParam) {
        this.maxParam = maxParam;
    }

    public double getAvgParam() {
        return avgParam;
    }

    public void setAvgParam(double avgParam) {
        this.avgParam = avgParam;
    }
}
