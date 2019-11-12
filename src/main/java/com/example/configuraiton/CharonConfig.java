package com.example.configuraiton;

import com.github.mkopylec.charon.configuration.CharonConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.interseptor.TestForwardingInterceptorConfigurer.testForwardingInterceptor;
import static com.github.mkopylec.charon.configuration.CharonConfigurer.charonConfiguration;
import static com.github.mkopylec.charon.configuration.RequestMappingConfigurer.requestMapping;
import static com.github.mkopylec.charon.forwarding.interceptors.rewrite.RequestServerNameRewriterConfigurer.requestServerNameRewriter;

@Configuration
public class CharonConfig {

    @Bean
    CharonConfigurer charonConfigurer() {
        return charonConfiguration()
//                .set(restTemplate())
                .set(requestServerNameRewriter().outgoingServers("localhost:9080"))
                .set(testForwardingInterceptor())
                .add(requestMapping("Just Testing").pathRegex("/test.*"));
    }
}
