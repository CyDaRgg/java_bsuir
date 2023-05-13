package services;

import dao.Parameter;
import dao.StatisticsValuesResponse;
import dao.TaskResponse;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerAverageMedian
{
    public void statisticsAverage(List<TaskResponse> responses,StatisticsValuesResponse statistics  )
    {
         statistics.setMaxAverage( responses.stream()
                .map(TaskResponse::getMedian)
                .max(Float::compareTo));
         statistics.setMinAverage(responses.stream()
                .map(TaskResponse::getMedian)
                .min(Float::compareTo));
         statistics.setAvgAverage( responses.stream()
                .mapToDouble(TaskResponse::getMedian)
                .average());
    }
    public void statisticsMedian(List<TaskResponse> responses,StatisticsValuesResponse statistics  )
    {
        statistics.setMaxMedian(responses.stream()
                .map(TaskResponse::getMedian)
                .max(Float::compareTo));
        statistics.setMinMedian(responses.stream()
                .map(TaskResponse::getMedian)
                .min(Float::compareTo));
        statistics.setAvgMedian(responses.stream()
                .mapToDouble(TaskResponse::getMedian)
                .average());
    }

    public void statisticsRequestsParams(List<Parameter>paramRequest,StatisticsValuesResponse statistics)
    {
        List<Float> parametersFloat = paramRequest.stream()
                .flatMap(param -> Stream.of(param.getFirst(), param.getSecond(), param.getThird(), param.getFourth()))
                .map(Float::parseFloat)
                .collect(Collectors.toList());

         statistics.setMaxParam(parametersFloat.stream()
                .max(Float::compareTo));
         statistics.setMinParam(parametersFloat.stream()
                .min(Float::compareTo));
         statistics.setAvgParam(parametersFloat.stream()
                .mapToDouble(Float::doubleValue)
                .average());
    }
    public String statisticsJsonStringResponse(StatisticsValuesResponse statistics)
    {
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder()
                .add("Average min", statistics.getMinAverage().orElse(0f).toString())
                .add("Average max", statistics.getMaxAverage().orElse(0f).toString())
                .add("Average avg", Double.toString(statistics.getAvgAverage().orElse(0.0)))
                .add("Median min", statistics.getMinMedian().orElse(0f).toString())
                .add("Median max", statistics.getMaxMedian().orElse(0f).toString())
                .add("Median avg", Double.toString(statistics.getAvgMedian().orElse(0.0)))
                .add("Param min", statistics.getMinParam().orElse(0f).toString())
                .add("Param max", statistics.getMaxParam().orElse(0f).toString())
                .add("Param avg",Double.toString(statistics.getAvgParam().orElse(0.0)));
        String jsonAvg = jsonBuild.build().toString();
        return jsonAvg;
    }

}
