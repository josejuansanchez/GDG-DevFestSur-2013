RemoteServiceBI
===============

Android Application with a Service running in another process, where the Service send a Broadcast Intent that can be picked up by the activity (running in another process) assuming the activity is still around and is not paused.

"Remote" in this example means that the Service is hosted in another process.

This example shows how to get results from a Service back to an invoking Activity using Broadcast Intents.

![RemoteService](http://josejuansanchez.org/blogimages/android_remoteservice.png)
