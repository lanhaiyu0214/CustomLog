# CustomLog

With this lib,you call filter the log that just belong to your app easily.

### How to use this lib

1. Copy loglib4.0.jar to libs folder of your app.

2. Do the init in Application 

    CustomLog.setSearchKey("APPO");  //APPO is the fitler which one you want
    
    CustomLog.enableDiskSaved(this); //Saving the logs to native sdcard
 
 3. The same using with Log
 ```
    CustomLog.d(TAG, "XXX");
    CustomLog.e(TAG, "XXX");
    CustomLog.printStack(TAG, "Where");  //Print the function calling stack  
 ```   
 4. Filter all the log belong to your app
 
    just filter "APPO"

Mark:

   Log saving path: storage/emulated/0/Android/data/com.xx.xxx/files/
    
