package com.bzwebframework.web.spi.socket.config;

import com.bzwebframework.web.spi.socket.WebSocketMessagingHandler;
import com.bzwebframework.web.spi.socket.manager.DefaultMessagingManager;
import com.bzwebframework.web.spi.socket.manager.MessagingManager;
import com.bzwebframework.web.spi.socket.subscribe.SubscriptionProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @Author CaptainBing
 * @Date 2024/8/30 17:30
 * @Description
 */
@Configuration
@EnableWebSocket
public class WebSocketMessagingHandlerConfiguration {

    @Bean
    public WebSocketMessagingHandler webSocketMessagingHandler(MessagingManager messagingManager) {
        return new WebSocketMessagingHandler(messagingManager);
    }

    @Bean
    @ConditionalOnMissingBean(MessagingManager.class)
    public DefaultMessagingManager defaultMessagingManager(ObjectProvider<SubscriptionProvider> providers) {
        DefaultMessagingManager messagingManager = new DefaultMessagingManager();
        providers.forEach(messagingManager::register);
        return messagingManager;
    }

}
