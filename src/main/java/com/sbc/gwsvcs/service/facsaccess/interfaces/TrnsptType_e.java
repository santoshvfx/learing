package com.sbc.gwsvcs.service.facsaccess.interfaces;
public final class TrnsptType_e implements org.omg.CORBA.portable.IDLEntity { 
	private int enum_value;

	protected TrnsptType_e(int i) { 
		enum_value = i; 
	}
	public static final int _FILE_TRNSPT = 0;
	public static final TrnsptType_e FILE_TRNSPT = 
		new TrnsptType_e (_FILE_TRNSPT); 

	public static final int _QUE_TRNSPT = 1;
	public static final TrnsptType_e QUE_TRNSPT = 
		new TrnsptType_e (_QUE_TRNSPT); 

	public static final int _RPC_TRNSPT = 2;
	public static final TrnsptType_e RPC_TRNSPT = 
		new TrnsptType_e (_RPC_TRNSPT); 

	public int value() { 
		return enum_value; 
	}
	public static TrnsptType_e from_int(int value) throws org.omg.CORBA.BAD_PARAM { 
		switch (value) {
		case 0: 
			return FILE_TRNSPT; 
		case 1: 
			return QUE_TRNSPT; 
		case 2: 
			return RPC_TRNSPT; 
		}
		throw new org.omg.CORBA.BAD_PARAM("No Enumeration value for int", 0, org.omg.CORBA.CompletionStatus.COMPLETED_NO); 
	} 
}
