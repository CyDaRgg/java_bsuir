package services;


import dao.AverageMedianResponse;
import dao.Parameter;
import dao.StatisticsValuesResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DataBaseRecord
{
    public static void dataBaseRecord(List<Parameter> paramRequest,List<AverageMedianResponse> responses, StatisticsValuesResponse statistics)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager(); //типо сессия

        em.getTransaction().begin();


        prepareCalculations(em,paramRequest,responses);

        em.getTransaction().commit();
    }

    private static void prepareCalculations( EntityManager em,List<Parameter> paramRequest,List<AverageMedianResponse> responses )
    {
        Parameter p;
        AverageMedianResponse amr;

        for(int i=0; i<paramRequest.size();++i)
        {
            p=paramRequest.get(i);
            amr=responses.get(i);

            em.merge(p);
            em.merge(amr);
        }
    }
}
