package org.kingdompoll;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PollSorter {

    private static final String KING = "король";
    private static final String POLL_DELIMITER = ": ";
    private static final String SERVANTS_DELIMITER = ", ";

    public Person sortPoll(List<String> pollResults) {
        if (pollResults == null || pollResults.isEmpty()) {
            return new Person(KING);
        }
        Map<String, List<String>> kingdomPoll = new TreeMap<>();
        for (String pollLine : pollResults) {
            String[] line = pollLine.split(POLL_DELIMITER);
            if (line.length > 1) {
                List<String> servantes = Arrays.asList(line[1].split(SERVANTS_DELIMITER));
                Collections.sort(servantes);
                kingdomPoll.put(line[0], servantes);
            } else {
                kingdomPoll.put(line[0], null);
            }
        }
        Person king = addKingsServants(new Person(KING), kingdomPoll);
        for (Person person : king.getServants()) {
            addServants(person, kingdomPoll);
        }
        return king;
    }

    private Person addKingsServants(Person king, Map<String, List<String>> kingdomPoll) {
        for (Map.Entry<String, List<String>> person : kingdomPoll.entrySet()) {
            String name = person.getKey();
            if (checkHasNotBoss(name, kingdomPoll)) {
                king.addServant(new Person(name));
            }
        }
        return king;
    }

    private boolean checkHasNotBoss(String name, Map<String, List<String>> kingdomPoll) {
        for (Map.Entry<String, List<String>> person : kingdomPoll.entrySet()) {
            if (person.getValue() != null && person.getValue().contains(name)) {
                return false;
            }
        }
        return true;
    }

    private Person addServants(Person person, Map<String, List<String>> kingdomPoll) {
        if (checkHasServants(person.getName(), kingdomPoll)) {
            for (String name : kingdomPoll.get(person.getName())) {
                if (checkServantHasServants(name, kingdomPoll)) {
                    Person servante = addServants(new Person(name), kingdomPoll);
                    person.addServant(servante);
                } else {
                    Person servant = new Person(name);
                    person.addServant(servant);
                }
            }
        }
        return person;
    }

    private boolean checkHasServants(String name, Map<String, List<String>> kingdomPoll) {
        return kingdomPoll.get(name) != null;
    }

    private boolean checkServantHasServants(String name, Map<String, List<String>> kingdomPoll) {
        return kingdomPoll.containsKey(name);
    }
}
