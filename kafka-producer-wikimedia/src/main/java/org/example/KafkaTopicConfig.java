package org.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("wikimedia_recent-change").build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name("wikimedia_recent").build();
    }

}
