package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;



import com.bellsouth.cbs.order.ws.CbsBasicAddressV7;
import com.bellsouth.cbs.order.ws.CbsCLLIV7;
import com.bellsouth.cbs.order.ws.CbsCircuitIDSerialNumberV7;
import com.bellsouth.cbs.order.ws.CbsCommunityV7;
import com.bellsouth.cbs.order.ws.CbsElevationV7;
import com.bellsouth.cbs.order.ws.CbsInstalledCircuitV7;
import com.bellsouth.cbs.order.ws.CbsInstalledTelephoneV7;
import com.bellsouth.cbs.order.ws.CbsLocationStandardV7;
import com.bellsouth.cbs.order.ws.CbsStructureV7;
import com.bellsouth.cbs.order.ws.CbsTelephoneNumberV7;
import com.bellsouth.cbs.order.ws.CbsUnitV7;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;

public class CbsBasicAddressHelper {
    private String aCrossBoundaryState = null;
    private AddressHandlerRSAG aAddressHandler = null;
    private CbsBasicAddressV7 aCbsBasicAddress = null;
    
    /**
     * Constructor: CbsBasicAddressHelper
     * @param addressHandler
     * @param crossBoundaryState
     */
    public CbsBasicAddressHelper(AddressHandlerRSAG addressHandler, String crossBoundaryState)
    {
        super();
        aAddressHandler = addressHandler;
        aCrossBoundaryState = crossBoundaryState;
    }
    
    /**
     * getCbsBasicAddress
     * @return CbsBasicAddressV7
     */
    public CbsBasicAddressV7 getCbsBasicAddress()
    {
        return buildCbsBasicAddress();
    }
    
    /**
     * buildCbsBasicAddress()
     * @return CbsBasicAddressV7
     */
    private CbsBasicAddressV7 buildCbsBasicAddress()
    {
        aCbsBasicAddress = new CbsBasicAddressV7();
        
        //HouseNumberPrefix + " " + HouseNumber + "-" +HouseNumberSuffix
        aCbsBasicAddress.setAddressNumber(aAddressHandler.getHouseNumberWithPrefixSuffix());
        
        //AssignedHouseNumber (Move some Assigned House Number rules to AddressHandlerRSAG)
        aCbsBasicAddress.setAssignedHouseNumber(aAddressHandler.getAssignedHouseNumber());   
        //Directional prefix + Street name + Thoroughfare + Directional suffix
        aCbsBasicAddress.setAddressLine(aAddressHandler.getRSAGAddresLine());
        //State
        aCbsBasicAddress.setState(aAddressHandler.getState());
        //Postal Code
        aCbsBasicAddress.setZipCode(aAddressHandler.getPostalCode());
        //County
        aCbsBasicAddress.setCounty(aAddressHandler.getCounty());
        //AdditionalInfo
        aCbsBasicAddress.setDescriptiveAddress(aAddressHandler.getAdditionalInfo());
        
        //Route
        aCbsBasicAddress.setRuralRoute(aAddressHandler.getRoute());
        //Box
        aCbsBasicAddress.setRuralBox(aAddressHandler.getBox());
           
        //City and CrossBoundaryStates
        aCbsBasicAddress.setCmnty(buildCbsCommunity());
        //Unit Type and Unit Value
        aCbsBasicAddress.setUnt(buildCbsUnit());
        //Structure Type and Structure Value
        aCbsBasicAddress.setStructr(buildCbsStructure());
        //Level Type and Level Value
        aCbsBasicAddress.setElevtn(buildCbsElevation());
        
        //TODO: check for default values to false ???
        aCbsBasicAddress.setUnusedAddress(false); 
        aCbsBasicAddress.setIsValidSearchInput(false);
        //Default the values to empty
        aCbsBasicAddress.setCity("");
        aCbsBasicAddress.setPostalCommunity("");
        aCbsBasicAddress.setOccupantName("");
        aCbsBasicAddress.setInternalUseFlag("");
 //       aCbsBasicAddress.setLocationStandards(new ArrayList<CbsLocationStandardV7>());
        aCbsBasicAddress.setPrimaryTN(buildCbsInstalledTelephone());
        aCbsBasicAddress.setPrimaryCircuit(buildCbsInstalledCircuit());
        
        return aCbsBasicAddress;
    }
    
    /** 
     * buildCbsCommunity
     * @return CbsCommunityV1
     */
    private CbsCommunityV7 buildCbsCommunity()
    {
        CbsCommunityV7 cbsCommunity = new CbsCommunityV7();
        cbsCommunity.setName(aAddressHandler.getCity());
        cbsCommunity.setAbbreviation("");
        if (aCrossBoundaryState != null)
        {
            cbsCommunity.setCrossBoundaryStates(aCrossBoundaryState);
        }
        else
        {
            cbsCommunity.setCrossBoundaryStates("");
        }
        return cbsCommunity;
    }
    
    /**
     * buildCbsUnit
     * @return CbsUnitV1
     */
    private CbsUnitV7 buildCbsUnit()
    {
        CbsUnitV7 cbsUnit = new CbsUnitV7();
        
        String unitType = aAddressHandler.getUnitType();
        
        if (unitType.equalsIgnoreCase("APT") 
            || unitType.equalsIgnoreCase("APARTMENT"))
        {
            cbsUnit.setUnitType(1);
        }
        else if(unitType.equalsIgnoreCase("LOT"))
        {
            cbsUnit.setUnitType(2);
        }
        else if(unitType.equalsIgnoreCase("RM")
                || unitType.equalsIgnoreCase("ROOM"))
        {
            cbsUnit.setUnitType(3);
        }
        else if(unitType.equalsIgnoreCase("SUIT")
                || unitType.equalsIgnoreCase("STE")
                || unitType.equalsIgnoreCase("SUITE"))
        {
            cbsUnit.setUnitType(4);;
        }
        else if(unitType.equalsIgnoreCase("SLIP"))
        {
            cbsUnit.setUnitType(5);;
        }
        else if(unitType.equalsIgnoreCase("UNIT")
                || unitType.equalsIgnoreCase("UNT"))
        {
            cbsUnit.setUnitType(6);;
        }
        else
        {
            cbsUnit.setUnitType(0);;
        }
        
        cbsUnit.setData(aAddressHandler.getUnitValue());
        cbsUnit.setMaxLineCountEver("");
        cbsUnit.setTypeDescription("");
        
        return cbsUnit;
    }

    /**
     * buildCbsStructure
     * @return CbsStructureV1
     */
    private CbsStructureV7 buildCbsStructure()
    {
    	CbsStructureV7 cbsStructure = new CbsStructureV7();
        
        String structureType = aAddressHandler.getStructType();
        
        if(structureType.equalsIgnoreCase("BLDG")
           || structureType.equalsIgnoreCase("BUILDING"))
        {
            cbsStructure.setStructureType(1);
        }
        else if(structureType.equalsIgnoreCase("PIER"))
        {
            cbsStructure.setStructureType(2);
        }
        else if(structureType.equalsIgnoreCase("WING")
                || structureType.equalsIgnoreCase("WNG"))
        {
            cbsStructure.setStructureType(3);
        }
        else
        {
            cbsStructure.setStructureType(0);
        }
        
        cbsStructure.setData(aAddressHandler.getStructValue());
        cbsStructure.setMaxLineCountEver("");
        cbsStructure.setTypeDescription("");
        
        return cbsStructure;
    }
    
    /**
     * buildCbsElevation
     * @return CbsElevationV1
     */
    private CbsElevationV7 buildCbsElevation()
    {
        CbsElevationV7 cbsElevation = new CbsElevationV7();
              
        String elevationType = aAddressHandler.getLevelType();
        
        if(elevationType.equalsIgnoreCase("FLR")
           || elevationType.equalsIgnoreCase("FL")
           || elevationType.equalsIgnoreCase("FLOOR"))
        {
            cbsElevation.setElevationType(1);
        }
        else
        {
            cbsElevation.setElevationType(0);
        }
        
        cbsElevation.setData(aAddressHandler.getLevelValue());
        cbsElevation.setMaxLineCountEver("");
        cbsElevation.setTypeDescription("");
        
        return cbsElevation;
    }
  
    /**
     * buildCbsInstalledTelephone
     * @return CbsInstalledTelephoneV1
     */
    private CbsInstalledTelephoneV7 buildCbsInstalledTelephone()
    {
        CbsInstalledTelephoneV7 cbsInstalledTelephone = new CbsInstalledTelephoneV7();
        cbsInstalledTelephone.setDisconnectReason("");
        cbsInstalledTelephone.setOccupantName("");
        cbsInstalledTelephone.setStatus("");
        cbsInstalledTelephone.setResellerCode("");
        cbsInstalledTelephone.setAccountNumber("");
        cbsInstalledTelephone.setDPA("");
        cbsInstalledTelephone.setSLA("");
        CbsTelephoneNumberV7 telNumV7 = new CbsTelephoneNumberV7();
        telNumV7.setLINE("");
        telNumV7.setNPA("");
        telNumV7.setNXX("");
        cbsInstalledTelephone.setTn(telNumV7);
        CbsCLLIV7 clliV7 = new CbsCLLIV7();
        clliV7.setCLLIFirst8("");
        clliV7.setCLLILast3("");
        cbsInstalledTelephone.setClli(clliV7);
        return cbsInstalledTelephone;
    }
    
    /**
     * buildCbsInstalledCircuit
     * @return CbsInstalledCircuitV1
     */
    private CbsInstalledCircuitV7 buildCbsInstalledCircuit()
    {
        CbsInstalledCircuitV7 cbsInstalledCircuit =  new CbsInstalledCircuitV7();
        cbsInstalledCircuit.setDisconnectReason("");
        cbsInstalledCircuit.setOccupantName("");
        cbsInstalledCircuit.setStatus("");
        cbsInstalledCircuit.setResellerCode("");
        cbsInstalledCircuit.setAccountNumber("");
        cbsInstalledCircuit.setCKL("");
        CbsCircuitIDSerialNumberV7 numV7 =  new CbsCircuitIDSerialNumberV7();
        numV7.setSerialNumber("");
        cbsInstalledCircuit.setCircuit(numV7);
        cbsInstalledCircuit.setIsActive(false);
        return cbsInstalledCircuit;
    }
}
