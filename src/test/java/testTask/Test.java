package testTask;

import actions.AverageMedian;
import dao.DataOfNumbers;
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
private static DataOfNumbers data1= new DataOfNumbers();
private static DataOfNumbers data2= new DataOfNumbers();
private static DataOfNumbers data3= new DataOfNumbers();

private AverageMedian myDo= new AverageMedian();

@BeforeClass
  public static void Initialization()
{
    logger.info("TESTS STARTED");
    int[] arr1 = {6,50,17,1};
    data1.SetArr(arr1);
    data1.SetAMOUNT((byte)4);

    int[] arr2 = {8,2,42,102,25};
    data2.SetArr(arr2);
    data2.SetAMOUNT((byte)5);

    int[] arr3 = {};
    data3.SetArr(arr3);
    data3.SetAMOUNT((byte)0);

}


@org.junit.Test
public void TestAverage()
{
    logger.info("TEST AVERAGE STARTED");
    try {
     float res1= myDo.DoAverage(data1);
     Assert.assertTrue(res1==aRES1);
     float res2= myDo.DoAverage(data2);
     Assert.assertTrue(res2==aRES2);

        myDo.DoAverage(data3);
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
        float res1= myDo.DoMedian(data1);
        Assert.assertTrue(res1==mRES1);
        float res2= myDo.DoMedian(data2);
        Assert.assertTrue(res2==mRES2);

        myDo.DoMedian(data3);
    }catch(ServerError ex)
    {
        Assert.assertFalse(ERROR);
    }

}


}
