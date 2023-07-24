package com.goovy.utils;

import com.goovy.bo.RequestUserBO;
import com.goovy.exception.GoovyException;

import java.util.Map;
import java.util.Objects;

public class UserContextUtils {
    public static final ThreadLocal<RequestUserBO> USER_CONTEXT = new ThreadLocal<>();

    public static void set(RequestUserBO requestUserBO) {
        if (Objects.nonNull(requestUserBO)) {
            USER_CONTEXT.set(requestUserBO);
        } else {
            throw new GoovyException("user is null");
        }
    }
    public static RequestUserBO get() {
            return USER_CONTEXT.get();
    }

    public static RequestUserBO getUserNoNull() {
        RequestUserBO requestUserBO = USER_CONTEXT.get();
        if (Objects.isNull(requestUserBO)){
            throw new GoovyException("user must not be null");
        }
        return requestUserBO;
    }


    public static void clear(){
        USER_CONTEXT.remove();
    }
}
