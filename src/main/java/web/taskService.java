package web;

import dao.dataOfNumbers;
import dao.taskResponse;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/task")
public class taskService
{
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response averageAndMedian(@QueryParam("first") String first,
                                     @QueryParam("second") String second,
                                     @QueryParam("third") String third,
                                     @QueryParam("fourth") String fourth)
    {
        taskResponse resp=new taskResponse();
        dataOfNumbers data= new dataOfNumbers(first,second, third, fourth);
        resp.setAverage(doAverage(data));
        resp.setMedian(doMedian(data));

        JsonObjectBuilder jsonBuild= Json.createObjectBuilder().add("average is",resp.getAverage())
                                                               .add("median is", resp.getMedian());
        String json= jsonBuild.build().toString();


        return Response.status(200).entity(json).build();

    }

    private float doAverage(dataOfNumbers data)
    {
        float sum=0f;
        int[] arrN=data.getArr();
        for(byte i=0; i<data.AMOUNT; ++i)
        {
            sum=sum+arrN[i];
        }
        return sum/data.AMOUNT;
    }
    private float doMedian(dataOfNumbers data)
    {
        float median;
        int[] copy= Arrays.copyOf(data.getArr(), data.AMOUNT);
        Arrays.sort(copy);
        byte mid=(byte)(copy.length/2);

        if(copy.length%2==0)
        {
            median=((float)copy[mid]+copy[mid-1])/2;
        }
        else
        {
            median=copy[mid];
        }
        return median;
    }
}
