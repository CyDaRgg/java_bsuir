package web;


import actions.counter.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/counter")
public class CounterResponse
{
    private static Logger logger = LoggerFactory.getLogger(CounterResponse.class);


    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response counter()
    {
        int count=Counter.getCounter();
        JsonObjectBuilder jsonBuild= Json.createObjectBuilder().add("Amount of all requests is ", count);
        String json= jsonBuild.build().toString();
        logger.info("SERVER COUNT RESPONSE, ALL AMOUNT OF RESPONSES IS {}",count);
        return Response.status(200).entity(json).build();
    }


}
