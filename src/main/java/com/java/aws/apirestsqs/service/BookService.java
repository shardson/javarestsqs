package com.java.aws.apirestsqs.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.aws.apirestsqs.config.BookConfig;
import com.java.aws.apirestsqs.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    Book book = new Book();
    ObjectMapper mapper = new ObjectMapper();
    String queueName ;
    String queue;
    String json;
    private AmazonSQS sqsClient;
    @Autowired
    private BookConfig bookConfig;

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private SetQueueAttributesRequest logRequest ;
    private SendMessageRequest sendMessageRequest = new SendMessageRequest();

    public BookService(BookConfig BookConfig){
        this.bookConfig = BookConfig;
        LOG.info("queue: " + queue);
        LOG.info("queueName " + queueName);
        sqsClient = this.bookConfig.sqsClient();
        queue = sqsClient.getQueueUrl("book-events").getQueueUrl();
        //sqsClient.getQueueUrl(queue).getQueueUrl();
        this.logRequest = new SetQueueAttributesRequest().withQueueUrl(queue)
                .addAttributesEntry(QueueAttributeName.VisibilityTimeout.toString(), "5000");
        sqsClient.setQueueAttributes(logRequest);
    }

    public void SendMessage(Book book){

        book.setId(book.getId());
        book.setName(book.getName());
        book.setAuthor(book.getAuthor());

        try {
            json = mapper.writeValueAsString(book);
            System.out.println("ResultingJSONstring = " + json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        sendMessageRequest.withMessageBody(json);
        sendMessageRequest.withQueueUrl(queue);
        //sendMessageRequest.withMessageAttributes(messageAttributes);
        sqsClient.sendMessage(sendMessageRequest);
    }

}
