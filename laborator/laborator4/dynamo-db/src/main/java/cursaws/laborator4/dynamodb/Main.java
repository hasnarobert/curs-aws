package cursaws.laborator4.dynamodb;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemResult;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static final String ACCESS_KEY = "xxx";
  public static final String SECRET_KEY = "xxx";
  public static final String TABLE_NAME = "xxx";

  public static void main(String[] args) throws Exception {
    AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentials);

    Map<String, AttributeValue> fields = new HashMap<String, AttributeValue>();
    fields.put("hash", new AttributeValue().withS("1"));
    fields.put("range", new AttributeValue().withS("2"));
    fields.put("metadata", new AttributeValue().withS("Ana nu mai are mere"));
    PutItemRequest putItemRequest = new PutItemRequest(TABLE_NAME, fields);
    dynamoDBClient.putItem(putItemRequest);
    System.out.println("Intrarea a fost salvata");

    fields.remove("metadata");
    GetItemRequest getItemRequest = new GetItemRequest(TABLE_NAME, fields);
    GetItemResult getItemResult = dynamoDBClient.getItem(getItemRequest);
    System.out.println("Mesajul a fost citit: " + getItemResult);


    Map<String, KeysAndAttributes> fields2 = new HashMap<String, KeysAndAttributes>();
    fields2.put(TABLE_NAME, new KeysAndAttributes().withKeys(fields));
    BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest().withRequestItems(fields2);
    BatchGetItemResult batchGetItemResult = dynamoDBClient.batchGetItem(batchGetItemRequest);
    System.out.println("Multe mesaje citite: " + batchGetItemResult.getResponses());
  }
}
