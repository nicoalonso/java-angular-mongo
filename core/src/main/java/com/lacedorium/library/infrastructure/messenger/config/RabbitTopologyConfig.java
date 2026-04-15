package com.lacedorium.library.infrastructure.messenger.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitTopologyConfig {
    public static final String LIBRARY_EXCHANGE = "library";
    public static final String LIBRARY_QUEUE = "library";
    public static final String LIBRARY_ROUTING_KEY = "library";

    @Bean
    ApplicationRunner declareRabbitTopology(AmqpAdmin amqpAdmin) {
        Logger logger = LoggerFactory.getLogger(RabbitTopologyConfig.class);
        logger.info("Declaring RabbitMQ Topology");

        return args -> {
            TopicExchange exchange = new TopicExchange(LIBRARY_EXCHANGE, true, false);
            Queue queue = QueueBuilder.durable(LIBRARY_QUEUE).build();
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(LIBRARY_ROUTING_KEY);

            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);
        };
    }

    @Bean
    JsonMapper rabbitJsonMapper() {
        return new JsonMapper();
    }

    @Bean
    public MessageConverter rabbitMessageConverter(JsonMapper rabbitJsonMapper) {
        return new JacksonJsonMessageConverter(rabbitJsonMapper);
    }
}
