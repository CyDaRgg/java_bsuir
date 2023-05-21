package dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="request_parameters")
public class Parameter
{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="parameters_id")
    private int parameters_id;
    @JsonProperty("first")
    @Column(name ="first_param")
    private String first;

    @JsonProperty("second")
    @Column(name ="second_param")
    private String second;

    @JsonProperty("third")
    @Column(name ="third_param")
    private String third;

    @JsonProperty("fourth")
    @Column(name ="fourth_param")
    private String fourth;

    public int getParameters_id() {
        return parameters_id;
    }

    public void setParameters_id(int parameters_id) {
        this.parameters_id = parameters_id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }
}
