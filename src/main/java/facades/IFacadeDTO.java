package facades;

import dto.PersonDTO;
import entity.Person;

import java.util.List;
import java.util.Set;

public interface IFacadeDTO {

    Set<PersonDTO> getAllPersons();

    PersonDTO getPersonInfoByPhoneNum(String phoneNum);

    Set<PersonDTO> getPersonsByHobby(String hobbyName);

    Set<PersonDTO> getPersonsByZip(String zip);

    int personCountByHobby(String hobbyName);

    Set<String> getAllZipcodes();

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO getPersonById(Long personId);

    PersonDTO editPerson(PersonDTO personDTO, Long personId);

    boolean deletePerson(Long personId);



}
