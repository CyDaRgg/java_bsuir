package services;

import actions.AverageMedian;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Parameter;
import dao.TaskResponse;
import exceptions.ClientError;
import exceptions.ServerError;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;

public class ControllerAverageMedian
{
    private AverageMedian contrAverageMedian= InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);

    public TaskResponse countAverageMedian(Parameter param) throws ClientError,ServerError
    {
        try {
            return contrAverageMedian.task(param.getFirst(),param.getSecond(),param.getThird(),param.getFourth(),null);
        } catch (ClientError ex) {
            throw ex;
        } catch (ServerError ex) {
            throw ex;
        }
    }
    public Parameter serializationJsonToParameter(String json, ObjectMapper mapper)
    {
        try {
            return mapper.readValue(json, Parameter.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


}
