package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
/**
 * Created by likun on 2018/2/5 0005.
 */
@WebService
public interface HelloWorld {
    @WebMethod
    String sayHi(@WebParam String name);
}
