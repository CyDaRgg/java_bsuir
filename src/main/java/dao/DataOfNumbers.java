package dao;

import exceptions.ClientError;

import java.util.Arrays;

public class DataOfNumbers
{
    private byte AMOUNT=0;

    private int[] arr;

    public DataOfNumbers(String first, String second, String third, String fourth) throws ClientError {
      try {
    this.AMOUNT=4;

          arr= new int[AMOUNT];

          arr[0] = Integer.parseInt(first);
          arr[1] = Integer.parseInt(second);
          arr[2] = Integer.parseInt(third);
          arr[3] = Integer.parseInt(fourth);
      }catch(NumberFormatException ex)
      {
          throw new ClientError("There is/are not suitable parameter/parameters," +
                  " input only numbers ");
      }
    }

    public DataOfNumbers() {

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
