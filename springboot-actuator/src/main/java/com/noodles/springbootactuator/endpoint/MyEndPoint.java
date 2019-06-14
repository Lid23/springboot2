package com.noodles.springbootactuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot2
 * @description: MyEndPoint
 * @author: Eric
 * @create: 2019-06-14 11:27
 **/
@Endpoint(id="eric")
public class MyEndPoint {

    @ReadOperation
    public Map<String, String> hello(){
        Map<String, String> result = new HashMap<>();
        result.put("author", "eric");
        result.put("age", "25");
        result.put("email", "645465550@qq.com");
        return result;
    }
}
