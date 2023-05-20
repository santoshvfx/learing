
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

import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
 * @author hw7243
 *
 *
 * Atlas Exception. 
 *
 */

public class AtlasException extends ServiceException
{

	private String exceptionCode = null;

	private String exceptionDescription = null;
	
	/**
	 * Method AtlasException.
	 * @param code
	 * @param description
	 */
	public AtlasException(String code, String description)
	{
		super(code + "[" + description + "]");
		exceptionCode = code;
		exceptionDescription = description;
	}
	
	
	/**
	 * @see com.sbc.eia.bis.embus.service.access.ServiceException#ServiceException(Exception)
	 */
	public AtlasException( Exception arg3)
	{
		super(arg3);
	
	}
	

	
	
	/**
	 * Method getExceptionCode.
	 * @return String
	 */
	public String getExceptionCode()
	{
		return exceptionCode;
	}
	
	/**
	 * Method setExceptionCode.
	 * @param exceptionCode
	 */
	
	public void setExceptionCode(String exceptionCode)
	{
		this.exceptionCode = exceptionCode;
	}

	/**
	 * Returns the exceptionDescription.
	 * @return String
	 */
	public String getExceptionDescription() {
		return exceptionDescription;
	}

	/**
	 * Sets the exceptionDescription.
	 * @param exceptionDescription The exceptionDescription to set
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

}
