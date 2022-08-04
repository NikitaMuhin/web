package com.orange.web.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class.getName());

    private final KafkaSender<Integer, String> sender;

    private ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer(String bootstrapServers) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        sender = KafkaSender.create(SenderOptions.create(properties));
    }

    public void sendMessage(String topic, Object object, CountDownLatch latch) throws InterruptedException {
        sender.send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic, toBinary(object)), object)))
        .doOnError(e -> log.error("Send failed", e)).delayElements(Duration.ofSeconds(10))
                .subscribe(record -> {
                    RecordMetadata metadata = record.recordMetadata();
                    Instant timestamp = Instant.ofEpochMilli(metadata.timestamp());
                    System.out.printf("Message  sent successfully, topic-partition=%s-%d offset=%d timestamp=%s\n",
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            timestamp);
                    latch.countDown();
                });
    }

    private String toBinary(Object object) {
        try {
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}