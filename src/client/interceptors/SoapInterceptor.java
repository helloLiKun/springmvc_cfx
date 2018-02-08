package client.interceptors;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * Created by likun on 2018/2/7 0007.
 */
public class SoapInterceptor extends AbstractSoapInterceptor {
    private static String nameURI = "http://localhost:8081/services/hw";
    public SoapInterceptor() {
        // 指定该拦截器在哪个阶段被激发
        super(Phase.WRITE);
    }
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        String spPassword = "123";
        String spName = "wang";
        QName qname = new QName("RequestSOAPHeader");
        Document doc = DOMUtils.createDocument();
        // 自定义节点
        Element spId = doc.createElement("tns:spId");
        spId.setTextContent(spName);
        // 自定义节点
        Element spPass = doc.createElement("tns:spPassword");
        spPass.setTextContent(spPassword);

        Element root = doc.createElementNS(nameURI, "tns:RequestSOAPHeader");
        root.appendChild(spId);
        root.appendChild(spPass);

        SoapHeader head = new SoapHeader(qname, root);
        List<Header> headers = message.getHeaders();
        headers.add(head);
    }

}
