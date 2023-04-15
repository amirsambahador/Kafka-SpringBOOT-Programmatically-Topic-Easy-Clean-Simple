package org.j2os.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSubscriberService {

    @KafkaListener(topics = "user-topic", groupId = "j2os-p3-r3-app")
    public void receiver(String message) {
        log.info("message received  -> " + message);
    }
}
