package dao;

import exceptions.ClientError;

import java.util.Arrays;

public class CheckClientError
{
    private byte AMOUNT=0;

    private float[] arr;

    public CheckClientError(String first, String second, String third, String fourth) throws ClientError {
      try {
    this.AMOUNT=4;

          arr= new float[AMOUNT];

          arr[0] = Float.parseFloat(first);
          arr[1] = Float.parseFloat(second);
          arr[2] = Float.parseFloat(third);
          arr[3] = Float.parseFloat(fourth);
      }catch(NumberFormatException ex)
      {
          throw new ClientError("There is/are not suitable parameter/parameters," +
                  " input only numbers ");
      }
    }

    public CheckClientError() {

    }

    public float[] getArr() {
        return arr;
    }

    public byte getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(byte AMOUNT) {
       this.AMOUNT = AMOUNT;
    }

    public void setArr(float[] arr) {
        float[] copy= Arrays.copyOf(arr, arr.length);
        this.arr = copy;
    }
}
