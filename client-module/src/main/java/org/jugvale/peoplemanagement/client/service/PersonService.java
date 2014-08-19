/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.service;

import java.util.List;
import java.util.function.Consumer;
import org.jugvale.peoplemanagement.client.model.Person;

/**
 *
 * @author william
 */
public abstract class PersonService {

    private static PersonService service;

    public abstract void save(Person p, Consumer<Person> onSuccess, Consumer<String> onFail);

    public abstract void listAll(Consumer<List<Person>> onSuccess, Consumer<String> onFail);

    public abstract void remove(long id, Consumer<String> onSuccess, Consumer<String> onFail);

    public abstract void get(String rfid, Consumer<Person> onSuccess, Consumer<String> onFail);

    /**
     * Return the actual PersonService
     *
     * @return
     */
    public static PersonService getInstance() {
        if (service == null) {
            //service = new MockPersonService();
            service = new RESTPersonService();
        }
        return service;
    }

}
