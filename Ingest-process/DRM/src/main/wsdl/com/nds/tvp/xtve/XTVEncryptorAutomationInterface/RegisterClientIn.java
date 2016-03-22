/**
 * RegisterClientIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class RegisterClientIn  implements java.io.Serializable {
    private java.lang.String notificationURL;

    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] features;

    public RegisterClientIn() {
    }

    public RegisterClientIn(
           java.lang.String notificationURL,
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] features) {
           this.notificationURL = notificationURL;
           this.features = features;
    }


    /**
     * Gets the notificationURL value for this RegisterClientIn.
     * 
     * @return notificationURL
     */
    public java.lang.String getNotificationURL() {
        return notificationURL;
    }


    /**
     * Sets the notificationURL value for this RegisterClientIn.
     * 
     * @param notificationURL
     */
    public void setNotificationURL(java.lang.String notificationURL) {
        this.notificationURL = notificationURL;
    }


    /**
     * Gets the features value for this RegisterClientIn.
     * 
     * @return features
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] getFeatures() {
        return features;
    }


    /**
     * Sets the features value for this RegisterClientIn.
     * 
     * @param features
     */
    public void setFeatures(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] features) {
        this.features = features;
    }

    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature getFeatures(int i) {
        return this.features[i];
    }

    public void setFeatures(int i, com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature _value) {
        this.features[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegisterClientIn)) return false;
        RegisterClientIn other = (RegisterClientIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.notificationURL==null && other.getNotificationURL()==null) || 
             (this.notificationURL!=null &&
              this.notificationURL.equals(other.getNotificationURL()))) &&
            ((this.features==null && other.getFeatures()==null) || 
             (this.features!=null &&
              java.util.Arrays.equals(this.features, other.getFeatures())));
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
        if (getNotificationURL() != null) {
            _hashCode += getNotificationURL().hashCode();
        }
        if (getFeatures() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFeatures());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFeatures(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegisterClientIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", ">RegisterClientIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "NotificationURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("features");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Features"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Feature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
