package facades;

import entity.Person;
import errorhandling.NotFoundException;

import java.util.Set;

public interface IFacade {

    Set<Person> getAllPersons();

    Person getPersonInfoByPhoneNum(String phoneNum);

    Set<Person> getPersonsByHobby(String hobbyName);

    Set<Person> getPersonsByZip(String zip);

    int personCountByHobby(String hobbyName);

    Set<String> getAllZipcodes();

    Person createPerson(Person person);

    Person getPersonById(Long personId) throws NotFoundException;

    Person editPerson(Person person, Long personId) throws NotFoundException;

    Person deletePerson(Long personId) throws NotFoundException;


}
