package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();

	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Gabrielle");
		person.setLastName("Lima");
		person.setAddress("Ribeirão Pires, SP");
		person.setGender("Female");
		return person;
	}

	public List<Person> findAll() {
		ArrayList<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	public Person create(Person person) {
		return person;
	}
	
	public void delete(String id) {
		
	}
	
	public Person update(Person person) {
		return person;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("First name" + i);
		person.setLastName("Last name" + i);
		person.setAddress("Some address" + i);
		person.setGender("Some gender" + i);
		return person;
	}
}
