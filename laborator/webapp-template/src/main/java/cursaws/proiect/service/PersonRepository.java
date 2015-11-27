package cursaws.proiect.service;

import cursaws.proiect.model.Person;

public interface PersonRepository {

    Person findPerson(String name);

    void savePerson(Person person);
}
