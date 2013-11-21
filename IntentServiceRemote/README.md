IntentService
=============

Android Application using an IntentService running in the same process as the rest of the components.

![IntentService](http://josejuansanchez.org/blogimages/android_intentservice.png)

## AndroidManifest.xml

We have set the `android:process` attribute to specify a process in which the IntentService should run.

	<?xml version="1.0" encoding="utf-8"?>
	<manifest ...
    	<uses-sdk ...

    	<application ...
        	<activity ...

        	<service android:name=".MyIntentService"
            	android:process=":background" />
                    
    	</application>

	</manifest>