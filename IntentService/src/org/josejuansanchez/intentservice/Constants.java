package org.josejuansanchez.intentservice;

public class Constants {
    // Defines a custom Intent action
    public static final String LONG_OPERATION_ACTION = "org.josejuansanchez.intentservice.LONG_OPERATION_ACTION";
    
    // Defines the key for the status "extra" in an Intent
    public static final String STATUS = "org.josejuansanchez.intentservice.STATUS";    
	
    // Status values to broadcast to the Activity	
	public static final int STATE_LONG_OPERATION_STARTED = 0;
	public static final int STATE_LONG_OPERATION_COMPLETED = 1;
}