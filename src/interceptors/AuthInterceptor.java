package interceptors;

/**
 * Created by likun on 2018/2/6 0006.
 */

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private SAAJInInterceptor saa = new SAAJInInterceptor();

    public AuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
        getAfter().add(SAAJInInterceptor.class.getName());
    }

    public void handleMessage(SoapMessage message) throws Fault {
        SOAPMessage mess = message.getContent(SOAPMessage.class);
        if (mess == null) {
            saa.handleMessage(message);
            mess = message.getContent(SOAPMessage.class);
        }
        SOAPHeader head = null;
        try {
            head = mess.getSOAPHeader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (head == null) {
            return;
        }
        NodeList nodes = head.getElementsByTagName("tns:spId");
        NodeList nodepass = head.getElementsByTagName("tns:spPassword");
        System.out.println("----------username--------"+nodes.item(0).getTextContent());
        System.out.println("----------password--------"+nodepass.item(0).getTextContent());
        if (nodes.item(0).getTextContent().equals("wang") && nodepass.item(0).getTextContent().equals("123")) {
            System.out.println("认证成功");
        } else {
            SOAPException soapExc = new SOAPException("认证错误");
            throw new Fault(soapExc);
        }

    }

}

