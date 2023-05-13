package web;

import actions.AverageMedian;
import actions.counter.CounterThread;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Parameter;
import dao.StatisticsValuesResponse;
import dao.TaskResponse;
import exceptions.ClientError;
import exceptions.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ControllerAverageMedian;
import services.InitSpringContext;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Singleton
@Path("/task")
public class TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static AverageMedian contrAverageMedian = InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);
    private static  ObjectMapper mapper = new ObjectMapper();
    private static ControllerAverageMedian controllerAverageMedian = new ControllerAverageMedian();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response averageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth)
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("SERVER START {}", dt1);
        countAllAmountOfRequests();
        try {
            TaskResponse resp = contrAverageMedian.task(first, second, third, fourth, fifth);

            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("average is", resp.getAverage())
                    .add("median is", resp.getMedian());
            String json = jsonBuild.build().toString();

            logger.info("STOP");
            return Response.status(200).entity(json).build();
        }
        catch(ClientError ex)
        {
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("clientError 400 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(400).entity(json).build();
        }
        catch(ServerError ex)
        {
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("serverError 500 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(500).entity(json).build();
        }

    }

    private void countAllAmountOfRequests() {
        CounterThread countTh = new CounterThread();
        Thread thread = new Thread(countTh);
        thread.start();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response bulkOperation(List<Parameter> paramRequest) {

        StatisticsValuesResponse statistics= new StatisticsValuesResponse();
        try {
            List<TaskResponse> responses = paramRequest.stream().map(param -> {
                try
                {
                    return contrAverageMedian.task(param.getFirst(), param.getSecond(), param.getThird(), param.getFourth(), null);
                } catch (ClientError ex) {
                    throw new RuntimeException(ex.getMessageError());
                } catch (ServerError ex) {
                    throw new RuntimeException(ex.getMessageError());
                }
            }).collect(Collectors.toList());

        String json = mapper.writeValueAsString(responses);
        json=json.replace("]", ",");


           controllerAverageMedian.statisticsAverage(responses,statistics);
           controllerAverageMedian.statisticsMedian(responses,statistics);
           controllerAverageMedian.statisticsRequestsParams(paramRequest,statistics);

           String jsonAvg=controllerAverageMedian.statisticsJsonStringResponse(statistics);
            json+=jsonAvg+"]";

            return Response.status(200).entity(json).build();
    }
         catch (JsonProcessingException e)
        {
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder()
                    .add("Error", "Something wrong");
            String jsonResp = jsonBuild.build().toString();
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResp).build();

        }
        catch(RuntimeException ex)
        {

            JsonObjectBuilder jsonBuild = Json.createObjectBuilder()
                    .add("clientError 400 ", ex.getMessage());
            String jsonResp = jsonBuild.build().toString();
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResp).build();

        }
    }
}
