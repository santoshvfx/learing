//$Id: Emailer.java,v 1.1 2008/06/25 14:42:56 nl9267 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date    		  | Author     	 	| Notes
//# ----------------------------------------------------------------------------
//# 06/28/2005	  		Sriram Chevuturu	Creation.
//# 08/07/2005			Sriram Chevuturu	Added all Other Methods and Logging Code.
//# 08/09/2005			Sriram Chevuturu	Modified and Improved Logging.

package com.sbc.eia.bis.lim.util;


import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
* @author sc8468
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates.
* To enable and disable the creation of type comments go to
* Window>Preferences>Java>Code Generation.
*/
public final class Emailer {

  private Utility aUtility = null;
  private Hashtable aProperties = null;


	
	private static Properties mailServerConfig = new Properties();
	//if you want to debug, default value is false.
	private boolean DEBUG = false;
	
	private String aSecondaryServerPropName = null;
	
	private String aPrimarySMTPServerName = null;
	
	
	private String MAIL_SERVER_PROP 				=	"mail.smtp.host";	


	/**
	 * Method Emailer.
	 * @param utility
	 * @param properties
	 */
	public Emailer(Utility utility, Hashtable properties) {
		super();
      aProperties = properties;
      aUtility = utility;		
	}
	
	
	/**
	 * Method setEmailConfig.
	 * @param emailProps
	 */
	public void setEmailConfig(Properties emailProps)
	{
      String myMethodName = "Emailer::setEmailConfig()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		mailServerConfig = emailProps;
		aPrimarySMTPServerName = (String) mailServerConfig.get(MAIL_SERVER_PROP);

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
	}
	
	/**
	 * Method setMailDebug.
	 * @param debugOrNot
	 */
	public void setMailDebug(boolean debugOrNot)
	{
      String myMethodName = "Emailer::setMailDebug()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
		
		DEBUG = debugOrNot;	

		
      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);			
	}		
		
	/**
	 * Method sendEmail.
	 * @param recipients
	 * @param sender
	 * @param subject
	 * @param body
	 */
	public void sendEmail(String[] recipients,String sender,String subject,String body) throws MessagingException {

      String myMethodName = "Emailer::sendEmail()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		

		// create some properties and get the default Session
  	Session session = Session.getDefaultInstance(mailServerConfig, null);
  	session.setDebug(DEBUG);

  	// create a message
	    Message msg = new MimeMessage(session);

		try{
		    // set the from and to address
  		InternetAddress addressFrom = new InternetAddress(sender);
	    	msg.setFrom(addressFrom);

		    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
  		for (int i = 0; i < recipients.length; i++)
	    	{
  	    		addressTo[i] = new InternetAddress(recipients[i]);
		    }
  		msg.setRecipients(Message.RecipientType.TO, addressTo);
 
 		    // Optional : You can also set your custom headers in the Email if you Want
  		//msg.addHeader("MyHeaderName", "myHeaderValue");
	    	// Setting the Subject and Content Type
	    	msg.setSubject(subject);
		    msg.setContent( body, "text/plain");
			Transport.send(msg);		
		}
		catch(MessagingException ex1)
		{			

			//now try to send email using secondary smtp server.
			if(aSecondaryServerPropName != null)
			{
				if(mailServerConfig.get(aSecondaryServerPropName) != null)
					mailServerConfig.put(MAIL_SERVER_PROP,mailServerConfig.get(aSecondaryServerPropName));
					session = Session.getDefaultInstance(mailServerConfig,null);
					try{
						Transport.send(msg);
					}
					catch(MessagingException ex2)
					{
				throw new MessagingException("Sending The Email with both the Primary SMTP Server = <"+
												aPrimarySMTPServerName+ "> \n"+
												"  and with the Secondary SMTP Server = <"+
												mailServerConfig.get(MAIL_SERVER_PROP)+
												"> Failed.\n",ex2);		
					}
			}
			//if no secondary smtp server throw the initial error.
			else
			{
				throw new MessagingException("Sending The Email with The Primary SMTP Server = <"+
												aPrimarySMTPServerName+ "> Failed.\n"+
												"and there is no Secondary SMTP server Available.",ex1);		
			}		
		}

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
		
	}

	/**
	 * Method setSecondarySMTPServerPropName.
	 * @param aSecondaryMailServerProp
	 */
	public void setSecondarySMTPServerPropName(String aSecondaryMailServerProp)
	{
      String myMethodName = "Emailer::setSecondarySMTPServerPropName()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);				
		
		aSecondaryServerPropName = aSecondaryMailServerProp;

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
      		
	}

}