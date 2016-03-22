/**
 * SystemInformationOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class SystemInformationOut  implements java.io.Serializable {
    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] features;

    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ConnectionTime connectionTime;

    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientCapacity clientCapacity;

    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration[] clientRegistrations;

    private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.CurrentTime currentTime;

    public SystemInformationOut() {
    }

    public SystemInformationOut(
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] features,
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ConnectionTime connectionTime,
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientCapacity clientCapacity,
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration[] clientRegistrations,
           com.nds.tvp.xtve.XTVEncryptorAutomationInterface.CurrentTime currentTime) {
           this.features = features;
           this.connectionTime = connectionTime;
           this.clientCapacity = clientCapacity;
           this.clientRegistrations = clientRegistrations;
           this.currentTime = currentTime;
    }


    /**
     * Gets the features value for this SystemInformationOut.
     * 
     * @return features
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] getFeatures() {
        return features;
    }


    /**
     * Sets the features value for this SystemInformationOut.
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


    /**
     * Gets the connectionTime value for this SystemInformationOut.
     * 
     * @return connectionTime
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ConnectionTime getConnectionTime() {
        return connectionTime;
    }


    /**
     * Sets the connectionTime value for this SystemInformationOut.
     * 
     * @param connectionTime
     */
    public void setConnectionTime(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ConnectionTime connectionTime) {
        this.connectionTime = connectionTime;
    }


    /**
     * Gets the clientCapacity value for this SystemInformationOut.
     * 
     * @return clientCapacity
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientCapacity getClientCapacity() {
        return clientCapacity;
    }


    /**
     * Sets the clientCapacity value for this SystemInformationOut.
     * 
     * @param clientCapacity
     */
    public void setClientCapacity(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientCapacity clientCapacity) {
        this.clientCapacity = clientCapacity;
    }


    /**
     * Gets the clientRegistrations value for this SystemInformationOut.
     * 
     * @return clientRegistrations
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration[] getClientRegistrations() {
        return clientRegistrations;
    }


    /**
     * Sets the clientRegistrations value for this SystemInformationOut.
     * 
     * @param clientRegistrations
     */
    public void setClientRegistrations(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration[] clientRegistrations) {
        this.clientRegistrations = clientRegistrations;
    }


    /**
     * Gets the currentTime value for this SystemInformationOut.
     * 
     * @return currentTime
     */
    public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.CurrentTime getCurrentTime() {
        return currentTime;
    }


    /**
     * Sets the currentTime value for this SystemInformationOut.
     * 
     * @param currentTime
     */
    public void setCurrentTime(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SystemInformationOut)) return false;
        SystemInformationOut other = (SystemInformationOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.features==null && other.getFeatures()==null) || 
             (this.features!=null &&
              java.util.Arrays.equals(this.features, other.getFeatures()))) &&
            ((this.connectionTime==null && other.getConnectionTime()==null) || 
             (this.connectionTime!=null &&
              this.connectionTime.equals(other.getConnectionTime()))) &&
            ((this.clientCapacity==null && other.getClientCapacity()==null) || 
             (this.clientCapacity!=null &&
              this.clientCapacity.equals(other.getClientCapacity()))) &&
            ((this.clientRegistrations==null && other.getClientRegistrations()==null) || 
             (this.clientRegistrations!=null &&
              java.util.Arrays.equals(this.clientRegistrations, other.getClientRegistrations()))) &&
            ((this.currentTime==null && other.getCurrentTime()==null) || 
             (this.currentTime!=null &&
              this.currentTime.equals(other.getCurrentTime())));
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
        if (getConnectionTime() != null) {
            _hashCode += getConnectionTime().hashCode();
        }
        if (getClientCapacity() != null) {
            _hashCode += getClientCapacity().hashCode();
        }
        if (getClientRegistrations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClientRegistrations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClientRegistrations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurrentTime() != null) {
            _hashCode += getCurrentTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemInformationOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", ">SystemInformationOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("features");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Features"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Feature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connectionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ConnectionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ConnectionTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCapacity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCapacity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCapacity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientRegistrations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientRegistrations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientRegistration"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientRegistration"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "CurrentTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "CurrentTime"));
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
