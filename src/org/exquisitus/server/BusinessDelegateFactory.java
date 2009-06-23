package org.exquisitus.server;

import java.util.logging.Logger;

public class BusinessDelegateFactory {
	
	private static final Logger log = Logger.getLogger(BusinessDelegateFactory.class.getName());
	
	// uses spring and iniect the right ServiceLocator vendor-specific instance!
	private static ServiceLocator serviceLocator = ServiceLocator.getInstance();

	// return an instance of Business Delegate with serviceLocator constructor-iniected
	public static BusinessDelegate getBusinessDelegate() {
	
		return new BusinessDelegate(serviceLocator);
	}

}
