package facades;

import dto.PersonDTO;
import errorhandling.NotFoundException;

import java.util.Set;

public interface IFacadeDTO {

    Set<PersonDTO> getAllPersons();

    PersonDTO getPersonInfoByPhoneNum(String phoneNum);

    Set<PersonDTO> getPersonsByHobby(String hobbyName);

    Set<PersonDTO> getPersonsByZip(String zip);

    int personCountByHobby(String hobbyName);

    Set<String> getAllZipcodes();

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO getPersonById(Long personId) throws NotFoundException;

    PersonDTO editPerson(PersonDTO personDTO, Long personId) throws NotFoundException;

    boolean deletePerson(Long personId) throws NotFoundException;



}
