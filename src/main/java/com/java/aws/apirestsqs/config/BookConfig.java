package com.java.aws.apirestsqs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.endpoint}")
    private String awsEndpoint;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;
    private AmazonSQS sqsClient;
    private static final Logger LOG = LoggerFactory.getLogger(BookConfig.class);
    //Definição do Client do SQS


    public BookConfig() {
    }

    @Bean
    public AmazonSQS sqsClient(){
        LOG.info("awsRegion : " + awsRegion);
        LOG.info("awsAccessKeyId : " + awsAccessKeyId);
        LOG.info("awsSecretKey : " + awsSecretKey);
        LOG.info("awsendpoint : " + awsEndpoint);
        return this.sqsClient = AmazonSQSClientBuilder
                .standard()
                //.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.getName()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, Regions.US_EAST_1.getName()))
                //.withCredentials(new DefaultAWSCredentialsProviderChain())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretKey)))
                .build();

    }
}
