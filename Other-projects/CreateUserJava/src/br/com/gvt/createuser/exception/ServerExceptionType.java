package br.com.gvt.createuser.exception;



import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Object returned by server in case of Server related faults
 * 
 * <p>Java class for ServerExceptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServerExceptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exceptionCategory">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="SVR"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="exceptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="variables" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerExceptionType", namespace = "http://www.telefonica.com/schemas/UNICA/SOAP/common/v1", propOrder = {
    "exceptionCategory",
    "exceptionId",
    "text",
    "variables"
})
public class ServerExceptionType {

    @XmlElement(required = true)
    protected String exceptionCategory;
    protected int exceptionId;
    @XmlElement(required = true)
    protected String text;
    protected List<String> variables;

    /**
     * Gets the value of the exceptionCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionCategory() {
        return exceptionCategory;
    }

    /**
     * Sets the value of the exceptionCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionCategory(String value) {
        this.exceptionCategory = value;
    }

    /**
     * Gets the value of the exceptionId property.
     * 
     */
    public int getExceptionId() {
        return exceptionId;
    }

    /**
     * Sets the value of the exceptionId property.
     * 
     */
    public void setExceptionId(int value) {
        this.exceptionId = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the variables property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variables property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariables().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVariables() {
        if (variables == null) {
            variables = new ArrayList<String>();
        }
        return this.variables;
    }

}
