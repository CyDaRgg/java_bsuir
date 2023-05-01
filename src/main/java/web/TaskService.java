package web;

import actions.AverageMedian;
import actions.counter.Counter;
import actions.counter.CounterThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.InitSpringContext;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/task")
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private static AverageMedian   contrAverageMedian;

    static
    {
      //  contrAverageMedian= InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);

    }


    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response AverageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth) throws InterruptedException {

      try {
          contrAverageMedian= InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);

          Thread thread = CountAllAmountOfRequests();
          Response res = contrAverageMedian.Task(first, second, third, fourth, fifth);


          logger.info("COUNTER: FINISHED, ALL AMOUNT OF REQUESTS IS {} ", Counter.getCounter());
          thread.join();
          return res;
      }
      catch(InterruptedException ex)
      {
          logger.error("SERVER ERROR 500");
          JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("serverError 500 ","thread.join");
          String json = jsonBuild.build().toString();
          return Response.status(500).entity(json).build();
      }

    }

    private Thread CountAllAmountOfRequests()
    {
        CounterThread countTh= new CounterThread();
        Thread thread= new Thread(countTh);
        thread.start();
        return thread;
    }

}
