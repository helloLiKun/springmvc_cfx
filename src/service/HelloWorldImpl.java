package service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by likun on 2018/2/5 0005.
 */
@WebService(endpointInterface="service.HelloWorld",serviceName="HelloWorldImpl")//指定webservice所实现的接口
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHi(@WebParam String name) {
        return "helloworldimpl:"+name;
    }
}
