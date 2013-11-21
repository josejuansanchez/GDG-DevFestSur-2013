package org.josejuansanchez.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
	
	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {		
		Log.d(getClass().getSimpleName(), "onHandleIntent");		

		// Send a broadcast to inform the activity
		broadcastIntentWithState(Constants.STATE_LONG_OPERATION_STARTED);
		
		SimulateALongOperation();		
				
		// Send a broadcast to inform the activity
		broadcastIntentWithState(Constants.STATE_LONG_OPERATION_COMPLETED);
	}
	
	/**
	 * 	Simulate taking some time to perform a task 
	 */
	private void SimulateALongOperation() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
    /**
    *
    * Send an Intent containing status.
    * The Intent has the action LONG_OPERATION_ACTION.
    *
    * status: denoting a work request status
    */
   public void broadcastIntentWithState(int status) {
       Intent broadcastIntent = new Intent();

       // The Intent contains the custom broadcast action for this app
       broadcastIntent.setAction(Constants.LONG_OPERATION_ACTION);       
       
       // Puts the status into the Intent
       broadcastIntent.putExtra(Constants.STATUS, status);

       // Broadcasts the Intent
       getBaseContext().sendBroadcast(broadcastIntent);
   }	
}