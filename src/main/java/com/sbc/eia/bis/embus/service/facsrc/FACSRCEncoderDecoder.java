//$Id: FACSRCEncoderDecoder.java,v 1.1 2007/03/06 22:30:40 mg5629 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2007 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 1/8/2007  | Manjula Goniguntla	| create
//
package com.sbc.eia.bis.embus.service.facsrc;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
 * @author mg5629
 *
 */
public class FACSRCEncoderDecoder extends DefaultJAXBEncoderDecoder {

	public FACSRCEncoderDecoder(
		String packageName,
		Properties marshalUnmarshalOptions) {
		super(packageName, marshalUnmarshalOptions);
	}

	public Object[] decode(String message)
		throws DecoderException, ServiceException {

		return new Object[] { super.decode(message)[0] };

	}

	public String encode(Object[] request)
		throws ServiceException, EncoderException {

		return (String) super.encode(request);

	}
}
