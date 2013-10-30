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
	IntentFilter intentFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// Intent to filter
		intentFilter = new IntentFilter(); 
		intentFilter.addAction("LONG_OPERATION_ACTION");
		
		// Register the receiver
		registerReceiver(intentReceiver, intentFilter);		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// Unregister the receiver
		unregisterReceiver(intentReceiver);		
	}	
	
    public void startService(View view) {
    	Log.d(getClass().getSimpleName(), "startService");
    	startService(new Intent(this, MyIntentService.class));    	
    }
    
    public void stopService(View view) {
    	stopService(new Intent(this, MyIntentService.class));
    }
    
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() { 
    	@Override
    	public void onReceive(Context context, Intent intent) { 
    		Toast.makeText(getBaseContext(), "The long operation has finished", Toast.LENGTH_LONG).show();
    	}
    };
}