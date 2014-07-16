package org.jugvale.peoplemanagement.misc;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jugvale.peoplemanagement.model.Person;
import org.jugvale.peoplemanagement.service.PersonService;

/**
 * 
 * Do initial stuff when the app is re-deployed;
 * 
 * @author william
 * 
 */
@Singleton
@Startup
public class AppBootstrap {

	@Inject
	PersonService service;

	/**
	 * Add some test data
	 */
	@PostConstruct
	public void bootstrap() {
		Person p = new Person();
		p.setFirstName("William");
		p.setLastName("Siqueira");
		p.setAge(26);
		p.setJob("Support Engineer");
		p.setRfid("abc");
		service.save(p);
	}
}
