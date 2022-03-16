package facades;

import entity.Person;

import java.util.List;
import java.util.Set;

public interface IFacade {

    Set<Person> getAllPersons();

    Person getPersonInfoByPhoneNum(String phoneNum);

    Set<Person> getPersonsByHobby(String hobbyName);

    Set<Person> getPersonsByZip(String zip);

    int personCountByHobby(String hobbyName);

    Set<String> getAllZipcodes();

    Person createPerson(Person person);

    Person getPersonById(Long personId);

    Person editPerson(Person person, Long personId);

    boolean deletePerson(Long personId);


}
