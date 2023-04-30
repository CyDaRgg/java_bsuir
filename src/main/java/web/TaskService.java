package web;

import actions.AverageMedian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/task")
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private AverageMedian myTask= new AverageMedian();
    private static ApplicationContext context;
    private  AverageMedian controller;

    static
    {
        context= new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});

    }


    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response AverageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth,
                                     @QueryParam("fifth") String fifth) {

        controller= context.getBean("AverageMedian", AverageMedian.class);
        return controller.Task(first, second, third, fourth, fifth);

    }

}
