package org.kingdompoll;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PollSorterTest {
    
    private PollSorter sorter;
    
    @BeforeEach
    void createNewPollSorter() {
        sorter = new PollSorter();
    }
    
    @Test
    void sortPollShouldReturnKingWithoutServantsWhenInputEmpty() {
        List<String> emptyList = new ArrayList<>();
        Person expected = new Person("король");        
        assertEquals(expected, sorter.sortPoll(emptyList));
    }
    
    @Test
    void sortPollShouldReturnKingWithoutServantsWhenInputNull() {
        Person expected = new Person("король");
        assertEquals(expected, sorter.sortPoll(null));
    }
    
    @Test
    void sortPollShouldReturnExpectedKingWhenInputListContaintsDifferentPerson() {
        List<String> pollResults = List.of("Антон первый подчиненый короля",
                "второй подчиненый короля: подчиненый второго слуги, ещё один подчиненый второго слуги",
                "подчиненый второго слуги: неизвестно кто - совсем уж зам зама короля");
        Person expected = new Person("король");
        expected.addServant(new Person("Антон первый подчиненый короля"));
        Person secondServant = new Person("второй подчиненый короля");
        Person secondServantsServant = new Person("подчиненый второго слуги");
        secondServantsServant.addServant(new Person("неизвестно кто - совсем уж зам зама короля"));
        secondServant.addServant(secondServantsServant);
        secondServant.addServant(new Person("ещё один подчиненый второго слуги"));
        expected.addServant(secondServant);
        assertEquals(expected, sorter.sortPoll(pollResults));
    }
}
