package dto;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;

import java.util.*;

public class PersonDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    AddressDTO addressDTO;
    private Set<String> phoneDTOSet = new HashSet<>();
    private Set<String> hobbyDTOSet = new HashSet<>();

    public PersonDTO(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.addressDTO = new AddressDTO(person.getAddress());

        for (Phone p : person.getPhoneSet()) {
            this.phoneDTOSet.add("id:" + p.getId() + " number:" + p.getNumber() + " description:" + p.getDescription());

        }
        for(Hobby h: person.getHobbySet()){
            hobbyDTOSet.add("id:" + h.getId() + " name:" + h.getName() + " description" + h.getDescription());
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public Set<String> getPhoneDTOSet() {
        return phoneDTOSet;
    }

    public void setPhoneDTOSet(Set<String> phoneDTOSet) {
        this.phoneDTOSet = phoneDTOSet;
    }

    public Set<String> getHobbyDTOSet() {
        return hobbyDTOSet;
    }

    public void setHobbyDTOSet(Set<String> hobbyDTOSet) {
        this.hobbyDTOSet = hobbyDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(email, personDTO.email) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }
}
