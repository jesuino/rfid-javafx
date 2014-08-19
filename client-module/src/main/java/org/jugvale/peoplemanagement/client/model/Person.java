/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author william
 */
@XmlRootElement
public class Person {

    private long id;
    private String firstName;
    private String lastName;
    private String rfid;
    private String job;
    private int age;
    private List<Role> roles;

    public Person() {
    }

    public Person(long id, String firstName, String lastName, String rfid, String job, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
        this.job = job;
        this.age = age;
    }

    public Person(String firstName, String lastName, String rfid, String job, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
        this.job = job;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
