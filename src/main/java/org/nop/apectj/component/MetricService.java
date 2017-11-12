package org.nop.apectj.component;

import org.springframework.stereotype.Component;

@Component
public class MetricService {

    public void increment(String name) {
        System.out.println("Incrementing metric: " + name);
    }
}
