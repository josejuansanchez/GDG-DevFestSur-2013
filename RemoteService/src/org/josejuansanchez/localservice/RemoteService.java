package org.josejuansanchez.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RemoteService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d(getClass().getSimpleName(), "onStartCommand");
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();		
		
		SimulateALongOperation();
		
		Log.d(getClass().getSimpleName(), "The long operation has finished");		
		return START_NOT_STICKY;
	}	
	
	// Simulate taking some time to perform a task
	private void SimulateALongOperation() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// We have to stop the service explicitly
		stopSelf();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(getClass().getSimpleName(), "onDestroy");		
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}
}