package services;


import dao.AverageMedianResponse;
import dao.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;



public class DataBaseRecord
{
    private static EntityManager em =Persistence.createEntityManagerFactory("persistence").createEntityManager();
    private static final String SCRIPT=
            "DROP TABLE IF EXISTS request_parameters; " +
            "DROP TABLE IF EXISTS average_median; "+
            "CREATE TABLE request_parameters( " +
            "    parameters_id SERIAL PRIMARY KEY, " +
            "    first_param varchar(20) NOT NULL, " +
            "    second_param varchar(20) NOT NULL, " +
            "    third_param varchar(20) NOT NULL, " +
            "    fourth_param varchar(20) NOT NULL " +
            " ); " +
            "CREATE TABLE average_median(" +
            "    average_median_id SERIAL PRIMARY KEY, " +
            "    average REAL NOT NULL, " +
            "    median REAL NOT NULL " +
            "); ";

    public static void dataBaseRecord(Parameter paramRequest,AverageMedianResponse response, int id)
    {
        em.getTransaction().begin();

        prepareCalculations(em,paramRequest,response, id);

        em.getTransaction().commit();
    }

    private static void prepareCalculations( EntityManager em,Parameter paramRequest,AverageMedianResponse response, int id )
    {
       paramRequest.setParameters_id(id);
       response.setAverageMedian_id(id);

       em.merge(paramRequest);
       em.merge(response);
    }

    public static void  executeInitialScript() {
        em.getTransaction().begin();
        em.createNativeQuery(SCRIPT).executeUpdate();
        em.getTransaction().commit();
    }
}
