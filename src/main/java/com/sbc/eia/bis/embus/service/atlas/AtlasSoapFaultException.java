// $Id: AtlasSoapFaultException.java,v 1.5 2005/02/09 18:08:23 as5472 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.    
 */

package com.sbc.eia.bis.embus.service.atlas;

import java.util.ArrayList;

import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
 * @author hw7243
 *
 *
 * Atlas Exception. 
 *
 */

public class AtlasSoapFaultException extends ServiceException
{
    private Response faultResponse = null;
    private ArrayList serviceProviderEntityList = new ArrayList();

    /**
     * Method initStringBuffer.
     * 
     * This method essentially checks whether the StringBuffer param is
     * a null reference.  If it is, a new reference to aStringBuffer is 
     * returned, otherwise, the param itself is returned.
     * 
     * @param buffer
     * @return StringBuffer returns a new StringBuffer if the StringBuffer
     * param is null, otherwise return the StringBuffer param itself.
     */
    private static StringBuffer initStringBuffer(StringBuffer buffer)
    {
        return buffer == null ? new StringBuffer() : buffer;
    }

    /**
     * Method getStringBufferForString.
     * This method returns a null if the String parameter is null, otherwise
     * it will return a StringBuffer containing the String parameter.  Since
     * the internal representation of the Strings are now captured in 
     * StringBuffers, his method is used in the String setter methods to 
     * preserve the existing behavior.  If you call a setter passing null,
     * you should get a null reference back on a call to the getter, not the
     * contents of a pre-existing StringBuffer with "null" appended to it.
     * 
     * @param strToSet
     * @return StringBuffer
     */
    private static StringBuffer getStringBufferForString(String strToSet)
    {
        StringBuffer buffer = null;

        if (strToSet != null)
        {
            // replacing existing buffered string to retain
            // existing behavior
            buffer = new StringBuffer().append(strToSet);
        }
        // else, user wants to set value to null, so set the Buffer to null
        // instead to preserve the existing behavior

        return buffer;
    }

    /**
     * Method getStringFromBuffer.
     * 
     * If the internal StringBuffer is null, return null.  Otherwise, return
     * the String in the StringBuffer.
     * 
     * @param buffer
     * @return String
     */
    private static String getStringFromBuffer(StringBuffer buffer)
    {
        String str = null;
        if (buffer != null)
        {
            str = buffer.toString();
        }
        return str;
    }

    /** Description
     *  Response class to encapsulate response info in an Atlas Soap Fault.
     *  Description
     */
    public static class Response
    {
        private StringBuffer code = null;
        private StringBuffer description = null;

        /**
         * Returns the code.
         * @return StringBuffer
         */
        public StringBuffer initCodeBuffer()
        {
            code = initStringBuffer(code);
            return code;
        }

        /**
         * Returns the description.
         * @return StringBuffer
         */
        public StringBuffer initDescriptionBuffer()
        {
            description = initStringBuffer(description);
            return description;
        }

        /**
         * Sets the code.
         * @param code The code to set
         */
        public void setCode(String code)
        {
            this.code = getStringBufferForString(code);
        }

        /**
         * Sets the description.
         * @param description The description to set
         */
        public void setDescription(String description)
        {
            this.description = getStringBufferForString(description);
        }

        /**
         * Returns the code.
         * @return String
         */
        public String getCode()
        {
            return getStringFromBuffer(code);
        }

        /**
         * Returns the description.
         * @return String
         */
        public String getDescription()
        {
            return getStringFromBuffer(description);
        }
    }

    /** Description
     *  ServicProviderEntity class is used to encapsulate one of possibly
     *  many Atlas Soap Fault ServicProviderEntity objects.
     *  Description
     */
    public static class ServiceProviderEntity
    {

        private StringBuffer reportingServiceEntity = null;
        private StringBuffer faultDate = null;
        private StringBuffer faultSequenceNumber = null;
        private StringBuffer faultLevel = null;
        private StringBuffer faultCode = null;
        private StringBuffer faultDescription = null;

        /**
         * Returns the faultCode.
         * @return StringBuffer
         */
        public StringBuffer initFaultCodeBuffer()
        {
            faultCode = initStringBuffer(faultCode);
            return faultCode;
        }

        /**
         * Returns the faultDate.
         * @return StringBuffer
         */
        public StringBuffer initFaultDateBuffer()
        {
            faultDate = initStringBuffer(faultDate);
            return faultDate;
        }

        /**
         * Returns the faultDescription.
         * @return StringBuffer
         */
        public StringBuffer initFaultDescriptionBuffer()
        {
            faultDescription = initStringBuffer(faultDescription);
            return faultDescription;
        }

        /**
         * Returns the faultLevel.
         * @return StringBuffer
         */
        public StringBuffer initFaultLevelBuffer()
        {
            faultLevel = initStringBuffer(faultLevel);
            return faultLevel;
        }

        /**
         * Returns the faultSequenceNumber.
         * @return StringBuffer
         */
        public StringBuffer initFaultSequenceNumberBuffer()
        {
            faultSequenceNumber = initStringBuffer(faultSequenceNumber);
            return faultSequenceNumber;
        }

        /**
         * Returns the reportingServiceEntity.
         * @return StringBuffer
         */
        public StringBuffer initReportingServiceEntityBuffer()
        {
            reportingServiceEntity =
                initStringBuffer(reportingServiceEntity);
            return reportingServiceEntity;
        }

        /**
         * Returns the faultCode.
         * @return String
         */
        public String getFaultCode()
        {
            return getStringFromBuffer(faultCode);
        }

        /**
         * Returns the faultDate.
         * @return String
         */
        public String getFaultDate()
        {
            return getStringFromBuffer(faultDate);
        }

        /**
         * Returns the faultDescription.
         * @return String
         */
        public String getFaultDescription()
        {
            return getStringFromBuffer(faultDescription);
        }

        /**
         * Returns the faultLevel.
         * @return String
         */
        public String getFaultLevel()
        {
            return getStringFromBuffer(faultLevel);
        }

        /**
         * Returns the faultSequenceNumber.
         * @return String
         */
        public String getFaultSequenceNumber()
        {
            return getStringFromBuffer(faultSequenceNumber);
        }

        /**
         * Returns the reportingServiceEntity.
         * @return String
         */
        public String getReportingServiceEntity()
        {
            return getStringFromBuffer(reportingServiceEntity);
        }

        /**
         * Sets the faultCode.
         * @param faultCode The faultCode to set
         */
        public void setFaultCode(String faultCode)
        {
            this.faultCode = getStringBufferForString(faultCode);
        }

        /**
         * Sets the faultDate.
         * @param faultDate The faultDate to set
         */
        public void setFaultDate(String faultDate)
        {
            this.faultDate = getStringBufferForString(faultDate);
        }

        /**
         * Sets the faultDescription.
         * @param faultDescription The faultDescription to set
         */
        public void setFaultDescription(String faultDescription)
        {
            this.faultDescription =
                getStringBufferForString(faultDescription);
        }

        /**
         * Sets the faultLevel.
         * @param faultLevel The faultLevel to set
         */
        public void setFaultLevel(String faultLevel)
        {
            this.faultLevel = getStringBufferForString(faultLevel);
        }

        /**
         * Sets the faultSequenceNumber.
         * @param faultSequenceNumber The faultSequenceNumber to set
         */
        public void setFaultSequenceNumber(String faultSequenceNumber)
        {
            this.faultSequenceNumber =
                getStringBufferForString(faultSequenceNumber);
        }

        /**
         * Sets the reportingServiceEntity.
         * @param reportingServiceEntity The reportingServiceEntity to set
         */
        public void setReportingServiceEntity(String reportingServiceEntity)
        {
            this.reportingServiceEntity =
                getStringBufferForString(reportingServiceEntity);
        }

    }

    /**
     * @see java.lang.Object#Object()
     */
    public AtlasSoapFaultException()
    {
        super();
    }

    /**
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(
            "SoapFault encountered with the following information - ");
            
        sb.append(
            "Response:code[" + getCode() + "], ");

        sb.append(
            "Response:description[" + getDescription() + "]. ");

        for (int i = 0; i < getServiceProviderEntityList().size(); i++)
        {
            try
            {
                ServiceProviderEntity providerEntity =
                    (ServiceProviderEntity) getServiceProviderEntityList()
                        .get(
                        i);
                sb.append("\nServiceProviderEntity[" + i + "]:");
                sb.append(
                    "reportingServiceEntity["
                        + providerEntity.reportingServiceEntity
                        + "], ");
                sb.append("faultDate[" + providerEntity.faultDate + "], ");
                sb.append(
                    "faultSequenceNumber["
                        + providerEntity.faultSequenceNumber
                        + "], ");
                sb.append(
                    "faultLevel[" + providerEntity.faultLevel + "], ");
                sb.append("faultCode[" + providerEntity.faultCode + "], ");
                sb.append(
                    "faultDescription["
                        + providerEntity.faultDescription
                        + "].");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(
                    e.getClass().getName()
                        + "encountered, stacktrace logged.");
            }
        }

        return sb.toString();
    }

    /**
     * Constructor for AtlasSoapFaultException.
     * @param i_serviceExceptionMessage
     * @param i_originalException
     */
    public AtlasSoapFaultException(
        String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super(i_serviceExceptionMessage, i_originalException);
    }

    /**
     * Constructor for AtlasSoapFaultException.
     * @param i_serviceExceptionMessage
     */
    public AtlasSoapFaultException(String i_serviceExceptionMessage)
    {
        super(i_serviceExceptionMessage);
    }

    /**
     * Constructor for AtlasSoapFaultException.
     * @param i_originalException
     */
    public AtlasSoapFaultException(Exception i_originalException)
    {
        super(i_originalException);
    }

    /**
     * Returns the serviceProviderEntity.
     * @return ArrayList
     */
    public ArrayList getServiceProviderEntityList()
    {
        return serviceProviderEntityList;
    }

    /**
     * Sets the serviceProviderEntity.
     * @param serviceProviderEntity The serviceProviderEntity to set
     */
    public void setServiceProviderEntityList(ArrayList serviceProviderEntity)
    {
        serviceProviderEntity = serviceProviderEntity;
    }

    /**
     * Returns the FaultResponse.
     * @return Response
     */
    public Response getFaultResponse()
    {
        return faultResponse;
    }
    
    /**
     * Initializes and returns FaultResponse.
     * @return Response
     */
    public Response initFaultResponse()
    {
        if (faultResponse == null)
        {
            setFaultResponse(createFaultResponse());
        }

        return faultResponse;
    }

    /**
     * Creates a new FaultResponse.
     * @return Response
     */
    private Response createFaultResponse()
    {
        return new Response();
    }

    /**
     * Sets the FaultResponse.
     * @param FaultResponse The FaultResponse to set
     */
    public void setFaultResponse(Response faultResponse)
    {
        this.faultResponse = faultResponse;
    }

    /**
     * Returns the faultCode.
     * @return String
     */
    public String getCode()
    {

        String faultcode = null;

        if (getFaultResponse() != null && getFaultResponse().code != null)
        {
            faultcode = getFaultResponse().code.toString();
        }

        return faultcode;
    }

    /**
     * Returns the description.
     * @return String
     */
    public String getDescription()
    {
        String description = null;

        if (getFaultResponse() != null && getFaultResponse().description != null)
        {
            description = getFaultResponse().description.toString();
        }

        return description;
    }

    /**
     * Convenience method addServiceProviderEntity for adding a 
     * ServiceProviderEntity to the list of ServiceProviderEntities.
     * @param providerEntity
     */
    public void addServiceProviderEntity(ServiceProviderEntity providerEntity)
    {
        getServiceProviderEntityList().add(providerEntity);
    }

}
