package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String street;
    private String additionalInfo;

    @OneToMany(mappedBy = "address")
    private Set<Person> personSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cityInfo_id", referencedColumnName = "id")
    private CityInfo cityInfo;

    public void addCityInfo(CityInfo cityInfo){
        cityInfo.addAddress(this);
        this.cityInfo = cityInfo;
    }

    public void addPerson(Person person){
        this.personSet.add(person);
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Address() {
    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
