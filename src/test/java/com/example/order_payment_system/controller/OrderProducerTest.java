package com.example.order_payment_system.controller;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"order-events"})
public class OrderProducerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Test
    void testKafkaMessageSend() {
        // given
        String topic = "order-events";
        String message = "{\"orderId\":1,\"product\":\"Test\",\"quantity\":100,\"state\":\"PENDING\"}";

        // when
        kafkaTemplate.send(topic, message);

        // then
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafkaBroker);

        DefaultKafkaConsumerFactory<String, String> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
        Consumer<String, String> consumer = consumerFactory.createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, topic);

        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, topic);
        assertThat(record.value()).isEqualTo(message);
    }
}
