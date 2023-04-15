package org.j2os.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic iran() {
        return TopicBuilder.name("user-topic")
                .partitions(3)
                .replicas(3)
                .build();
    }
}