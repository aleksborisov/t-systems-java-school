package com.marsarmy.converter;

import com.marsarmy.dto.CustomerDto;
import com.marsarmy.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());

        return customerDto;
    }

    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());

        return customer;
    }

    public List<CustomerDto> convertToListOfDto(List<Customer> customers) {
        List<CustomerDto> customersDto = new ArrayList<>();
        for (Customer customer : customers) {
            customersDto.add(convertToDto(customer));
        }
        return customersDto;
    }

    public List<Customer> convertToListOfEntity(List<CustomerDto> customersDto) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerDto customerDto : customersDto) {
            customers.add(convertToEntity(customerDto));
        }
        return customers;
    }
}
