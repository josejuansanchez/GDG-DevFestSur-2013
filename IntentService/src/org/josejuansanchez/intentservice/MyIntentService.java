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
		
		SimulateALongOperation();
		Log.d(getClass().getSimpleName(), "The long operation has finished");
		
		// Send a broadcast to inform the activity
		Intent broadcastIntent = new Intent(); 
		broadcastIntent.setAction("LONG_OPERATION_ACTION"); 
		getBaseContext().sendBroadcast(broadcastIntent);
	}
	
	// Simulate taking some time to perform a task
	private void SimulateALongOperation() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}	
}