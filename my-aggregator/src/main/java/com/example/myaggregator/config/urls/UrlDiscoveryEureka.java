package com.example.myaggregator.config.urls;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.stereotype.Service;

@Service
public class UrlDiscoveryEureka {
    private final EurekaClient discoveryClient;

    public UrlDiscoveryEureka(EurekaClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }
    public String getServerUrl(String serverName){
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serverName, false);
        return instance.getHomePageUrl();
    }
}
