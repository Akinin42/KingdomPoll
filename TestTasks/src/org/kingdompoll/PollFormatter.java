package org.kingdompoll;

import java.util.ArrayList;
import java.util.List;

public class PollFormatter {

    private List<String> formatedPoll = new ArrayList<>();

    private static final int START_INDENT = 0;
    private static final int NEXT_INDENT = 4;
    private static final char WHITESPACE = ' ';

    public List<String> format(Person person) {
        formatPerson(person, START_INDENT);
        return formatedPoll;
    }

    private void formatPerson(Person person, int indent) {
        formatedPoll.add(String.format("%s%s", appendWhitespacesTimes(indent), person.getName()));
        if (checkPersonHasServants(person)) {
            for (Person servant : person.getServants()) {
                formatPerson(servant, indent + NEXT_INDENT);
            }
        }
    }

    private boolean checkPersonHasServants(Person person) {
        return person.getServants() != null && !person.getServants().isEmpty();
    }

    private static String appendWhitespacesTimes(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(WHITESPACE);
        }
        return sb.toString();
    }
}
