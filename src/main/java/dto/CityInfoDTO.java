package dto;

import entity.Address;
import entity.CityInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CityInfoDTO {

    private Long id;
    private String zipCode;
    private String city;
    private Set<String> addressDTOSet = new HashSet<>();

    public CityInfoDTO(Long id, String zipCode, String city) {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfoDTO(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfoDTO(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zipCode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();

        for (Address a : cityInfo.getAddressSet()) {
            this.addressDTOSet.add("id:" + a.getId() + ", street:" + a.getStreet() + ", additionalInfo:" + a.getAdditionalInfo());
        }
    }

    public List<CityInfoDTO> getDTOs(List<CityInfo> cityInfoList){
        List<CityInfoDTO> cityInfoDTOlist = new ArrayList<>();
        for (CityInfo c :  cityInfoList) {
            cityInfoDTOlist.add(new CityInfoDTO(c));
        }
        return cityInfoDTOlist;
    }

}
