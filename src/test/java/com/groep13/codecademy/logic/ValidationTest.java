/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.logic;

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
    private int amountTests;
    public ValidationTest() {
        amountTests = 0;
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("starting tests");
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        amountTests++;
        System.out.println("test Nr: " + amountTests);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * @desc checks if the given email is valid
     * 
     */
    @Test
    public void testIsValidEmail() {
        //arange
        
        //act
        
        //assert
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
