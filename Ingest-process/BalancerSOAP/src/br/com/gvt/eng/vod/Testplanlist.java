
package br.com.gvt.eng.vod;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for testplanlist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testplanlist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testplan" type="{urn:envivio:balancer:1.0}testplan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testplanlist", propOrder = {
    "testplan"
})
public class Testplanlist {

    protected List<Testplan> testplan;

    /**
     * Gets the value of the testplan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testplan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestplan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Testplan }
     * 
     * 
     */
    public List<Testplan> getTestplan() {
        if (testplan == null) {
            testplan = new ArrayList<Testplan>();
        }
        return this.testplan;
    }

}
