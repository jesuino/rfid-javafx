package org.jugvale.peoplemanagement.service;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jugvale.peoplemanagement.model.Role;

@Stateless
@Default
public class RoleService {

	@PersistenceContext(unitName = "primary")
	protected EntityManager em;

	public Role save(Role r) {
		return em.merge(r);
	}

	public void remove(Role r) {
		em.remove(r);
	}

}