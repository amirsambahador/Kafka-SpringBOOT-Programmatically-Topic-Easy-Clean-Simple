package org.j2os.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
/*
    Bahador, Amirsam
 */
@Service
@Slf4j
public class KafkaSenderService {
    private final KafkaTemplate<String, String> template;

    public KafkaSenderService(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public KafkaSenderService send() throws ExecutionException, InterruptedException {
        SendResult<String, String> stringStringSendResult = template
                .send("user-topic", "create user event")
                .get();
        log.info("partition         -> " + stringStringSendResult.getRecordMetadata().partition());
        log.info("timestamp         -> " + stringStringSendResult.getRecordMetadata().timestamp());
        log.info("offset            -> " + stringStringSendResult.getRecordMetadata().offset());
        return this;
    }

    public KafkaSenderService asyncSend() {
        template
                .send("user-topic", "create user event")
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log.error("error", throwable);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> stringStringSendResult) {
                        log.info("partition         -> " + stringStringSendResult.getRecordMetadata().partition());
                        log.info("timestamp         -> " + stringStringSendResult.getRecordMetadata().timestamp());
                        log.info("offset            -> " + stringStringSendResult.getRecordMetadata().offset());
                    }
                });
        return this;
    }

    public KafkaSenderService asyncSendWithoutCallBack() {
        template.send("user-topic", "create user event");
        return this;
    }
}
