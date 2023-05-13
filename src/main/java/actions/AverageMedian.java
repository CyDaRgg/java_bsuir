package actions;

import dao.CheckClientError;
import dao.TaskResponse;
import exceptions.ClientError;
import exceptions.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.InMemoryCache;

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

    public TaskResponse task(String first, String second, String third, String fourth, String fifth  ) throws ClientError, ServerError
    {
        try {
            checkParam(fourth,fifth);
            CheckClientError data = new CheckClientError(first, second, third, fourth);

            TaskResponse countAnswer =cache.isContains(first, second, third, fourth);
            if(countAnswer!=null)
            {
                logger.info("CACHE: THIS VALUES HAVE BEEN USED, WE DON'T CALCULATE IT AGAIN: Average is {}, Median is {} {}",countAnswer.getAverage(), countAnswer.getMedian());
                logger.info("WE HAVE CALCULATED AVERAGE AND MEDIAN");
                return countAnswer;
            }
            else {

                TaskResponse resp = new TaskResponse();

                resp.setAverage(doAverage(data));
                resp.setMedian(doMedian(data));


                cache.put(first+"_"+second+"_"+third+"_"+fourth, resp);
                logger.info("CACHE: WE HAVE ADDED A NEW VALUE IN CACHE: Average is {}, Median is {}", resp.getAverage(), resp.getMedian());
                logger.info("WE HAVE CALCULATED AVERAGE AND MEDIAN");
                return resp;
            }
        }
        catch(ClientError ex)
        {
            logger.error("CLIENT ERROR 400");
            throw ex;
        }
        catch(ServerError ex)
        {
            logger.error("SERVER ERROR 500");
            throw ex;
        }
    }

    public float doAverage(CheckClientError data) throws ServerError
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
            float[] arrN = data.getArr();
            for (byte i = 0; i < data.getAMOUNT(); ++i) {
                sum = sum + arrN[i];
            }
            return sum / data.getAMOUNT();

    }
    public float doMedian(CheckClientError data) throws ServerError
    {
        LocalDateTime dt1= LocalDateTime.now();
        logger.info("START COUNTING MEDIAN {}", dt1);
        float median;
        float[] copy= Arrays.copyOf(data.getArr(), data.getAMOUNT());
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
