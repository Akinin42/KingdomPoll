package org.kingdompoll;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PollFormatterTest {
    
private PollFormatter formatter;
    
    @BeforeEach
    void createNewPollSorter() {
        formatter = new PollFormatter();
    }

    @Test
    void formatShouldReturnEmptyListWhenInputNull() {
        List<String> expected = new ArrayList<>();
        assertEquals(expected, formatter.format(null));
    }
    
    @Test
    void formatShouldReturnExpectedListWhenInputPersonHasServantsWithoutServants() {
        List<String> expected = new ArrayList<>();
        expected.add("самый главный");
        expected.add("    а - первый по алфавиту слуга");
        expected.add("    б - следующий по алфавиту слуга");
        expected.add("    в - и ещё один слуга");
        Person inputPerson = new Person("самый главный");
        inputPerson.addServant(new Person("а - первый по алфавиту слуга"));
        inputPerson.addServant(new Person("б - следующий по алфавиту слуга"));
        inputPerson.addServant(new Person("в - и ещё один слуга"));
        assertEquals(expected, formatter.format(inputPerson));
    }
    
    @Test
    void formatShouldReturnExpectedListWhenInputPersonHasServantsWithServants() {
        List<String> expected = new ArrayList<>();
        expected.add("самый главный");
        expected.add("    а - первый по алфавиту слуга");
        expected.add("        а - слуга первого слуги");
        expected.add("        б - второй слуга первого слуги");
        expected.add("    б - следующий по алфавиту слуга");
        expected.add("        холоп второго слуги");
        expected.add("            и у него есть свой слуга");
        Person inputPerson = new Person("самый главный");
        Person firstServant = new Person("а - первый по алфавиту слуга");
        firstServant.addServant(new Person("а - слуга первого слуги"));
        firstServant.addServant(new Person("б - второй слуга первого слуги"));
        Person secondServant = new Person("б - следующий по алфавиту слуга");
        Person secondServantsServant = new Person("холоп второго слуги");
        secondServantsServant.addServant(new Person("и у него есть свой слуга"));
        secondServant.addServant(secondServantsServant);
        inputPerson.addServant(firstServant);
        inputPerson.addServant(secondServant);
        assertEquals(expected, formatter.format(inputPerson));
    }
}
