package actions.countId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import services.ControllerDataBaseRecord;

public class Id
{
    private static int counter=0;
//    static
//    {
//        EntityManager em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
//  //      em.getTransaction().begin();
//        String tableName = "average_median";
//        String idColumnName = "average_median_id";
////        Id.setCounter(ControllerDataBaseRecord.getCurrentMaxId(em));
//        Query query = em.createQuery("SELECT MAX(" + tableName + "." + idColumnName + ") FROM " + tableName);
//        Integer maxId = (Integer) query.getSingleResult();
//        counter=maxId != null ? maxId : 0;
//   //     em.getTransaction().commit();
//        //em.close();


//    }

    public static synchronized void   increment(MetaId id)
    {
        counter++;
        id.setCurrentId(counter);
    }

    public static  int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Id.counter = counter;
    }
}
