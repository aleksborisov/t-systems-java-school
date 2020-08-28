package com.marsarmy.controller;

import com.marsarmy.converter.AddressConverter;
import com.marsarmy.converter.CustomerConverter;
import com.marsarmy.converter.OrderConverter;
import com.marsarmy.dto.AddressDto;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

/**
 * Controller responsible for handling account-related requests
 */
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

    /**
     * Returns a view of user's account information
     *
     * @param model Spring MVC {@link Model}
     * @return View
     */
    @GetMapping("/account")
    public String getAccount(Model model) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        Customer customer = customerService.getCurrentUser();
        model.addAttribute("dateFormatter", dateFormatter);
        model.addAttribute("dateTimeFormatter", dateTimeFormatter);
        model.addAttribute("customerDto", customerConverter.convertToDto(customer));
        model.addAttribute("addressesDto", addressConverter.convertToListOfDto(
                addressService.getByCustomerEmail(customerService.getCurrentUser().getEmail())
        ));
        model.addAttribute("ordersDto", orderConverter.convertToListOfDto(orderService.getByCustomer(customer.getId())));
        return "account/account";
    }

    /**
     * Returns a view of editing user's account information
     *
     * @param model Spring MVC {@link Model}
     * @return View
     */
    @GetMapping("/edit_account")
    public String getEditAccount(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        model.addAttribute("formatter", formatter);
        model.addAttribute("customerDto", customerConverter.convertToDto(customerService.getCurrentUser()));
        return "account/edit_account";
    }

    /**
     * Edits user's account information
     *
     * @param customerDto DTO of Customer entity
     * @param request HttpServletRequest
     * @return Redirect to view
     */
    @PostMapping("/edit_account")
    public String editAccount(@ModelAttribute("customerDto") CustomerDto customerDto, HttpServletRequest request) {
        customerService.update(customerConverter.convertToEntity(customerDto));
        authenticateUserAndSetSession(customerConverter.convertToEntity(customerDto), request);
        return "redirect:/account/account";
    }

    /**
     * Returns a view of creating user's address
     *
     * @param model Spring MVC {@link Model}
     * @return View
     */
    @GetMapping("/create_address")
    public String getCreateAddress(Model model) {
        model.addAttribute("addressDto", new AddressDto());
        model.addAttribute("customerDto", customerConverter.convertToDto(customerService.getCurrentUser()));
        return "account/create_address";
    }

    /**
     * Creates user's address
     *
     * @param addressDto DTO of Address entity
     * @return Redirect to view
     */
    @PostMapping("/create_address")
    public String createAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.create(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }

    /**
     * Returns a view of editing user's address
     *
     * @param model Spring MVC {@link Model}
     * @param id Address id to edit
     * @return View
     */
    @GetMapping("/edit_address")
    public String getEditAddress(Model model, @RequestParam Long id) {
        model.addAttribute("addressDto", addressConverter.convertToDto(addressService.getOne(id)));
        return "account/edit_address";
    }

    /**
     * Edits user's address
     *
     * @param addressDto DTO of Address entity
     * @return Redirect to view
     */
    @PostMapping("/edit_address")
    public String editAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.update(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }

    /**
     * Returns a view of deleting user's address
     *
     * @param model Spring MVC {@link Model}
     * @param id Address id to delete
     * @return View
     */
    @GetMapping("/delete_address")
    public String getDeleteAddress(Model model, @RequestParam Long id) {
        model.addAttribute("addressDto", addressConverter.convertToDto(addressService.getOne(id)));
        return "account/delete_address";
    }

    /**
     * Deletes user's address
     *
     * @param addressDto DTO of Address entity
     * @return Redirect to view
     */
    @PostMapping("/delete_address")
    public String deleteAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.delete(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }

    /**
     * Authenticate transmitted user
     *
     * @param customer User to authenticate
     * @param request HttpServletRequest
     */
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
