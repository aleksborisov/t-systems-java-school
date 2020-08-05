package com.marsarmy.converter;

import com.marsarmy.dto.AddressDto;
import com.marsarmy.model.Address;
import com.marsarmy.service.interf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConverter {

    private final CustomerService customerService;

    @Autowired
    public AddressConverter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public AddressDto convertToDto(Address address) {
        AddressDto addressDto = new AddressDto();

        addressDto.setId(address.getId());
        addressDto.setCustomerDto(address.getCustomer().getEmail());
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setStreet(address.getStreet());
        addressDto.setBuilding(address.getBuilding());
        addressDto.setApartment(address.getApartment());

        return addressDto;
    }

    public Address convertToEntity(AddressDto addressDto) {
        Address address = new Address();

        if (addressDto.getId() != null) {
            address.setId(addressDto.getId());
        }

        address.setCustomer(customerService.getOne(addressDto.getCustomerDto()));
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreet(addressDto.getStreet());
        address.setBuilding(addressDto.getBuilding());
        address.setApartment(addressDto.getApartment());

        return address;
    }

    public List<AddressDto> convertToListOfDto(List<Address> addresses) {
        List<AddressDto> addressesDto = new ArrayList<>();
        for (Address address : addresses) {
            addressesDto.add(convertToDto(address));
        }
        return addressesDto;
    }

    public List<Address> convertToListOfEntity(List<AddressDto> addressesDto) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto : addressesDto) {
            addresses.add(convertToEntity(addressDto));
        }
        return addresses;
    }
}
