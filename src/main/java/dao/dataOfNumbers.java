package dao;

public class dataOfNumbers
{
    public static final byte AMOUNT=4;

    private int[] arr= new int[AMOUNT];

   //todo handl the excaption
    public dataOfNumbers(String first, String second, String third, String fourth) {
       arr[0]=Integer.parseInt(first);
       arr[1]=Integer.parseInt(second);
       arr[2]=Integer.parseInt(third);
       arr[3]=Integer.parseInt(fourth);
    }

    public int[] getArr() {
        return arr;
    }

}
