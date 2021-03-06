package ru.cg.cda.axl.generated;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2015-08-04T12:12:34.777+03:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "AXLAPIService", 
                  wsdlLocation = "classpath:ru/cg/cda/axl/AXLAPI.wsdl",
                  targetNamespace = "http://www.cisco.com/AXLAPIService/") 
public class AXLAPIService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.cisco.com/AXLAPIService/", "AXLAPIService");
    public final static QName AXLPort = new QName("http://www.cisco.com/AXLAPIService/", "AXLPort");
    static {
        URL url = AXLAPIService.class.getClassLoader().getResource("ru/cg/cda/axl/AXLAPI.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(AXLAPIService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:ru/cg/cda/axl/AXLAPI.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public AXLAPIService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AXLAPIService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AXLAPIService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AXLAPIService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AXLAPIService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AXLAPIService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns AXLPort
     */
    @WebEndpoint(name = "AXLPort")
    public AXLPort getAXLPort() {
        return super.getPort(AXLPort, AXLPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AXLPort
     */
    @WebEndpoint(name = "AXLPort")
    public AXLPort getAXLPort(WebServiceFeature... features) {
        return super.getPort(AXLPort, AXLPort.class, features);
    }

}
