package dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameter
{
    @JsonProperty("first")
    private String first;

    @JsonProperty("second")
    private String second;

    @JsonProperty("third")
    private String third;

    @JsonProperty("fourth")
    private String fourth;

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
