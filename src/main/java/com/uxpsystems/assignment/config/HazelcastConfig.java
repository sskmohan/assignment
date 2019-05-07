package com.uxpsystems.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfig {

    private static final String HAZELCAST_INSTANCE_NAME = "hazelcast-instance";
    public static final String HAZELCAST_REQUEST_CONFIG = "findRequests";

    @Lazy
    @Autowired
    HazelcastInstance hazelcastInstance;
    
    @Bean
    public Config hazelCastConfig() {
        Config config = new Config()
                .setInstanceName(HAZELCAST_INSTANCE_NAME)
                .addMapConfig(
                        new MapConfig()
                                .setName(HAZELCAST_REQUEST_CONFIG)
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(20));
                enableManagementCenter(config);
                
                return config; 
    }
    
    private static void enableManagementCenter(Config cfg) {
        ManagementCenterConfig mgtCenter = new ManagementCenterConfig("http://localhost:5702/mancenter", 3);
        mgtCenter.setEnabled(true);
        cfg.setManagementCenterConfig(mgtCenter);

    }
}