package web;

import actions.AverageMedian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/task")
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private AverageMedian myTask= new AverageMedian();
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response AverageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth) {

        return myTask.Task(first, second, third, fourth, fifth);
    }


}
