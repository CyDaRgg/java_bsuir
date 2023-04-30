package actions;

import dao.DataOfNumbers;
import dao.TaskResponse;
import exceptions.ClientError;
import exceptions.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.InMemoryCaсhe;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

public class AverageMedian
{
    private static final Logger logger= LoggerFactory.getLogger(AverageMedian.class);
    private InMemoryCaсhe<String, Response> cache;

    public InMemoryCaсhe<String, Response> getCache() {
        return cache;
    }

    public void setCache(InMemoryCaсhe<String, Response> cache) {
        this.cache = cache;
    }

    public Response Task(String first, String second, String third, String fourth, String fifth  )
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("SERVER START {}", dt1);
        try {
            CheckParam(fourth,fifth);

            String itWas =cache.IsContains(first, second, third, fourth);
            if(itWas!=null)
            {
                logger.info("CACHE: THIS VALUES HAVE BEEN USED, WE DON'T CALCULATE IT AGAIN");
                logger.info("STOP");
                return Response.fromResponse(cache.Get(itWas)).build();
            }
            else {

                DataOfNumbers data = new DataOfNumbers(first, second, third, fourth);
                TaskResponse resp = new TaskResponse();

                resp.SetAverage(DoAverage(data));
                resp.SetMedian(DoMedian(data));

                JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("average is", resp.GetAverage())
                        .add("median is", resp.GetMedian());
                String json = jsonBuild.build().toString();

                Response answer= Response.status(200).entity(json).build();
                cache.Put(first+"_"+second+"_"+third+"_"+fourth, answer);
                logger.info("CACHE: WE HAVE ADDED A NEW VALUE IN CACHE");
                logger.info("SERVER ENDS CORRECT");
                return answer;
            }
        }
        catch(ClientError ex)
        {
            logger.error("CLIENT ERROR 400");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("clientError 400 ", ex.GetMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(400).entity(json).build();
        }
        catch(ServerError ex)
        {
            logger.error("SERVER ERROR 500");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("serverError 500 ", ex.GetMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(500).entity(json).build();
        }
    }

    public float DoAverage(DataOfNumbers data) throws ServerError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING AVERAGE {}", dt1);

        if(data.GetAMOUNT()==0)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR COUNTING AVERAGE {}", dt2);
            throw new ServerError("When we considered the average value, we had to divide by 0");
        }
            float sum = 0f;
            int[] arrN = data.GetArr();
            for (byte i = 0; i < data.GetAMOUNT(); ++i) {
                sum = sum + arrN[i];
            }
            return sum / data.GetAMOUNT();

    }
    public float DoMedian(DataOfNumbers data) throws ServerError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING MEDIAN {}", dt1);
        float median;
        int[] copy= Arrays.copyOf(data.GetArr(), data.GetAMOUNT());
        Arrays.sort(copy);
        byte mid=(byte)(copy.length/2);
        try {
            if (copy.length % 2 == 0) {
                median = ((float) copy[mid] + copy[mid - 1]) / 2;
            } else  median = copy[mid];

            return median;
        }catch(ArrayIndexOutOfBoundsException ex) {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR COUNTING MEDIAN {}", dt2);
            throw new ServerError("When we considered the median value, we had to go out of Bound of Array, input more than 0 parameters");
        }
    }
    public void CheckParam(String fourth, String fifth ) throws ClientError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START CHECK PARAM {}", dt1);
        if(fifth!=null)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR CHECK PARAMETERS, there are parameters more than 4 {}", dt2);
            throw new ClientError("There are parameters more than 4");
        }
        if(fourth==null)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR CHECK PARAMETERS, there are parameters less than 4 {}", dt2);
            throw new ClientError("There are parameters less than 4");
        }

    }

}
