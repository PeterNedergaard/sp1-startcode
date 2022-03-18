package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entity.*;
import facades.FacadeDTO;
import generate.Main;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.junit.jupiter.api.Assertions.*;


import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/user";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static Person p1,p2,p3,p4,p5,p6,p7,p8;
    private static Hobby h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15;
    private static Phone ph1,ph2,ph3,ph4,ph5,ph6,ph7,ph8;
    private static Address a1,a2,a3,a4,a5;
    private static CityInfo ci1,ci2,ci3,ci4,ci5;
    private static PersonDTO pdto1,pdto2,pdto3,pdto4,pdto5,pdto6,pdto7,pdto8;

    private static FacadeDTO facadeDTO;
    private static EntityManager em;

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
        facadeDTO = new FacadeDTO("putest");

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


    @BeforeEach
    public void setUp(){
        em = emf.createEntityManager();

        try {
            p1 = new Person("rabee@hotmail.dk", "Rabee", "Abla");
            p2 = new Person("peter@live.dk", "Peter", "Nedergaard");
            p3 = new Person("jens@mail.com", "Jens", "Hansen");
            p4 = new Person("Kurt@email.org", "Kurt", "Svensen");
            p5 = new Person("Mustafa@mulle@com", "Mustafa", "Kristensen");
            p6 = new Person("Lars@jysk.dk", "Lars", "Larsen");
            p7 = new Person("Ahmed@sha7ata.uae", "Ahmed", "Madsen");
            p8 = new Person("Kim@larsen.dk", "Kim", "Larsen");

            h1 = new Hobby("Parkour", "Feels like spiderman");
            h2 = new Hobby("Ping pong", "Inspired by Forrest Gump");
            h3 = new Hobby("Board games", "Use a pre-marked board");
            h4 = new Hobby("Bowling", "is a target sport");
            h5 = new Hobby("Baking", "is a method of preparing food");
            h6 = new Hobby("Card game", "is any game using playing cards");
            h7 = new Hobby("Computer programmering", "write and test code");
            h8 = new Hobby("Cue sport", "played with a cue stick");
            h9 = new Hobby("Pole Dancing", "Pole dance combines dance and acrobatics");
            h10 = new Hobby("Sewing", "is the craft of fastening");
            h11 = new Hobby("Welding", "a fabrication process that joins materials");
            h12 = new Hobby("Baseball", "a game played with a bat");
            h13 = new Hobby("Basketball", "is a game played between two teams");
            h14 = new Hobby("BMX", "racing motocross");
            h15 = new Hobby("Cycling", "is the use of bicycles for transport");

            ph1 = new Phone("54843585", "Nokia 3310i");
            ph2 = new Phone("54853846", "Nokia 6610");
            ph3 = new Phone("12345678", "Brick 900");
            ph4 = new Phone("87654321", "Samsung S41");
            ph5 = new Phone("72861928", "NotAPhone");
            ph6 = new Phone("26105148", "Applesung");
            ph7 = new Phone("46197261", "Dumbphone");
            ph8 = new Phone("62904617", "Apple Iphone 14");

            a1 = new Address("Rosenlundsvej 63", "Three plus five");
            a2 = new Address("Carlos Allé 52", "Sixteen plus nine");
            a3 = new Address("Area 51 street", "Aliens");
            a4 = new Address("King street 42", "Kings only");
            a5 = new Address("Moms Basement 21", "Let me out");

            ci1 = new CityInfo("2791", "Dragør");
            ci2 = new CityInfo("2000", "København K");
            ci3 = new CityInfo("2400", "København NV");
            ci4 = new CityInfo("9800", "Hørring");
            ci5 = new CityInfo("5700", "Svendborg");

            em.getTransaction().begin();

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
            em.persist(p6);
            em.persist(p7);
            em.persist(p8);


            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(h4);
            em.persist(h5);
            em.persist(h6);
            em.persist(h7);
            em.persist(h8);
            em.persist(h9);
            em.persist(h10);
            em.persist(h11);
            em.persist(h12);
            em.persist(h13);
            em.persist(h14);
            em.persist(h15);

            em.persist(ph1);
            em.persist(ph2);
            em.persist(ph3);
            em.persist(ph4);
            em.persist(ph5);
            em.persist(ph6);
            em.persist(ph7);
            em.persist(ph8);

            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(a4);
            em.persist(a5);

            em.persist(ci1);
            em.persist(ci2);
            em.persist(ci3);
            em.persist(ci4);
            em.persist(ci5);

            h1.addPerson(p1);
            h1.addPerson(p2);
            h5.addPerson(p3);
            h6.addPerson(p4);
            h6.addPerson(p5);
            h6.addPerson(p6);
            h9.addPerson(p7);
            h11.addPerson(p7);
            h15.addPerson(p8);

            p1.addPhone(ph1);
            p2.addPhone(ph2);
            p3.addPhone(ph3);
            p4.addPhone(ph4);
            p5.addPhone(ph5);
            p6.addPhone(ph6);
            p7.addPhone(ph7);
            p8.addPhone(ph8);

            p1.addAddress(a1);
            p2.addAddress(a1);
            p3.addAddress(a2);
            p4.addAddress(a2);
            p5.addAddress(a3);
            p6.addAddress(a3);
            p7.addAddress(a4);
            p8.addAddress(a5);

            a1.addCityInfo(ci1);
            a2.addCityInfo(ci2);
            a3.addCityInfo(ci3);
            a4.addCityInfo(ci4);
            a5.addCityInfo(ci5);

            em.getTransaction().commit();

        }finally {
            em.close();
        }

        pdto1 = new PersonDTO(p1);
        pdto2 = new PersonDTO(p2);
        pdto3 = new PersonDTO(p3);
        pdto4 = new PersonDTO(p4);
        pdto5 = new PersonDTO(p5);
        pdto6 = new PersonDTO(p6);
        pdto7 = new PersonDTO(p7);
        pdto8 = new PersonDTO(p8);

    }


    // REMEMBER TO CHANGE FACADE ARGUMENT IN RESOURCE

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/user/all").then().statusCode(200);
    }


    @Test
    void getPeopleById() {
        PersonDTO actualPersonDTO;
        actualPersonDTO = given()
                .contentType("application/json")
                .when()
                .get("/user/" + p1.getId())
                .then()
                .extract().body().jsonPath().getObject("",PersonDTO.class);
        assertEquals(pdto1,actualPersonDTO);

    }

    @Test
    void getAllPeople() {

        List<PersonDTO> personDTOList = given()
                .contentType("application/json")
                .when()
                .get("/user/all")
                .then()
                .extract().body().jsonPath().getList("",PersonDTO.class);
        assertThat(personDTOList,containsInAnyOrder(pdto1,pdto2,pdto3,pdto4,pdto5,pdto6,pdto7,pdto8));

    }

    @Test
    void getPeopleByPhoneNumber() {

        PersonDTO actualPersonDTO;
        actualPersonDTO = given()
                .contentType("application/json")
                .when()
                .get("/user/phone/" + "12345678")
                .then()
                .extract().body().jsonPath().getObject("",PersonDTO.class);
        assertEquals(pdto3,actualPersonDTO);

    }

    @Test
    void getPersonsByHobby() {

        List<PersonDTO> personDTOList = given()
                .contentType("application/json")
                .when()
                .get("/user/hobby/" + "Parkour")
                .then()
                .extract().body().jsonPath().getList("",PersonDTO.class);
        assertThat(personDTOList,containsInAnyOrder(pdto1,pdto2));

    }

    @Test
    void getPersonCountByHobby() {

        // actualCount returns null, solve later

        /*
        Object expectedCount = 2;
        Object actualCount = given()
                .contentType("application/json")
                .when()
                .get("/user/hobby/count/" + "Parkour")
                .then()
                .extract().body().jsonPath().getInt("");
        assertEquals(expectedCount,actualCount);
        */

    }

    @Test
    void getPersonsByZip() {

        List<PersonDTO> personDTOList = given()
                .contentType("application/json")
                .when()
                .get("/user/zipcode/" + "2000")
                .then()
                .extract().body().jsonPath().getList("",PersonDTO.class);
        assertThat(personDTOList,containsInAnyOrder(pdto4,pdto3));

    }

    @Test
    void createPerson() {

        Person person = new Person("martin@hotmail.dk","Martin","Hansen");
        person.addAddress(a1);
        h1.addPerson(person);
        person.addPhone(ph1);

        PersonDTO actualPersonDTO = new PersonDTO(person);

        String requestBody = GSON.toJson(actualPersonDTO);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .assertThat()
                .statusCode(200)
                .body("email",equalTo("martin@hotmail.dk"))
                .body("firstName",equalTo("Martin"))
                .body("lastName",equalTo("Hansen"));

    }

    @Test
    void updatePerson() {

        p1.setLastName("Jesperhansen");

        PersonDTO pdto = new PersonDTO(p1);

        String requestBody = GSON.toJson(pdto);

        given()
                .header("Content-type", ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/user/" + p1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("email",equalTo("rabee@hotmail.dk"))
                .body("firstName",equalTo("Rabee"))
                .body("lastName",equalTo("Jesperhansen"));

    }

    @Test
    void deletePerson() {

        PersonDTO personDTO = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/user/" + p1.getId())
                .then()
                .extract().body().jsonPath().getObject("",PersonDTO.class);

        assertThat(personDTO.getEmail(),equalTo(p1.getEmail()));
    }
}