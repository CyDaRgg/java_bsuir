package actions.counter;

public class CounterThread implements Runnable
{

    @Override
    public  void run() {
        Counter.increment();
    }
}
