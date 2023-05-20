//$Id: RsagController.java,v 1.11 2008/10/17 18:08:17 jh9785 Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import java.util.Properties;

import com.bellsouth.cbs.order.ws.CbsOrderAddressResponseV7;
import com.bellsouth.cbs.order.ws.CbsTelephoneNumberV7;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;
import com.sbc.eia.idl.types.Severity;

/**
 * @author gg2712
 */
public class RsagController
{
    private LIMBase aLimBase = null;
    private CbsService aCbsService = null;
    private RsagResponseFactory aRsagResponseFactory = null;
  
    /**
     * Constructor: CbsController
     * @param limBase
     */
    public RsagController(LIMBase limBase)
    {
        aLimBase = limBase;
    }
    
    /**
     * validateAddressByAddress
     * @param retrieveLocReq
     * @return
     */
    public RetrieveLocResp validateAddressByAddress(RsagRetrieveLocReq retrieveLocReq)
	    throws 
	    	InvalidData,
		    AccessDenied,
		    BusinessViolation,
		    DataNotFound,
		    SystemFailure,
		    NotImplemented,
		    ObjectNotFound
    {
        RetrieveLocResp retrieveLocResp = null;

        try
        {
            CbsBasicAddressHelper cbsBasicAddressHelper = 
                new CbsBasicAddressHelper((AddressHandlerRSAG) retrieveLocReq.getAddr(), retrieveLocReq.getCrossBoundaryState());
            CbsOrderAddressResponseV7 cbsOrderAddressResponse =
                getCbsService().locateAddressByAddress(cbsBasicAddressHelper.getCbsBasicAddress());
            retrieveLocResp = getRsagResponseFactory().getRetrieveLocationResponseForAddress(retrieveLocReq, cbsOrderAddressResponse);
        }
        catch(CbsServiceException e)
        {
            throwSystemFailure(e);
        }
        //If aMaxBasicAddressAlternatives is set to EXACT_MATCH_REQ (-1), request is for exactMatch only response
        if (!(retrieveLocResp instanceof RsagHitResp) &&
            (retrieveLocReq.getMaxBasicAddressAlternatives() == LIMTag.EXACT_MATCH_REQ))
        {
            ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    getLimBase().getLimRulesFile(),
                    "",
                    LIMTag.CSV_AddressError,
                    null,
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "RSAGController",
                    Severity.UnRecoverable,
                    null);
        }
        
        return retrieveLocResp;
    }

    /**
     * validateAddressByTN
     * @param retrieveLocReq
     * @return
     */
    public RetrieveLocResp validateAddressByTN(RsagRetrieveLocReq retrieveLocReq)
	    throws 
			InvalidData,
		    AccessDenied,
		    BusinessViolation,
		    DataNotFound,
		    SystemFailure,
		    NotImplemented,
		    ObjectNotFound
    {
        RetrieveLocResp retrieveLocResp = null;
        try
        {
            TN tn = retrieveLocReq.getTn();
            CbsTelephoneNumberV7 cbsTN = new CbsTelephoneNumberV7();
            cbsTN.setNPA(tn.getNpa());
            cbsTN.setNXX(tn.getNxx());
            cbsTN.setLINE(tn.getLine());
            CbsOrderAddressResponseV7 cbsOrderAddressResponse = getCbsService().locateAddressByTN(cbsTN);
            retrieveLocResp = getRsagResponseFactory().getRetrieveLocationResponseForTN(retrieveLocReq, cbsOrderAddressResponse);
        }
        catch(CbsServiceException e)
        {
            throwSystemFailure(e);
        }
        return retrieveLocResp;
    }
    
    /**
     * throwSystemFailure
     * @param e
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    private void throwSystemFailure(CbsServiceException e)
    	throws
		    InvalidData,
		    AccessDenied,
		    BusinessViolation,
		    DataNotFound,
		    SystemFailure,
		    NotImplemented,
		    ObjectNotFound
    {
        Properties p = new Properties();
        p.setProperty("MSG", e.getMessage());
        
        ExceptionBuilder.parseException(
            getLimBase().getCallerContext(),
            getLimBase().getRsagRulesFile(),
            "",
            LIMTag.CSV_InternalError,
            "Catalyst Error",
            true,
            ExceptionBuilderRule.NO_DEFAULT,
            null,
            e.getOriginalException(),
            getLimBase(),
            "RSAGController",
            Severity.UnRecoverable,
            p);        
    }
    
    /**
     * getService
     * @return CbsService
     */
    private CbsService getCbsService()
    {
        if(aCbsService == null)
        {
            aCbsService = new CbsService(aLimBase);
        }
        return aCbsService;
    }
    
    /**
     * getRsagResponseFactory
     * @return RsagResponseFactory
     */
    private RsagResponseFactory getRsagResponseFactory()
    {
        if(aRsagResponseFactory == null)
        {
            aRsagResponseFactory = new RsagResponseFactory(aLimBase);
        }
        return aRsagResponseFactory;
    }
    
    /**
     * @return Returns the aLimBase.
     */
    public LIMBase getLimBase()
    {
        return aLimBase;
    }
}
