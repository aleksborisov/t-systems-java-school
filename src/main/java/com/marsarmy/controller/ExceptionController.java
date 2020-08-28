package com.marsarmy.controller;

import com.marsarmy.exception.OutOfStockException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller responsible for handling exceptions
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);

    /**
     * Handles Data Access Exception
     *
     * @param e DataAccessException
     * @return View
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e) {
        LOGGER.error(e.getMessage(), e);
        return "exception/data_access";
    }

    /**
     * Handles Username Not Found Exception
     *
     * @param e UsernameNotFoundException
     * @return View
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public String usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        LOGGER.error(e.getMessage(), e);
        return "exception/username_not_found";
    }

    /**
     * Handles Out Of Stock Exception
     *
     * @param e OutOfStockException
     * @return View
     */
    @ExceptionHandler(OutOfStockException.class)
    public String outOfStockExceptionHandler(OutOfStockException e, Model model) {
        LOGGER.error(e.getMessage(), e);
        model.addAttribute("errorCode", e.getErrorCode());
        model.addAttribute("errorMessage", e.getErrorMessage());
        return "exception/out_of_stock";
    }

    /**
     * Handles all other exceptions
     *
     * @param e Exception
     * @return View
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return "exception/unexpected";
    }
}
