package web;

import actions.AverageMedian;
import actions.countId.IdThread;
import actions.counter.Counter;
import actions.counter.CounterThread;
import dao.AverageMedianResponse;
import dao.Parameter;
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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
@Path("/asynch")
public class AsynchronousCall
{
    private static Logger logger = LoggerFactory.getLogger(TaskService.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static AverageMedian contrAverageMedian = InitSpringContext.getContext().getBean("AverageMedian", AverageMedian.class);


    static
    {
        DataBaseRecord.executeInitialScript();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response asynch ( @QueryParam("first") String first,
                            @QueryParam("second") String second,
                            @QueryParam("third") String third,
                            @QueryParam("fourth") String fourth,
                            @QueryParam("fifth") String fifth) {
       LocalDateTime dt1 = LocalDateTime.now();
       logger.info("SERVER START {}", dt1);
       int id =countId();
        executorService.submit(() -> {
                asynchCalculations(first, second, third, fourth, fifth, id);
        });
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("response id is "+ id, "Server is calculating average and median, please wait!");
        String json = jsonBuild.build().toString();
        return Response.status(200).entity(json).build();

   }

    private int countId() {
        IdThread countId = new IdThread();
        Thread thread = new Thread(countId);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            new RuntimeException(e.getMessage());
        }
        return countId.getCurrentId().getCurrentId();
    }

 void asynchCalculations(String first, String second, String third, String fourth, String fifth, int id  )  {
     try {
         AverageMedianResponse resp = contrAverageMedian.task(first, second, third, fourth, fifth);
         Parameter p= new Parameter();
         p.setFirst(first);
         p.setSecond(second);
         p.setThird(third);
         p.setFourth(fourth);

         try {
             Thread.sleep(30000);
         } catch (InterruptedException e) {
             new RuntimeException(e.getMessage());
         }
         DataBaseRecord.dataBaseRecord(p,resp,id);
         logger.info("STOP");
     } catch (ClientError ex) {
         throw new RuntimeException(ex.getMessage());
     } catch (ServerError ex) {
         throw new RuntimeException(ex.getMessage());
     }
 }
}
