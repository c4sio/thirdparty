/**
 * ClientRegistration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class ClientRegistration  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedLong clientCookie;

    private long lastRequest;

    private boolean active;

    private org.apache.axis.types.UnsignedLong jobNumber;

    public ClientRegistration() {
    }

    public ClientRegistration(
           org.apache.axis.types.UnsignedLong clientCookie,
           long lastRequest,
           boolean active,
           org.apache.axis.types.UnsignedLong jobNumber) {
           this.clientCookie = clientCookie;
           this.lastRequest = lastRequest;
           this.active = active;
           this.jobNumber = jobNumber;
    }


    /**
     * Gets the clientCookie value for this ClientRegistration.
     * 
     * @return clientCookie
     */
    public org.apache.axis.types.UnsignedLong getClientCookie() {
        return clientCookie;
    }


    /**
     * Sets the clientCookie value for this ClientRegistration.
     * 
     * @param clientCookie
     */
    public void setClientCookie(org.apache.axis.types.UnsignedLong clientCookie) {
        this.clientCookie = clientCookie;
    }


    /**
     * Gets the lastRequest value for this ClientRegistration.
     * 
     * @return lastRequest
     */
    public long getLastRequest() {
        return lastRequest;
    }


    /**
     * Sets the lastRequest value for this ClientRegistration.
     * 
     * @param lastRequest
     */
    public void setLastRequest(long lastRequest) {
        this.lastRequest = lastRequest;
    }


    /**
     * Gets the active value for this ClientRegistration.
     * 
     * @return active
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Sets the active value for this ClientRegistration.
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Gets the jobNumber value for this ClientRegistration.
     * 
     * @return jobNumber
     */
    public org.apache.axis.types.UnsignedLong getJobNumber() {
        return jobNumber;
    }


    /**
     * Sets the jobNumber value for this ClientRegistration.
     * 
     * @param jobNumber
     */
    public void setJobNumber(org.apache.axis.types.UnsignedLong jobNumber) {
        this.jobNumber = jobNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClientRegistration)) return false;
        ClientRegistration other = (ClientRegistration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clientCookie==null && other.getClientCookie()==null) || 
             (this.clientCookie!=null &&
              this.clientCookie.equals(other.getClientCookie()))) &&
            this.lastRequest == other.getLastRequest() &&
            this.active == other.isActive() &&
            ((this.jobNumber==null && other.getJobNumber()==null) || 
             (this.jobNumber!=null &&
              this.jobNumber.equals(other.getJobNumber())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getClientCookie() != null) {
            _hashCode += getClientCookie().hashCode();
        }
        _hashCode += new Long(getLastRequest()).hashCode();
        _hashCode += (isActive() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getJobNumber() != null) {
            _hashCode += getJobNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClientRegistration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientRegistration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCookie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "clientCookie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "lastRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("active");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "jobNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
