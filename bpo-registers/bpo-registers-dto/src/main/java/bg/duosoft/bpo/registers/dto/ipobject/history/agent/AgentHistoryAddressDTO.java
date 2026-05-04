package bg.duosoft.bpo.registers.dto.ipobject.history.agent;

import lombok.Data;

@Data
public class AgentHistoryAddressDTO {
    private String countryCode;
    private String cityName;
    private String cityNameEn;
    private String addressStreet;
    private String addressStreetEn;
    private String zipCode;
    private String phone;
    private String email;
    private String website;
    private String caAddress;
    private String caAddressEn;
    private String caZipCode;
    private String caPhone;
    private String caEmail;
    private String caFax;
    private String caCityName;
    private String caCityNameEn;
}
