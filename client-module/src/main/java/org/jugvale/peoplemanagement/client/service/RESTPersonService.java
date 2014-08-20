/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.service;

import java.util.List;
import java.util.function.Consumer;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.jugvale.peoplemanagement.client.model.Person;

/**
 *
 * @author william
 */
public class RESTPersonService extends PersonService {

    final String BASE_URL = "http://people-fxapps.rhcloud.com/people-management/rest/person/";
    final String username = "restadmin";
    final String password = "restadmin123!";
    final private String MEDIA_TYPE = "application/json";

    // The JAX-RS 2 client to perform HTTP Requests
    ResteasyClient client;

    public RESTPersonService() {
        super();
        createClient();
    }


    public void save(Person p, Consumer<Person> onSuccess, Consumer<String> onFail) {
        try {
            Entity<Person> personEntity = Entity.entity(p, MEDIA_TYPE);
            Response r = client.target(BASE_URL).request(MEDIA_TYPE).post(personEntity);
            if (r.getStatus() != 201) {
                onFail.accept("Error saving person with RFID " + p.getRfid() + ". Check if it already exists.");
            } else {
                get(p.getRfid(), onSuccess, onFail);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            handle(e, onFail);
        }
    }

    public void listAll(Consumer<List<Person>> onSuccess, Consumer<String> onFail) {
        try {
            List<Person> people = client.target(BASE_URL)
                    .request(MEDIA_TYPE)
                    .get(new GenericType<List<Person>>() {
                    });
            onSuccess.accept(people);
        } catch (IllegalArgumentException | NullPointerException e) {
            handle(e, onFail);
        }
    }

    public void remove(long id, Consumer<String> onSuccess, Consumer<String> onFail) {
        try {
            client.target(BASE_URL)
                    .path(String.valueOf(id))
                    .request()
                    .delete();
            onSuccess.accept("Person with id " + id + " removed with success");
        } catch (IllegalArgumentException | NullPointerException e) {
            handle(e, onFail);
        }
    }

    public void get(String rfid, Consumer<Person> onSuccess, Consumer<String> onFail) {
        try {
            Person p = client.target(BASE_URL)
                    .path("rfid").path(rfid)
                    .request(MEDIA_TYPE)
                    .get(Person.class);
            onSuccess.accept(p);
        } catch (IllegalArgumentException | NullPointerException e) {
            handle(e, onFail);
        }
    }

    private void createClient() {
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), credentials);
        client = new ResteasyClientBuilder()
                .httpEngine(new ApacheHttpClient4Engine(httpClient))
                .build();
    }

    private void handle(java.lang.RuntimeException e, Consumer<String> onFail) {
        e.printStackTrace();
        onFail.accept("Error: " + e.getMessage() + "; Exception: " + e.getClass().getSimpleName());
    }

    @Override
    public String save(Person p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person get(String rfid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
