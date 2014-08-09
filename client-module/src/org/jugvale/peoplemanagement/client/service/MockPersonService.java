/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.jugvale.peoplemanagement.client.model.Person;

/**
 * A Mock Service, which won't interact with the REST WS (I'm lazy to write this
 * right now...)
 *
 * @author william
 */
class MockPersonService extends PersonService {

    private List<Person> people;

    MockPersonService() {
        people = Arrays.asList(
                new Person(1, "John", "Smith", "abc", "Programmer", 20),
                new Person(2, "Joshua", "Ali", "kld", "Analyst", 30),
                new Person(3, "Maste", "Ram", "vvv", "Programmer", 27),
                new Person(4, "Blood", "Mary", "jnh", "Typewriter", 65)
        );

        // adding some random data..
    }

    @Override
    public void save(Person p, Consumer<Person> onSuccess, Consumer<String> onFail) {
        people.add(p);
        onSuccess.accept(p);
    }

    @Override
    public void listAll(Consumer<List<Person>> onSuccess, Consumer<String> onFail) {
        onSuccess.accept(people);
    }

    @Override
    public void remove(long id, Consumer<String> onSuccess, Consumer<String> onFail) {
        Person toRemove = null;
        for (Person p : people) {
            if (p.getId() == id) {
                toRemove = p;
                break;
            }
        }
        if (toRemove == null) {
            onFail.accept("Cant find person with ID " + id);
        } else {
            onSuccess.accept("People with ID " + id + " removed with success");
        }

    }

    @Override
    public void get(String rfid, Consumer<Person> onSuccess, Consumer<String> onFail) {
        Optional<Person> found = people.stream().filter(p -> p.getRfid().equals(rfid)).findAny();
        if (found.isPresent()) {
            onSuccess.accept(found.get());
        } else {
            onFail.accept("Person with RFID " + rfid + " not found");
        }
    }
}
