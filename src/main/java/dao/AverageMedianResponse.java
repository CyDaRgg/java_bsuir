package dao;

import jakarta.persistence.*;

@Entity
@Table(name="average_median")
public class AverageMedianResponse
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="average_median_id")
    private int averageMedian_id;
    @Column(name ="average")
    private float average;
    @Column(name ="median")
    private float median;

    public int getAverageMedian_id() {
        return averageMedian_id;
    }

    public void setAverageMedian_id(int averageMedian_id) {
        this.averageMedian_id = averageMedian_id;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getMedian() {
        return median;
    }

    public void setMedian(float median) {
        this.median = median;
    }


}
