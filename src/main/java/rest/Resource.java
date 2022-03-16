package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entity.Person;
import facades.Facade;
import facades.FacadeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class Resource {

    FacadeDTO facadeDTO = new FacadeDTO("pu");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPeople() {
        Set<PersonDTO> personDTOs = facadeDTO.getAllPersons();
        return Response
                .ok()
                .entity(gson.toJson(personDTOs))
                .build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleById(@PathParam("id") long id) {
        PersonDTO p = facadeDTO.getPersonById(id);
        return Response
                .ok()
                .entity(gson.toJson(p))
                .build();
    }



    @GET
    @Path("/phone/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
        PersonDTO p = facadeDTO.getPersonInfoByPhoneNum(phoneNumber);
        return Response
                .ok()
                .entity(gson.toJson(p))
                .build();
    }


    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("hobby") String hobby) {
        Set<PersonDTO> p = facadeDTO.getPersonsByHobby(hobby);
        return Response
                .ok()
                .entity(gson.toJson(p))
                .build();
    }


    @GET
    @Path("/hobby/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonCountByHobby(@PathParam("hobby") String hobby) {
        int count = facadeDTO.getPersonsByHobby(hobby).size();
        return "{\"count\":"+count+"}";
    }


    @GET
    @Path("/zipcode/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByZip(@PathParam("zip") String zip) {
        Set<PersonDTO> personDTOS = facadeDTO.getPersonsByZip(zip);
        return Response
                .ok()
                .entity(gson.toJson(personDTOS))
                .build();
    }


    @GET
    @Path("/zipcode/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby() {
        Set<String> zips = facadeDTO.getAllZipcodes();
        return Response
                .ok()
                .entity(gson.toJson(zips))
                .build();
    }


    @POST
    //@Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(String content) {
        PersonDTO pdto = gson.fromJson(content,PersonDTO.class);
        PersonDTO newpdto = facadeDTO.createPerson(pdto);

        return Response
                .ok()
                .entity(gson.toJson(newpdto))
                .build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(String jsonContext, @PathParam("id") Long id) {
        PersonDTO pdto = gson.fromJson(jsonContext,PersonDTO.class);
        System.out.println(pdto.getEmail());
        System.out.println(pdto.getFirstName());
        PersonDTO updatedPdto = facadeDTO.editPerson(pdto,id);
        return Response
                .ok()
                .entity(gson.toJson(updatedPdto))
                .build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deletedPerson = facadeDTO.deletePerson(id);
        return Response
                .ok()
                .entity(gson.toJson(deletedPerson))
                .build();
    }



}
