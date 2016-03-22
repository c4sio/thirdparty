/**
 * HeartbeatOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class HeartbeatOut  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedLong clientCookie;

    private org.apache.axis.types.UnsignedLong jobNumber;

    private org.apache.axis.types.UnsignedByte jobType;

    private org.apache.axis.types.UnsignedByte statusCode;

    private org.apache.axis.types.UnsignedByte percentageComplete;

    private java.lang.String statusDetails;

    private java.lang.String errorCode;

    private java.lang.String physicalContentFile;

    public HeartbeatOut() {
    }

    public HeartbeatOut(
           org.apache.axis.types.UnsignedLong clientCookie,
           org.apache.axis.types.UnsignedLong jobNumber,
           org.apache.axis.types.UnsignedByte jobType,
           org.apache.axis.types.UnsignedByte statusCode,
           org.apache.axis.types.UnsignedByte percentageComplete,
           java.lang.String statusDetails,
           java.lang.String errorCode,
           java.lang.String physicalContentFile) {
           this.clientCookie = clientCookie;
           this.jobNumber = jobNumber;
           this.jobType = jobType;
           this.statusCode = statusCode;
           this.percentageComplete = percentageComplete;
           this.statusDetails = statusDetails;
           this.errorCode = errorCode;
           this.physicalContentFile = physicalContentFile;
    }


    /**
     * Gets the clientCookie value for this HeartbeatOut.
     * 
     * @return clientCookie
     */
    public org.apache.axis.types.UnsignedLong getClientCookie() {
        return clientCookie;
    }


    /**
     * Sets the clientCookie value for this HeartbeatOut.
     * 
     * @param clientCookie
     */
    public void setClientCookie(org.apache.axis.types.UnsignedLong clientCookie) {
        this.clientCookie = clientCookie;
    }


    /**
     * Gets the jobNumber value for this HeartbeatOut.
     * 
     * @return jobNumber
     */
    public org.apache.axis.types.UnsignedLong getJobNumber() {
        return jobNumber;
    }


    /**
     * Sets the jobNumber value for this HeartbeatOut.
     * 
     * @param jobNumber
     */
    public void setJobNumber(org.apache.axis.types.UnsignedLong jobNumber) {
        this.jobNumber = jobNumber;
    }


    /**
     * Gets the jobType value for this HeartbeatOut.
     * 
     * @return jobType
     */
    public org.apache.axis.types.UnsignedByte getJobType() {
        return jobType;
    }


    /**
     * Sets the jobType value for this HeartbeatOut.
     * 
     * @param jobType
     */
    public void setJobType(org.apache.axis.types.UnsignedByte jobType) {
        this.jobType = jobType;
    }


    /**
     * Gets the statusCode value for this HeartbeatOut.
     * 
     * @return statusCode
     */
    public org.apache.axis.types.UnsignedByte getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this HeartbeatOut.
     * 
     * @param statusCode
     */
    public void setStatusCode(org.apache.axis.types.UnsignedByte statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the percentageComplete value for this HeartbeatOut.
     * 
     * @return percentageComplete
     */
    public org.apache.axis.types.UnsignedByte getPercentageComplete() {
        return percentageComplete;
    }


    /**
     * Sets the percentageComplete value for this HeartbeatOut.
     * 
     * @param percentageComplete
     */
    public void setPercentageComplete(org.apache.axis.types.UnsignedByte percentageComplete) {
        this.percentageComplete = percentageComplete;
    }


    /**
     * Gets the statusDetails value for this HeartbeatOut.
     * 
     * @return statusDetails
     */
    public java.lang.String getStatusDetails() {
        return statusDetails;
    }


    /**
     * Sets the statusDetails value for this HeartbeatOut.
     * 
     * @param statusDetails
     */
    public void setStatusDetails(java.lang.String statusDetails) {
        this.statusDetails = statusDetails;
    }


    /**
     * Gets the errorCode value for this HeartbeatOut.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this HeartbeatOut.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the physicalContentFile value for this HeartbeatOut.
     * 
     * @return physicalContentFile
     */
    public java.lang.String getPhysicalContentFile() {
        return physicalContentFile;
    }


    /**
     * Sets the physicalContentFile value for this HeartbeatOut.
     * 
     * @param physicalContentFile
     */
    public void setPhysicalContentFile(java.lang.String physicalContentFile) {
        this.physicalContentFile = physicalContentFile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HeartbeatOut)) return false;
        HeartbeatOut other = (HeartbeatOut) obj;
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
            ((this.jobNumber==null && other.getJobNumber()==null) || 
             (this.jobNumber!=null &&
              this.jobNumber.equals(other.getJobNumber()))) &&
            ((this.jobType==null && other.getJobType()==null) || 
             (this.jobType!=null &&
              this.jobType.equals(other.getJobType()))) &&
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.percentageComplete==null && other.getPercentageComplete()==null) || 
             (this.percentageComplete!=null &&
              this.percentageComplete.equals(other.getPercentageComplete()))) &&
            ((this.statusDetails==null && other.getStatusDetails()==null) || 
             (this.statusDetails!=null &&
              this.statusDetails.equals(other.getStatusDetails()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.physicalContentFile==null && other.getPhysicalContentFile()==null) || 
             (this.physicalContentFile!=null &&
              this.physicalContentFile.equals(other.getPhysicalContentFile())));
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
        if (getJobNumber() != null) {
            _hashCode += getJobNumber().hashCode();
        }
        if (getJobType() != null) {
            _hashCode += getJobType().hashCode();
        }
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getPercentageComplete() != null) {
            _hashCode += getPercentageComplete().hashCode();
        }
        if (getStatusDetails() != null) {
            _hashCode += getStatusDetails().hashCode();
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getPhysicalContentFile() != null) {
            _hashCode += getPhysicalContentFile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HeartbeatOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", ">HeartbeatOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCookie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCookie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "JobNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "JobType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "StatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentageComplete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "PercentageComplete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "StatusDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physicalContentFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "PhysicalContentFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
