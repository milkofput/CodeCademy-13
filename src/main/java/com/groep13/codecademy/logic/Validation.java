/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.logic;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 *
 * @author Milko
 */
public class Validation {

    public boolean isValidEmail(String email) {
        return isValidInput(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean isValidPostcode(String postcode) {
        return isValidInput(postcode, "^[1-9][0-9]{3}\\s[A-Z]{2}$");
    }

    public boolean isValidURL(String url) {
        return isValidInput(url, "^(http|https)://[a-zA-Z0-9]+.[a-zA-Z0-9]+.[a-z]+$");
    }

    public boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    public boolean isValidPercentage(int percentage) {
        return (percentage >= 0 && percentage <= 100);
    }

    public boolean isValidCijfer(double cijfer) {
        return (cijfer >= 1 && cijfer <= 10);
    }

    private boolean isValidInput(String input, String regex) {
        if(input.isBlank() || input.isEmpty()){
            return false;
        }
        return Pattern.compile(regex)
                .matcher(input)
                .matches();
    }
    
}
