// $Id: FieldedAddressList.java,v 1.5.2.1 2009/09/16 22:32:13 jv7958 Exp $

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;
/**
 * FieldedAddressList defines constants associated with address.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */

public class FieldedAddressList 
{
	private static ArrayList unit_name_arraylist = null;
	private static ArrayList level_name_arraylist = null;
	private static ArrayList structure_name_arraylist = null;
	private static ArrayList street_thoroughfare_arraylist = null;
	private static ArrayList street_number_suffix_arraylist = null;
	private static ArrayList street_direction_suffix_arraylist = null;

	/*
	 * In the future: Initialization values should come from
	 * a file or some other external source.
	 */	 
	/* initializer for unit_name_arraylist */
	static {
		unit_name_arraylist = new ArrayList();
		
		unit_name_arraylist.add("APT");
		unit_name_arraylist.add("DRW");
		unit_name_arraylist.add("LOT");
		unit_name_arraylist.add("RM");
		unit_name_arraylist.add("SLIP");
		unit_name_arraylist.add("SUIT");
		unit_name_arraylist.add("UNIT");
		unit_name_arraylist.add("STE");
	}
	
	/* initializer for leve_name_arraylist */
	static {
		level_name_arraylist = new ArrayList();
		
		level_name_arraylist.add("FLR");
		level_name_arraylist.add("FL");
	}

	/* initializer for structure_name_arraylist */
	static {
		structure_name_arraylist = new ArrayList();

		structure_name_arraylist.add("BLDG");
		structure_name_arraylist.add("WING");
		structure_name_arraylist.add("WNG");
		structure_name_arraylist.add("PIER");
		structure_name_arraylist.add("TRLR");
	}
	
	/* initializer for street_thoroughfare_arraylist */
	static {
		street_thoroughfare_arraylist = new ArrayList();

		street_thoroughfare_arraylist.add("ARFLD");
		street_thoroughfare_arraylist.add("ARPT");
		street_thoroughfare_arraylist.add("ALY");
		street_thoroughfare_arraylist.add("ANX");
		//street_thoroughfare_arraylist.add("APT");
		street_thoroughfare_arraylist.add("ARCH");
		street_thoroughfare_arraylist.add("ARC");
		street_thoroughfare_arraylist.add("AV");
		street_thoroughfare_arraylist.add("AVE");

		street_thoroughfare_arraylist.add("BNK");
		street_thoroughfare_arraylist.add("BAY");
		//street_thoroughfare_arraylist.add("BLDG");
		street_thoroughfare_arraylist.add("BLFS");
		street_thoroughfare_arraylist.add("BRRKS");
		street_thoroughfare_arraylist.add("BS");
		street_thoroughfare_arraylist.add("BCH");
		street_thoroughfare_arraylist.add("BND");
		street_thoroughfare_arraylist.add("BLK");
		street_thoroughfare_arraylist.add("BLF");
		street_thoroughfare_arraylist.add("BDWK");
		street_thoroughfare_arraylist.add("BL");
		street_thoroughfare_arraylist.add("BLVD");
		street_thoroughfare_arraylist.add("BR");
		street_thoroughfare_arraylist.add("BRK");
		street_thoroughfare_arraylist.add("BG");
		street_thoroughfare_arraylist.add("BRDG");
		street_thoroughfare_arraylist.add("BRGS");
		street_thoroughfare_arraylist.add("BRKS");
		street_thoroughfare_arraylist.add("BYP");
		street_thoroughfare_arraylist.add("BYU");
						
		street_thoroughfare_arraylist.add("CYN");
		street_thoroughfare_arraylist.add("CNYN");
		street_thoroughfare_arraylist.add("CAP");
		street_thoroughfare_arraylist.add("CSWY");
		street_thoroughfare_arraylist.add("CTR");
		street_thoroughfare_arraylist.add("CNTR");
		street_thoroughfare_arraylist.add("CIR");
		street_thoroughfare_arraylist.add("CP");
		street_thoroughfare_arraylist.add("CR");
		street_thoroughfare_arraylist.add("CTY");
		street_thoroughfare_arraylist.add("CLS");
		street_thoroughfare_arraylist.add("CLG");
		street_thoroughfare_arraylist.add("CLNY");
		street_thoroughfare_arraylist.add("CMMN");
		street_thoroughfare_arraylist.add("CONC");
		street_thoroughfare_arraylist.add("COR");
		street_thoroughfare_arraylist.add("CORS");
		street_thoroughfare_arraylist.add("CTG");
		street_thoroughfare_arraylist.add("CT");
		street_thoroughfare_arraylist.add("CTHSE");
		street_thoroughfare_arraylist.add("CV");
		street_thoroughfare_arraylist.add("CREEK");
		street_thoroughfare_arraylist.add("CRK");
		street_thoroughfare_arraylist.add("CRES");
		street_thoroughfare_arraylist.add("CRST");
		street_thoroughfare_arraylist.add("CRS");
		street_thoroughfare_arraylist.add("CRSG");
		street_thoroughfare_arraylist.add("CRSNG");
		street_thoroughfare_arraylist.add("CRSRD");
		street_thoroughfare_arraylist.add("CRSWY");
		street_thoroughfare_arraylist.add("CURV");
		street_thoroughfare_arraylist.add("CO");
		street_thoroughfare_arraylist.add("CTRS");
		street_thoroughfare_arraylist.add("CTS");
		street_thoroughfare_arraylist.add("CVS");
		// street_thoroughfare_arraylist.add("CALLE");
		// street_thoroughfare_arraylist.add("CLL");
		// street_thoroughfare_arraylist.add("CAMINITO");
		// street_thoroughfare_arraylist.add("CMT");
		// street_thoroughfare_arraylist.add("CAM");
		// street_thoroughfare_arraylist.add("CAMINO");
		// street_thoroughfare_arraylist.add("CER");
		// street_thoroughfare_arraylist.add("CERRADA");	
		// street_thoroughfare_arraylist.add("CIRCULO");	

		street_thoroughfare_arraylist.add("DL");
		street_thoroughfare_arraylist.add("DEP");
		street_thoroughfare_arraylist.add("DIST");
		street_thoroughfare_arraylist.add("DR");

		street_thoroughfare_arraylist.add("EAS");
		street_thoroughfare_arraylist.add("ESPLAND");
		street_thoroughfare_arraylist.add("ESPLND");
		street_thoroughfare_arraylist.add("EST");
		street_thoroughfare_arraylist.add("ESTS");
		street_thoroughfare_arraylist.add("EXPY");
		street_thoroughfare_arraylist.add("EXPWY");
		street_thoroughfare_arraylist.add("EXT");
		street_thoroughfare_arraylist.add("EXTN");
		// street_thoroughfare_arraylist.add("ENT");
		// street_thoroughfare_arraylist.add("ENTRADA");

		street_thoroughfare_arraylist.add("FALL");
		street_thoroughfare_arraylist.add("FLS");
		street_thoroughfare_arraylist.add("FRM");
		street_thoroughfare_arraylist.add("FRMS");
		street_thoroughfare_arraylist.add("FRY");
		street_thoroughfare_arraylist.add("FLD");
		street_thoroughfare_arraylist.add("FLTS");
		street_thoroughfare_arraylist.add("FT");
		street_thoroughfare_arraylist.add("FRWAY");
		street_thoroughfare_arraylist.add("FRWY");
		street_thoroughfare_arraylist.add("FWY");

		street_thoroughfare_arraylist.add("GRDN");
		street_thoroughfare_arraylist.add("GRDNS");
		street_thoroughfare_arraylist.add("GATE");
		street_thoroughfare_arraylist.add("GLN");
		street_thoroughfare_arraylist.add("GRN");
		street_thoroughfare_arraylist.add("GRV");
		street_thoroughfare_arraylist.add("GRVS");

		//street_thoroughfare_arraylist.add("HALL");
		street_thoroughfare_arraylist.add("HRBR");
		street_thoroughfare_arraylist.add("HD");
		street_thoroughfare_arraylist.add("HVN");
		street_thoroughfare_arraylist.add("HTS");
		street_thoroughfare_arraylist.add("HGTS");
		street_thoroughfare_arraylist.add("HOLW");
		street_thoroughfare_arraylist.add("HWY");
		street_thoroughfare_arraylist.add("HL");
		street_thoroughfare_arraylist.add("HLS");
		street_thoroughfare_arraylist.add("HLSD");
		street_thoroughfare_arraylist.add("HLW");
		street_thoroughfare_arraylist.add("HSE");
		street_thoroughfare_arraylist.add("HT");

		street_thoroughfare_arraylist.add("ISL");
		street_thoroughfare_arraylist.add("ISLE");
		
		street_thoroughfare_arraylist.add("JCTN");
		street_thoroughfare_arraylist.add("JUT");
		street_thoroughfare_arraylist.add("JCTS");
		//street_thoroughfare_arraylist.add("JCT");

		street_thoroughfare_arraylist.add("KNL");
		street_thoroughfare_arraylist.add("KNLS");

		street_thoroughfare_arraylist.add("LK");
		street_thoroughfare_arraylist.add("LND");
		street_thoroughfare_arraylist.add("LNDG");
		street_thoroughfare_arraylist.add("LN");
		street_thoroughfare_arraylist.add("LA");
		street_thoroughfare_arraylist.add("LNK");
		street_thoroughfare_arraylist.add("LNS");
		street_thoroughfare_arraylist.add("LP");
		street_thoroughfare_arraylist.add("LOT");
		street_thoroughfare_arraylist.add("LOW");
		// street_thoroughfare_arraylist.add("LANE");

		street_thoroughfare_arraylist.add("ML");
		street_thoroughfare_arraylist.add("MNR");
		street_thoroughfare_arraylist.add("MKT");
		street_thoroughfare_arraylist.add("MRSH");
		street_thoroughfare_arraylist.add("MDW");
		street_thoroughfare_arraylist.add("MDWS");
		street_thoroughfare_arraylist.add("MEW");
		street_thoroughfare_arraylist.add("MLS");
		street_thoroughfare_arraylist.add("MT");
		street_thoroughfare_arraylist.add("MTN");

		street_thoroughfare_arraylist.add("NR");
		street_thoroughfare_arraylist.add("NK");
		street_thoroughfare_arraylist.add("NTCH");

		street_thoroughfare_arraylist.add("OKS");
		street_thoroughfare_arraylist.add("ORCH");
		street_thoroughfare_arraylist.add("ORCHD");
		street_thoroughfare_arraylist.add("OV");
		
		street_thoroughfare_arraylist.add("PK");
		street_thoroughfare_arraylist.add("PKS");
		street_thoroughfare_arraylist.add("PKWY");
		street_thoroughfare_arraylist.add("PS");
		street_thoroughfare_arraylist.add("PA");
		street_thoroughfare_arraylist.add("PTH");
		street_thoroughfare_arraylist.add("PR");
		street_thoroughfare_arraylist.add("PKE");
		street_thoroughfare_arraylist.add("PL");
		street_thoroughfare_arraylist.add("PLN");
		street_thoroughfare_arraylist.add("PLNS");
		street_thoroughfare_arraylist.add("PLZ");
		street_thoroughfare_arraylist.add("PT");
		street_thoroughfare_arraylist.add("PD");
		street_thoroughfare_arraylist.add("PND");
		street_thoroughfare_arraylist.add("PROM");
		street_thoroughfare_arraylist.add("PTS");
		// street_thoroughfare_arraylist.add("PARK");
		// street_thoroughfare_arraylist.add("PKY");
		// street_thoroughfare_arraylist.add("PASEO");
		// street_thoroughfare_arraylist.add("PSO");
		// street_thoroughfare_arraylist.add("PLA");
		// street_thoroughfare_arraylist.add("PLACITA");

		street_thoroughfare_arraylist.add("QRTR");
		street_thoroughfare_arraylist.add("QRTRS");

		street_thoroughfare_arraylist.add("RR");
		street_thoroughfare_arraylist.add("RY");
		street_thoroughfare_arraylist.add("RL");
		street_thoroughfare_arraylist.add("RF");
		street_thoroughfare_arraylist.add("RSRVR");
		street_thoroughfare_arraylist.add("RDG");
		street_thoroughfare_arraylist.add("RDGS");
		street_thoroughfare_arraylist.add("RDS");
		street_thoroughfare_arraylist.add("RVR");
		street_thoroughfare_arraylist.add("RD");
		street_thoroughfare_arraylist.add("RDWY");
		street_thoroughfare_arraylist.add("RCK");
		street_thoroughfare_arraylist.add("RT");
		street_thoroughfare_arraylist.add("RTE");
		street_thoroughfare_arraylist.add("ROW");
		street_thoroughfare_arraylist.add("RUE");
		street_thoroughfare_arraylist.add("RFD");
		street_thoroughfare_arraylist.add("RUN");
		// street_thoroughfare_arraylist.add("RANCHO");
		// street_thoroughfare_arraylist.add("RCH");
		// street_thoroughfare_arraylist.add("REAL");

		street_thoroughfare_arraylist.add("SH");
		street_thoroughfare_arraylist.add("SHR");
		street_thoroughfare_arraylist.add("SHRS");
		street_thoroughfare_arraylist.add("SHWY");
		street_thoroughfare_arraylist.add("SP");
		street_thoroughfare_arraylist.add("SPG");
		street_thoroughfare_arraylist.add("SPGS");
		street_thoroughfare_arraylist.add("SQ");
		street_thoroughfare_arraylist.add("SQS");
		street_thoroughfare_arraylist.add("STA");
		street_thoroughfare_arraylist.add("ST");
		street_thoroughfare_arraylist.add("STS");
		// street_thoroughfare_arraylist.add("SANTA");
		// street_thoroughfare_arraylist.add("SQ");	
			
		street_thoroughfare_arraylist.add("TERM");
		street_thoroughfare_arraylist.add("TERR");
		street_thoroughfare_arraylist.add("TER");
		street_thoroughfare_arraylist.add("TRK");
		street_thoroughfare_arraylist.add("TWR");
		street_thoroughfare_arraylist.add("THRWY");
		street_thoroughfare_arraylist.add("TWN");
		street_thoroughfare_arraylist.add("TRFWY");
		street_thoroughfare_arraylist.add("TR");
		street_thoroughfare_arraylist.add("TRS");
		street_thoroughfare_arraylist.add("TRL");
		street_thoroughfare_arraylist.add("TRLR");
		street_thoroughfare_arraylist.add("TRLS");
		street_thoroughfare_arraylist.add("TUN");
		street_thoroughfare_arraylist.add("TRN");
		street_thoroughfare_arraylist.add("TRNPK");
		street_thoroughfare_arraylist.add("TNPK");
		street_thoroughfare_arraylist.add("TPKE");

		street_thoroughfare_arraylist.add("UN");
		street_thoroughfare_arraylist.add("UP");

		street_thoroughfare_arraylist.add("VAL");
		street_thoroughfare_arraylist.add("VLY");
		street_thoroughfare_arraylist.add("VLYS");
		street_thoroughfare_arraylist.add("VLLY");
		street_thoroughfare_arraylist.add("VLLYS");
		street_thoroughfare_arraylist.add("VDCT");
		street_thoroughfare_arraylist.add("VW");
		street_thoroughfare_arraylist.add("VWS");
		street_thoroughfare_arraylist.add("VLG");
		// street_thoroughfare_arraylist.add("VER");
		// street_thoroughfare_arraylist.add("VEREDA");
		// street_thoroughfare_arraylist.add("VIS");
		// street_thoroughfare_arraylist.add("VISTA");

		street_thoroughfare_arraylist.add("WK");
		street_thoroughfare_arraylist.add("WALK");
		street_thoroughfare_arraylist.add("WLK");
		street_thoroughfare_arraylist.add("WHSE");
		street_thoroughfare_arraylist.add("WAY");
		street_thoroughfare_arraylist.add("WY");
		street_thoroughfare_arraylist.add("WHF");
		street_thoroughfare_arraylist.add("WHRF");
		street_thoroughfare_arraylist.add("WD");

		street_thoroughfare_arraylist.add("YD");

	}

	/* initializer for street_number_suffix_arraylist */
	static {
		street_number_suffix_arraylist = new ArrayList();

		street_number_suffix_arraylist.add("1/2");
		street_number_suffix_arraylist.add("1/3");
		street_number_suffix_arraylist.add("1/4");
	}

	/* initializer for street_direction_suffix_arraylist */
	static {
		street_direction_suffix_arraylist = new ArrayList();

		street_direction_suffix_arraylist.add("E");
		street_direction_suffix_arraylist.add("W");
		street_direction_suffix_arraylist.add("N");
		street_direction_suffix_arraylist.add("S");

		street_direction_suffix_arraylist.add("NE");
		street_direction_suffix_arraylist.add("NW");
		street_direction_suffix_arraylist.add("SE");
		street_direction_suffix_arraylist.add("SW");
	}
/**
 * returns a ArrayList with a list of allowed Level Names.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return ArrayList
 */

 public final static ArrayList getLevelNameList() 
{
	/*
	 * level_name_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return level_name_arraylist;
}
/**
 * returns a ArrayList with a list of allowed Street Direction/Suffix Names.
 * Creation date: (5/3/01 12:00:00 PM)
 * @return ArrayList
 */

public final static ArrayList getStreetDirSufList() {
	/*
	 * street_direction_suffix_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return street_direction_suffix_arraylist;
}
/**
 * returns a ArrayList with a list of allowed Street Number Suffix Names.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return ArrayList
 */

public final static ArrayList getStreetNumberSuffixList() {
	/*
	 * street_number_suffix_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return street_number_suffix_arraylist;
}
/**
 * returns a ArrayList with a list of allowed Strret Thoroughfare Names.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return ArrayList
 */

public final static ArrayList getStreetThoroughfareList() {
	/*
	 * street_thoroughfare_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return street_thoroughfare_arraylist;
}
/**
 * returns a ArrayList with a list of allowed Structure Names.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return ArrayList
 */

public final static ArrayList getStructureNameList() 
{
	/*
	 * structure_name_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return structure_name_arraylist;
}
/**
 * returns a ArrayList with a list of allowed Unit Names.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return ArrayList
 */

public final static ArrayList getUnitNameList() 
{
	/*
	 * unit_name_arraylist is now a class variable and is
	 * initialized through static initializers when the class
	 * is first initialized
	 */
	return unit_name_arraylist;
}
}
