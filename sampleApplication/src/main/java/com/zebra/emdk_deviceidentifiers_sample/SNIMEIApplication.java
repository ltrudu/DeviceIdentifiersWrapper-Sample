package com.zebra.emdk_deviceidentifiers_sample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zebra.deviceidentifierswrapper.DIHelper;
import com.zebra.deviceidentifierswrapper.IDIResultCallbacks;

public class SNIMEIApplication extends Application {
    private String sIMEI = null;
    private String sSerialNumber = null;

    private ISerialNumberIMEIObserver iSerialNumberIMEIObserver = null;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if(activity != null && activity instanceof ISerialNumberIMEIObserver)
                    iSerialNumberIMEIObserver = (ISerialNumberIMEIObserver)activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if(iSerialNumberIMEIObserver == activity)
                    iSerialNumberIMEIObserver = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if(iSerialNumberIMEIObserver == activity)
                    iSerialNumberIMEIObserver = null;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(iSerialNumberIMEIObserver == activity)
                    iSerialNumberIMEIObserver = null;
            }
        });
        retrieveSerialNumber();
    }

    public String getIMEI()
    {
        return sIMEI;
    }

    public String getSerialNumber()
    {
        return sSerialNumber;
    }

    private void retrieveSerialNumber()
    {
        DIHelper.getSerialNumber(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                sSerialNumber = message;
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onSerialNumberUpdate(message);
                // We got the serial number, let's try the IMEI number
                retrieveIMEINumber();
            }

            @Override
            public void onError(String message) {
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onErrorMessage(message);
                // We had an error, but we like to play, so we try the IMEI Number
                retrieveIMEINumber();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveIMEINumber()
    {
        DIHelper.getIMEINumber(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got an IMEI number, let's update the text view
                sIMEI = message;
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onIMEINumberUpdate(message);
            }

            @Override
            public void onError(String message) {
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onErrorMessage(message);
            }

            @Override
            public void onDebugStatus(String message) {
                if(iSerialNumberIMEIObserver != null)
                    iSerialNumberIMEIObserver.onDebugMessage(message);
            }
        });
    }

}
