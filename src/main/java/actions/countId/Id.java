package actions.countId;

public class Id
{
    private static int counter=0;

    public static synchronized void   increment(MetaId id)
    {
        counter++;
        id.setCurrentId(counter);
    }

    public static  int getCounter() {
        return counter;
    }
}
