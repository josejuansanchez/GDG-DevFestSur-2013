IntentService running in a remote process
=========================================

Android Application using an IntentService running in a remote process.

"Remote" in this example means that the IntentService is hosted in another process, but there is only one application.

![IntentService](http://josejuansanchez.org/blogimages/android_intentservice_remote.png)

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