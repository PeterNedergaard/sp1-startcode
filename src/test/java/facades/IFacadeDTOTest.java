package facades;

import dto.PersonDTO;
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

class IFacadeDTOTest {

    EntityManagerFactory emf;
    EntityManager em;
    FacadeDTO facadeDTO;

    @BeforeEach
    void setUp() {
        facadeDTO = new FacadeDTO("putest");
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
        int expected = 8;
        int actual = facadeDTO.getAllPersons().size();

        assertEquals(expected,actual);
    }

    @Test
    void getPersonInfoByPhoneNum() {
        String expected = "peter@live.dk";
        String actual = facadeDTO.getPersonInfoByPhoneNum("54853846").getEmail();

        assertEquals(expected,actual);
    }

    @Test
    void getPersonsByHobby() {
        Set<String> expected = new HashSet<>();
        Set<String> actual = new HashSet<>();

        expected.add("peter@live.dk");
        expected.add("rabee@hotmail.dk");



        for (PersonDTO p : facadeDTO.getPersonsByHobby("Parkour")) {
            actual.add(p.getEmail());
        }



        assertEquals(expected,actual);
    }

    @Test
    void getPersonsByZip() {
        Set<String> expected = new HashSet<>();
        Set<String> actual = new HashSet<>();

        expected.add("peter@live.dk");
        expected.add("rabee@hotmail.dk");


        for (PersonDTO p : facadeDTO.getPersonsByZip("2791")) {
            actual.add(p.getEmail());
        }

        assertEquals(expected,actual);
    }

    @Test
    void personCountByHobby() {
        int expected = 3;
        int actual = facadeDTO.personCountByHobby("Card game");

        assertEquals(expected,actual);
    }

    @Test
    void getAllZipcodes() {
        Set<String> expected = new HashSet<>();
        expected.add("2791");
        expected.add("2000");
        expected.add("2400");
        expected.add("9800");
        expected.add("5700");
        Set<String> actual= facadeDTO.getAllZipcodes();
        assertEquals(expected,actual);
    }

    @Test
    void createPerson() {
        Person person = new Person("Hans@Bent.dk","Hans","Bent");

        PersonDTO actual = facadeDTO.createPerson(person);

        Person expectedPerson = em.createQuery("SELECT p FROM Person p ORDER BY p.id DESC",Person.class).setMaxResults(1).getSingleResult();
        PersonDTO expectedPersonDTO = new PersonDTO(expectedPerson.getEmail(),expectedPerson.getFirstName(), expectedPerson.getLastName());


        assertEquals(expectedPersonDTO,actual);
    }

    @Test
    void getPersonById() throws NotFoundException {
        Long expected = 1L;
        Long actual = facadeDTO.getPersonById(1L).getId();

        assertEquals(expected,actual);
    }

    @Test
    void editPerson() throws NotFoundException {
        String expected = "Kurt";
        PersonDTO personToEdit = new PersonDTO("rabee@hotmail.dk","Kurt","Abla");
        String actual = facadeDTO.editPerson(personToEdit,1L).getFirstName();

        assertEquals(expected,actual);
    }

    @Test
    void deletePerson() throws NotFoundException {

        Long idToDelete = 2L;
        facadeDTO.deletePerson(idToDelete);

        boolean expected = true;
        boolean actual = true;

        for (Person p : em.createQuery("SELECT p FROM Person p",Person.class).getResultList()) {
            if (p.getId().equals(idToDelete)){
                actual = false;
            }
        }

        assertEquals(expected,actual);
    }
}