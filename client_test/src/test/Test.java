package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.HelloWorld;


public class Test {
	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-client.xml");
	        HelloWorld helloWorld = ctx.getBean("userWsClient", HelloWorld.class);
	        System.out.println("#############Client getUserByName##############");
	        System.out.println(helloWorld.sayHi("-----client test--------"));


	}
}
