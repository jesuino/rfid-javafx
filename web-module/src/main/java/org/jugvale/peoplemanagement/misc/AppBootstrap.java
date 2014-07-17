package org.jugvale.peoplemanagement.misc;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jugvale.peoplemanagement.model.Person;
import org.jugvale.peoplemanagement.model.Role;
import org.jugvale.peoplemanagement.service.PersonService;
import org.jugvale.peoplemanagement.service.RoleService;

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
	PersonService personService;
	@Inject
	RoleService roleService;

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
		Role r = new Role();
		r.setName("Admin");
		r = roleService.save(r);
		p.setRoles(Arrays.asList(r));
		personService.save(p);
		
		
	}
}
