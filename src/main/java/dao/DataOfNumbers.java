package dao;

import exceptions.ClientError;

import java.util.Arrays;

public class DataOfNumbers
{
    private byte AMOUNT=0;

    private int[] arr;

   //todo handl the excaption
    public DataOfNumbers(String first, String second, String third, String fourth) throws ClientError {
      try {
          if(first!=null)
              AMOUNT++;
          if(second!=null)
              AMOUNT++;
          if(third!=null)
              AMOUNT++;
          if(fourth!=null)
              AMOUNT++;

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
          throw new ClientError("There is/are not suitable parameter/parameters," +
                  " input only numbers ");
      }
    }

    public DataOfNumbers() {

    }

    public int[] GetArr() {
        return arr;
    }

    public byte GetAMOUNT() {
        return AMOUNT;
    }

    public void SetAMOUNT(byte AMOUNT) {
       this.AMOUNT = AMOUNT;
    }

    public void SetArr(int[] arr) {
        int[] copy= Arrays.copyOf(arr, arr.length);
        this.arr = copy;
    }
}
