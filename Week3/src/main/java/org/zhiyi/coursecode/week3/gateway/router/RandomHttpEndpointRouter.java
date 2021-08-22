package org.zhiyi.coursecode.week3.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));


        // Load Balance
        // Random
        // RoundRibbon
        // Weight
        // - server01,20
        // - server02,30
        // - server03,50
    }
}
