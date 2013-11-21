package org.josejuansanchez.intentservice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	IntentFilter mIntentFilter;

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() { 
    	@Override
    	public void onReceive(Context context, Intent intent) { 
    		
    		switch(intent.getIntExtra(Constants.STATUS, Constants.STATE_LONG_OPERATION_STARTED)) {
    		case Constants.STATE_LONG_OPERATION_STARTED:
        		Toast.makeText(getBaseContext(), "The long operation has started", Toast.LENGTH_LONG).show();
        		Log.d(getClass().getSimpleName(), "The long operation has started");
    			break;
    		
    		case Constants.STATE_LONG_OPERATION_COMPLETED:
        		Toast.makeText(getBaseContext(), "The long operation has finished", Toast.LENGTH_LONG).show();
        		Log.d(getClass().getSimpleName(), "The long operation has finished");        		
    			break;
    		}    		
    	}
    };	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// Intent to filter
		mIntentFilter = new IntentFilter(); 
		mIntentFilter.addAction(Constants.LONG_OPERATION_ACTION);
		
		// Register the receiver
		registerReceiver(mIntentReceiver, mIntentFilter);		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// Unregister the receiver
		unregisterReceiver(mIntentReceiver);		
	}	
	
    public void startService(View view) {
    	Log.d(getClass().getSimpleName(), "startService");
    	startService(new Intent(this, MyIntentService.class));    	
    }
    
    public void stopService(View view) {
    	stopService(new Intent(this, MyIntentService.class));
    }   
}