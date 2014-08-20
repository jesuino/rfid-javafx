/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jugvale.peoplemanagement.client.model.Person;

/**
 * A Mock Service, which won't interact with the REST WS (I'm lazy to write this
 * right now...)
 *
 * @author william
 */
class MockPersonService extends PersonService {

    private final List<Person> people;

    MockPersonService() {
        people = new ArrayList<>();
        people.addAll(
                Arrays.asList(
                        new Person(1, "John", "Smith", "abc", "Programmer", 20),
                        new Person(2, "Joshua", "Ali", "kld", "Analyst", 30),
                        new Person(3, "Maste", "Ram", "vvv", "Programmer", 27),
                        new Person(4, "Blood", "Mary", "jnh", "Typewriter", 65)
                ));

        // adding some random data..
    }

    @Override
    public String save(Person p) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockPersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Person q = get(p.getRfid());
        if (q != null) {
            return "Person with RFID " + p.getRfid() + " found on the database.";
        } else {
            people.add(p);
            return "Person with RFID " + p.getRfid() + " saved with success.";
        }
    }

    @Override
    public List<Person> listAll() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockPersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return people;
    }

    @Override
    public String remove(long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockPersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Person toRemove = null;
        for (Person p : people) {
            if (p.getId() == id) {
                toRemove = p;
                break;
            }
        }
        if (toRemove == null) {
            return "Cant find person with ID " + id;
        } else {
            people.remove(toRemove);
            return "People with ID " + id + " removed with success";
        }

    }

    @Override
    public Person get(String rfid) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockPersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Optional<Person> found = people.stream().filter(p -> p.getRfid().equals(rfid)).findAny();
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }
}
