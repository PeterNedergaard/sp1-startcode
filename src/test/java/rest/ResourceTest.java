package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/user";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        //EMF_Creator.startREST_TestWithDB();
        emf = Persistence.createEntityManagerFactory("putest");

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        //EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }




    /*
    @Test
    public void testMethod() {
        post("/1").then().assertThat().body("PERSON.")
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/parent").then().statusCode(200);
    }


    @Test
    public void testCount() {
        given()
                .contentType("application/json")
                .get("http://localhost:7777/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
    }
    */


    @Test
    void getAllPeople() {
    }

    @Test
    void getPeopleById() {
    }

    @Test
    void getPeopleByPhoneNumber() {
    }

    @Test
    void getPersonsByHobby() {
    }

    @Test
    void getPersonCountByHobby() {
    }

    @Test
    void getPersonsByZip() {
    }

    @Test
    void testGetPersonsByHobby() {
    }

    @Test
    void createPerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}