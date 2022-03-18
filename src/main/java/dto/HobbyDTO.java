package dto;

import entity.Hobby;
import entity.Person;

import java.util.HashSet;
import java.util.Set;

public class HobbyDTO {

    private Long id;
    private String name;
    private String description;
    private Set<String> personDTOSet = new HashSet<>();

    public HobbyDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public HobbyDTO(Hobby hobby) {
        this.name=hobby.getName();
        this.description= hobby.getDescription();

        for(Person p: hobby.getPersonSet()){
            personDTOSet.add("email:" + p.getEmail() + ", firstname:" + p.getFirstName() + ", lastname:" + p.getLastName());
        }


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getPersonDTOSet() {
        return personDTOSet;
    }

    public void setPersonDTOSet(Set<String> personDTOSet) {
        this.personDTOSet = personDTOSet;
    }
}
