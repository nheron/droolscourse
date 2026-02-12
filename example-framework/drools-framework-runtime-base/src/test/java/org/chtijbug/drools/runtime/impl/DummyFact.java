package org.chtijbug.drools.runtime.impl;

import java.util.List;

public class DummyFact {
    private String name;
    private DummyFact property;
    private List<DummyFact> listOfFacts;

    public DummyFact() {
    }

    DummyFact(String name) {
        this.name = name;
    }

    DummyFact(String name,DummyFact property) {
        this(name);
        this.property = property;
    }

    DummyFact(String name, DummyFact property, List<DummyFact> listOfFacts) {
        this(name, property);
        this.listOfFacts = listOfFacts;
    }

    public String getName() {
        return name;
    }

    public DummyFact getProperty() {
        return property;
    }

    public List<DummyFact> getListOfFacts() {
        return listOfFacts;
    }

}
