package com.sbc.eia.bis.embus.service.access;

/**
 * @author GG2712
 * This is a wrapper class for com.sbc.embus.jxclient.Receipt
 */
public class Receipt {
	
	private com.sbc.embus.jxclient.Receipt theValue;	

	public Receipt(com.sbc.embus.jxclient.Receipt receipt)
	{
		theValue = receipt;
	}
	
	protected com.sbc.embus.jxclient.Receipt getValue()
	{
		return theValue;
	}
	
	protected void setValue(com.sbc.embus.jxclient.Receipt receipt)
	{
		theValue = receipt;
	}
}
