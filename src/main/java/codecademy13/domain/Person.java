/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy13.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.NullPointerException;

/**
 *
 * @author Milko
 */
public class Person {
    private int id;
    private String name;
    //TODO: private Gender_Enum gender;
    private String email;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String country;
    private ArrayList<Signup> signups = new ArrayList<>();
    private HashMap<ContentItem,Double> viewedContentItemsWithPercentage = new HashMap<>();

    public Person(int id, String name, String email, LocalDate birthDate, String address, String city, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.country = country;
        this.signups = signups;
    }
    
    public void addSignup(Signup s) {
        signups.add(s);
    }
    
    public void removeSignup(Signup s) throws NullPointerException {
        if (signups.contains(s)) {
            signups.remove(s);
        }   else    {
            throw new NullPointerException("This signup doesn't exist or doesn't belong to this person!");
        }       
    }
    
    public void addContentItem(ContentItem c, double viewtime) {
        viewedContentItemsWithPercentage.put(c,viewtime);
    }
    
    public void updateContentItem(ContentItem c, double viewtime) {
        viewedContentItemsWithPercentage.putIfAbsent(c,viewtime);
        
        if (!(viewedContentItemsWithPercentage.get(c)==viewtime)) {
            viewedContentItemsWithPercentage.putIfAbsent(c,viewtime);
        }
    }
    
    public double percentageOfSignupsWithCertificate() {
        long countTrue = signups.stream()
                        .map(s -> s.hasCertificate())
                        .filter(s -> s==true)
                        .count();
        
        long countAll = signups.stream()
                        .map(s -> s.hasCertificate())
                        .count();
        
        return (double) 100*countTrue/countAll;
    }
    
    public double getPercentageViewed(ContentItem c) {
        return this.viewedContentItemsWithPercentage.get(c);
    }
    
    public double getAveragePercentageViewed() {
        return viewedContentItemsWithPercentage.values().stream()
                    .mapToDouble(p -> p)
                    .summaryStatistics()
                    .getAverage();
    }
  
}
