/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy13.domain;

import java.time.LocalDate;

/**
 *
 * @author Milko
 */
public class Signup {
    private int id;
    private Person person;
    private Course course;
    private Certificate certificate;
    private LocalDate signupDate;

    public Signup(int id, Person person, Course course, LocalDate signupDate) {
        this.id = id;
        this.person = person;
        this.course = course;
        this.signupDate = signupDate;
    }

    public void grantCertificate(Certificate cert) {
        certificate=cert;
    }
    
    public boolean hasCertificate() {
        return certificate==null ? false : true;
    }
    
   @Override
    public String toString() {
        //TODO
        return null;
    }
  
}
