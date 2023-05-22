package web;

import actions.AverageMedian;
import actions.counter.CounterThread;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Parameter;
import dao.StatisticsValuesResponse;
import dao.AverageMedianResponse;
import exceptions.ClientError;
import exceptions.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ControllerAverageMedian;
import services.DataBaseRecord;
import services.InitSpringContext;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@Singleton
@Path("/task")
public class TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static AverageMedian contrAverageMedian = InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static ControllerAverageMedian controllerAverageMedian = new ControllerAverageMedian();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response averageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth) {
        LocalDateTime dt1 = LocalDateTime.now();
        logger.info("SERVER START {}", dt1);
        countAllAmountOfRequests();
        try {
            AverageMedianResponse resp = contrAverageMedian.task(first, second, third, fourth, fifth);

            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("average is", resp.getAverage())
                    .add("median is", resp.getMedian());
            String json = jsonBuild.build().toString();

            logger.info("STOP");
            return Response.status(200).entity(json).build();
        } catch (ClientError ex) {
            return jsonAverMedErrorResp("clientError 400 ", ex.getMessageError(), 400);
        } catch (ServerError ex) {
            return jsonAverMedErrorResp("serverError 500 ", ex.getMessageError(), 500);
        }

    }

    private Response jsonAverMedErrorResp(String numberError, String messageError, int numbError) {
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add(numberError, messageError);
        String json = jsonBuild.build().toString();
        return Response.status(numbError).entity(json).build();
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
            List<AverageMedianResponse> responses = paramRequest.stream().map(param -> {
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
            return jsonAverMedErrorResp("Error","Something wrong",400);
        }
        catch(RuntimeException ex)
        {
            return jsonAverMedErrorResp("clientError 400 ", ex.getMessage(),400);
        }
    }
}
