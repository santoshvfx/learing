// $Id: ServiceDetails.java,v 1.3 2003/02/14 17:23:54 as5472 Exp $

package com.sbc.gwsvcs.access.vicuna.multiplexer;

/**
 * This class encaspuslates the information used to connect to a service.
 * Creation date: (6/29/01 1:30:55 PM)
 * @author: Creighton Malet
 */
public class ServiceDetails {
	private String serviceName = null;
	private String applData = null;
	/**
	 * ServiceDetails default constructor.
	 */
	public ServiceDetails() {
		super();
	}
	/**
	 * ServiceDetails constructor comment.
	 */
	public ServiceDetails(String aServiceName, String anApplData) {
		serviceName = aServiceName;
		applData = anApplData;
	}
	/**
	 * Checks the equality of the contents of this service detail against another.
	 * @param aServiceDetails ServiceDetails
	 * @return boolean
	 */
	public boolean equals(ServiceDetails aServiceDetails) {
		if ((this.applData == null && aServiceDetails.applData == null)
			|| (this.applData != null
				&& aServiceDetails.applData != null
				&& this.applData.equals(aServiceDetails.applData))
			&& ((this.serviceName == null && aServiceDetails.serviceName == null)
				|| (this.serviceName == null
					&& aServiceDetails.serviceName == null
					&& this.serviceName.equals(aServiceDetails.serviceName))))
			return true;

		return false;
	}
	/**
	 * Gets the applData.
	 * Creation date: (6/29/01 1:32:28 PM)
	 * @return String
	 */
	public String getApplData() {
		return applData;
	}
	/**
	 * Gets the serviceName.
	 * Creation date: (6/29/01 1:32:28 PM)
	 * @return String
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * Sets the applData.
	 * Creation date: (6/29/01 1:32:28 PM)
	 * @param newApplData String
	 */
	public void setApplData(String newApplData) {
		applData = newApplData;
	}
	/**
	 * Sets the serviceName.
	 * Creation date: (6/29/01 1:32:28 PM)
	 * @param newServiceName String
	 */
	public void setServiceName(String newServiceName) {
		serviceName = newServiceName;
	}
}
