
package br.com.gvt.eng.vod;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for qualityserverlist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qualityserverlist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qualityserver" type="{urn:envivio:balancer:1.0}qualityserver" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qualityserverlist", propOrder = {
    "qualityserver"
})
public class Qualityserverlist {

    protected List<Qualityserver> qualityserver;

    /**
     * Gets the value of the qualityserver property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qualityserver property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQualityserver().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Qualityserver }
     * 
     * 
     */
    public List<Qualityserver> getQualityserver() {
        if (qualityserver == null) {
            qualityserver = new ArrayList<Qualityserver>();
        }
        return this.qualityserver;
    }

}
