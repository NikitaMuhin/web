package com.orange.web.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.web.domain.CreditScore;
import com.orange.web.server.CreditScoreServe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    @Autowired
    private CreditScoreServe creditScoreServer;
    private final Flux<ReceiverRecord<String, String>> reactiveKafkaReceiver;

    @EventListener(ApplicationStartedEvent.class)
    public void onMessage() {
        reactiveKafkaReceiver
                .doOnNext(r -> createPersonalDataMono(r.value()))
                .doOnError(e -> log.error("KafkaFlux exception", e))
                .subscribe();
    }

    private void createPersonalDataMono(String personalDataString)  {
        log.info(personalDataString);
        CreditScore creditScore = fromBinary(personalDataString, CreditScore.class);
        creditScoreServer.save(creditScore.getId(),Mono.just(creditScore));
    }

    private <T> T fromBinary(String object, Class<T> resultType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.readValue(object, resultType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
