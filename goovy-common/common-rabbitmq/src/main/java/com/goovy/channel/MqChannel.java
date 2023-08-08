package com.goovy.channel;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MqChannel {

    MessageChannel output();

    SubscribableChannel input();
}
