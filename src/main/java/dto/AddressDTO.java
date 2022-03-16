package dto;

import entity.Address;
import entity.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressDTO {

    private Long id;
    private String street;
    private String additionalInfo;
    private CityInfoDTO cityInfoDTO;
    private Set<String> personDTOSet = new HashSet<>();

    public AddressDTO(Long id,String street, String additionalInfo) {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public AddressDTO(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfoDTO = new CityInfoDTO(address.getCityInfo());

        for (Person p : address.getPersonSet()) {
            this.personDTOSet.add("id:" + p.getId() + ", email:" + p.getEmail() +
                    ", firstname:" + p.getFirstName() + ", lastname:" + p.getLastName());
        }
    }

    public List<AddressDTO> getDTOs(List<Address> addressList){
        List<AddressDTO> addressDTOlist = new ArrayList<>();
        for (Address a :  addressList) {
            addressDTOlist.add(new AddressDTO(a));
        }
        return addressDTOlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CityInfoDTO getCityInfoDTO() {
        return cityInfoDTO;
    }

    public void setCityInfoDTO(CityInfoDTO cityInfoDTO) {
        this.cityInfoDTO = cityInfoDTO;
    }

    public Set<String> getPersonDTOSet() {
        return personDTOSet;
    }

    public void setPersonDTOSet(Set<String> personDTOSet) {
        this.personDTOSet = personDTOSet;
    }
}
