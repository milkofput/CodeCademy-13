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
    
    // tests for email validation

    /**
     * @desc checks if the given email is valid returns true if that is the case
     * 
     * @subcontract email is null{
     * @requires !email.isEmpty 
     * @signals (nullPointerExeption) email.isEmpty()
     * }
     */
    @Test
    public void testIsValidEmailNotEmpty() {
        //arrange
        String email = "";
        //act
        boolean answer = val.isValidEmail(email);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract email doesn't have a .{ 
     * @requires email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$")
     * @signals (IllegalArgumentExeption) !email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
     * }
     */
    @Test
    public void testIsValidEmailMissesDot() {
        //arrange
        String email = "email@emailcom";
        //act
        boolean answer = val.isValidEmail(email);
        //assert
        assertEquals(false, answer);
    }    
    /**
     * @subcontract email doesn't have a @ { 
     * @requires email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$")
     * @signals (IllegalArgumentExeption) !email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
     * }
     */
    @Test
    public void testIsValidEmailMissesAt() {
        //arrange
        String email = "email.com";
        //act
        boolean answer = val.isValidEmail(email);
        //assert
        assertEquals(false, answer);
    }       
    /**
     * @subcontract email has forbidden icons{
     * @requires email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$")
     * @signals (IllegalArgumentExeption) !email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$")
     * }
     */
    @Test
    public void testIsValidEmailHasHashtag() {
        //arrange
        String email = "#email@email.com";
        //act
        boolean answer = val.isValidEmail(email);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract valid email{
     * @requires email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$") && !email.isEmpty())
     * @ensures \result = an email that isn't null and has a @ and a .
     * }
     * 
     */
    @Test
    public void testIsValidEmail() {
        //arrange
        String email = "email@email.com";
        //act
        boolean answer = val.isValidEmail(email);
        //assert
        assertEquals(true, answer);
    }

    
    // tests for postcode validation
    
    /**
     * @desc checks if the postcode is valid returns true if this is the case
     * @subcontract postcode is empty{
     * @requires !postcode.isEmpty()
     * @signals (NullPointerExeption) !email.isEmpty() 
     * }
     */
    @Test
    public void testIsValidPostcodeIsEmpty() {
        //arrange
        String postcode = "";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode isn't six digits{
     * @requires postcode.length() == 7 (seven because of the space)
     * @signals (IllegalArgumentExeption) postcode.length() > 7
     * }
     */
    @Test
    public void testIsValidPostcodeTooManyDigits() {
        //arrange
        String postcode = "12345 AB";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode isn't six digits{
     * @requires postcode.length() == 7 (seven because of the space)
     * @signals (IllegalArgumentExeption) postcode.length() < 7
     * }
     */
    @Test
    public void testIsValidPostcodeTooLittleDigits() {
        //arrange
        String postcode = "123 AB";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode starts with a zero{
     * @requires !postcode.startsWith("0")
     * @signals (IllegalArgumentExeption) postcode.startsWith("0")
     * }
     */
        @Test
    public void testIsValidPostcodeStartsWithZero() {
        //arrange
        String postcode = "0123 GG";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode is not in capitals{
     * @requires postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * @signals (IllegalArgumentExeption) !postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * }
     */
        @Test
    public void testIsValidPostcodeNotInCapitals() {
        //arrange
        String postcode = "1123 gg";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode has more or less than two letters{
     * @requires postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * @signals (IllegalArgumentExeption) !postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * }
     */
        @Test
    public void testIsValidPostcodeMoreThanTwoLetters() {
        //arrange
        String postcode = "1123 WQR";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract postcode has more or less than two letters{
     * @requires postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * @signals (IllegalArgumentExeption) !postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$")
     * }
     */
        @Test
    public void testIsValidPostcodeLessThanTwoLetters() {
        //arrange
        String postcode = "1123 R";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(false, answer);
    }   
    /**
     * @subcontract postcode is valid{
     * @requires postcode.matches("^[1-9][0-9]{3}[A-Z]{2}$") && !postcode.isEmpty()
     * @ensures \result a valid dutch postcode
     * }
     */
        @Test
    public void testIsValidPostcodeIsValid() {
        //arrange
        String postcode = "1123 WE";
        //act
        boolean answer = val.isValidPostcode(postcode);
        //assert
        assertEquals(true, answer);
    }
    
    // tests for url validation
    
    /**
     * @desc checks if the url is valid
     * @subcontract url begins with either an https:// or an http://{
     * @requires url.matches("^[https|http]://[-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+$")
     * @signals (IllegalArgumentExeption) !url.matches("^[https|http]://[-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+$")
     * }
     */
    @Test
    public void testIsValidURLBeginsCorrectly() {
        //arrange
        String url = "htttps://asd.asd.asd";
        //act
        boolean answer = val.isValidURL(url);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract checks if the url has 3 dots with at least one letter in between.{
     * @requires url.matches("^[https|http]//:[-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+$")
     * @signals (IllegalArgumentExeption) !url.matches("^[https|http]//:[-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+$")
     * }
     */
    @Test
    public void testIsValidURLHasMultipleDots() {
        //arrange
        String url = "https://asd.asdasd";
        //act
        boolean answer = val.isValidURL(url);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract checks if the code gives an error when the input is empty{
     * @requires !url.isEmpty()
     * @signals (NullPointerExeption) url.isEmpty()
     * }
     */
    @Test
    public void testIsValidURLIsEmpty() {
        //arrange
        String url = "";
        //act
        boolean answer = val.isValidURL(url);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract returns true when the URL is valid{
     * @requires url.matches("^[https|http]://[-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+[.][-a-zA-Z0-9#?!]+$") && !url.isEmpty()
     * @ensures answer returns true
     * }
     */
    @Test
    public void testIsValidURLIsCorrect() {
        //arrange
        String url = "https://codecademy.nederland.nl";
        //act
        boolean answer = val.isValidURL(url);
        //assert
        assertEquals(true, answer);
    }
    
    // tests for date validation

    /**
     * @desc checks if the test has a valid date returns true if valid. otherwise false
     * @subcontract date is not negative{
     * @requires day > 0 && month > 0 && year > 0
     * @signals(IllegalArgumentExeption) day <= 0 || month <= 0 || year < 0
     * }
     */
    @Test
    public void testIsValidDateDayIsNegative() {
        //arrange
        int day = -10;
        int month = 12;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract day/month is not 0{
     * @requires day > 0 && month > 0 && year > 0
     * @signals(IllegalArgumentExeption) day <= 0 || month <= 0 || year < 0
     * }
     */
    @Test
    public void testIsValidDateNotContainsZero() {
        //arrange
        int day = 0;
        int month = 12;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract day in month feb is not bigger than 28 days{
     * @requires when month == 2 (and year not a leap year)--> !day > 28 
     * @signals(IllegalArgumentExeption) day <= 0 || month <= 0 || year < 0
     */
    @Test
    public void testIsValidDateDayFebNotBiggerThan28() {
        //arrange
        int day = 30;
        int month = 02;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract day not bigger than 31 days{
     * @requires !day > 31
     * @signals(IllegalArgumentExeption) day > 31
     */
    @Test
    public void testIsValidDateDayNotBiggerThan31() {
        //arrange
        int day = 32;
        int month = 01;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract day not bigger than 30 days when month is subtractable by 2{
     * @requires if(month/2.0 != 0)!day > 30
     * @signals(IllegalArgumentExeption) day > 30
     */
    @Test
    public void testIsValidDateDayNotBiggerThan30WhenMonthSubtractableWith2() {
        //arrange
        int day = 31;
        int month = 04;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract month can noet be bigger than 12{
     * @requires month <=12
     * @signals(IllegalArgumentExeption) month > 12
     */
    @Test
    public void testIsValidDateDayMoreThan12Months() {
        //arrange
        int day = 10;
        int month = 14;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(false, answer);
    }
    /**
     * @subcontract month can noet be bigger than 12{
     * @requires month <=12
     * @signals(IllegalArgumentExeption) month > 12
     */
    @Test
    public void testIsValidDateIsCorrect() {
        //arrange
        int day = 10;
        int month = 10;
        int Year = 2000;
        //act
        boolean answer = val.isValidDate(Year, month, day);
        //assert
        assertEquals(true, answer);
    }
    
    
    
    // tests for percentage validations

    /**
     * @desc check if the test has a valid percentage {
     * @subcontract percentage not negative
     * @requires percentage < 0
     * @signals answer returns false
     * }
     */
    @Test
    public void testIsValidPercentageNotNegative() {
        //arrange
        int percentage = -10;
        //act
        boolean answer = val.isValidPercentage(percentage);
        //assert
        assertEquals(false, answer);
    }  
    /**
     * @subcontract percentage not too great
     * @requires percentage > 100
     * @signals answer returns false
     */
    @Test
    public void testIsValidPercentageTooGreat() {
        //arrange
        int percentage = 102;
        //act
        boolean answer = val.isValidPercentage(percentage);
        //assert
        assertEquals(false, answer);
    } 
    /**
     * @subcontract percentage not negative
     * @requires percentage >= 0 && percentage <=100
     * @signals answer returns true
     */
    @Test
    public void testIsValidPercentageIsValid() {
        //arrange
        int percentage = 57;
        //act
        boolean answer = val.isValidPercentage(percentage);
        //assert
        assertEquals(true, answer);
    } 
    
    // tests for cijfer validation
    
    /**
     * @desc check if the test has a valid cijfer {
     * @subcontract cijfer not too small
     * @requires cijfer < 1
     * @signals answer returns false
     * }
     */
    @Test
    public void testIsValidCijferNotTooSmall() {
        //arrange
        double cijfer = 0.2;
        //act
        boolean answer = val.isValidCijfer(cijfer);
        //assert
        assertEquals(false, answer);
    }  
    /**
     * @subcontract cijfer not too great
     * @requires cijfer > 10
     * @signals answer returns false
     */
    @Test
    public void testIsValidCijferTooGreat() {
        //arrange
        double cijfer = 11.4;
        //act
        boolean answer = val.isValidCijfer(cijfer);
        //assert
        assertEquals(false, answer);
    } 
    /**
     * @subcontract cijfer not negative
     * @requires cijfer >= 0 && cijfer <=100
     * @signals answer returns true
     */
    @Test
    public void testIsValidCijferIsValid() {
        //arrange
        double cijfer = 6.5;
        //act
        boolean answer = val.isValidCijfer(cijfer);
        //assert
        assertEquals(true, answer);
    } 
    
    // **input validation kan ik niet testen omdat het private is**
    
}
