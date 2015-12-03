package cursaws.proiect.service;

import cursaws.proiect.model.Person;

/**
 * Interfata de lucrul cu persoane.
 */
public interface PersonRepository {

    Person findPerson(String name);

    void savePerson(Person person);
}
