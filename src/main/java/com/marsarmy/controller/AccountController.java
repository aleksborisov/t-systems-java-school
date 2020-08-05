package com.marsarmy.controller;

import com.marsarmy.converter.AddressConverter;
import com.marsarmy.converter.CustomerConverter;
import com.marsarmy.converter.OrderConverter;
import com.marsarmy.dto.CustomerDto;
import com.marsarmy.model.Customer;
import com.marsarmy.service.interf.AddressService;
import com.marsarmy.service.interf.CustomerService;
import com.marsarmy.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;
    private final AddressService addressService;
    private final AddressConverter addressConverter;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Autowired
    public AccountController(AuthenticationManager authenticationManager, CustomerService customerService,
                             CustomerConverter customerConverter, AddressService addressService,
                             AddressConverter addressConverter, OrderService orderService,
                             OrderConverter orderConverter) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
        this.authenticationManager = authenticationManager;
        this.addressService = addressService;
        this.addressConverter = addressConverter;
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @GetMapping("/account")
    public String getAccount(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Customer customer = customerService.getCurrentUser();
        model.addAttribute("formatter", formatter);
        model.addAttribute("customerDto", customerConverter.convertToDto(customer));
        model.addAttribute("addressesDto", addressConverter.convertToListOfDto(
                addressService.getByCustomerEmail(customerService.getCurrentUser().getEmail())
        ));
        model.addAttribute("ordersDto", orderConverter.convertToListOfDto(orderService.getByCustomer(customer.getId())));
        return "account/account";
    }

    @GetMapping("/edit_account")
    public String getEditAccount(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        model.addAttribute("formatter", formatter);
        model.addAttribute("customerDto", customerConverter.convertToDto(customerService.getCurrentUser()));
        return "account/edit_account";
    }

    @PostMapping("/edit_account")
    public String editAccount(@ModelAttribute("customerDto") CustomerDto customerDto, HttpServletRequest request) {
        customerService.update(customerConverter.convertToEntity(customerDto));
        authenticateUserAndSetSession(customerConverter.convertToEntity(customerDto), request);
        return "redirect:/account/account";
    }

    private void authenticateUserAndSetSession(Customer customer, HttpServletRequest request) {
        String email = customer.getUsername();
        String password = customer.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
