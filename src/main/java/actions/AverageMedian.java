package actions;

import dao.DataOfNumbers;
import dao.TaskResponse;
import exceptions.ClientError;
import exceptions.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.InMemoryCache;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;


public class AverageMedian
{
    private static final Logger logger= LoggerFactory.getLogger(AverageMedian.class);
    private static InMemoryCache cache;

    public InMemoryCache getCache() {
        return cache;
    }

    public void setCache(InMemoryCache cache) {
        this.cache = cache;
    }

    public Response task(String first, String second, String third, String fourth, String fifth  )
    {
        try {
            checkParam(fourth,fifth);

            String jsonTaskAnswer =cache.isContains(first, second, third, fourth);
            if(jsonTaskAnswer!=null)
            {
                logger.info("CACHE: THIS VALUES HAVE BEEN USED, WE DON'T CALCULATE IT AGAIN {}",jsonTaskAnswer);
                logger.info("WE HAVE CALCULATED AVERAGE AND MEDIAN");
                return Response.status(200).entity(jsonTaskAnswer).build();
            }
            else {

                DataOfNumbers data = new DataOfNumbers(first, second, third, fourth);
                TaskResponse resp = new TaskResponse();

                resp.setAverage(doAverage(data));
                resp.setMedian(doMedian(data));

                JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("average is", resp.getAverage())
                        .add("median is", resp.getMedian());
                String json = jsonBuild.build().toString();

                Response answer= Response.status(200).entity(json).build();
                cache.put(first+"_"+second+"_"+third+"_"+fourth, json);
                logger.info("CACHE: WE HAVE ADDED A NEW VALUE IN CACHE: {}",json);
                logger.info("WE HAVE CALCULATED AVERAGE AND MEDIAN");
                return answer;
            }
        }
        catch(ClientError ex)
        {
            logger.error("CLIENT ERROR 400");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("clientError 400 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(400).entity(json).build();
        }
        catch(ServerError ex)
        {
            logger.error("SERVER ERROR 500");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("serverError 500 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(500).entity(json).build();
        }
    }

    public float doAverage(DataOfNumbers data) throws ServerError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING AVERAGE {}", dt1);

        if(data.getAMOUNT()==0)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR COUNTING AVERAGE {}", dt2);
            throw new ServerError("When we considered the average value, we had to divide by 0");
        }
            float sum = 0f;
            int[] arrN = data.getArr();
            for (byte i = 0; i < data.getAMOUNT(); ++i) {
                sum = sum + arrN[i];
            }
            return sum / data.getAMOUNT();

    }
    public float doMedian(DataOfNumbers data) throws ServerError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING MEDIAN {}", dt1);
        float median;
        int[] copy= Arrays.copyOf(data.getArr(), data.getAMOUNT());
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
    public void checkParam(String fourth, String fifth ) throws ClientError
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
