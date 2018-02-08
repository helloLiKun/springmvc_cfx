package client;

/**
 * Created by likun on 2018/2/7 0007.
 */
import client.interceptors.SoapInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import service.HelloWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likun on 2018/2/7 0007.
 */
public class ClientMain {
    public static void main(String[] args){
        List list = new ArrayList();
        SoapInterceptor saopInterceptor = new SoapInterceptor();
        list.add(saopInterceptor);
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // 注册WebService接口
        factory.setServiceClass(HelloWorld.class);
        // webservice请求地址
        String wsdlAdder = "http://localhost:8081/services/hw";
        // 发布接口
        factory.setAddress(wsdlAdder);
        factory.setOutInterceptors(list);
        HelloWorld helloWorld = (HelloWorld) factory.create();
        System.out.println("----------back result--------"+helloWorld.sayHi("cxf hello"));
    }
}

