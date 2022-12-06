
import codecademy13.domain.Certificate;
import codecademy13.domain.Course;
import codecademy13.domain.Person;
import codecademy13.domain.Signup;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikki
 */

//Person(int id, String name, String email, LocalDate birthDate, String address, String city, String country)
//Signup(int id, Person person, Course course, LocalDate signupDate)
public class Main {
    public static void main(String[] args) {
        Person john = new Person(1,"John","john@hotmail.com",LocalDate.now(),"Straat 31", "Rotterdam", "Netherlands");
        Signup s1 = new Signup(1,john,new Course(),LocalDate.now());
        Signup s2 = new Signup(2,john,new Course(),LocalDate.now());       
        john.addSignup(s1);
        john.addSignup(s2);
        s1.grantCertificate(new Certificate());
        System.out.println(john.percentageOfSignupsWithCertificate());
    }
}

