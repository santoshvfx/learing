/*
 * Created on Aug 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.idl.lim.helpers;

import java.util.Properties;

import com.sbc.eia.idl.types.LongSeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;

/**
 * @author sm4986
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *
 */
public class AddressHandlerInterconnect extends AddressHandler {

	private String aAddressLine1 = "";
	private String aAddressLine2 = "";

	//the Interconnect BI calls the constructor of AddressHandlerInterconnect to create the object
	//it also calls getAddressLine1, getAddressLine2, getCity, getState and getZipCode to get the data
	public AddressHandlerInterconnect(Address address) throws AddressHandlerException {
		super(address);
		setEquifaxAddressLines();
	}

	public String getAddressLine1() {
		return aAddressLine1;
	}
	
	public String getAddressLine2() {
		return aAddressLine2;
	}
	

	//this method is used/called by by the AddressHandlerInterconnect constructor
	//it assumes that addressline1/addressline2 are populated without "" or null,
	//as such condition is checked in the transaction level via static method
	//AddressHandlerInterconnect.checkEquifaxAddressLine(Address aAddress, int aLineNumber)
	private void setEquifaxAddressLines() {
		//unfielded address
		if (getAddress().discriminator().value() == AddressChoice._UNFIELDED_ADDRESS) {
			String[] lines = getAddress().aUnfieldedAddress().aAddressLines.theValue();
			aAddressLine1 = lines[0];
			if (lines.length > 1 && !lines[1].trim().equals(""))
				aAddressLine2 = lines[1];					
		}
		//fielded address
		else if (getAddress().discriminator().value() == AddressChoice._FIELDED_ADDRESS) {
			if(getAddress().aFieldedAddress().aCassAddressLines != null && getAddress().aFieldedAddress().aCassAddressLines.discriminator()
				&& getAddress().aFieldedAddress().aCassAddressLines.theValue() != null) {
				String[] lines = getAddress().aFieldedAddress().aCassAddressLines.theValue();
				//with cass address lines input
				aAddressLine1 = lines[0];
				if (lines.length > 1 && !lines[1].trim().equals(""))
					aAddressLine2 = lines[1];
			}
			else {
				aAddressLine1 = getEquifaxAddressLine1_Indv_Fields();
				aAddressLine2 = getEquifaxAddressLine2_Indv_Fields();	
			}
		}		
	}
			

	//used for fielded address for address line 1
	private String getEquifaxAddressLine1_Indv_Fields() {
		StringBuffer tempString = new StringBuffer();
		tempString.append("");
		if (!aRoute.trim().equals("")) 
			tempString.append(aRoute.trim() + " ");
		if (!aBox.trim().equals("")) 
			tempString.append(aBox.trim() + " ");
		if (!aHouseNumberPrefix.trim().equals("")) 
			tempString.append(aHouseNumberPrefix.trim() + " ");
		if (!aHouseNumber.trim().equals("")) 
			tempString.append(aHouseNumber.trim() + " ");
		if (!aAssignedHouseNumber.trim().equals("")) 
			tempString.append(aAssignedHouseNumber.trim() + " ");
		if (!aHouseNumberSuffix.trim().equals("")) 
			tempString.append(aHouseNumberSuffix.trim() + " ");
		if (!aStreetName.trim().equals("")) 
			tempString.append(aStreetName.trim());
		
		return tempString.toString().trim();
	}
	

	//used for fielded address for address line 2
	private String getEquifaxAddressLine2_Indv_Fields() {
		StringBuffer tempString = new StringBuffer();
		tempString.append("");
		if (!m_structType.trim().equals("")) 
			tempString.append(m_structType.trim() + " ");
		if (!m_structValue.trim().equals("")) 
			tempString.append(m_structValue.trim() + " ");
		if (!m_levelType.trim().equals("")) 
			tempString.append(m_levelType.trim() + " ");
		if (!m_levelValue.trim().equals("")) 
			tempString.append(m_levelValue.trim() + " ");
		if (!m_unitType.trim().equals("")) 
			tempString.append(m_unitType.trim() + " ");
		if (!m_unitValue.trim().equals("")) 
			tempString.append(m_unitValue.trim());
				
		return tempString.toString().trim();
	}
	
	
	//used by the submitCreditApplicationTrans and updateCreditApplicationTrans classes
	public static boolean checkEquifaxAddressLine1(Address aAddress) {
		if (aAddress!=null && aAddress.discriminator().value() == AddressChoice._UNFIELDED_ADDRESS) 
			return notNullAndElementNotEmpty(aAddress.aUnfieldedAddress().aAddressLines);	
		else if (aAddress!=null && aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS) 
			return (notNullAndElementNotEmpty(aAddress.aFieldedAddress().aCassAddressLines)
					|| checkEquifaxAddressLine1_Indv_Fields(aAddress.aFieldedAddress()));
							
		return false;				
	}	
	
	
	public static boolean checkEquifaxAddressLine1_Indv_Fields(FieldedAddress aFieldedAddress) {
		return (notNullOrEmpty(aFieldedAddress.aRoute)
				|| notNullOrEmpty(aFieldedAddress.aBox)
				|| notNullOrEmpty(aFieldedAddress.aHouseNumberPrefix)
				|| notNullOrEmpty(aFieldedAddress.aHouseNumber)
				|| notNullOrEmpty(aFieldedAddress.aAssignedHouseNumber)
				|| notNullOrEmpty(aFieldedAddress.aHouseNumberSuffix)
				|| notNullOrEmpty(aFieldedAddress.aStreetName));
	}
	
	
	//this method is used in the submitCreditApplicationTrans and updateCreditApplicationTrans classes
	//to check if the city is valid
	//for example: AddressHandlerInterconnect.checkEquifaxCity(address)
	public static boolean checkEquifaxCity(Address aAddress) {		
		return (aAddress!=null && 
				((aAddress.discriminator().value() == AddressChoice._UNFIELDED_ADDRESS
				&& notNullOrEmpty(aAddress.aUnfieldedAddress().aCity))
				|| (aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS
					&& notNullOrEmpty(aAddress.aFieldedAddress().aCity))));
	
	}
	
	//this method is used in the submitCreditApplicationTrans and updateCreditApplicationTrans classes
	//to check if the state is valid
	//for example: AddressHandlerInterconnect.checkEquifaxState(address)
	public static boolean checkEquifaxState(Address aAddress) {	
		return (aAddress!=null && 
				((aAddress.discriminator().value() == AddressChoice._UNFIELDED_ADDRESS
				&& notNullOrEmpty(aAddress.aUnfieldedAddress().aState))
				|| (aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS
					&& notNullOrEmpty(aAddress.aFieldedAddress().aState))));
	
	}
	
	//this method is used in the submitCreditApplicationTrans and updateCreditApplicationTrans classes
	//to check if the zip is valid
	//for example: AddressHandlerInterconnect.checkEquifaxZipCode(address)
	public static boolean checkEquifaxZipCode(Address aAddress) {
		return (aAddress!=null && 
				((aAddress.discriminator().value() == AddressChoice._UNFIELDED_ADDRESS
				&& notNullOrEmpty(aAddress.aUnfieldedAddress().aPostalCode))
				|| (aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS
					&& notNullOrEmpty(aAddress.aFieldedAddress().aPostalCode))));
	}


	public static boolean notNullOrEmpty(StringOpt aStringOpt) {
		return (aStringOpt!= null && aStringOpt.discriminator() && !aStringOpt.theValue().trim().equals(""));

	}
	
	public static boolean notNullAndElementNotEmpty(StringSeqOpt aStringSeqOpt) {
		return (aStringSeqOpt!= null 
				&& aStringSeqOpt.discriminator() 
				&& aStringSeqOpt.theValue()!=null 
				&& aStringSeqOpt.theValue().length > 0
				&& aStringSeqOpt.theValue()[0] != null
				&& !aStringSeqOpt.theValue()[0].trim().equals(""));
	}
}

