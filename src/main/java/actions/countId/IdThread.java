package actions.countId;

import actions.counter.Counter;

public class IdThread implements Runnable
{
    private MetaId currentId=new MetaId();

    @Override
    public  void run()
    {
        Id.increment(currentId);
    }

    public MetaId getCurrentId() {
        return currentId;
    }
}

