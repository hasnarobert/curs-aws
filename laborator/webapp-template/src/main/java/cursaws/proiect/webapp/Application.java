package cursaws.proiect.webapp;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cursaws.proiect.service.PersonRepository;
import cursaws.proiect.service.PersonRepositoryImpl;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PersonRepository personRepository() {
        AmazonDynamoDB dynamoDB = new AmazonDynamoDBClient();
        dynamoDB.setRegion(Region.getRegion(Regions.US_EAST_1));
        return new PersonRepositoryImpl(dynamoDB);
    }

}
