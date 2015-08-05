
package ru.cg.cda.axl.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.8
 * 2015-08-04T12:12:34.100+03:00
 * Generated source version: 2.7.8
 */

@WebFault(name = "axlError", targetNamespace = "http://www.cisco.com/AXL/API/10.0")
public class AXLError_Exception extends Exception {
    
    private ru.cg.cda.axl.generated.AXLError axlError;

    public AXLError_Exception() {
        super();
    }
    
    public AXLError_Exception(String message) {
        super(message);
    }
    
    public AXLError_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public AXLError_Exception(String message, ru.cg.cda.axl.generated.AXLError axlError) {
        super(message);
        this.axlError = axlError;
    }

    public AXLError_Exception(String message, ru.cg.cda.axl.generated.AXLError axlError, Throwable cause) {
        super(message, cause);
        this.axlError = axlError;
    }

    public ru.cg.cda.axl.generated.AXLError getFaultInfo() {
        return this.axlError;
    }
}
