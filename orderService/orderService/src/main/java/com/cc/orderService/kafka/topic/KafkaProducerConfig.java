package com.cc.orderService.kafka.topic;

import com.cc.orderService.Entity.Order;
import com.cc.orderService.kafka.customSeralizer.OrderSUmmarySeralizer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {


    @Bean
    public NewTopic orderEventsTopic() {
        return TopicBuilder.name("order-events")
                .partitions(3)
                .replicas(1)                                          // was 2
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000") // 7 days (also fixes the ~70-day typo)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, "delete")
                .build();                                             // dropped min.insync.replicas
    }
    // when we haev more than one custom seralizer
    @Bean
    public KafkaTemplate<String, Order>customSeralizerKafkaTemplate(KafkaProperties  kafkaProperties) {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties(null));
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSUmmarySeralizer.class); // seralizing only value VALUE_SERIALIZER_CLASS_CONFIG
        DefaultKafkaProducerFactory<String, Order> factory = new DefaultKafkaProducerFactory<>(props);
        return new KafkaTemplate<>(factory);
    }
}
