package org.josejuansanchez.localservice;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
    	startService(new Intent(getBaseContext(), LocalService.class));
    }
    
    public void stopService(View view) {
    	stopService(new Intent(getBaseContext(), LocalService.class));
    }    
}