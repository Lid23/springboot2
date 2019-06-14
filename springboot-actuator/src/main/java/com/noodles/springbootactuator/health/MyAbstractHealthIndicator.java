package com.noodles.springbootactuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 继承AbstractHealthIndicator抽象类，重写doHealthCheck方法，
 * 功能比第一种要强大一点点，默认的DataSourceHealthIndicator 、
 * RedisHealthIndicator都是这种写法，内容回调中还做了异常的处理
 * @program: springboot2
 * @description: MyAbstractHealthIndicator
 * @author: Eric
 * @create: 2019-06-14 11:18
 **/

@Component("my2")
public class MyAbstractHealthIndicator extends AbstractHealthIndicator {

    private static final String VERSION = "v1.0.0";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = check();
        if (code != 0) {
            builder.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        builder.withDetail("code", code)
                .withDetail("version", VERSION).up().build();
    }

    private int check() {
        return 0;
    }
}
