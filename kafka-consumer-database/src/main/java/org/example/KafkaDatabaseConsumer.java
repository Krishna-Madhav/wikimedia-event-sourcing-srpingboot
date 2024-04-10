package org.example;

import org.example.entity.WikimediaData;
import org.example.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRepository dataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "wikimedia_recent-change", groupId = "myGroup")
    public void consume(String message) {

        LOGGER.info(String.format("Event Message received -->> %s", message));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(message);

        dataRepository.save(wikimediaData);


    }

}
