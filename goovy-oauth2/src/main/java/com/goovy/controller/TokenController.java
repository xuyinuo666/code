package com.goovy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goovy.bo.UserDetailBO;
import com.goovy.dto.UserLoginDTO;
import com.goovy.exception.GoovyException;
import com.goovy.oauth2.feign.OAuth2FeignClient;
import com.goovy.response.ResEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.goovy.response.Res;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
public class TokenController {
    @Resource
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/login")
    public Res getToken(UserLoginDTO userLogin) {
        Map<String,String> params = new HashMap<>();
        params.put(OAuth2Utils.GRANT_TYPE,userLogin.getGrant_type());
        params.put(OAuth2Utils.SCOPE,userLogin.getScope());
        params.put(OAuth2Utils.CLIENT_ID,userLogin.getClient_id());
        params.put("username",userLogin.getUsername());
        params.put("password",userLogin.getPasswd());
        params.put("secret",userLogin.getClient_secret());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogin.getClient_id(),userLogin.getClient_secret(),new ArrayList<>());
        try {
            ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(token, params);
            OAuth2AccessToken body = oAuth2AccessTokenResponseEntity.getBody();
            JSONObject tokenJson = new JSONObject();
            tokenJson.put("token",body.getValue());
            tokenJson.put("scope",body.getScope());
            tokenJson.put("refresh_token",body.getRefreshToken());
            tokenJson.put("type",body.getTokenType());
            tokenJson.put("expiration_date",body.getExpiration());
            tokenJson.put("expires_in",body.getExpiresIn());
            return Res.success(tokenJson);
        } catch (Exception e) {
            return Res.fail(ResEnum.UNAUTHORIZED);
        }
    }
}
