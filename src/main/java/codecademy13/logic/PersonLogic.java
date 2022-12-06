package com.milko.codecademydemo.logic;

import codecademy13.domain.ContentItem;
import codecademy13.domain.Person;
import java.util.ArrayList;

/**
 *  In this class logic is done with multiple Person objects.
 */

public class PersonLogic {
    ArrayList<Person> allPeople = new ArrayList<>();

    public PersonLogic() {
        //TODO: get all people from the database.
    }
    
    public void refreshAllPeople() {
        //TODO: get all people from the database again after a change.
    }
    
    public int amountOfPeople() {
        return allPeople.size();
    }
    
    public double averagePercentageViewedForLearnable(ContentItem c) {
        return allPeople.stream()
                            .mapToDouble(p -> p.getPercentageViewed(c))
                            .summaryStatistics()
                            .getAverage();                          
    }
    
    public double averagePercentageViewedPerPerson() {
        return allPeople.stream()
                            .mapToDouble(p -> p.getAveragePercentageViewed())
                            .summaryStatistics()
                            .getAverage();   
    }
    
    public double averagePercentageViewedPerGender() {
        //TODO: gender as param input, return average;
        return 0;
    }
    
    
    
}
