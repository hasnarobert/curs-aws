package cursaws.laborator2.producatorconsumator;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;

import java.util.List;

public class Main {
    public static final String ACCESS_KEY = "xxx";
    public static final String SECRET_KEY = "xxx";
    public static final String QUEUE_URL = "xxx";

    public static void main(String[] args) throws Exception {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonSQSClient sqs = new AmazonSQSClient(credentials);

        SendMessageRequest sendRequest = new SendMessageRequest(QUEUE_URL, "Mesaj de test.");
        SendMessageResult sendResult = sqs.sendMessage(sendRequest);

        System.out.println("Am trimis mesajul cu id: '" + sendResult.getMessageId() +
                "' si md5: '" + sendResult.getMD5OfMessageBody() + "'.");

        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest(QUEUE_URL).withMaxNumberOfMessages(1);
        ReceiveMessageResult receiveResult = sqs.receiveMessage(receiveRequest);

        List<Message> messages = receiveResult.getMessages();
        Message message;
        while (true) {
            if (messages.isEmpty()) {
                Thread.currentThread().sleep(3000);
                continue;
            }

            message = messages.get(0);
            System.out.println("Am primit mesajul cu id: '" + message.getMessageId() +
                    "', md5: '" + message.getMD5OfBody() +
                    "' si continut: '" + message.getBody());
            break;
        }

        DeleteMessageRequest deleteRequest = new DeleteMessageRequest(QUEUE_URL, message.getReceiptHandle());
        sqs.deleteMessage(deleteRequest);

        System.out.println("Mesajul a fost sters");
    }
}
