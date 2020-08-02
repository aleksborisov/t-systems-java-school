package com.marsarmy.converter;

import com.marsarmy.dto.OrderDto;
import com.marsarmy.dto.ProductsInOrderDto;
import com.marsarmy.model.Order;
import com.marsarmy.model.ProductsInOrder;
import com.marsarmy.model.enumeration.DeliveryMethod;
import com.marsarmy.model.enumeration.OrderStatus;
import com.marsarmy.model.enumeration.PaymentMethod;
import com.marsarmy.model.enumeration.PaymentStatus;
import com.marsarmy.service.interf.AddressService;
import com.marsarmy.service.interf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    private final AddressService addressService;
    private final CustomerService customerService;
    private final ProductsInOrderConverter productsInOrderConverter;

    @Autowired
    public OrderConverter(AddressService addressService, CustomerService customerService,
                          ProductsInOrderConverter productsInOrderConverter) {
        this.addressService = addressService;
        this.customerService = customerService;
        this.productsInOrderConverter = productsInOrderConverter;
    }

    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setCustomerDto(order.getCustomer().getEmail());
        orderDto.setAddressDto(order.getAddress().getCustomer().getId());
        orderDto.setPaymentMethod(order.getPaymentMethod().name());
        orderDto.setDeliveryMethod(order.getDeliveryMethod().name());
        orderDto.setPaymentStatus(order.getPaymentStatus().name());
        orderDto.setOrderStatus(order.getOrderStatus().name());

        List<ProductsInOrderDto> productsInOrdersDto = new ArrayList<>();
        for (ProductsInOrder productsInOrder : order.getProductsInOrders()) {
            productsInOrdersDto.add(productsInOrderConverter.convertToDto(productsInOrder));
        }
        orderDto.setProductsInOrdersDto(productsInOrdersDto);

        return orderDto;
    }

    public Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setId(order.getId());
        order.setCustomer(customerService.getOne(orderDto.getCustomerDto()));
        order.setAddress(addressService.getByCustomerId(orderDto.getAddressDto()));
        order.setPaymentMethod(PaymentMethod.valueOf(orderDto.getPaymentMethod()));
        order.setDeliveryMethod(DeliveryMethod.valueOf(orderDto.getDeliveryMethod()));
        order.setPaymentStatus(PaymentStatus.valueOf(orderDto.getPaymentStatus()));
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));

        List<ProductsInOrder> productsInOrders = new ArrayList<>();
        for (ProductsInOrderDto productsInOrderDto : orderDto.getProductsInOrdersDto()) {
            productsInOrders.add(productsInOrderConverter.convertToEntity(productsInOrderDto));
        }
        order.setProductsInOrders(productsInOrders);

        return order;
    }

    public List<OrderDto> convertToListOfDto(List<Order> orders) {
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            ordersDto.add(convertToDto(order));
        }
        return ordersDto;
    }

    public List<Order> convertToListOfEntity(List<OrderDto> ordersDto) {
        List<Order> orders = new ArrayList<>();
        for (OrderDto orderDto : ordersDto) {
            orders.add(convertToEntity(orderDto));
        }
        return orders;
    }
}
