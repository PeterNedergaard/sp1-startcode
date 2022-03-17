package facades;

import entity.Person;
import errorhandling.NotFoundException;
import generate.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IFacadeTest {

    EntityManagerFactory emf;
    EntityManager em;
    Facade facade;

    @BeforeEach
    void setUp() {
        facade = new Facade("putest");
        emf = Persistence.createEntityManagerFactory("putest");
        em = emf.createEntityManager();
        Main.generate(emf);
    }

    @AfterEach
    void tearDown() {


//        try {
//            em.getTransaction().begin();
//            Query query = em.createQuery("DELETE FROM Person where id >= 0");
//            Query query2 = em.createQuery("DELETE FROM Hobby where id >= 0");
//            int rowCount = query.executeUpdate();
//            int rowCount2 = query2.executeUpdate();
//            em.getTransaction().commit();
//        }finally {
//            em.close();
//        }
//
    }



    @Test
    void getAllPersons() {
        int expected = 3;
        int actual = facade.getAllPersons().size();

        assertEquals(expected,actual);
    }

    @Test
    void getPersonInfoByPhoneNum() {
        String expected = "rabee@hotmail.dk";
        String actual = facade.getPersonInfoByPhoneNum("54853846").getEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getPersonsByHobby() {
        Set<String> expected = new HashSet<>();
        Set<String> actual = new HashSet<>();

        expected.add("peter@live.dk");
        expected.add("rabee@hotmail.dk");



        for (Person p : facade.getPersonsByHobby("Parkour")) {
            actual.add(p.getEmail());
        }



        assertEquals(expected,actual);
    }

    @Test
    void getPersonsByZip() {
        Set<String> expected = new HashSet<>();
        Set<String> actual = new HashSet<>();

        expected.add("mo@yahoo.dk");
        expected.add("rabee@hotmail.dk");


        for (Person p : facade.getPersonsByZip("2791")) {
            actual.add(p.getEmail());
        }

        assertEquals(expected,actual);
    }

    @Test
    void personCountByHobby() {
        int expected = 1;
        int actual = facade.personCountByHobby("Ping pong");

        assertEquals(expected,actual);
    }

    @Test
    void getAllZipcodes() {
        Set<String> expected = new HashSet<>();
        expected.add("2000");
        expected.add("2791");
        Set<String> actual= facade.getAllZipcodes();
        assertEquals(expected,actual);
    }

    @Test
    void createPerson() {
        Person actual = facade.createPerson(new Person("hej@email.dk","Jens","Jensen"));
        Person expected = em.createQuery("SELECT p FROM Person p ORDER BY p.id DESC",Person.class).setMaxResults(1).getSingleResult();


        assertEquals(expected,actual);
    }

    @Test
    void getPersonById() throws NotFoundException {
        Long expected = 1L;
        Long actual = facade.getPersonById(1L).getId();

        assertEquals(expected,actual);
    }

    @Test
    void editPerson() throws NotFoundException {
        String expected = "Kurt";
        Person personToEdit = new Person("rabee@hotmail.dk","Kurt","Abla");
        String actual = facade.editPerson(personToEdit,1L).getFirstName();

        assertEquals(expected,actual);
    }

    @Test
    void deletePerson() throws NotFoundException {
        Long expected = 3L;
        Long actual = facade.deletePerson(3L);

        assertEquals(expected,actual);
    }
}