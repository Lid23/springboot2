package com.noodles.springbootactuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 实现HealthIndicator接口，根据自己的需要判断返回的状态是UP还是DOWN，功能简单
 * @program: springboot2
 * @description: MyHealthIndicator
 * @author: Eric
 * @create: 2019-06-14 11:04
 **/
@Component("my1")
public class MyHealthIndicator implements HealthIndicator {

    public static final String VERSION = "v1.0.0";


    @Override
    public Health health() {
        int code = check();
        if(code != 0){
            Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }

        return Health.up().withDetail("code", code).withDetail("version", VERSION).up().build();
    }

    private int check(){
        return 0;
    }
}
