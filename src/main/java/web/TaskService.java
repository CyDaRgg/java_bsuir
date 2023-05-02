package web;

import actions.AverageMedian;
import actions.counter.CounterThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.InitSpringContext;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Singleton
@Path("/task")
public class TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static AverageMedian contrAverageMedian = InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);


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
        Response res = contrAverageMedian.task(first, second, third, fourth, fifth);
        logger.info("STOP");
        return res;
    }
    private Thread countAllAmountOfRequests() {
        CounterThread countTh = new CounterThread();
        Thread thread = new Thread(countTh);
        thread.start();
        return thread;
    }

}
