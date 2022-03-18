package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entity.Person;
import errorhandling.NotFoundException;
import facades.FacadeDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class Resource {

    FacadeDTO facadeDTO = new FacadeDTO("putest");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("error")
    public String throwError() throws Exception {
        throw new Exception();
    }


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
    public Response getPeopleById(@PathParam("id") long id) throws NotFoundException {
        PersonDTO p = facadeDTO.getPersonById(id);
        System.out.println(p);
        if (p==null)
            throw new NotFoundException("Person with id: " + id + "  not found");
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
    public Response getPersonCountByHobby(@PathParam("hobby") String hobby) {
        int count = facadeDTO.personCountByHobby(hobby);
        //return "{\"count\":"+count+"}";
        return Response
                .ok()
                .entity(gson.toJson(count))
                .build();
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
    public Response getAllZips() {
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
        Person p = gson.fromJson(content,Person.class);
        PersonDTO pdto = facadeDTO.createPerson(p);

        return Response
                .ok()
                .entity(gson.toJson(pdto))
                .build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(String jsonContext, @PathParam("id") Long id) throws NotFoundException {
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
    public PersonDTO deletePerson(@PathParam("id") Long id) throws NotFoundException {
        return facadeDTO.deletePerson(id);
    }



}
