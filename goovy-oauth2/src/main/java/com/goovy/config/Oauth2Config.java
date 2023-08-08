package com.goovy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Preconditions;
import com.goovy.constant.RedisCacheConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationStrategy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.BaseRedisTokenStoreSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.nio.charset.Charset;

@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
    @Resource
    private DataSource dataSource;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource(name = "myUserDetailService")
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        redisTokenStore.setSerializationStrategy(new MyRedisSerializationConfig());
        redisTokenStore.setPrefix(RedisCacheConstant.USER_TOKEN);
        return redisTokenStore;
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds(30 * 24 * 3600);
        return defaultTokenServices;
    }

//    @Bean
//    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


        clients.inMemory().withClient("app")
                .secret(passwordEncoder.encode("12345"))
                .scopes("all", "write", "read")
                .authorizedGrantTypes("refresh_token", "password", "authorization_code", "client_credentials")
                .accessTokenValiditySeconds(3600 * 24 * 7)
                .autoApprove(true)
                .refreshTokenValiditySeconds(3600 * 24 * 14);
//        clients.inMemory()
//                .withClient("app").secret("secret_app").scopes("all", "read", "write").authorizedGrantTypes("refresh_token","password","authorization_code","client_credentials").accessTokenValiditySeconds(1000000000)
//                .and()
//                .withClient("pc").secret("secret_pc").scopes("all", "read", "write").authorizedGrantTypes("refresh_token","password","authorization_code","client_credentials").accessTokenValiditySeconds(1000000000);

//        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
        endpoints.tokenServices(defaultTokenServices());
    }
}
