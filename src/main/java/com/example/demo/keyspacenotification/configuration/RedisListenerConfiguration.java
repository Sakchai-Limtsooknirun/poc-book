package com.example.demo.keyspacenotification.configuration;

import com.example.demo.keyspacenotification.listener.TopicMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

@Component
@Profile("worker")
public class RedisListenerConfiguration {

    private TopicMessageListener topicMessageListener;

    @Bean
    public RedisMessageListenerContainer getListenerContainer(RedisConnectionFactory connectionFactory) {
        //Create connection container
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //Put in redis connection
        container.setConnectionFactory(connectionFactory);
        //Write the type to be monitored, i.e. timeout monitoring
        Topic topic = new PatternTopic("__keyevent@1__:expired");
        container.addMessageListener(topicMessageListener, topic);
        return container;
    }

    public void setTopicMessageListener(TopicMessageListener topicMessageListener) {
        this.topicMessageListener = topicMessageListener;
    }
}
