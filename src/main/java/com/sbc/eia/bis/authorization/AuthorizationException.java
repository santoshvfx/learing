// $Id: AuthorizationException.java,v 1.2 2004/05/07 16:51:13 vc7563 Exp $

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
package com.sbc.eia.bis.authorization;

/** Description
 *  Appropriate Description.
 *  Description
 */
public class AuthorizationException extends Throwable
{
	/**
	 * Constructor for AuthorizationException.
	 */
	public AuthorizationException()
	{
		super();
	}
	
	/**
	 * Constructor for AuthorizationException.
	 */
    public AuthorizationException ( String s ) {
		super ( s );
    }
}
