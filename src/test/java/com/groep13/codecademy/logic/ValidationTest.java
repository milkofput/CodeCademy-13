/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.logic;

import com.groep13.codecademy.logic.Validation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jelle
 */
public class ValidationTest {
    private Validation val;
    
    @Before
    public void setUp() {
        val = new Validation();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * @desc checks if the given email is valid returns true it that is the case
     * 
     * @subcontract email is null{
     * @requires !email.isEmpty 
     * @signals (nullPointerExeption) email.isEmpty()
     * }
     */
    @Test
    public void testIsValidEmailNotNull() {
        //arange
        String email = null;
        //act
        boolean awnser = val.isValidEmail(email);
        //assert
        assertEquals(false, awnser);
    }
    /**
     * @subcontract email doesn't have a @ or a .{ 
     * @requires email.matches("@." )
     * @signals (IllegalArgumentExeption) !email.matches("@") || !email.matches(".")
     * }
     */
    @Test
    public void testIsValidEmailmissesDot() {
        //arange
        String email = "emai@emailDOTcom";
        //act
        boolean awnser = val.isValidEmail(email);
        //assert
        assertEquals(false, awnser);
    }
    /**
     * @subcontract email has forbidden icons{
     * @requires !email.matches("[]{}!#$%€^&*()_+=/\|><?")
     * @signals (IllegalArgumentExeption) email.matches("[]{}!#$%€^&*()_+=/\|><?")
     * }
     */
    @Test
    public void testIsValidEmailHasHashtag() {
        //arange
        String email = "#email@email.com";
        //act
        boolean awnser = val.isValidEmail(email);
        //assert
        assertEquals(false, awnser);
    }
    /**
     * @subcontract valid email{
     * @requires email.matches(% + "@" + % + "." + %) && !email.isempty() && !email.matches("[]{}!#$%€^&*()_+=/\|><?")
     * @ensures \result = an email that isn't null and has a @ and a .
     * }
     * 
     */
    @Test
    public void testIsValidEmail() {
        //arange
        String email = "email@email.com";
        //act
        boolean awnser = val.isValidEmail(email);
        //assert
        assertEquals(true, awnser);
    }

    /**
     * @desc checks if the postcode is valid
     */
    @Test
    public void testIsValidPostcode() {
        //arange
        
        //act
        
        //assert
    }

    /**
     * @desc checks if the URL is valid
     */
    @Test
    public void testIsValidURL() {
        //arange
        
        //act
        
        //assert
    }

    /**
     * @desc checks if the test has a valid date
     */
    @Test
    public void testIsValidDate() {
        //arange
        
        //act
        
        //assert
    }

    /**
     * @desc check if the test has a valid percentage
     */
    @Test
    public void testIsValidPercentage() {
        //arange
        
        //act
        
        //assert
    }

    /**
     * @desc check if the test has a valid number
     */
    @Test
    public void testIsValidCijfer() {
        //arange
        
        //act
        
        //assert
    }   
}
