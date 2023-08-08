package com.goovy.service.impl;

import com.goovy.channel.MqChannel;
import com.goovy.service.SendMessageService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@EnableBinding(MqChannel.class)
public class SendMessageServiceImpl implements SendMessageService {
    @Resource
    private MqChannel mqChannel;
    @Override
    public boolean SendMessage(String msg) {
        return mqChannel.output().send(MessageBuilder.withPayload(msg.getBytes()).build());
    }
}
