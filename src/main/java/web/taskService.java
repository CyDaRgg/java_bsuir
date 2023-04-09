package web;

import actions.averageMedian;
import dao.dataOfNumbers;
import dao.taskResponse;
import exceptions.clientError;
import exceptions.serverError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;

@Path("/task")
public class taskService
{
    private static final Logger logger= LoggerFactory.getLogger(taskService.class);

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response averageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth)
    {
            averageMedian myTask = new averageMedian();
      return myTask.task(first, second,third,fourth,fifth);
    }


}
