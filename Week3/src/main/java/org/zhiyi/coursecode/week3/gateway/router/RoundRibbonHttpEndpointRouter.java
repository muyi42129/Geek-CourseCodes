package org.zhiyi.coursecode.week3.gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {

    private static AtomicInteger counts = new AtomicInteger(0);
    
    @Override
    public String route(List<String> endpoints) {
        //TODO judge servers is alive
        int base = counts.getAndIncrement();
        int index = base % endpoints.size();
        return endpoints.get(index);
    }
}
