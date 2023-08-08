package com.goovy.config;

import com.goovy.channel.MqChannel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface UserOuthChannel extends MqChannel {
    String OUTPUT_CHANNEL = "user_output";
    /**
     * 输入信道
     */
    String INPUT_CHANNEL ="user_input";
    /**
     * 死信队列
     */
    String INPUT_CHANNEL_DLQ = "user_dlq_input";

    @Output(OUTPUT_CHANNEL)
    MessageChannel output();

    @Input(INPUT_CHANNEL)
    SubscribableChannel input();
}
