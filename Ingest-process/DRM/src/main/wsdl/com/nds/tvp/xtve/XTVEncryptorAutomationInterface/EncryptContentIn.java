/**
 * EncryptContentIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class EncryptContentIn  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedLong clientCookie;

    private org.apache.axis.types.UnsignedByte jobType;

    private java.lang.String sourceLoc;

    private java.lang.String destLoc;

    private java.lang.String metadataLoc;

    private java.lang.String physical_CRID;

    private java.lang.String clip_CRID;

    private java.lang.String title;

    private org.apache.axis.types.UnsignedByte streamTypes;

    private java.lang.String accessCriteria;

    private org.apache.axis.types.UnsignedLong cryptoPeriod;

    private org.apache.axis.types.UnsignedByte deleteInputAfterProcess;

    public EncryptContentIn() {
    }

    public EncryptContentIn(
           org.apache.axis.types.UnsignedLong clientCookie,
           org.apache.axis.types.UnsignedByte jobType,
           java.lang.String sourceLoc,
           java.lang.String destLoc,
           java.lang.String metadataLoc,
           java.lang.String physical_CRID,
           java.lang.String clip_CRID,
           java.lang.String title,
           org.apache.axis.types.UnsignedByte streamTypes,
           java.lang.String accessCriteria,
           org.apache.axis.types.UnsignedLong cryptoPeriod,
           org.apache.axis.types.UnsignedByte deleteInputAfterProcess) {
           this.clientCookie = clientCookie;
           this.jobType = jobType;
           this.sourceLoc = sourceLoc;
           this.destLoc = destLoc;
           this.metadataLoc = metadataLoc;
           this.physical_CRID = physical_CRID;
           this.clip_CRID = clip_CRID;
           this.title = title;
           this.streamTypes = streamTypes;
           this.accessCriteria = accessCriteria;
           this.cryptoPeriod = cryptoPeriod;
           this.deleteInputAfterProcess = deleteInputAfterProcess;
    }


    /**
     * Gets the clientCookie value for this EncryptContentIn.
     * 
     * @return clientCookie
     */
    public org.apache.axis.types.UnsignedLong getClientCookie() {
        return clientCookie;
    }


    /**
     * Sets the clientCookie value for this EncryptContentIn.
     * 
     * @param clientCookie
     */
    public void setClientCookie(org.apache.axis.types.UnsignedLong clientCookie) {
        this.clientCookie = clientCookie;
    }


    /**
     * Gets the jobType value for this EncryptContentIn.
     * 
     * @return jobType
     */
    public org.apache.axis.types.UnsignedByte getJobType() {
        return jobType;
    }


    /**
     * Sets the jobType value for this EncryptContentIn.
     * 
     * @param jobType
     */
    public void setJobType(org.apache.axis.types.UnsignedByte jobType) {
        this.jobType = jobType;
    }


    /**
     * Gets the sourceLoc value for this EncryptContentIn.
     * 
     * @return sourceLoc
     */
    public java.lang.String getSourceLoc() {
        return sourceLoc;
    }


    /**
     * Sets the sourceLoc value for this EncryptContentIn.
     * 
     * @param sourceLoc
     */
    public void setSourceLoc(java.lang.String sourceLoc) {
        this.sourceLoc = sourceLoc;
    }


    /**
     * Gets the destLoc value for this EncryptContentIn.
     * 
     * @return destLoc
     */
    public java.lang.String getDestLoc() {
        return destLoc;
    }


    /**
     * Sets the destLoc value for this EncryptContentIn.
     * 
     * @param destLoc
     */
    public void setDestLoc(java.lang.String destLoc) {
        this.destLoc = destLoc;
    }


    /**
     * Gets the metadataLoc value for this EncryptContentIn.
     * 
     * @return metadataLoc
     */
    public java.lang.String getMetadataLoc() {
        return metadataLoc;
    }


    /**
     * Sets the metadataLoc value for this EncryptContentIn.
     * 
     * @param metadataLoc
     */
    public void setMetadataLoc(java.lang.String metadataLoc) {
        this.metadataLoc = metadataLoc;
    }


    /**
     * Gets the physical_CRID value for this EncryptContentIn.
     * 
     * @return physical_CRID
     */
    public java.lang.String getPhysical_CRID() {
        return physical_CRID;
    }


    /**
     * Sets the physical_CRID value for this EncryptContentIn.
     * 
     * @param physical_CRID
     */
    public void setPhysical_CRID(java.lang.String physical_CRID) {
        this.physical_CRID = physical_CRID;
    }


    /**
     * Gets the clip_CRID value for this EncryptContentIn.
     * 
     * @return clip_CRID
     */
    public java.lang.String getClip_CRID() {
        return clip_CRID;
    }


    /**
     * Sets the clip_CRID value for this EncryptContentIn.
     * 
     * @param clip_CRID
     */
    public void setClip_CRID(java.lang.String clip_CRID) {
        this.clip_CRID = clip_CRID;
    }


    /**
     * Gets the title value for this EncryptContentIn.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this EncryptContentIn.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the streamTypes value for this EncryptContentIn.
     * 
     * @return streamTypes
     */
    public org.apache.axis.types.UnsignedByte getStreamTypes() {
        return streamTypes;
    }


    /**
     * Sets the streamTypes value for this EncryptContentIn.
     * 
     * @param streamTypes
     */
    public void setStreamTypes(org.apache.axis.types.UnsignedByte streamTypes) {
        this.streamTypes = streamTypes;
    }


    /**
     * Gets the accessCriteria value for this EncryptContentIn.
     * 
     * @return accessCriteria
     */
    public java.lang.String getAccessCriteria() {
        return accessCriteria;
    }


    /**
     * Sets the accessCriteria value for this EncryptContentIn.
     * 
     * @param accessCriteria
     */
    public void setAccessCriteria(java.lang.String accessCriteria) {
        this.accessCriteria = accessCriteria;
    }


    /**
     * Gets the cryptoPeriod value for this EncryptContentIn.
     * 
     * @return cryptoPeriod
     */
    public org.apache.axis.types.UnsignedLong getCryptoPeriod() {
        return cryptoPeriod;
    }


    /**
     * Sets the cryptoPeriod value for this EncryptContentIn.
     * 
     * @param cryptoPeriod
     */
    public void setCryptoPeriod(org.apache.axis.types.UnsignedLong cryptoPeriod) {
        this.cryptoPeriod = cryptoPeriod;
    }


    /**
     * Gets the deleteInputAfterProcess value for this EncryptContentIn.
     * 
     * @return deleteInputAfterProcess
     */
    public org.apache.axis.types.UnsignedByte getDeleteInputAfterProcess() {
        return deleteInputAfterProcess;
    }


    /**
     * Sets the deleteInputAfterProcess value for this EncryptContentIn.
     * 
     * @param deleteInputAfterProcess
     */
    public void setDeleteInputAfterProcess(org.apache.axis.types.UnsignedByte deleteInputAfterProcess) {
        this.deleteInputAfterProcess = deleteInputAfterProcess;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EncryptContentIn)) return false;
        EncryptContentIn other = (EncryptContentIn) obj;
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
            ((this.jobType==null && other.getJobType()==null) || 
             (this.jobType!=null &&
              this.jobType.equals(other.getJobType()))) &&
            ((this.sourceLoc==null && other.getSourceLoc()==null) || 
             (this.sourceLoc!=null &&
              this.sourceLoc.equals(other.getSourceLoc()))) &&
            ((this.destLoc==null && other.getDestLoc()==null) || 
             (this.destLoc!=null &&
              this.destLoc.equals(other.getDestLoc()))) &&
            ((this.metadataLoc==null && other.getMetadataLoc()==null) || 
             (this.metadataLoc!=null &&
              this.metadataLoc.equals(other.getMetadataLoc()))) &&
            ((this.physical_CRID==null && other.getPhysical_CRID()==null) || 
             (this.physical_CRID!=null &&
              this.physical_CRID.equals(other.getPhysical_CRID()))) &&
            ((this.clip_CRID==null && other.getClip_CRID()==null) || 
             (this.clip_CRID!=null &&
              this.clip_CRID.equals(other.getClip_CRID()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.streamTypes==null && other.getStreamTypes()==null) || 
             (this.streamTypes!=null &&
              this.streamTypes.equals(other.getStreamTypes()))) &&
            ((this.accessCriteria==null && other.getAccessCriteria()==null) || 
             (this.accessCriteria!=null &&
              this.accessCriteria.equals(other.getAccessCriteria()))) &&
            ((this.cryptoPeriod==null && other.getCryptoPeriod()==null) || 
             (this.cryptoPeriod!=null &&
              this.cryptoPeriod.equals(other.getCryptoPeriod()))) &&
            ((this.deleteInputAfterProcess==null && other.getDeleteInputAfterProcess()==null) || 
             (this.deleteInputAfterProcess!=null &&
              this.deleteInputAfterProcess.equals(other.getDeleteInputAfterProcess())));
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
        if (getJobType() != null) {
            _hashCode += getJobType().hashCode();
        }
        if (getSourceLoc() != null) {
            _hashCode += getSourceLoc().hashCode();
        }
        if (getDestLoc() != null) {
            _hashCode += getDestLoc().hashCode();
        }
        if (getMetadataLoc() != null) {
            _hashCode += getMetadataLoc().hashCode();
        }
        if (getPhysical_CRID() != null) {
            _hashCode += getPhysical_CRID().hashCode();
        }
        if (getClip_CRID() != null) {
            _hashCode += getClip_CRID().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getStreamTypes() != null) {
            _hashCode += getStreamTypes().hashCode();
        }
        if (getAccessCriteria() != null) {
            _hashCode += getAccessCriteria().hashCode();
        }
        if (getCryptoPeriod() != null) {
            _hashCode += getCryptoPeriod().hashCode();
        }
        if (getDeleteInputAfterProcess() != null) {
            _hashCode += getDeleteInputAfterProcess().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EncryptContentIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", ">EncryptContentIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCookie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "ClientCookie"));
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
        elemField.setFieldName("sourceLoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "SourceLoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destLoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "DestLoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadataLoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "MetadataLoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physical_CRID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Physical_CRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clip_CRID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Clip_CRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streamTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "StreamTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessCriteria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "AccessCriteria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cryptoPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "CryptoPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteInputAfterProcess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/", "DeleteInputAfterProcess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
