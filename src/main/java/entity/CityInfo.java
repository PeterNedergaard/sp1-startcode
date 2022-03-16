package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CityInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String zipCode;
    private String city;

    @OneToMany(mappedBy="cityInfo")
    private Set<Address> addressSet = new HashSet<>();

    public void addAddress(Address address){
        this.addressSet.add(address);
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CityInfo() {
    }

    public CityInfo(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
