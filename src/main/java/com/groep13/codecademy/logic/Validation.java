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

    // returns true if input matches
    public boolean isValidEmail(String email) {
        return isValidInput(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    // returns true if input matches
    public boolean isValidPostcode(String postcode) {
        return isValidInput(postcode, "^[1-9][0-9]{3}\\s[A-Z]{2}$");
    }

    // returns true if input matches
    public boolean isValidURL(String url) {
        return isValidInput(url, "^(http|https)://[a-zA-Z0-9]+.[a-zA-Z0-9]+.[a-z]+$");
    }

    // returns true if input matches
    public boolean isValidDate(String year, String month, String day) {
        try {
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    // returns true if input matches
    public boolean isValidPercentage(String percentage) {
        return (Integer.parseInt(percentage) >= 0 && Integer.parseInt(percentage) <= 100);
    }

    // returns true if input matches
    public boolean isValidCijfer(String cijfer) {
        try {
            return (Double.parseDouble(cijfer) >= 1 && Double.parseDouble(cijfer) <= 10);
        } catch (Exception e) {
            return false;
        }
    }

    // returns true if input matches
    private boolean isValidInput(String input, String regex) {
        if(input.isBlank() || input.isEmpty()){
            return false;
        }
        return Pattern.compile(regex)
                .matcher(input)
                .matches();
    }
    
}
