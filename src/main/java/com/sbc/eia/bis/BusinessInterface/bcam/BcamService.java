//$Id: BcamService.java,v 1.5 2008/03/03 00:40:10 gg2712 Exp $
package com.sbc.eia.bis.BusinessInterface.bcam;

import java.rmi.RemoteException;
import java.util.Properties;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.sbc.bccs.utilities.Utility;
import com.sbc.billing.bcam.BCAM;
import com.sbc.billing.bcam.BCAMHome;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;

/**
 * BcamService provides an interface to the BCAM application.
 * @author gg2712
 */
public class BcamService
{
    public static final String BCAM_PROVIDER_URL = "BCAM_PROVIDER_URL";
    public static final String BCAM_HOME_NAME = "BCAM_HOME_NAME";
    
    private LIMBase aLimBase = null;
    private Utility aUtility = null;
    private String aProviderURL = null;
    private String aBcamHomeName = null;
    private BCAMHome aBcamHome = null;

    public BcamService()
    {
        aProviderURL = getProviderURL();
        aBcamHomeName = getBcamHomeName();
    }
    
    /**
     * A BcamService constructor.
     * @param limBase A LIMBase utility
     */
    public BcamService(LIMBase limBase)
    {
        super();
        aLimBase = limBase;
        aProviderURL = getProviderURL();
        aBcamHomeName = getBcamHomeName();
    }

    /**
     * Send the XML request to BCAM
     * @param xml The XML request to be sent to BCAM
     * @return The response XML 
     * @throws BcamServiceException Thrown if an error is encountered  
     */
    public String processXMLRequest(String xml)
    	throws BcamServiceException
    {
        try
        {
            aLimBase.log(LogEventId.REMOTE_CALL, "BcamService", "processXMLRequest", "BCAM", aProviderURL);
            
            String xmlResp = getBCAMBean().processXMLRequest(xml);
            
            aLimBase.log(LogEventId.REMOTE_RETURN, "BcamService", "processXMLRequest", "BCAM", aProviderURL);
            
            return xmlResp;
        }
        catch(Exception e)
        {
            throw new BcamServiceException(e.getMessage(), e);
        }
    }

    /**
     * Get a reference to a BCAM EJB
     * @return a reference to a BCAM EJB
     * @throws RemoteException
     * @throws CreateException
     * @throws NamingException
     */
    public final BCAM getBCAMBean()
        throws RemoteException, CreateException, NamingException
    {
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "BcamService::getBcamBean()");

        BCAM bcamBean = null;

        try
        {
            bcamBean = getBCAMHome();
        }
        catch(Exception e)
        {
            // BCAMHome may be stale - get a new one
            bcamBean = refreshBCAMHome();
        }
        return bcamBean;
    }
    
    /**
     * Set aBcamHome to null before calling getBcamHome().
     * @return BCAMHome
     */
    private final BCAM refreshBCAMHome()
    	throws NamingException
    {
        aBcamHome = null;
        return getBCAMHome();
    }
    
    /**
     * Get a BCAM home.
     * @return BCAMHome
     */
    private final BCAM getBCAMHome()
    	throws NamingException
    {
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "BcamService::getBCAMHome()");

        BCAM bcam = null;
        if (aBcamHome == null)
        {
            bcam = (BCAM) getInitialContext().lookup(aBcamHomeName);
            // TODO: Need this
//            aBcamHome = (BCAMHome) javax.rmi.PortableRemoteObject.narrow(o, BCAMHome.class);
        }
        return bcam;
    }
    
    /**
     * Get an initial context for JNDI lookup.
     * @return an initial context
     */
    private InitialContext getInitialContext() 
    	throws NamingException
    {
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "BcamService::getInitialContext()");
        
        Properties p = new Properties();
		p.put(Context.PROVIDER_URL, aProviderURL);
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		return new InitialContext(p);
    }
    
    /**
     * Get the BCAM Home name from the lim.properties file
     * @return the BCAM home name
     */
    public String getBcamHomeName()
    {
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "BcamService::getBCAMHomeName()");
        
        if (aBcamHomeName == null)
        {
            aBcamHomeName = (String) aLimBase.PROPERTIES.get(BCAM_HOME_NAME);
        }
        return aBcamHomeName;
    }
    
    /**
     * Get the BCAM provider url from the lim.properties file
     * Try to lookup BCAM_PROVIDER_URL_<hostname> from the lim.properties file.
     * If not found, lookup default BCAM_PROVIDER_URL from the lim.properties file.
     * @return the provider URL
     */
    public String getProviderURL()
    {
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "BcamService::getProviderURL()");
        
        if (aProviderURL == null)
        {
            try
            {
                String host = java.net.InetAddress.getLocalHost().getHostName();
                aLimBase.log(LogEventId.DEBUG_LEVEL_2, "Hostname is: " + host);
                
                aProviderURL = (String) aLimBase.PROPERTIES.get(BCAM_PROVIDER_URL + "_" + host.trim().toUpperCase());	
            }
            catch(Exception e)
            {
                aLimBase.log(LogEventId.DEBUG_LEVEL_2, "Failed to look up hostname! Will look up default BCAM_PROVIDER_URL instead.");
            }
            
            if (aProviderURL == null)
            {
                aLimBase.log(LogEventId.DEBUG_LEVEL_2, "Looking up default BCAM_PROVIDER_URL.");
                aProviderURL = (String) aLimBase.PROPERTIES.get(BCAM_PROVIDER_URL);	
            }
        }
        return aProviderURL;
    }
}
