package org.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topicName = "wikimedia_recent-change";

        // In order to read real time stream data from wikimedia, we will use event source
        // (For this an event source dependency need to be added to POM) and then we will create an event handler class to read any changes in wikimedia

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);

        // Event Source connects to source i.e wikimedia
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(url)).build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
