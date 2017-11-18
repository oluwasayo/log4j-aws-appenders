// Copyright (c) Keith D Gregory, all rights reserved
package com.kdgregory.log4j.aws;

import java.net.URL;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import com.kdgregory.log4j.aws.testhelpers.MessageWriter;


public class SNSAppenderIntegrationTest
{
    private Logger mainLogger;
    
    private String runId;           // used to create unique names for queues and topic
    private String resourceName;    // used for both topic and queue
    private String topicArn;        // set after the topic has been created
    
    private AmazonSNS snsClient;
    private AmazonSQS sqsClient;

    
    public void setUp(String propertiesName, boolean createTopicAndQueue) throws Exception
    {
        URL config = ClassLoader.getSystemResource(propertiesName);
        PropertyConfigurator.configure(config);

        mainLogger = Logger.getLogger(getClass());
        
        runId = String.valueOf(System.currentTimeMillis());
        System.setProperty("SNSAppenderIntegrationTest.runId", runId);
        
        snsClient = AmazonSNSClientBuilder.defaultClient();
        sqsClient = AmazonSQSClientBuilder.defaultClient();
        
        if (! createTopicAndQueue)
            return;
        
        resourceName = "SNSAppenderIntegrationTest-" + runId;
        
        CreateTopicResult createTopicResult = snsClient.createTopic(
                                                new CreateTopicRequest()
                                                    .withName(resourceName));
        topicArn = createTopicResult.getTopicArn();
        

    }

}
