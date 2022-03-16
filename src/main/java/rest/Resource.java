package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import facades.Facade;
import facades.FacadeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class Resource {

    FacadeDTO facadeDTO = new FacadeDTO("pu");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /*@GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }*/


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") long id) {
        PersonDTO p = facadeDTO.getPersonById(id);
        return Response
                .ok()
                .entity(gson.toJson(p))
                .build();
    }


}
