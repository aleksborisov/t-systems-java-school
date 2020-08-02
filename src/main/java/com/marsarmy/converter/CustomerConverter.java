package com.marsarmy.converter;

import com.marsarmy.dto.AddressDto;
import com.marsarmy.dto.CustomerDto;
import com.marsarmy.dto.OrderDto;
import com.marsarmy.model.Address;
import com.marsarmy.model.Customer;
import com.marsarmy.model.Order;
import com.marsarmy.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    private final AddressConverter addressConverter;
    private final OrderConverter orderConverter;

    @Autowired
    public CustomerConverter(AddressConverter addressConverter, OrderConverter orderConverter) {
        this.addressConverter = addressConverter;
        this.orderConverter = orderConverter;
    }

    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setRole(customer.getRole().name());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());

        List<AddressDto> addressesDto = new ArrayList<>();
        for (Address address : customer.getAddresses()) {
            addressesDto.add(addressConverter.convertToDto(address));
        }
        customerDto.setAddressesDto(addressesDto);

        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : customer.getOrders()) {
            ordersDto.add(orderConverter.convertToDto(order));
        }
        customerDto.setOrdersDto(ordersDto);

        return customerDto;
    }

    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setRole(Role.valueOf(customerDto.getRole()));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());

        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto : customerDto.getAddressesDto()) {
            addresses.add(addressConverter.convertToEntity(addressDto));
        }
        customer.setAddresses(addresses);

        List<Order> orders = new ArrayList<>();
        for (OrderDto orderDto : customerDto.getOrdersDto()) {
            orders.add(orderConverter.convertToEntity(orderDto));
        }
        customer.setOrders(orders);

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
