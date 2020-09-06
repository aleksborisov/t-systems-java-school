package com.marsarmy.controller;

import com.marsarmy.converter.CustomerConverter;
import com.marsarmy.dto.CustomerDto;
import com.marsarmy.model.Customer;
import com.marsarmy.service.interf.CustomerService;
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

/**
 * Controller responsible for handling authentication-related requests
 */
@Controller
public class AuthController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(CustomerService customerService, CustomerConverter customerConverter, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Returns a view of singing up
     *
     * @param model Spring MVC {@link Model}
     * @return View
     */
    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "auth/sign_up";
    }

    /**
     * Process singing up
     *
     * @param customerDto Spring MVC {@link Model}
     * @param request HttpServletRequest
     * @return Redirect to view
     */
    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute("customerDto") CustomerDto customerDto, HttpServletRequest request) {
        customerService.create(customerConverter.convertToEntity(customerDto));
        authenticateUserAndSetSession(customerConverter.convertToEntity(customerDto), request);
        return "redirect:/";
    }

    /**
     * Process singing in
     *
     * @param error Error flag
     * @param model Spring MVC {@link Model}
     * @return View
     */
    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "auth/sign_in";
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
