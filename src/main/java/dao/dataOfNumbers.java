package dao;

import exceptions.clientError;
import exceptions.serverError;

import java.util.Arrays;

public class dataOfNumbers
{
    private byte AMOUNT=0;

    private int[] arr;

   //todo handl the excaption
    public dataOfNumbers(String first, String second, String third, String fourth) throws clientError {
      try {
          if(first!=null)AMOUNT++; if(second!=null)AMOUNT++;if(third!=null)AMOUNT++;if(fourth!=null)AMOUNT++;

          arr= new int[AMOUNT];
          if(AMOUNT>0)
          arr[0] = Integer.parseInt(first);
          if(AMOUNT>1)
          arr[1] = Integer.parseInt(second);
          if(AMOUNT>2)
          arr[2] = Integer.parseInt(third);
          if(AMOUNT>3)
          arr[3] = Integer.parseInt(fourth);
      }catch(NumberFormatException ex)
      {
          throw new clientError("There is/are not suitable parameter/parameters," +
                  " input only numbers ");
      }
    }

    public dataOfNumbers() {

    }

    public int[] getArr() {
        return arr;
    }

    public byte getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(byte AMOUNT) {
       this.AMOUNT = AMOUNT;
    }

    public void setArr(int[] arr) {
        int[] copy= Arrays.copyOf(arr, arr.length);
        this.arr = copy;
    }
}
