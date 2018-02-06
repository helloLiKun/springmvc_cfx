package interceptors;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * token认证
 * User: sunbingwei
 * Date: 2010-5-22
 * Time: 18:01:15
 */
public class HeaderIntercepter extends AbstractSoapInterceptor {
	private String qname;
    public HeaderIntercepter(){
		super(Phase.WRITE);
	}


    public void handleMessage(SoapMessage soapMessage) throws Fault {
    			 
    	        String spPassword="wang";  
    	        String spName="can";  
    	          
    	        QName name=new QName("RequestSOAPHeader");  
    	        Document doc=DOMUtils.createDocument();  
    	          
    	        Element spId=doc.createElement("tns:spId");  
    	        spId.setTextContent(spName);  
              
    	        Element spPass=doc.createElement("tns:spPassword");  
    	        spPass.setTextContent(spPassword);  
    	          
    	        Element root=doc.createElementNS(qname, "tns:RequestSOAPHeader");  
    	       root.appendChild(spId);  
    	       root.appendChild(spPass);  
    	          
    	        SoapHeader head=new SoapHeader(name,root);  
    	        List<Header> headers=soapMessage.getHeaders();  
    	        headers.add(head);  

    }

    private Object getHeader() {
		QName qName=new QName("", "", "");
		Document document= DOMUtils.createDocument();
		Element element=document.createElementNS(qname, "RequestSOAPHeader");
		Element token = document.createElement("token");
		token.setTextContent("kkkkk");
		//element.appendChild(token);
		SoapHeader header=new SoapHeader(qName, token);
		return(header);
	}


	public String getQname() {
		return qname;
	}


	public void setQname(String qname) {
		this.qname = qname;
	}

   
}
