package org.j2os.api;

import org.j2os.service.KafkaSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KafkaAPI {
    private final KafkaSenderService kafkaSenderService;

    public KafkaAPI(KafkaSenderService kafkaSenderService) {
        this.kafkaSenderService = kafkaSenderService;

    }

    @GetMapping("/send")
    public String send() throws ExecutionException, InterruptedException {
        kafkaSenderService.send();
        return "Sent.";
    }

    @GetMapping("/asyncSend")
    public String asyncSend() {
        kafkaSenderService.asyncSend();
        return "Sent.";
    }

    @GetMapping("/asyncSendWithoutCallBack")
    public String asyncSendWithoutCallBack() {
        kafkaSenderService.asyncSendWithoutCallBack();
        return "Sent.";
    }
}
