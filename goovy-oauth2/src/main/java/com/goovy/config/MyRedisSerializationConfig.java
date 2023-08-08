package com.goovy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;

import java.nio.charset.Charset;

public class MyRedisSerializationConfig implements RedisTokenStoreSerializationStrategy {
    public static ParserConfig config = new ParserConfig();
    static {
        ParserConfig.getGlobalInstance().addAccept("org.springframework.security.oauth2.common.DefaultOAuth2AccessToken");
        ParserConfig.getGlobalInstance().addAccept("org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken");
        config.setAutoTypeSupport(true);//开启AutoType
        //添加autotype白名单
        config.addAccept("org.springframework.security.oauth2.provider.");
        config.addAccept("org.springframework.security.oauth2.provider.client");
        config.addAccept("org.springframework.security.oauth2.common.");
        config.addAccept("org.springframework.security.oauth2.common.DefaultOAuth2AccessToken");
        config.addAccept("com.goovy");
        config.addAccept("org.springframework.security.web.authentication.preauth");

    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {

        Preconditions.checkArgument(clazz != null,
                "clazz can't be null");
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        return JSON.parseObject(new String(bytes, IOUtils.UTF8), clazz,config);
    }

    @Override
    public String deserializeString(byte[] bytes) {
        return new String(bytes, IOUtils.UTF8);
    }

    @Override
    public byte[] serialize(Object object) {

        if (object == null) {
            return new byte[0];
        }

        try {
            return JSON.toJSONBytes(object,
                    SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);

        }
    }


    @Override
    public byte[] serialize(String data) {
        if (data == null || data.length() == 0) {
            return new byte[0];
        }
        return data.getBytes(Charset.forName("utf-8"));
    }
}
