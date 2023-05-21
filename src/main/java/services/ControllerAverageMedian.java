package services;

import dao.Parameter;
import dao.StatisticsValuesResponse;
import dao.AverageMedianResponse;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerAverageMedian
{
    public void statisticsAverage(List<AverageMedianResponse> responses, StatisticsValuesResponse statistics  )
    {
         statistics.setMaxAverage( responses.stream()
                .map(AverageMedianResponse::getMedian)
                .max(Float::compareTo).orElse(0.0f));
         statistics.setMinAverage(responses.stream()
                .map(AverageMedianResponse::getMedian)
                .min(Float::compareTo).orElse(0.0f));
         statistics.setAvgAverage( responses.stream()
                .mapToDouble(AverageMedianResponse::getMedian)
                .average().orElse(0.0));
    }
    public void statisticsMedian(List<AverageMedianResponse> responses, StatisticsValuesResponse statistics  )
    {
        statistics.setMaxMedian(responses.stream()
                .map(AverageMedianResponse::getMedian)
                .max(Float::compareTo).orElse(0.0f));
        statistics.setMinMedian(responses.stream()
                .map(AverageMedianResponse::getMedian)
                .min(Float::compareTo).orElse(0.0f));
        statistics.setAvgMedian(responses.stream()
                .mapToDouble(AverageMedianResponse::getMedian)
                .average().orElse(0.0));
    }

    public void statisticsRequestsParams(List<Parameter>paramRequest,StatisticsValuesResponse statistics)
    {
        List<Float> parametersFloat = paramRequest.stream()
                .flatMap(param -> Stream.of(param.getFirst(), param.getSecond(), param.getThird(), param.getFourth()))
                .map(Float::parseFloat)
                .collect(Collectors.toList());

         statistics.setMaxParam(parametersFloat.stream()
                .max(Float::compareTo).orElse(0.0f));
         statistics.setMinParam(parametersFloat.stream()
                .min(Float::compareTo).orElse(0.0f));
         statistics.setAvgParam(parametersFloat.stream()
                .mapToDouble(Float::doubleValue)
                .average().orElse(0.0));
    }
    public String statisticsJsonStringResponse(StatisticsValuesResponse statistics)
    {
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder()
                .add("Average min", Float.toString(statistics.getMinAverage()))
                .add("Average max",  Float.toString(statistics.getMaxAverage()))
                .add("Average avg", Double.toString(statistics.getAvgAverage()))
                .add("Median min", Float.toString(statistics.getMinMedian()))
                .add("Median max", Float.toString(statistics.getMaxMedian()))
                .add("Median avg", Double.toString(statistics.getAvgMedian()))
                .add("Param min", Float.toString(statistics.getMinParam()))
                .add("Param max", Float.toString(statistics.getMaxParam()))
                .add("Param avg",Double.toString(statistics.getAvgParam()));
        String jsonAvg = jsonBuild.build().toString();
        return jsonAvg;
    }

}
