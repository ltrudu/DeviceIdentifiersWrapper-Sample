# DeviceIdentifiersWrapper-Sample

A wrapper to easily retrieve the Serial Number and the IMEI number of an Android 10+ Zebra device.

How to access device identifiers such as serial number and IMEI on Zebra devices running Android 10

Android 10 limited access to device identifiers for all apps running on the platform regardless of their target API level.  As explained in the docs for [Android 10 privacy changes](https://developer.android.com/about/versions/10/privacy/changes) this includes the serial number, IMEI and some other identifiable information.

**Zebra mobile computers running Android 10 are able to access both the serial number and IMEI** however applications need to be **explicitly granted the ability** to do so and use a proprietary API.

To access to this API, you must first register your application using the AccessMgr MX's CSP.

You can do it using StageNow, more details here: https://github.com/darryncampbell/EMDK-DeviceIdentifiers-Sample

Or you can use this wrapper that will automatically register your application if it is necessary.



To use this helper on Zebra Android devices running Android 10 or higher, first declare a new permission in your AndroidManifest.xml

```xml
<uses-permission android:name="com.zebra.provider.READ"/>
<uses-permission android:name="com.symbol.emdk.permission.EMDK" />
```


Then add the uses-library element to your application 
```xml
        <uses-library android:name="com.symbol.emdk" />
```

Sample AdroidManifest.xml:
```xml
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <uses-library android:name="com.symbol.emdk" />
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```


Finally, add DeviceIdentifierWrapper dependency to your application build.graddle file:
```text
        implementation 'com.zebra.deviceidentifierswrapper:deviceidentifierswrapper:0.1'        
```

Sample build.graddle:
```text
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.zebra.deviceidentifierswrapper:deviceidentifierswrapper:0.1'
}
```

Now you can use the following snippet codes to retrieve IMEI number and Serial Number information.


Snippet code to use to retrieve the Serial Number of the device:
```java
     private void getSerialNumber(Context context)
     {
         DIHelper.getSerialNumber(context, new IDIResultCallbacks() {
             @Override
             public void onSuccess(String message) {
                 // The message contains the serial number
                 String mySerialNumber = message;
             }

             @Override
             public void onError(String message) {
                // An error occurred
             }

             @Override
             public void onDebugStatus(String message) {
                // You can use this method to get verbose information
                // about what's happening behind the curtain             
             }
         });
     }
```


Snippet code to use to retrieve the IMEI of the device:
```java
    private void getIMEINumber(Context context)
    {
        DIHelper.getIMEINumber(context, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got an EMEI number
                String myIMEI = message;
            }

            @Override
            public void onError(String message) {
                // An error occurred
            }

            @Override
            public void onDebugStatus(String message) {
                // You can use this method to get verbose information
                // about what's happening behind the curtain
            }
        });
    }
```


As the previous methods are asynchronous, if you need both information, it is strongly recommended to call the second request inside the onSuccess or onError of the first request. 

Sample code if you need to get both device identifiers:
```java
     private void getDevicesIdentifiers(Context context)
     {
        // We first ask for the SerialNumber
         DIHelper.getSerialNumber(context, new IDIResultCallbacks() {
             @Override
             public void onSuccess(String message) {
                 // The message contains the serial number
                 String mySerialNumber = message;
                 // We've got the serial number, now we can ask for the IMEINumber
                 getIMEINumber(context);
             }

             @Override
             public void onError(String message) {
                // An error occured
                // Do something here with the error message
                // We had an error with the Serial Number, but it
                // doesn't prevent us from calling the getIMEINumber method
                getIMEINumber(context);
             }

             @Override
             public void onDebugStatus(String message) {
                // You can use this method to get verbose information
                // about what's happening behind the curtain             
             }
         });
     }
```
