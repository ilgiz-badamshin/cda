
package ru.cg.cda.axl.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetMobilityRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMobilityRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.cisco.com/AXL/API/10.0}APIResponse">
 *       &lt;sequence>
 *         &lt;element name="return">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="mobility" type="{http://www.cisco.com/AXL/API/10.0}RMobility"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMobilityRes", propOrder = {
    "_return"
})
public class GetMobilityRes
    extends APIResponse
{

    @XmlElement(name = "return", required = true)
    protected GetMobilityRes.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GetMobilityRes.Return }
     *     
     */
    public GetMobilityRes.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMobilityRes.Return }
     *     
     */
    public void setReturn(GetMobilityRes.Return value) {
        this._return = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="mobility" type="{http://www.cisco.com/AXL/API/10.0}RMobility"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mobility"
    })
    public static class Return {

        @XmlElement(required = true)
        protected RMobility mobility;

        /**
         * Gets the value of the mobility property.
         * 
         * @return
         *     possible object is
         *     {@link RMobility }
         *     
         */
        public RMobility getMobility() {
            return mobility;
        }

        /**
         * Sets the value of the mobility property.
         * 
         * @param value
         *     allowed object is
         *     {@link RMobility }
         *     
         */
        public void setMobility(RMobility value) {
            this.mobility = value;
        }

    }

}