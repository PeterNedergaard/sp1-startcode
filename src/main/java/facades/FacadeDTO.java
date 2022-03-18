package facades;

import dto.PersonDTO;
import entity.Person;
import errorhandling.NotFoundException;

import java.util.HashSet;
import java.util.Set;

public class FacadeDTO implements IFacadeDTO{

    Facade facade;
    private String pu;

    public FacadeDTO(String pu) {
        this.pu = pu;
        facade = new Facade(pu);
    }





    public Set<PersonDTO> getAllPersons(){

        return personsToPersonDTOs(facade.getAllPersons());
    }

    @Override
    public PersonDTO getPersonInfoByPhoneNum(String phoneNum) {
        return new PersonDTO(facade.getPersonInfoByPhoneNum(phoneNum));

    }

    @Override
    public Set<PersonDTO> getPersonsByHobby(String hobbyName) {
        return personsToPersonDTOs(facade.getPersonsByHobby(hobbyName));
    }

    @Override
    public Set<PersonDTO> getPersonsByZip(String zip) {
        return personsToPersonDTOs(facade.getPersonsByZip(zip));
    }

    @Override
    public int personCountByHobby(String hobbyName) {
        return facade.personCountByHobby(hobbyName);
    }

    @Override
    public Set<String> getAllZipcodes() {
        return facade.getAllZipcodes();
    }

    @Override
    public PersonDTO createPerson(Person person) {

        Person returnPerson;

        if (person.getAddress() == null){
            returnPerson = facade.createPerson(new Person(person.getEmail(),person.getFirstName(),person.getLastName()));
        } else {
            returnPerson = facade.createPerson(person);
        }
        return new PersonDTO(returnPerson.getEmail(),returnPerson.getFirstName(), returnPerson.getLastName());
    }

    @Override
    public PersonDTO getPersonById(Long personId) throws NotFoundException {
        return new PersonDTO(facade.getPersonById(personId));
    }

    @Override
    public PersonDTO editPerson(PersonDTO personDTO, Long personId) throws NotFoundException {
        Person person = new Person(personDTO.getEmail(),personDTO.getFirstName(),personDTO.getLastName());

        return new PersonDTO(facade.editPerson(person,personId));
    }

    @Override
    public PersonDTO deletePerson(Long personId) throws NotFoundException {
        Person deletedPerson = facade.deletePerson(personId);
        PersonDTO deletedPersonDTO = new PersonDTO(deletedPerson.getId(),deletedPerson.getEmail(), deletedPerson.getFirstName(), deletedPerson.getLastName());
        return deletedPersonDTO;
    }




    public Set<PersonDTO> personsToPersonDTOs(Set<Person> persons){

        Set<PersonDTO> personDTOs = new HashSet<>();

        for (Person p : persons) {

            if (p.getAddress() == null){
                personDTOs.add(new PersonDTO(p.getEmail(),p.getFirstName(),p.getLastName()));
            } else {
                personDTOs.add(new PersonDTO(p));
            }


        }

        return personDTOs;
    }

}
