package dao;

public class TaskResponse
{
    private float average;
    private float median;

    public float GetAverage() {
        return average;
    }

    public void SetAverage(float average) {
        this.average = average;
    }

    public float GetMedian() {
        return median;
    }

    public void SetMedian(float median) {
        this.median = median;
    }
}
