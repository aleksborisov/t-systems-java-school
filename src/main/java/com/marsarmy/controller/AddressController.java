package com.marsarmy.controller;

import com.marsarmy.converter.AddressConverter;
import com.marsarmy.converter.CustomerConverter;
import com.marsarmy.dto.AddressDto;
import com.marsarmy.service.interf.AddressService;
import com.marsarmy.service.interf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Autowired
    public AddressController(AddressService addressService, AddressConverter addressConverter,
                             CustomerService customerService, CustomerConverter customerConverter) {
        this.addressService = addressService;
        this.addressConverter = addressConverter;
        this.customerService = customerService;
        this.customerConverter = customerConverter;
    }

    @GetMapping("/account/create_address")
    public String getCreateAddress(Model model) {
        model.addAttribute("addressDto", new AddressDto());
        model.addAttribute("customerDto", customerConverter.convertToDto(customerService.getCurrentUser()));
        return "account/create_address";
    }

    @PostMapping("/account/create_address")
    public String createAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.create(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }

    @GetMapping("/account/edit_address")
    public String getEditAddress(Model model, @RequestParam Long id) {
        model.addAttribute("addressDto", addressConverter.convertToDto(addressService.getOne(id)));
        return "account/edit_address";
    }

    @PostMapping("/account/edit_address")
    public String editAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.update(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }

    @GetMapping("/account/delete_address")
    public String getDeleteAddress(Model model, @RequestParam Long id) {
        model.addAttribute("addressDto", addressConverter.convertToDto(addressService.getOne(id)));
        return "account/delete_address";
    }

    @PostMapping("/account/delete_address")
    public String deleteAddress(@ModelAttribute("addressDto") AddressDto addressDto) {
        addressService.delete(addressConverter.convertToEntity(addressDto));
        return "redirect:/account/account";
    }
}
