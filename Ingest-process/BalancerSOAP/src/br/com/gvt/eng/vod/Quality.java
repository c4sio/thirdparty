
package br.com.gvt.eng.vod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for quality complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quality">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="serverid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="testplan" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quality")
public class Quality {

    @XmlAttribute(name = "serverid", required = true)
    protected String serverid;
    @XmlAttribute(name = "testplan", required = true)
    protected String testplan;

    /**
     * Gets the value of the serverid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerid() {
        return serverid;
    }

    /**
     * Sets the value of the serverid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerid(String value) {
        this.serverid = value;
    }

    /**
     * Gets the value of the testplan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestplan() {
        return testplan;
    }

    /**
     * Sets the value of the testplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestplan(String value) {
        this.testplan = value;
    }

}
