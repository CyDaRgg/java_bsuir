package actions;

import dao.dataOfNumbers;
import dao.taskResponse;
import exceptions.clientError;
import exceptions.serverError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.taskService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;

public class averageMedian
{
    private static final Logger logger= LoggerFactory.getLogger(averageMedian.class);

    public Response task(String first, String second, String third, String fourth, String fifth  )
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("SERVER START {}", dt1);
        try {
            CheckParam(first, second, third, fourth, fifth);
            dataOfNumbers data = new dataOfNumbers(first, second, third, fourth);

            taskResponse resp = new taskResponse();
            resp.setAverage(doAverage(data));
            resp.setMedian(doMedian(data));

            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("average is", resp.getAverage())
                    .add("median is", resp.getMedian());
            String json = jsonBuild.build().toString();

            logger.info("SERVER ENDS CORRECT");
            return Response.status(200).entity(json).build();
        }
        catch(clientError ex)
        {
            logger.error("CLIENT ERROR 400");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("clientError 400 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(400).entity(json).build();
        }
        catch(serverError ex)
        {
            logger.error("SERVER ERROR 500");
            JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("serverError 500 ", ex.getMessageError());
            String json = jsonBuild.build().toString();
            return Response.status(500).entity(json).build();
        }
    }

    public float doAverage(dataOfNumbers data) throws serverError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING AVERAGE {}", dt1);

        if(data.getAMOUNT()==0)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR COUNTING AVERAGE {}", dt2);
            throw new serverError("When we considered the average value, we had to divide by 0");
        }
            float sum = 0f;
            int[] arrN = data.getArr();
            for (byte i = 0; i < data.getAMOUNT(); ++i) {
                sum = sum + arrN[i];
            }
            return sum / data.getAMOUNT();

    }
    public float doMedian(dataOfNumbers data) throws serverError
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
            throw new serverError("When we considered the median value, we had to go out of Bound of Array, input more than 0 parameters");
        }
    }
    public void CheckParam(String first,String second, String thirst, String fourth, String fifth ) throws clientError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START CHECK PARAM {}", dt1);
        if(fifth!=null)
        {
            LocalDateTime dt2= LocalDateTime.now();
            logger.error("ERROR CHECK PARAMETERS, there are parameters more than 4 {}", dt2);
            throw new clientError("There are parameters more than 4");
        }

    }
}
