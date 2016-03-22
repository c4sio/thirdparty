/**
 * HeartbeatIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class HeartbeatIn  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedLong clientCookie;

    public HeartbeatIn() {
    }

    public HeartbeatIn(
           org.apache.axis.types.UnsignedLong clientCookie) {
           this.clientCookie = clientCookie;
    }


    /**
     * Gets the clientCookie value for this HeartbeatIn.
     * 
     * @return clientCookie
     */
    public org.apache.axis.types.UnsignedLong getClientCookie() {
        return clientCookie;
    }


    /**
     * Sets the clientCookie value for this HeartbeatIn.
     * 
     * @param clientCookie
     */
    public void setClientCookie(org.apache.axis.types.UnsignedLong clientCookie) {
        this.clientCookie = clientCookie;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HeartbeatIn)) return false;
        HeartbeatIn other = (HeartbeatIn) obj;
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
              this.clientCookie.equals(other.getClientCookie())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HeartbeatIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", ">HeartbeatIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCookie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCookie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
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
