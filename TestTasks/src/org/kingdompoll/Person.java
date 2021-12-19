package org.kingdompoll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private List<Person> servants;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Person> getServants() {
        return servants;
    }

    public void addServant(Person servant) {
        if (this.servants == null) {
            this.servants = new ArrayList<>();
        }
        servants.add(servant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Person))
            return false;
        Person other = (Person) obj;
        return Objects.equals(name, other.name);
    }
}
