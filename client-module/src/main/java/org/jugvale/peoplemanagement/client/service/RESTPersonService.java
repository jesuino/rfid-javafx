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
public class RESTPersonService extends PersonService{
    
    final String BASE_URL = "";

    @Override
    public void save(Person p, Consumer<Person> onSuccess, Consumer<String> onFail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listAll(Consumer<List<Person>> onSuccess, Consumer<String> onFail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id, Consumer<String> onSuccess, Consumer<String> onFail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void get(String rfid, Consumer<Person> onSuccess, Consumer<String> onFail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
