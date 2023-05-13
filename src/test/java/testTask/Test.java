package testTask;

import actions.AverageMedian;
import dao.CheckClientError;
import exceptions.ServerError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test
{
    private static Logger logger = LoggerFactory.getLogger(Test.class);
   private static final boolean ERROR =false;
   private static final boolean CORRECT=true;
   private static final float aRES1= 18.5f;
   private static final float aRES2= 35.8f;

    private static final float mRES1= 11.5f;
    private static final float mRES2= 25f;
private static CheckClientError data1= new CheckClientError();
private static CheckClientError data2= new CheckClientError();
private static CheckClientError data3= new CheckClientError();

private AverageMedian myDo= new AverageMedian();

@BeforeClass
  public static void Initialization()
{
    logger.info("TESTS STARTED");
    float[] arr1 = {6,50,17,1};
    data1.setArr(arr1);
    data1.setAMOUNT((byte)4);

    float[] arr2 = {8,2,42,102,25};
    data2.setArr(arr2);
    data2.setAMOUNT((byte)5);

    float[] arr3 = {};
    data3.setArr(arr3);
    data3.setAMOUNT((byte)0);

}


@org.junit.Test
public void TestAverage()
{
    logger.info("TEST AVERAGE STARTED");
    try {
     float res1= myDo.doAverage(data1);
     Assert.assertTrue(res1==aRES1);
     float res2= myDo.doAverage(data2);
     Assert.assertTrue(res2==aRES2);

        myDo.doAverage(data3);
    }catch(ServerError ex)
    {
        Assert.assertFalse(ERROR);
    }

}
@org.junit.Test
    public void TestMedian()
{
    logger.info("TEST MEDIAN STARTED");
    try {
        float res1= myDo.doMedian(data1);
        Assert.assertTrue(res1==mRES1);
        float res2= myDo.doMedian(data2);
        Assert.assertTrue(res2==mRES2);

        myDo.doMedian(data3);
    }catch(ServerError ex)
    {
        Assert.assertFalse(ERROR);
    }

}



}
