package cursaws.proiect.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

import cursaws.proiect.model.Person;

import java.util.HashMap;

/**
 * Lucreaza cu persone stocand si extragand datele lor din DynamoDB.
 */
public class PersonRepositoryImpl implements PersonRepository {

    private static final String TABLE_NAME = "webapp-template-person";
    private AmazonDynamoDB dynamoDB;

    /**
     * Acest constructor este apelat din {@link cursaws.proiect.webapp.Application} pentru a crea o componenta spring (@Bean)
     */
    public PersonRepositoryImpl(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    /**
     * Extrage date despre o anume persoana din baza da date.
     */
    @Override
    public Person findPerson(final String name) {
        GetItemResult result = dynamoDB.getItem(new GetItemRequest()
                                                    .withTableName(TABLE_NAME)
                                                    .withKey(new HashMap<String, AttributeValue>() {{
                                                        put("name", new AttributeValue().withS(name));
                                                    }}));

        if (result.getItem() == null) {
            return null;
        }

        return new Person(result.getItem().get("name").getS(),
                          Integer.parseInt(result.getItem().get("age").getS()));
    }

    /**
     * Stocheaza informatiile despre o persoana in baza de date.
     */
    @Override
    public void savePerson(final Person person) {
        dynamoDB.putItem(new PutItemRequest()
                             .withTableName(TABLE_NAME)
                             .withItem(new HashMap<String, AttributeValue>() {{
                                 put("name", new AttributeValue().withS(person.getName()));
                                 put("age", new AttributeValue().withS(Integer.toString(person.getAge())));
                             }}));
    }
}
