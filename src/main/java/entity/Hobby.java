package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hobby {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name ="person_hobby",
            joinColumns = @JoinColumn(name="hobby_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private Set<Person> personSet = new HashSet<>();


    public void addPerson(Person person){
        this.personSet.add(person);
        person.addHobby(this);
    }


    public Set<Person> getPersonSet() {
        return personSet;
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

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
