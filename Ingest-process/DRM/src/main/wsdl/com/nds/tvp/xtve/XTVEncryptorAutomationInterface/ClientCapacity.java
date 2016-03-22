/**
 * ClientCapacity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class ClientCapacity  implements java.io.Serializable {
    private int capacity;

    private int available;

    public ClientCapacity() {
    }

    public ClientCapacity(
           int capacity,
           int available) {
           this.capacity = capacity;
           this.available = available;
    }


    /**
     * Gets the capacity value for this ClientCapacity.
     * 
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Sets the capacity value for this ClientCapacity.
     * 
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    /**
     * Gets the available value for this ClientCapacity.
     * 
     * @return available
     */
    public int getAvailable() {
        return available;
    }


    /**
     * Sets the available value for this ClientCapacity.
     * 
     * @param available
     */
    public void setAvailable(int available) {
        this.available = available;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClientCapacity)) return false;
        ClientCapacity other = (ClientCapacity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.capacity == other.getCapacity() &&
            this.available == other.getAvailable();
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
        _hashCode += getCapacity();
        _hashCode += getAvailable();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClientCapacity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCapacity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capacity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "capacity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("available");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "available"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
