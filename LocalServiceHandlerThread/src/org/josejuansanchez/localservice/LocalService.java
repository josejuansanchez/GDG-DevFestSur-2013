package org.josejuansanchez.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;

public class LocalService extends Service {
	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;	

	// Handler that receives messages from the thread
	private final class ServiceHandler extends Handler {
		
		public ServiceHandler(Looper looper) {
			super(looper);
		}
		
	    @Override
	    public void handleMessage(Message msg) {

	    	// Simulate taking some time to perform a task	    	
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	    	
	    	
			Log.d(getClass().getSimpleName(), "The long operation has finished");
			
	    	// Stop the service using the startId, so that we don't stop
	        // the service in the middle of handling another job
	    	stopSelf(msg.arg1);
	    }		
	}
	      	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate");
		
		// Start up the thread running the service.  Note that we create a
	    // separate thread because the service normally runs in the process's
	    // main thread, which we don't want to block.  We also make it
	    // background priority so CPU-intensive work will not disrupt our UI.
	    HandlerThread thread = new HandlerThread("LocalServiceHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
	    thread.start();
	    
	    // Get the HandlerThread's Looper and use it for our Handler 
	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d(getClass().getSimpleName(), "onStartCommand");
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();		
		
		// For each start request, send a message to start a job and deliver the
	    // start ID so we know which request we're stopping when we finish the job
	    Message msg = mServiceHandler.obtainMessage();
	    msg.arg1 = startId;
	    mServiceHandler.sendMessage(msg);
	      
		Log.d(getClass().getSimpleName(), "startId: " + startId);
	    
	    // If we get killed, after returning from here, restart
	    return START_STICKY;
	}	
		
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(getClass().getSimpleName(), "onDestroy");		
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
		
		if (mServiceLooper != null) {
			mServiceLooper.quit();
		}
	}
}