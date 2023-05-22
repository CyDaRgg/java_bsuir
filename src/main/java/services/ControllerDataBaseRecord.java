package services;


import dao.AverageMedianResponse;
import dao.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/get_asynch")
public class ControllerDataBaseRecord
{
    private static EntityManager em =Persistence.createEntityManagerFactory("persistence").createEntityManager();
//    private static final String TABLENAME = "average_median";
//    private static final  String IDCOLUMNNAME = "average_median_id";

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

//    public static int getCurrentMaxId(EntityManager entityM) {
//        Query query = entityM.createQuery("SELECT MAX(" + TABLENAME + "." + IDCOLUMNNAME + ") FROM " + TABLENAME);
//        Integer maxId = (Integer) query.getSingleResult();
//        return maxId != null ? maxId : 0;
//    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
public Response getAverageMedian(@QueryParam("id") int id)
{
    AverageMedianResponse amr = em.find(AverageMedianResponse.class, id);
    Parameter p = em.find(Parameter.class, id);

    if (amr != null || p!=null) {
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("response id is "+ id, "there are the following values").
                add("first_param",p.getFirst()).
                add("second_param", p.getSecond()).
                add("third_param",p.getThird()).
                add("fourth_param",p.getFourth()).
                add("average",amr.getAverage()).
                add("median",amr.getMedian());
        String json = jsonBuild.build().toString();
        return Response.status(200).entity(json).build();
    } else {
        JsonObjectBuilder jsonBuild = Json.createObjectBuilder().add("response id is "+ id, "No values according to this id");
        String json = jsonBuild.build().toString();
        return Response.status(400).entity(json).build();
    }
}
}

