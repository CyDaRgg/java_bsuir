package testTask;

import actions.averageMedian;
import dao.dataOfNumbers;
import exceptions.serverError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class test
{
    private static Logger logger = LoggerFactory.getLogger(test.class);
   private static final boolean ERROR =false;
   private static final boolean CORRECT=true;
   private static final float aRES1= 18.5f;
   private static final float aRES2= 35.8f;

    private static final float mRES1= 11.5f;
    private static final float mRES2= 25f;
private static dataOfNumbers data1= new dataOfNumbers();
private static dataOfNumbers data2= new dataOfNumbers();
private static dataOfNumbers data3= new dataOfNumbers();

private averageMedian myDo= new averageMedian();

@BeforeClass
  public static void initialization()
{
    logger.info("TESTS STARTED");
    int[] arr1 = {6,50,17,1};
    data1.setArr(arr1);
    data1.setAMOUNT((byte)4);

    int[] arr2 = {8,2,42,102,25};
    data2.setArr(arr2);
    data2.setAMOUNT((byte)5);

    int[] arr3 = {};
    data3.setArr(arr3);
    data3.setAMOUNT((byte)0);

}


@Test
public void testAverage()
{
    logger.info("TEST AVERAGE STARTED");
    try {
     float res1= myDo.doAverage(data1);
     Assert.assertTrue(res1==aRES1);
     float res2= myDo.doAverage(data2);
     Assert.assertTrue(res2==aRES2);

        myDo.doAverage(data3);
    }catch(serverError ex)
    {
        Assert.assertFalse(ERROR);
    }

}
@Test
    public void testMedian()
{
    logger.info("TEST MEDIAN STARTED");
    try {
        float res1= myDo.doMedian(data1);
        Assert.assertTrue(res1==mRES1);
        float res2= myDo.doMedian(data2);
        Assert.assertTrue(res2==mRES2);

        myDo.doMedian(data3);
    }catch(serverError ex)
    {
        Assert.assertFalse(ERROR);
    }

}


}
