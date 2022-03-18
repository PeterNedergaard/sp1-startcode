package facades;

import entity.*;
import errorhandling.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Facade implements IFacade{

    private String pu;
    EntityManagerFactory emf;


    public Facade(String pu) {
        this.pu = pu;
        emf = Persistence.createEntityManagerFactory(pu);
    }

    @Override
    public Set<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();

        try{
            List<Person> personList = em.createQuery("SELECT p FROM Person p",Person.class).getResultList();
            Set<Person> personSet = new HashSet<>(personList);

            return personSet;
        } finally {
            em.close();
        }

    }

    @Override
    public Person getPersonInfoByPhoneNum(String phoneNum) {

        EntityManager em = emf.createEntityManager();
        Long foundId = 0L;

        try{
            TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p WHERE p.number = :phoneNum",Phone.class);
            tq.setParameter("phoneNum",phoneNum);
            foundId = tq.setMaxResults(1).getSingleResult().getPerson().getId();

            return em.createQuery("SELECT p FROM Person p WHERE p.id =:foundId",Person.class)
                    .setParameter("foundId",foundId)
                    .getSingleResult();
        } finally {
            em.close();
        }

    }

    @Override
    public Set<Person> getPersonsByHobby(String hobbyName) {
        EntityManager em = emf.createEntityManager();

        Set<Person> personSet = new HashSet<>();


        try{

            TypedQuery<Hobby> tq = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :hobbyName",Hobby.class);
            tq.setParameter("hobbyName",hobbyName);

            return tq.getSingleResult().getPersonSet();

        } finally {
            em.close();
        }

    }

    @Override
    public Set<Person> getPersonsByZip(String zip) {
        EntityManager em = emf.createEntityManager();

        Long cityInfoId = 0L;
        List<Person> listOfPersons = new ArrayList<>();

        try{

            TypedQuery<CityInfo> tq = em.createQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zip",CityInfo.class);
            tq.setParameter("zip",zip);

            for (Address a : tq.getSingleResult().getAddressSet()) {
                for (Person p : a.getPersonSet()) {
                    listOfPersons.add(p);
                }
            }

            return new HashSet(listOfPersons);

        } finally {
            em.close();
        }


    }

    @Override
    public int personCountByHobby(String hobbyName) {
        EntityManager em = emf.createEntityManager();

        Long hobbyId = 0L;
        int count = 0;

        try{

            TypedQuery<Hobby> tq = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :hobbyName",Hobby.class);
            tq.setParameter("hobbyName",hobbyName);

            hobbyId = tq.getSingleResult().getId();

            for (Person p : getAllPersons()) {
                for (Hobby h : p.getHobbySet()) {
                    if (h.getId() == hobbyId){
                        count ++;
                    }
                }
            }

            return count;

        } finally {
            em.close();
        }

    }

    @Override
    public Set<String> getAllZipcodes() {
        List<String> allZipCodes= new ArrayList<>();
        EntityManager em = emf.createEntityManager();

        try{
            TypedQuery<CityInfo> tq=em.createQuery("SELECT e from CityInfo e", CityInfo.class);
            for (CityInfo a: tq.getResultList()) {
                allZipCodes.add(a.getZipCode());

            }
            return new HashSet<>(allZipCodes);

        } finally {
            em.close();
        }


    }

    @Override
    public Person createPerson(Person person) {
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            em.persist(person);

            em.getTransaction().commit();

            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPersonById(Long personId) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class,personId);

        if (person==null)
            throw new NotFoundException("person with id " + personId + " not found");
        try{
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person person, Long personId) throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        Person find = getPersonById(personId);
        if (!person.getFirstName().equals(null)){
            find.setFirstName(person.getFirstName());
            find.setLastName(person.getLastName());
            find.setEmail(person.getEmail());
        }

        try{
            em.getTransaction().begin();
            em.merge(find);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return getPersonById(personId);
    }

    @Override
    public Person deletePerson(Long personId) throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Person current = em.merge(getPersonById(personId));
            em.remove(current);
            em.getTransaction().commit();

            return current;

        } finally {
            em.close();
        }
    }


}
