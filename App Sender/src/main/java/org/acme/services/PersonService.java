package org.acme.services;

import org.acme.entitites.Person;
import org.acme.utils.Status;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {
    @Inject
    EntityManager em;

    @Transactional
    public Person createPerson(String name) {
        Person person = new Person(name, Status.ACTIVE);
        em.persist(person);
        return person;
    }

    public List<Person> findAll() {
        return Person.findAll().list();
    }

    public List<Person> findActive() {
        return Person.findActive();
    }

    public Person findByName(String name) {
        return Person.findByName(name);
    }


}
